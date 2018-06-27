package hk.oro.iefas.web.funds.investment.fundsdeposit.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.security.CustomUserDetails;
import hk.oro.iefas.domain.bank.vo.CalculationOfFundsAvailableItemVO;
import hk.oro.iefas.domain.bank.vo.CalculationOfFundsAvailableVO;
import hk.oro.iefas.domain.bank.vo.FundsAvailableItemTypeVO;
import hk.oro.iefas.domain.funds.vo.InvestmentTypeVO;
import hk.oro.iefas.web.common.constant.FundsAvailableWebConstant;
import hk.oro.iefas.web.common.constant.FundsAvailableWebConstant.FundsAvailableItemTypeConstant;
import hk.oro.iefas.web.common.constant.FundsAvailableWebConstant.InvestmentTypeConstant;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.investment.fundsdeposit.service.PlacingDepositsClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2743 $ $Date: 2018-05-30 19:14:53 +0800 (週三, 30 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class PlacingDepositsDetailView implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String LINE = "line";
    private static final String COLUMN = "column";

    @Autowired
    private PlacingDepositsClientService placingDepositsClientService;

    @Getter
    @Setter
    private CalculationOfFundsAvailableVO detailVO;

    @Getter
    @Setter
    private Map<String, Map<String, BigDecimal>> availables = new HashMap<String, Map<String, BigDecimal>>();

    @PostConstruct
    private void init() throws ParseException {
        log.info("======PlacingDepositsDetailView init======");
        Integer calculationOfFundsAvailableId
            = Faces.getRequestParameter("calculationOfFundsAvailableId", Integer.class);
        log.info("calculationOfFundsAvailableId: " + calculationOfFundsAvailableId);
        for (FundsAvailableItemTypeConstant fundsAvailableItemType : FundsAvailableWebConstant.FundsAvailableItemTypeConstant
            .values()) {
            String lineKey = LINE + fundsAvailableItemType.getId();
            Map<String, BigDecimal> columns = new HashMap<>();
            for (InvestmentTypeConstant investmentType : FundsAvailableWebConstant.InvestmentTypeConstant.values()) {
                String columnKey = COLUMN + investmentType.getId();
                columns.put(columnKey, new BigDecimal(0));
            }
            availables.put(lineKey, columns);
        }
        if (calculationOfFundsAvailableId != null && calculationOfFundsAvailableId.intValue() > 0) {
            detailVO = placingDepositsClientService.searchPlacingDeposits(calculationOfFundsAvailableId);
            List<CalculationOfFundsAvailableItemVO> calculationOfFundsAvailableItems
                = detailVO.getCalculationOfFundsAvailableItems();
            for (CalculationOfFundsAvailableItemVO item : calculationOfFundsAvailableItems) {
                String lineKey = LINE + item.getFundsAvailableItemType().getFundsAvailableItemTypeId();
                String columnKey = COLUMN + item.getInvestmentType().getInvestmentTypeId();
                if (lineKey != null && columnKey != null) {
                    if (availables.containsKey(lineKey)) {
                        availables.get(lineKey).put(columnKey, item.getAvailableAmount());
                    } else {
                        Map<String, BigDecimal> columns = new HashMap<>();
                        columns.put(columnKey, item.getAvailableAmount());
                        availables.put(lineKey, columns);
                    }
                }
            }
        } else {
            detailVO = new CalculationOfFundsAvailableVO();
            detailVO.setStatus(CoreConstant.STATUS_ACTIVE);
            Date investmentDate = Faces.getFlashAttribute("investmentDate");
            if (investmentDate != null) {
                detailVO.setInvestmentDate(investmentDate);
            }
            detailVO.setAvailableAmount(BigDecimal.ZERO);
        }
    }

    public void save() {
        List<CalculationOfFundsAvailableItemVO> calculationOfFundsAvailableItems
            = detailVO.getCalculationOfFundsAvailableItems();
        if (calculationOfFundsAvailableItems == null) {
            calculationOfFundsAvailableItems = new ArrayList<>();
            detailVO.setCalculationOfFundsAvailableItems(calculationOfFundsAvailableItems);
        }
        for (String lineKey : availables.keySet()) {
            Integer fundsAvailableItemTypeId = Integer.valueOf(lineKey.split(LINE)[1]);
            Map<String, BigDecimal> columns = availables.get(lineKey);
            for (String columnKey : columns.keySet()) {
                Integer investmentTypeId = Integer.valueOf(columnKey.split(COLUMN)[1]);
                BigDecimal availableAmount = columns.get(columnKey);
                if (availableAmount != null && availableAmount.intValue() != 0) {
                    log.info("fundsAvailableItemTypeId[" + fundsAvailableItemTypeId + "];investmentTypeId["
                        + investmentTypeId + "];amount[" + availableAmount + "]");
                }
                CalculationOfFundsAvailableItemVO dbItemVO = null;
                for (CalculationOfFundsAvailableItemVO item : calculationOfFundsAvailableItems) {
                    if (item.getCalculationOfFundsAvailableItemId() != null
                        && item.getCalculationOfFundsAvailableItemId().intValue() > 0
                        && fundsAvailableItemTypeId
                            .equals(item.getFundsAvailableItemType().getFundsAvailableItemTypeId())
                        && investmentTypeId.equals(item.getInvestmentType().getInvestmentTypeId())) {
                        dbItemVO = item;
                        break;
                    }
                }
                // already existed in db
                if (dbItemVO != null) {
                    dbItemVO.setAvailableAmount(availableAmount);
                } else {
                    // need to create new record
                    if (availableAmount != null && availableAmount.intValue() != 0) {
                        FundsAvailableItemTypeVO fundsAvailableItemType = new FundsAvailableItemTypeVO();
                        fundsAvailableItemType.setFundsAvailableItemTypeId(fundsAvailableItemTypeId);
                        InvestmentTypeVO investmentType = new InvestmentTypeVO();
                        investmentType.setInvestmentTypeId(investmentTypeId);
                        CalculationOfFundsAvailableItemVO newItemVO = new CalculationOfFundsAvailableItemVO();
                        newItemVO.setAvailableAmount(availableAmount);
                        newItemVO.setFundsAvailableItemType(fundsAvailableItemType);
                        newItemVO.setInvestmentType(investmentType);
                        newItemVO.setStatus(CoreConstant.STATUS_ACTIVE);
                        calculationOfFundsAvailableItems.add(newItemVO);
                    }
                }
            }
        }
        detailVO.setStatus(CoreConstant.COMMON_STATUS_DRAFT);
        Integer availableId = placingDepositsClientService.savePlacingDeposits(detailVO);
        detailVO = placingDepositsClientService.searchPlacingDeposits(availableId);
        Messages.addGlobalInfo("Save Successfully!");
    }

    public void submit() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            detailVO.setSubmittedBy(user.getUserFullName());
        }
        detailVO.setStatus(CoreConstant.COMMON_STATUS_SUBMITTED);
        Integer availableId = placingDepositsClientService.savePlacingDeposits(detailVO);
        detailVO = placingDepositsClientService.searchPlacingDeposits(availableId);
        Messages.addGlobalInfo("Submit Successfully!");
    }

    public void approve() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            detailVO.setApprovedBy(user.getUserFullName());
        }
        detailVO.setStatus(CoreConstant.COMMON_STATUS_APPROVED);
        Integer availableId = placingDepositsClientService.savePlacingDeposits(detailVO);
        detailVO = placingDepositsClientService.searchPlacingDeposits(availableId);
        Messages.addGlobalInfo("Approve Successfully!");
    }

    public void reject() {
        detailVO.setSubmittedBy(null);
        detailVO.setApprovedBy(null);
        detailVO.setStatus(CoreConstant.COMMON_STATUS_REJECTED);
        Integer availableId = placingDepositsClientService.savePlacingDeposits(detailVO);
        detailVO = placingDepositsClientService.searchPlacingDeposits(availableId);
        Messages.addGlobalInfo("Reject Successfully!");
    }
}
