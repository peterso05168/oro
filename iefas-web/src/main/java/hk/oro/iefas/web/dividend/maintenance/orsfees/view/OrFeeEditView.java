package hk.oro.iefas.web.dividend.maintenance.orsfees.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.CoreConstant.CalculationMethod;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.core.util.ValidationUtils;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.dividend.vo.CalculationInfoVO;
import hk.oro.iefas.domain.dividend.vo.CalculationMaintenanceVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeMaintenanceVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeTypeVO;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.orsfees.service.OrFeeClientService;
import hk.oro.iefas.web.dividend.maintenance.orsfees.view.validator.OrFeeValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@Named(value = "feeEditView")
@ViewScoped
public class OrFeeEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = -348147694194087643L;

    @Inject
    private OrFeeClientService oRFeeClientService;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private OrFeeValidator orFeeValidator;

    @Getter
    @Setter
    private Integer caseFeeTypeId;

    @Getter
    @Setter
    private CaseFeeTypeVO caseFeeTypeVO;

    @Getter
    @Setter
    private Map<String, String> calculationMethodMap = new LinkedHashMap<>();

    private Map<String, String> calculationMethodDescMap = new HashMap<>();

    @Getter
    @Setter
    private List<CalculationMaintenanceVO> calculationMaintenanceVOs = new ArrayList<>();

    @Getter
    @Setter
    private CalculationMaintenanceVO calculationMaintenanceVO;

    @Getter
    @Setter
    private boolean enableFix = false;

    @Getter
    @Setter
    private boolean enableRat = false;

    @Getter
    @Setter
    private boolean enableSli = false;

    @PostConstruct
    private void init() {
        log.info("ORFeeEditView init");
        caseFeeTypeId = Faces.getRequestParameter("caseFeeTypeId", Integer.class);
        this.doInit();
        log.info("ORFeeEditView init end -");
    }

    private void doInit() {
        List<ApplicationCodeTableVO> calculationMethods = commonClientService.searchCalculationMethod();
        if (calculationMethods != null) {
            calculationMethodMap.clear();
            calculationMethods.forEach(calMethod -> {
                String codeValue = calMethod.getCodeValue();
                String codeDesc = null;
                if (!CalculationMethod.ADD.name().equals(codeValue)) {
                    codeDesc = calMethod.getCodeDesc();
                    calculationMethodMap.put(codeDesc, codeDesc);
                    calculationMethodDescMap.put(codeDesc, codeValue);
                    calculationMethodDescMap.put(codeValue, codeDesc);
                }
            });
        }

        if (caseFeeTypeId != null) {
            caseFeeTypeVO = oRFeeClientService.searchORFeeItemWithCalculationMethod(caseFeeTypeId);
            if (caseFeeTypeVO != null) {
                List<CaseFeeMaintenanceVO> caseFeeMaintenances = caseFeeTypeVO.getCaseFeeMaintenances();
                if (caseFeeMaintenances != null) {
                    Map<CalculationInfoVO, List<CaseFeeMaintenanceVO>> feeMainMap = new LinkedHashMap<>();
                    CalculationInfoVO calculationInfoVO = null;
                    List<CaseFeeMaintenanceVO> list = null;
                    String calculationType = null;
                    for (CaseFeeMaintenanceVO caseFeeMaintenanceVO : caseFeeMaintenances) {
                        calculationType = calculationMethodDescMap.get(caseFeeMaintenanceVO.getCalculationType());
                        calculationInfoVO = new CalculationInfoVO(calculationType, calculationType,
                            caseFeeMaintenanceVO.getPeriodFrom(), caseFeeMaintenanceVO.getPeriodTo());
                        list = feeMainMap.get(calculationInfoVO);
                        if (list == null) {
                            list = new ArrayList<>();
                            feeMainMap.put(calculationInfoVO, list);
                        }
                        list.add(caseFeeMaintenanceVO);
                    }
                    calculationMaintenanceVOs.clear();
                    Set<Entry<CalculationInfoVO, List<CaseFeeMaintenanceVO>>> entrySet = feeMainMap.entrySet();
                    for (Iterator<Entry<CalculationInfoVO, List<CaseFeeMaintenanceVO>>> iterator = entrySet.iterator();
                        iterator.hasNext();) {
                        Entry<CalculationInfoVO, List<CaseFeeMaintenanceVO>> entry = iterator.next();
                        calculationMaintenanceVOs.add(
                            new CalculationMaintenanceVO(System.currentTimeMillis(), entry.getKey(), entry.getValue()));
                    }
                    this.calculationMaintenanceVOs.sort(new CalculationMaintenanceVOComparator());

                }

            }
        }
        this.checkPrivilege();
    }

    public void addCalculationRule() {
        this.calculationMaintenanceVO = new CalculationMaintenanceVO();
        CalculationInfoVO calculationInfoVO = new CalculationInfoVO();
        calculationInfoVO.setCalculationType(CalculationMethod.FIX.getDesc());
        if (this.calculationMaintenanceVOs != null) {
            int size = this.calculationMaintenanceVOs.size();
            if (size > 0) {
                CalculationMaintenanceVO vo = this.calculationMaintenanceVOs.get(size - 1);
                Date periodTo = vo.getCalculationInfo().getPeriodTo();
                Date nextDate = DateUtils.addDays(periodTo, 1);
                calculationInfoVO.setPeriodFrom(nextDate);
            }

        }

        this.calculationMaintenanceVO.setCalculationInfo(calculationInfoVO);
        CaseFeeMaintenanceVO caseFeeMaintenanceVO = new CaseFeeMaintenanceVO();
        caseFeeMaintenanceVO.setValueFrom(0);
        this.calculationMaintenanceVO.getCaseFeeMaintenances().add(caseFeeMaintenanceVO);
        this.calculateMethodChange();
    }

    public void editCalculationMaintenanceVO(CalculationMaintenanceVO calculationMaintenanceVO) {
        this.calculationMaintenanceVO
            = DataUtils.copyProperties(calculationMaintenanceVO, CalculationMaintenanceVO.class);
        List<CaseFeeMaintenanceVO> caseFeeMaintenanceVOs = this.calculationMaintenanceVO.getCaseFeeMaintenances();
        if (CommonUtils.isNotEmpty(caseFeeMaintenanceVOs)) {
            this.calculationMaintenanceVO.getCaseFeeMaintenances().sort((a, b) -> {
                if (a == null && b == null) {
                    return 0;
                }
                if (a == null) {
                    return 1;
                }
                if (b == null) {
                    return -1;
                }

                return a.getValueFrom().compareTo(b.getValueTo());
            });
        }

        this.calculateMethodChange();
    }

    public void deleteCalculationMaintenanceVO(CalculationMaintenanceVO calculationMaintenanceVO) {
        if (CommonUtils.isNotEmpty(this.calculationMaintenanceVOs)) {
            int size = this.calculationMaintenanceVOs.size();
            CalculationMaintenanceVO temp = null;
            CalculationMaintenanceVO last = null;
            CalculationMaintenanceVO next = null;
            List<CaseFeeMaintenanceVO> list = null;
            Date newDate = null;
            for (int i = 0; i < size; i++) {
                temp = this.calculationMaintenanceVOs.get(i);
                if (temp.getId().equals(calculationMaintenanceVO.getId())) {

                    if (i > 0) {
                        last = this.calculationMaintenanceVOs.get(i - 1);
                        newDate = calculationMaintenanceVO.getCalculationInfo().getPeriodTo();
                        last.getCalculationInfo().setPeriodTo(newDate);
                        list = last.getCaseFeeMaintenances();
                        if (CommonUtils.isNotEmpty(list)) {
                            for (CaseFeeMaintenanceVO cfmVO : list) {
                                cfmVO.setPeriodTo(newDate);
                            }
                        }
                    } else if (i == 0) {
                        if (1 < size) {
                            next = this.calculationMaintenanceVOs.get(1);
                            newDate = calculationMaintenanceVO.getCalculationInfo().getPeriodFrom();
                            next.getCalculationInfo().setPeriodFrom(newDate);
                            list = next.getCaseFeeMaintenances();
                            if (CommonUtils.isNotEmpty(list)) {
                                for (CaseFeeMaintenanceVO cfmVO : list) {
                                    cfmVO.setPeriodFrom(newDate);
                                }
                            }
                        }

                    }

                    this.calculationMaintenanceVOs.remove(i);
                    break;
                }
            }
        }
    }

    public void addCaseFeeMaintenanceVO() {
        List<CaseFeeMaintenanceVO> caseFeeMaintenanceVOs = this.calculationMaintenanceVO.getCaseFeeMaintenances();
        int size = caseFeeMaintenanceVOs.size();
        CaseFeeMaintenanceVO addObj = null;
        if (size == 0) {
            addObj = new CaseFeeMaintenanceVO();
            addObj.setValueFrom(0);
        } else {
            boolean validate
                = orFeeValidator.validateValueOfCaseFeeMaintenanceListOfCalculationMaintenanceVO(caseFeeMaintenanceVOs);
            if (validate) {
                CaseFeeMaintenanceVO caseFeeMaintenanceVO = caseFeeMaintenanceVOs.get(size - 1);
                addObj = new CaseFeeMaintenanceVO();
                addObj.setValueFrom(caseFeeMaintenanceVO.getValueTo());
            }

        }

        if (addObj != null) {
            caseFeeMaintenanceVOs.add(addObj);
        }

    }

    public void deleteCaseFeeMaintenanceVO(CaseFeeMaintenanceVO caseFeeMaintenanceVO) {
        List<CaseFeeMaintenanceVO> caseFeeMaintenanceVOs = this.calculationMaintenanceVO.getCaseFeeMaintenances();
        int size = caseFeeMaintenanceVOs.size();
        CaseFeeMaintenanceVO temp = null;
        CaseFeeMaintenanceVO last = null;
        CaseFeeMaintenanceVO next = null;
        for (int i = 0; i < size; i++) {
            temp = caseFeeMaintenanceVOs.get(i);
            if (temp.getValueFrom().equals(caseFeeMaintenanceVO.getValueFrom())) {
                if (i > 0) {
                    last = caseFeeMaintenanceVOs.get(i - 1);
                    Integer valueTo = caseFeeMaintenanceVO.getValueTo();
                    if (valueTo != null) {
                        last.setValueTo(valueTo);
                    }

                } else if (i == 0) {
                    if (1 < size) {
                        next = caseFeeMaintenanceVOs.get(1);
                        next.setValueFrom(0);
                    }

                }
                caseFeeMaintenanceVOs.remove(i);
                break;
            }
        }
    }

    public void resetCaseFeeMaintenanceListOfCalculationMaintenanceVO(CaseFeeMaintenanceVO caseFeeMaintenanceVO) {
        List<CaseFeeMaintenanceVO> caseFeeMaintenanceVOs = this.calculationMaintenanceVO.getCaseFeeMaintenances();
        int size = caseFeeMaintenanceVOs.size();
        CaseFeeMaintenanceVO temp = null;
        CaseFeeMaintenanceVO next = null;
        for (int i = 0; i < size; i++) {
            temp = caseFeeMaintenanceVOs.get(i);
            if (temp.getValueFrom().equals(caseFeeMaintenanceVO.getValueFrom())) {
                if (i + 1 < size) {
                    next = caseFeeMaintenanceVOs.get(i + 1);
                    next.setValueFrom(caseFeeMaintenanceVO.getValueTo());
                }
                break;
            }
        }
    }

    public void calculateMethodChange() {
        String calculationType = this.calculationMaintenanceVO.getCalculationInfo().getCalculationType();
        this.enableFix = CalculationMethod.FIX.getDesc().equals(calculationType);
        this.enableRat = CalculationMethod.RAT.getDesc().equals(calculationType);
        this.enableSli = CalculationMethod.SLI.getDesc().equals(calculationType);

    }

    public void saveCalculationRule() {

        CalculationInfoVO calculationInfoVO = calculationMaintenanceVO.getCalculationInfo();
        if (calculationInfoVO != null) {
            if (calculationInfoVO.getPeriodTo() == null) {
                calculationInfoVO.setPeriodTo(DateUtils.getDefaultSystemEndDate());
            }

            if (calculationInfoVO.getPeriodFrom() != null) {
                boolean validateDatePeriod = ValidationUtils.validateDatePeriod(calculationInfoVO.getPeriodFrom(),
                    calculationInfoVO.getPeriodTo());
                if (!validateDatePeriod) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_BEFORE, MsgParamCodeConstant.TO_DATE,
                                MsgParamCodeConstant.FROM_DATE),
                            null));
                    Faces.renderResponse();
                    return;
                }
            }
        }

        CaseFeeMaintenanceVO caseFeeMaintenanceVO = null;
        List<CaseFeeMaintenanceVO> caseFeeMaintenanceVOs = this.calculationMaintenanceVO.getCaseFeeMaintenances();

        String calculationMethodVal = this.calculationMethodDescMap.get(calculationInfoVO.getCalculationType());
        if (this.enableSli) {
            boolean validate
                = orFeeValidator.validateValueOfCaseFeeMaintenanceListOfCalculationMaintenanceVO(caseFeeMaintenanceVOs);
            if (!validate) {
                return;
            }
            for (CaseFeeMaintenanceVO temp : caseFeeMaintenanceVOs) {
                temp.setCalculationType(calculationMethodVal);
                temp.setPeriodFrom(calculationInfoVO.getPeriodFrom());
                temp.setPeriodTo(calculationInfoVO.getPeriodTo());
                temp.setFeeAmount(null);
                temp.setPercent(null);
                temp.setValuePercent(null);
            }

        } else {
            if (caseFeeMaintenanceVOs.size() > 0) {
                caseFeeMaintenanceVO = caseFeeMaintenanceVOs.get(0);
                caseFeeMaintenanceVOs.clear();
                caseFeeMaintenanceVOs.add(caseFeeMaintenanceVO);

                caseFeeMaintenanceVO.setCalculationType(calculationMethodVal);
                caseFeeMaintenanceVO.setPeriodFrom(calculationInfoVO.getPeriodFrom());
                caseFeeMaintenanceVO.setPeriodTo(calculationInfoVO.getPeriodTo());
                caseFeeMaintenanceVO.setRoundingAmount(null);
                caseFeeMaintenanceVO.setRoundingUnit(null);
                caseFeeMaintenanceVO.setValueFrom(null);
                caseFeeMaintenanceVO.setValueTo(null);

                if (enableFix) {
                    caseFeeMaintenanceVO.setPercent(null);
                    caseFeeMaintenanceVO.setValuePercent(null);
                } else if (enableRat) {
                    caseFeeMaintenanceVO.setFeeAmount(null);
                    caseFeeMaintenanceVO.setValuePercent(null);
                }

            }
        }

        boolean flag = true;
        int size = calculationMaintenanceVOs.size();
        CalculationMaintenanceVO temp = null;
        for (int i = 0; i < size; i++) {
            temp = calculationMaintenanceVOs.get(i);
            if (temp.getId().equals(this.calculationMaintenanceVO.getId())) {
                this.calculationMaintenanceVOs.set(i, this.calculationMaintenanceVO);
                flag = false;
            }
        }

        if (flag) {
            this.calculationMaintenanceVO.setId(System.currentTimeMillis());
            this.calculationMaintenanceVOs.add(this.calculationMaintenanceVO);
            this.calculationMaintenanceVOs.sort(new CalculationMaintenanceVOComparator());
        }
        hideComponent("editDialog");
    }

    public void saveORFeeItemWithCalculationMethod() {

        Boolean validate
            = this.oRFeeClientService.validatesSaveORFeeItemWithCalculationMethod(this.calculationMaintenanceVOs);
        if (validate != null && validate) {
            List<CaseFeeMaintenanceVO> caseFeeMaintenances = this.caseFeeTypeVO.getCaseFeeMaintenances();
            caseFeeMaintenances.clear();
            if (CommonUtils.isNotEmpty(this.calculationMaintenanceVOs)) {
                for (CalculationMaintenanceVO temp : calculationMaintenanceVOs) {
                    caseFeeMaintenances.addAll(temp.getCaseFeeMaintenances());
                }
            }
            caseFeeTypeId = this.oRFeeClientService.saveORFeeItemWithCalculationMethod(caseFeeTypeVO);
            if (caseFeeTypeId != null && caseFeeTypeId.intValue() > 0) {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                this.doInit();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PERIOD), null));
            Faces.renderResponse();
        }
    }

    private class CalculationMaintenanceVOComparator implements Comparator<CalculationMaintenanceVO> {

        @Override
        public int compare(CalculationMaintenanceVO a, CalculationMaintenanceVO b) {
            if (a == null && b == null) {
                return 0;
            }
            if (a == null) {
                return 1;
            }
            if (b == null) {
                return -1;
            }

            return a.getCalculationInfo().getPeriodFrom().compareTo(b.getCalculationInfo().getPeriodFrom());
        }

    }
}
