package hk.oro.iefas.web.casemgt.proofofdebt.form.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorSectionVO;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.counter.vo.ProofOfDebtItemVO;
import hk.oro.iefas.domain.counter.vo.ProofOfDebtVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.casemgt.proofofdebt.enquiry.service.ProofOfDebtClientService;
import hk.oro.iefas.web.casemgt.proofofdebt.enquiry.service.ProofOfDebtItemClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.CommonCreditorSectionClientService;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.DividendCommonCreditorClientService;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3293 $ $Date: 2018-06-26 10:40:47 +0800 (週二, 26 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Named
@ViewScoped
public class ProofOfDebtFormView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private ProofOfDebtClientService proofOfDebtClientService;

    @Inject
    private ProofOfDebtItemClientService proofOfDebtItemClientService;

    @Inject
    private CaseClientService caseClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private DividendCommonCreditorClientService commonCreditorClientService;

    @Inject
    private CommonCreditorSectionClientService commonCreditorSectionClientService;

    @Getter
    @Setter
    private ProofOfDebtVO proofOfDebtVO;

    @Getter
    @Setter
    private Integer proofOfDebtId;

    @Getter
    @Setter
    private List<ProofOfDebtItemVO> itemVOs;

    @Getter
    @Setter
    private List<ProofOfDebtItemVO> delItemVOs = new ArrayList<>();

    @Getter
    @Setter
    private ProofOfDebtItemVO proofOfDebtItemVO;

    @Getter
    @Setter
    private CaseNumberVO caseNumberVO = new CaseNumberVO();

    @Getter
    @Setter
    private CaseVO caseInfo;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyInfoVOs;

    @Getter
    @Setter
    private CommonCreditorVO creditor;

    @Getter
    @Setter
    private List<CommonCreditorVO> creditorList;

    @Getter
    @Setter
    private CommonCreditorSectionVO section;

    @Getter
    @Setter
    private List<CommonCreditorSectionVO> sectionList;

    @PostConstruct
    public void init() {
        log.info("======= ProofOfDebtFormView init =======");
        currencyInfoVOs = currencyClientService.findAll();
        proofOfDebtId = Faces.getRequestParameter("proofOfDebtId",Integer.class);
        if (proofOfDebtId != null && proofOfDebtId != 0) {
            proofOfDebtVO = proofOfDebtClientService.findOne(proofOfDebtId);
            itemVOs = proofOfDebtVO.getProofDebtItems();
        } else {
            proofOfDebtVO = new ProofOfDebtVO();
            proofOfDebtVO.setReceiptDate(appResourceUtils.getBusinessDate());
            proofOfDebtVO.setUncapInt(BigDecimal.ZERO);
        }

    }

    public void save() {
        log.info("save() start");
        if (CommonUtils.isEmpty(itemVOs)) {
            Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_ADD_ONE_DEBT_ITEM));
        } else {
            itemVOs = itemVOs.stream()
                .filter(item -> item.getProofOfDebtItemId() == null || item.getProofOfDebtItemId() == 0)
                .collect(Collectors.toList());
            List<ProofOfDebtItemVO> itemList = new ArrayList<>();
            itemList.addAll(itemVOs);
            itemList.addAll(delItemVOs);
            itemList.stream().forEach(item -> {
                proofOfDebtItemClientService.save(item);
            });
            Integer resultId = proofOfDebtClientService.save(proofOfDebtVO);
            if (resultId != null) {
                proofOfDebtVO = proofOfDebtClientService.findOne(resultId);
                itemVOs = proofOfDebtVO.getProofDebtItems();
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
            } else {
                Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
            }
        }
        log.info("save() end");
    }

    public void createNew() {
        log.info("createNew() start");
        proofOfDebtVO = new ProofOfDebtVO();
        log.info("createNew() end");
    }

    public void createNewWithSameCase() {
        log.info("createNewWithSameCase() start");
        proofOfDebtVO = new ProofOfDebtVO();
        proofOfDebtVO.setCaseInfo(caseInfo);
        log.info("createNewWithSameCase() start");
    }

    public void createNewWithSameCreditor() {
        log.info("createNewWithSameCase() start");
        proofOfDebtVO = new ProofOfDebtVO();
        proofOfDebtVO.setCommonCreditorName(proofOfDebtVO.getCommonCreditorName());
        proofOfDebtVO.setCommonCreditorNameChi(proofOfDebtVO.getCommonCreditorNameChi());
        proofOfDebtVO.setCommonCreditorSeqNo(proofOfDebtVO.getCommonCreditorSeqNo());
        proofOfDebtVO.setContactNo(proofOfDebtVO.getContactNo());
        proofOfDebtVO.setContactPerson(proofOfDebtVO.getContactPerson());
        proofOfDebtVO.setCreditorAddress1(proofOfDebtVO.getCreditorAddress1());
        proofOfDebtVO.setCreditorAddress2(proofOfDebtVO.getCreditorAddress2());
        proofOfDebtVO.setCreditorAddress3(proofOfDebtVO.getCreditorAddress3());
        proofOfDebtVO.setCreditorAddressChi1(proofOfDebtVO.getCreditorAddressChi1());
        proofOfDebtVO.setCreditorAddressChi2(proofOfDebtVO.getCreditorAddressChi2());
        proofOfDebtVO.setCreditorAddressChi3(proofOfDebtVO.getCreditorAddressChi3());
        proofOfDebtVO.setSectionSeqNo(proofOfDebtVO.getSectionSeqNo());
        proofOfDebtVO.setSectionNameChi(proofOfDebtVO.getSectionNameChi());
        proofOfDebtVO.setSectionContactNoNo(proofOfDebtVO.getSectionContactNoNo());
        proofOfDebtVO.setSectionContactPerson(proofOfDebtVO.getContactPerson());
        proofOfDebtVO.setSectionName(proofOfDebtVO.getSectionName());
        proofOfDebtVO.setSectionNameChi(proofOfDebtVO.getSectionNameChi());
        proofOfDebtVO.setSectionAddress1(proofOfDebtVO.getSectionAddress1());
        proofOfDebtVO.setSectionAddress2(proofOfDebtVO.getSectionAddress2());
        proofOfDebtVO.setSectionAddress3(proofOfDebtVO.getSectionAddress3());
        proofOfDebtVO.setSectionAddressChi1(proofOfDebtVO.getSectionAddressChi1());
        proofOfDebtVO.setSectionAddressChi2(proofOfDebtVO.getSectionAddressChi2());
        proofOfDebtVO.setSectionAddressChi3(proofOfDebtVO.getSectionAddressChi3());
        log.info("createNewWithSameCase() start");
    }

    public void addProofOfDebtItem() {
        log.info("addProofOfDebtItem() start");
        if (CommonUtils.isEmpty(itemVOs)) {
            itemVOs = new ArrayList<ProofOfDebtItemVO>();
        }
        proofOfDebtItemVO = new ProofOfDebtItemVO();
        proofOfDebtItemVO.setProofDebtId(proofOfDebtId);
        itemVOs.add(proofOfDebtItemVO);
        log.info("addProofOfDebtItem() end");
    }

    public void removeProofOfDebtItem() {
        log.info("removeProofOfDebtItem() start");
        itemVOs.remove(proofOfDebtItemVO);
        if (proofOfDebtItemVO.getProofOfDebtItemId() != null) {
            proofOfDebtItemVO.setStatus(CoreConstant.STATUS_DELETE);
            delItemVOs.add(proofOfDebtItemVO);
        }
        log.info("removeProofOfDebtItem() end");
    }

    public void searchCaseInfo() {
        log.info("getCaseInfo() start");
        if (CommonUtils.isNotBlank(caseNumberVO.getCaseType()) && CommonUtils.isNotBlank(caseNumberVO.getCaseSequence())
            && CommonUtils.isNotBlank(caseNumberVO.getCaseYear())) {
            caseInfo = caseClientService.findByCaseNumber(caseNumberVO);
            if (caseInfo != null) {
                proofOfDebtVO.setCaseInfo(caseInfo);
            }
        }
        log.info("getCaseInfo() end");
    }

    public List<String> getCommonCreditorInfoBySeqNo(String seqNo) {
        log.info("getCommonCreditorInfo() start");
        List<String> result = new ArrayList<String>();
        creditorList = commonCreditorClientService.searchCommonCreditorBySeqNo(seqNo);
        if (CommonUtils.isNotEmpty(creditorList)) {
            result = creditorList.stream().map(item -> item.getCommonCreditorName()).collect(Collectors.toList());
        }
        log.info("getCommonCreditorInfo() end");
        return result;
    }

    public List<String> getCommonCreditorInfoByName(String name) {
        log.info("getCommonCreditorInfo() start");
        List<String> result = new ArrayList<String>();
        creditorList = commonCreditorClientService.searchCommonCreditorByName(name);
        if (CommonUtils.isNotEmpty(creditorList)) {
            result = creditorList.stream().map(item -> item.getCommonCreditorName()).collect(Collectors.toList());
        }
        log.info("getCommonCreditorInfo() end");
        return result;
    }

    public void changeCreditorInfo() {
        log.info("changeCreditorInfo() start");
        creditor = null;
        creditorList.stream().forEach(item -> {
            if (item.getCommonCreditorName().equals(proofOfDebtVO.getCommonCreditorName())) {
                creditor = item;
            }
        });
        if (creditor != null) {
            proofOfDebtVO.setCommonCreditorSeqNo(creditor.getCommonCreditorSeqNo());
            proofOfDebtVO.setCommonCreditorNameChi(creditor.getCommonCreditorNameChinese());
            proofOfDebtVO.setContactNo(creditor.getContactNo());
            proofOfDebtVO.setContactPerson(creditor.getContactPerson());
            if (creditor.getAddress() != null) {
                proofOfDebtVO.setCreditorAddress1(creditor.getAddress().getAddress1());
                proofOfDebtVO.setCreditorAddress2(creditor.getAddress().getAddress2());
                proofOfDebtVO.setCreditorAddress3(creditor.getAddress().getAddress3());
                proofOfDebtVO.setCreditorAddressChi1(creditor.getAddress().getChiAddress1());
                proofOfDebtVO.setCreditorAddressChi2(creditor.getAddress().getChiAddress2());
                proofOfDebtVO.setCreditorAddressChi3(creditor.getAddress().getChiAddress3());
            }
        }
        log.info("changeCreditorInfo() end");
    }

    public List<String> getSectionInfoBySeqNo(String seqNo) {
        log.info("getSectionInfoByName() start");
        List<String> result = new ArrayList<>();
        sectionList = commonCreditorSectionClientService.searchCommonCreditorSectionBySeqNo(seqNo);
        if (CommonUtils.isNotEmpty(sectionList)) {
            result = sectionList.stream().map(item -> item.getSectionName()).collect(Collectors.toList());
        }
        log.info("getSectionInfoByName() end");
        return result;
    }

    public List<String> getSectionInfoByName(String name) {
        log.info("getSectionInfoByName() start");
        List<String> result = new ArrayList<>();
        sectionList = commonCreditorSectionClientService.searchCommonCreditorSectionByName(name);
        if (CommonUtils.isNotEmpty(sectionList)) {
            result = sectionList.stream().map(item -> item.getSectionName()).collect(Collectors.toList());
        }
        log.info("getSectionInfoByName() end");
        return result;
    }

    public void changeSectionInfo() {
        log.info("changeSectionInfo() start");
        section = null;
        sectionList.stream().forEach(item -> {
            if (item.getSectionName().equals(proofOfDebtVO.getSectionName())) {
                section = item;
            }
        });
        if (section != null) {
            proofOfDebtVO.setSectionSeqNo(section.getSectionSeqNo());
            proofOfDebtVO.setSectionNameChi(section.getSectionNameChinese());
            proofOfDebtVO.setSectionContactNoNo(section.getSectionAccountNumber());
            proofOfDebtVO.setSectionContactPerson(section.getContactPerson());
            proofOfDebtVO.setSectionName(section.getSectionName());
            proofOfDebtVO.setSectionNameChi(section.getSectionNameChinese());
            if (section.getAddress() != null) {
                proofOfDebtVO.setSectionAddress1(section.getAddress().getAddress1());
                proofOfDebtVO.setSectionAddress2(section.getAddress().getAddress2());
                proofOfDebtVO.setSectionAddress3(section.getAddress().getAddress3());
                proofOfDebtVO.setSectionAddressChi1(section.getAddress().getChiAddress1());
                proofOfDebtVO.setSectionAddressChi2(section.getAddress().getChiAddress2());
                proofOfDebtVO.setSectionAddressChi3(section.getAddress().getChiAddress3());
            }
        }
        log.info("changeSectionInfo() end");
    }

    public void reset() {
        itemVOs = null;
        delItemVOs = new ArrayList<>();
        creditorList = null;
        creditor = null;
        sectionList = null;
        section = null;
    }
}
