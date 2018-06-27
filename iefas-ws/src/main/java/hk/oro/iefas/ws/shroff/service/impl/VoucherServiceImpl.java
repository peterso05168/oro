package hk.oro.iefas.ws.shroff.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.security.UserInfo;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.core.util.MimeTypeUtils;
import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.casemgt.dto.CaseAccountInfoDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseAccountInfo;
import hk.oro.iefas.domain.common.dto.ActionDTO;
import hk.oro.iefas.domain.common.dto.ApplicationCodeTableDTO;
import hk.oro.iefas.domain.dividend.dto.SysApprovalWfDTO;
import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.BankTransferItemDTO;
import hk.oro.iefas.domain.shroff.dto.ChequeDTO;
import hk.oro.iefas.domain.shroff.dto.ControlAccountDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherBasicInformationDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherResultItemDTO;
import hk.oro.iefas.domain.shroff.dto.JournalVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherBasicInformationDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherResultDTO;
import hk.oro.iefas.domain.shroff.dto.PaymentVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherAccountItemDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherBasicInfoDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherDetailDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherResultDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherSearchDTO;
import hk.oro.iefas.domain.shroff.dto.VoucherDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCtlAc;
import hk.oro.iefas.domain.shroff.entity.ShrVcrItem;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.domain.shroff.entity.VoucherType;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;
import hk.oro.iefas.domain.system.entity.SysApprovalWf;
import hk.oro.iefas.ws.bank.service.CurrencyService;
import hk.oro.iefas.ws.casemgt.repository.CaseAccountInfoRepository;
import hk.oro.iefas.ws.common.service.CommonService;
import hk.oro.iefas.ws.common.util.RequestContextUtils;
import hk.oro.iefas.ws.core.constant.SysSequenceEnum;
import hk.oro.iefas.ws.shroff.repository.ControlAccountRepository;
import hk.oro.iefas.ws.shroff.repository.ShrVcrItemRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherTypeRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.JournalVoucherDetailDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.JournalVoucherResultItemDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.PaymentVoucherDetailDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.PaymentVoucherResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.ReceiptVoucherDetailDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.ReceiptVoucherResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.VoucherPredicate;
import hk.oro.iefas.ws.shroff.service.ChequeService;
import hk.oro.iefas.ws.shroff.service.ControlAccountService;
import hk.oro.iefas.ws.shroff.service.ShrGeneralLedgerService;
import hk.oro.iefas.ws.shroff.service.VoucherService;
import hk.oro.iefas.ws.system.repository.SysApprovalWFRepository;
import hk.oro.iefas.ws.system.service.SysSequenceService;
import hk.oro.iefas.ws.system.service.SysWorkFlowRuleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3268 $ $Date: 2018-06-25 11:31:25 +0800 (週一, 25 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class VoucherServiceImpl implements VoucherService {

    private static final String TEMPLATE_FILE_NAME = "Import Account Template.csv";

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private ShrVcrItemRepository shrVcrItemRepository;

    @Autowired
    private VoucherTypeRepository voucherTypeRepository;

    @Autowired
    private CaseAccountInfoRepository caseAccountInfoRepository;

    @Autowired
    private ReceiptVoucherDetailDTOAssembler receiptVoucherDetailDTOAssembler;

    @Autowired
    private JournalVoucherDetailDTOAssembler journalVoucherDetailDTOAssembler;

    @Autowired
    private JournalVoucherResultItemDTOAssembler journalVoucherResultItemDTOAssembler;

    @Autowired
    private PaymentVoucherDetailDTOAssembler paymentVoucherDetailDTOAssembler;

    @Autowired
    private PaymentVoucherResultDTOAssembler paymentVoucherResultDTOAssembler;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ControlAccountRepository controlAccountRepository;

    @Autowired
    private SysApprovalWFRepository sysApprovalWFRepository;

    @Autowired
    private SysSequenceService sysSequenceService;

    @Autowired
    private ControlAccountService controlAccountService;

    @Autowired
    private ShrGeneralLedgerService shrGeneralLedgerService;

    @Autowired
    private ChequeService chequeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private SysWorkFlowRuleService sysWorkFlowRuleService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveReceiptVoucher(ReceiptVoucherBasicInfoDTO receiptVoucherBasicInformation) {
        log.info("saveReceiptVoucher() start - " + receiptVoucherBasicInformation);
        Voucher voucher = DataUtils.copyProperties(receiptVoucherBasicInformation, Voucher.class);
        VoucherType voucherType = voucherTypeRepository.findByVoucherTypeCode(ShroffConstant.VT_REC);
        voucher.setVoucherType(voucherType);
        voucher = voucherRepository.save(voucher);
        Integer voucherId = voucher.getVoucherId();
        log.info("saveReceiptVoucher() end - voucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ReceiptVoucherResultDTO> findReceiptVoucherByCriteria(SearchDTO<ReceiptVoucherSearchDTO> searchDTO) {
        log.info("findReceiptVoucherByCriteria() start - " + searchDTO);
        Page<ReceiptVoucherResultDTO> results = null;
        if (searchDTO != null) {
            Pageable pageable = null;
            if (searchDTO.getPage() != null) {
                pageable = searchDTO.getPage().toPageable();
            }
            if (searchDTO.getCriteria() != null) {
                Page<Voucher> page = this.voucherRepository
                    .findAll(VoucherPredicate.searchReceiptVoucher(searchDTO.getCriteria()), pageable);
                results = ReceiptVoucherResultDTOAssembler.toListResultDTO(page);
            }
        }
        log.info("findReceiptVoucherByCriteria() end - " + results);
        return results;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("saveJournalVoucher() start - " + journalVoucherDetail);
        JournalVoucherBasicInformationDTO journalVoucherBasicInformation
            = journalVoucherDetail.getJournalVoucherBasicInformation();
        Voucher voucher = DataUtils.copyProperties(journalVoucherBasicInformation, Voucher.class);
        VoucherType voucherType = voucherTypeRepository.findByVoucherTypeCode(ShroffConstant.VT_JOU);
        voucher.setVoucherType(voucherType);
        voucher = voucherRepository.save(voucher);
        Integer voucherId = voucher.getVoucherId();

        List<JournalVoucherAccountItemDTO> journalVoucherAccountItems
            = journalVoucherDetail.getJournalVoucherAccountItems();
        if (CommonUtils.isNotEmpty(journalVoucherAccountItems)) {
            journalVoucherAccountItems.stream().forEach(item -> {
                ShrVcrItem shrVcrItem = DataUtils.copyProperties(item, ShrVcrItem.class);
                shrVcrItem.setVoucherId(voucherId);
                if (item.getAccount() != null) {
                    CaseAccountInfo caseAccountInfo
                        = caseAccountInfoRepository.findOne(item.getAccount().getCaseAcId());
                    shrVcrItem.setCaseAccount(caseAccountInfo);
                }
                if (CoreConstant.STATUS_ACTIVE.equals(item.getStatus())) {
                    shrVcrItem.setNature(item.getParticulars());
                    if (item.getAmountDr() != null) {
                        shrVcrItem.setVoucherAmount(item.getAmountDr());
                        shrVcrItem.setDebitCredit(ShroffConstant.DR);
                    } else {
                        shrVcrItem.setVoucherAmount(item.getAmountCr());
                        shrVcrItem.setDebitCredit(ShroffConstant.CR);
                    }
                }
                shrVcrItemRepository.save(shrVcrItem);

            });
        }

        log.info("saveJournalVoucher() end VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(readOnly = true)
    @Override
    public JournalVoucherDetailDTO findJournalVoucher(Integer voucherId) {
        log.info("findJournalVoucher() start - VoucherId: " + voucherId);
        Voucher voucher = voucherRepository.findOne(voucherId);
        List<ShrVcrItem> shrVcrItems = shrVcrItemRepository.findAvaiableVoucherItem(voucherId);
        List<SysApprovalWf> approvalWorkflows
            = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(
                voucher.getWorkflowId(), CoreConstant.STATUS_ACTIVE);
        JournalVoucherDetailDTO dto = journalVoucherDetailDTOAssembler.toDTO(voucher, shrVcrItems, approvalWorkflows);
        ActionDTO actionDTO = sysWorkFlowRuleService.findAction(PrivilegeConstant.PRIVILEGE_JVM, voucher.getStatus());
        dto.setAction(actionDTO);
        log.info("findJournalVoucher() end - " + dto);
        return dto;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<JournalVoucherResultItemDTO> searchJournalVoucher(SearchDTO<JournalVoucherSearchDTO> searchDTO) {
        log.info("searchJournalVoucher() start - " + searchDTO);

        Page<JournalVoucherResultItemDTO> result
            = voucherRepository.findAll(journalVoucherResultItemDTOAssembler.getQBean(),
                VoucherPredicate.searchJournalVoucher(searchDTO.getCriteria()), searchDTO.getPage().toPageable());
        log.info("searchJournalVoucher() end - " + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PaymentVoucherResultDTO> findPaymentVoucherByCriteria(SearchDTO<PaymentVoucherSearchDTO> criteria) {
        log.info("findPaymentVoucherByCriteria() start - criteria = " + criteria);
        Page<Voucher> page = null;
        Page<PaymentVoucherResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = voucherRepository.findAll(VoucherPredicate.searchPaymentVoucher(criteria.getCriteria()),
                    pageable);
            }

            if (page != null) {
                result = paymentVoucherResultDTOAssembler.toResultDTO(page);
                if (result != null && CommonUtils.isNotBlank(criteria.getCriteria().getAccountNumber())) {
                    List<PaymentVoucherResultDTO> temp = result.getContent();
                    temp.stream().forEach(item -> item.setAccountNumber(criteria.getCriteria().getAccountNumber()));

                    return new PageImpl<>(temp, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
                        page.getTotalElements());
                }
            }

        }
        log.info("findPaymentVoucherByCriteria() end - ");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("saveReceiptVoucher() start - " + receiptVoucherDetail);

        ReceiptVoucherBasicInfoDTO receiptVoucherBasicInfo = receiptVoucherDetail.getReceiptVoucherBasicInformation();
        Voucher voucher = DataUtils.copyProperties(receiptVoucherBasicInfo, Voucher.class);
        VoucherType voucherType = voucherTypeRepository.findByVoucherTypeCode(ShroffConstant.VT_REC);
        voucher.setVoucherType(voucherType);

        if (receiptVoucherBasicInfo.getControlAccount() != null
            && receiptVoucherBasicInfo.getControlAccount().getCtlAcId() != null
            && receiptVoucherBasicInfo.getControlAccount().getCtlAcId() != 0) {
            ShrCtlAc controlAccount
                = controlAccountRepository.findOne(receiptVoucherBasicInfo.getControlAccount().getCtlAcId());
            if (receiptVoucherBasicInfo.getControlAccount().getLiquidCashAmount() != null) {
                controlAccount.setLiquidCashAmount(receiptVoucherBasicInfo.getControlAccount().getLiquidCashAmount());
            }
            voucher.setControlAccount(controlAccount);
        } else {
            voucher.setControlAccount(null);
        }

        voucher = voucherRepository.save(voucher);
        Integer voucherId = voucher.getVoucherId();

        List<ReceiptVoucherAccountItemDTO> receiptVoucherAccountItems
            = receiptVoucherDetail.getReceiptVoucherAccountItems();
        if (CommonUtils.isNotEmpty(receiptVoucherAccountItems)) {
            receiptVoucherAccountItems.stream().forEach(item -> {
                ShrVcrItem shrVcrItem = DataUtils.copyProperties(item, ShrVcrItem.class);
                if (CoreConstant.STATUS_INACTIVE.equals(item.getStatus())) {
                    shrVcrItem.setStatus(CoreConstant.STATUS_DELETE);
                }

                if (item.getAccount() != null) {
                    CaseAccountInfo caseAccountInfo
                        = DataUtils.copyProperties(item.getAccount(), CaseAccountInfo.class);
                    shrVcrItem.setCaseAccount(caseAccountInfo);
                }
                shrVcrItem.setReceiver(item.getPayerName());
                shrVcrItem.setVoucherId(voucherId);
                if (CoreConstant.STATUS_ACTIVE.equals(item.getStatus())) {
                    if (item.getVoucherAmount() != null) {
                        shrVcrItem.setVoucherAmount(item.getVoucherAmount());
                        shrVcrItem.setDebitCredit(ShroffConstant.DR);
                    }
                }
                shrVcrItemRepository.save(shrVcrItem);
            });
        }

        log.info("saveReceiptVoucher() end - voucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer savePaymentVoucher(PaymentVoucherDetailDTO voucherDetail) {
        log.info("savePaymentVoucher() start - Payment Voucher Detail = " + voucherDetail);
        PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation
            = voucherDetail.getPaymentVoucherBasicInformation();
        Voucher voucher = DataUtils.copyProperties(paymentVoucherBasicInformation, Voucher.class);
        VoucherType voucherType = voucherTypeRepository.findByVoucherTypeCode(ShroffConstant.VT_PAY);
        voucher.setVoucherType(voucherType);
        if (paymentVoucherBasicInformation.getControlAccount() != null
            && paymentVoucherBasicInformation.getControlAccount().getCtlAcId() != null
            && paymentVoucherBasicInformation.getControlAccount().getCtlAcId() != 0) {
            voucher.setControlAccount(
                controlAccountRepository.findOne(paymentVoucherBasicInformation.getControlAccount().getCtlAcId()));
        } else {
            voucher.setControlAccount(null);
        }
        voucher = voucherRepository.save(voucher);
        Integer voucherId = voucher.getVoucherId();
        List<PaymentVoucherAccountItemDTO> paymentVoucherItems = voucherDetail.getPaymentVoucherItems();
        if (CommonUtils.isNotEmpty(paymentVoucherItems)) {
            paymentVoucherItems.stream().forEach(item -> {
                ShrVcrItem shrVcrItem = DataUtils.copyProperties(item, ShrVcrItem.class);
                shrVcrItem.setVoucherId(voucherId);
                if (item.getAccount() != null) {
                    CaseAccountInfo caseAccountInfo
                        = DataUtils.copyProperties(item.getAccount(), CaseAccountInfo.class);
                    shrVcrItem.setCaseAccount(caseAccountInfo);
                }
                if (CoreConstant.STATUS_ACTIVE.equals(item.getStatus())) {
                    if (item.getAmount() != null) {
                        shrVcrItem.setVoucherAmount(item.getAmount());
                        shrVcrItem.setDebitCredit(ShroffConstant.CR);
                    }
                }
                shrVcrItemRepository.save(shrVcrItem);

            });
        }

        log.info("savePaymentVoucher() end - voucher Id = " + voucherId);
        return voucherId;
    }

    @Transactional(readOnly = true)
    @Override
    public ReceiptVoucherDetailDTO findReceiptVoucher(Integer voucherId) {
        log.info("findReceiptVoucher() start - VoucherId: " + voucherId);
        Voucher voucher = voucherRepository.findOne(voucherId);
        List<ShrVcrItem> shrVcrItems = shrVcrItemRepository.findAvaiableVoucherItem(voucherId);
        List<SysApprovalWf> approvalWorkflows
            = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(
                voucher.getWorkflowId(), CoreConstant.STATUS_ACTIVE);
        ReceiptVoucherDetailDTO dto = receiptVoucherDetailDTOAssembler.toDTO(voucher, shrVcrItems, approvalWorkflows);
        ActionDTO actionDTO = sysWorkFlowRuleService.findAction(PrivilegeConstant.PRIVILEGE_RVM, voucher.getStatus());
        dto.setAction(actionDTO);
        log.info("findReceiptVoucher() end - " + dto);
        return dto;
    }

    @Transactional(readOnly = true)
    @Override
    public PaymentVoucherDetailDTO findPaymentVoucher(Integer voucherId) {
        log.info("findPaymentVoucher() start - VoucherId: " + voucherId);
        Voucher voucher = voucherRepository.findOne(voucherId);
        List<ShrVcrItem> shrVcrItems = shrVcrItemRepository.findByVoucherIdOrderByVoucherItemNoAsc(voucherId);
        List<SysApprovalWf> approvalWorkflows
            = sysApprovalWFRepository.findByWorkflowIdAndStatusIgnoreCaseOrderByApprovalWorkflowId(
                voucher.getWorkflowId(), CoreConstant.STATUS_ACTIVE);
        PaymentVoucherDetailDTO dto = paymentVoucherDetailDTOAssembler.toDTO(voucher, shrVcrItems, approvalWorkflows);
        ActionDTO actionDTO = sysWorkFlowRuleService.findAction(PrivilegeConstant.PRIVILEGE_PVM, voucher.getStatus());
        dto.setAction(actionDTO);
        log.info("findPaymentVoucher() end - " + dto);
        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteVoucher(Integer voucherId) {
        log.info("deleteVoucher() start - VoucherId: " + voucherId);
        List<ShrVcrItem> shrVcrItems = shrVcrItemRepository.findByVoucherId(voucherId);
        shrVcrItems.stream().forEach(item -> {
            item.setStatus(CoreConstant.VOUCHER_STATUS_DELETED);
            shrVcrItemRepository.save(item);
        });

        Voucher voucher = voucherRepository.findOne(voucherId);
        voucher.setStatus(CoreConstant.VOUCHER_STATUS_DELETED);
        voucherRepository.save(voucher);
        log.info("deleteVoucher() end - ");
    }

    @Transactional(readOnly = true)
    @Override
    public DownloadFileDTO downloadImportTemplate(List<String> header) {
        log.info("downloadImportTemplate() start - header: " + header);
        StringBuilder sb = new StringBuilder();
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(CoreConstant.CSV_LINE_END);

        try {
            csvFilePrinter = new CSVPrinter(sb, csvFileFormat);
            csvFilePrinter.printRecord(header.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFilePrinter.flush();
            } catch (Exception oe) {
                oe.printStackTrace();
            }
        }
        DownloadFileDTO downloadFileDTO = new DownloadFileDTO();
        downloadFileDTO.setFileFormat(MimeTypeUtils.FileExtension.CSV.name());
        downloadFileDTO.setFileName(TEMPLATE_FILE_NAME);
        downloadFileDTO.setFileResult(sb.toString().getBytes());
        log.info("downloadImportTemplate() end - result = " + downloadFileDTO);
        return downloadFileDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer submitJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("submitJournalVoucher() start - " + journalVoucherDetail);
        JournalVoucherBasicInformationDTO journalVoucherBasicInformation
            = journalVoucherDetail.getJournalVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(journalVoucherBasicInformation.getWorkflowId(),
            journalVoucherBasicInformation.getSysWorkFlowRule(), journalVoucherBasicInformation.getRemark());

        if (CommonUtils.isBlank(journalVoucherBasicInformation.getVoucherNo())) {
            String voucherNumber = genVoucherNumber(journalVoucherBasicInformation.getGroupCode(),
                ShroffConstant.VT_JOU, SysSequenceEnum.JOU_VOUCHER_SEQ_NO.name());
            journalVoucherBasicInformation.setVoucherNo(voucherNumber);
        }

        journalVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = saveJournalVoucher(journalVoucherDetail);

        List<JournalVoucherAccountItemDTO> journalVoucherAccountItems
            = journalVoucherDetail.getJournalVoucherAccountItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        journalVoucherAccountItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            if (item.getAmountCr() != null) {
                account.setOnHoldAmountCr(account.getOnHoldAmountCr().add(item.getAmountCr()));
            } else if (item.getAmountDr() != null) {
                account.setOnHoldAmountDr(account.getOnHoldAmountDr().add(item.getAmountDr()));
            }
        });

        saveCaseAccountInfos(accountMap.values());

        log.info("submitJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    private String genVoucherNumber(String groupCode, String voucherTypeCode, String seqCode) {
        log.info("genVoucherNumber() start - GroupCode: " + groupCode + ", VoucherTypeCode: " + voucherTypeCode);
        String generateVoucherSeqNo = sysSequenceService.generateVoucherSeqNo(seqCode);
        String voucherNumber = groupCode + voucherTypeCode + generateVoucherSeqNo;
        log.info("genVoucherNumber() end - VoucherNumber: " + voucherNumber);
        return voucherNumber;
    }

    private Integer saveWorkFlowHistory(Integer workFlowId, SysWorkFlowRuleDTO sysWorkFlowRuleDTO, String remark) {
        log.info("saveWorkFlowHistory() start - WorkFlowId: " + workFlowId + ", SysWorkFlowRuleDTO: "
            + sysWorkFlowRuleDTO + ", Remark: " + remark);
        UserInfo currentUserInfo = RequestContextUtils.getCurrentUserInfo();
        ApplicationCodeTableDTO actionDone = sysWorkFlowRuleDTO.getActionDone();
        if (actionDone != null) {
            SysApprovalWfDTO sysApprovalWf = new SysApprovalWfDTO();
            sysApprovalWf.setAction(actionDone.getCodeDesc());
            sysApprovalWf.setActionByPerson(currentUserInfo.getUserName());
            sysApprovalWf.setActionByPostId(currentUserInfo.getPostId());
            sysApprovalWf.setDivisionId(currentUserInfo.getDivisionId());
            sysApprovalWf.setPrivilegeCode(sysWorkFlowRuleDTO.getPrivilege().getPrivilegeCode());
            sysApprovalWf.setRemark(remark);
            workFlowId = commonService.saveSysApprovalWf(workFlowId, sysApprovalWf);
        }

        ApplicationCodeTableDTO actionToBeTaken = sysWorkFlowRuleDTO.getActionToBeTaken();
        if (actionToBeTaken != null) {
            SysApprovalWfDTO sysApprovalWf = new SysApprovalWfDTO();
            sysApprovalWf.setAction(actionToBeTaken.getCodeDesc());
            sysApprovalWf.setActionByPerson(currentUserInfo.getUserName());
            sysApprovalWf.setActionByPostId(currentUserInfo.getPostId());
            sysApprovalWf.setDivisionId(currentUserInfo.getDivisionId());
            sysApprovalWf.setPrivilegeCode(sysWorkFlowRuleDTO.getPrivilege().getPrivilegeCode());
            sysApprovalWf.setRemark(remark);
            workFlowId = commonService.saveSysApprovalWf(workFlowId, sysApprovalWf);
        }

        log.info("saveWorkFlowHistory() end - WorkFlowId: " + workFlowId);
        return workFlowId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer approveJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("approveJournalVoucher() start - " + journalVoucherDetail);
        JournalVoucherBasicInformationDTO journalVoucherBasicInformation
            = journalVoucherDetail.getJournalVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(journalVoucherBasicInformation.getWorkflowId(),
            journalVoucherBasicInformation.getSysWorkFlowRule(), journalVoucherBasicInformation.getRemark());

        journalVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = saveJournalVoucher(journalVoucherDetail);

        List<JournalVoucherAccountItemDTO> journalVoucherAccountItems
            = journalVoucherDetail.getJournalVoucherAccountItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        journalVoucherAccountItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            BigDecimal debit = null;
            BigDecimal credit = null;
            if (item.getAmountCr() != null) {
                account.setOnHoldAmountCr(account.getOnHoldAmountCr().subtract(item.getAmountCr()));
                account.setLiquidCashAmount(account.getLiquidCashAmount().subtract(item.getAmountCr()));
                credit = item.getAmountCr();
            } else if (item.getAmountDr() != null) {
                account.setOnHoldAmountDr(account.getOnHoldAmountDr().subtract(item.getAmountDr()));
                account.setLiquidCashAmount(account.getLiquidCashAmount().add(item.getAmountDr()));
                debit = item.getAmountDr();
            }

            shrGeneralLedgerService.saveGeneralLedger(item.getAnalysisCode(), voucherId, credit, debit,
                account.getLiquidCashAmount(), caseAcId, null, null);
        });

        saveCaseAccountInfos(accountMap.values());

        log.info("approveJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer rejectJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("rejectJournalVoucher() start - " + journalVoucherDetail);
        JournalVoucherBasicInformationDTO journalVoucherBasicInformation
            = journalVoucherDetail.getJournalVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(journalVoucherBasicInformation.getWorkflowId(),
            journalVoucherBasicInformation.getSysWorkFlowRule(), journalVoucherBasicInformation.getRemark());

        journalVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = saveJournalVoucher(journalVoucherDetail);

        List<JournalVoucherAccountItemDTO> journalVoucherAccountItems
            = journalVoucherDetail.getJournalVoucherAccountItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        journalVoucherAccountItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            if (item.getAmountCr() != null) {
                account.setOnHoldAmountCr(account.getOnHoldAmountCr().subtract(item.getAmountCr()));
            } else if (item.getAmountDr() != null) {
                account.setOnHoldAmountDr(account.getOnHoldAmountDr().subtract(item.getAmountDr()));
            }
        });

        saveCaseAccountInfos(accountMap.values());
        log.info("rejectJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    private void saveCaseAccountInfos(Collection<CaseAccountInfoDTO> collection) {
        collection.stream().forEach(account -> {
            CaseAccountInfo caseAccountInfo = DataUtils.copyProperties(account, CaseAccountInfo.class);
            caseAccountInfoRepository.save(caseAccountInfo);
        });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer verifyJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("verifyJournalVoucher() start - " + journalVoucherDetail);
        JournalVoucherBasicInformationDTO journalVoucherBasicInformation
            = journalVoucherDetail.getJournalVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(journalVoucherBasicInformation.getWorkflowId(),
            journalVoucherBasicInformation.getSysWorkFlowRule(), journalVoucherBasicInformation.getRemark());

        journalVoucherBasicInformation.setWorkflowId(workFlowId);
        journalVoucherBasicInformation.setPaymentVerifierId(RequestContextUtils.getCurrentUserInfo().getPostId());
        journalVoucherBasicInformation.setPaymentApproverName(RequestContextUtils.getCurrentUserInfo().getUserName());
        Integer voucherId = saveJournalVoucher(journalVoucherDetail);

        log.info("verifyJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer reverseJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("reverseJournalVoucher() start - " + journalVoucherDetail);
        JournalVoucherBasicInformationDTO journalVoucherBasicInformation
            = journalVoucherDetail.getJournalVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(journalVoucherBasicInformation.getWorkflowId(),
            journalVoucherBasicInformation.getSysWorkFlowRule(), journalVoucherBasicInformation.getRemark());

        journalVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = saveJournalVoucher(journalVoucherDetail);

        List<JournalVoucherAccountItemDTO> journalVoucherAccountItems
            = journalVoucherDetail.getJournalVoucherAccountItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        journalVoucherAccountItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            BigDecimal debit = null;
            BigDecimal credit = null;
            if (item.getAmountCr() != null) {
                account.setLiquidCashAmount(account.getLiquidCashAmount().add(item.getAmountCr()));
                debit = item.getAmountCr();
            } else if (item.getAmountDr() != null) {
                account.setLiquidCashAmount(account.getLiquidCashAmount().subtract(item.getAmountDr()));
                credit = item.getAmountDr();
            }

            shrGeneralLedgerService.saveGeneralLedger(item.getAnalysisCode(), voucherId, credit, debit,
                account.getLiquidCashAmount(), caseAcId, null, null);
        });

        saveCaseAccountInfos(accountMap.values());

        log.info("reverseJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer deleteJournalVoucher(JournalVoucherDetailDTO journalVoucherDetail) {
        log.info("deleteJournalVoucher() start - " + journalVoucherDetail);
        JournalVoucherBasicInformationDTO journalVoucherBasicInformation
            = journalVoucherDetail.getJournalVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(journalVoucherBasicInformation.getWorkflowId(),
            journalVoucherBasicInformation.getSysWorkFlowRule(), journalVoucherBasicInformation.getRemark());

        journalVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = saveJournalVoucher(journalVoucherDetail);

        log.info("deleteJournalVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer submitReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("submitReceiptVoucher() start - receiptVoucherDetail: " + receiptVoucherDetail);

        ReceiptVoucherBasicInfoDTO receiptVoucherBasicInfo = receiptVoucherDetail.getReceiptVoucherBasicInformation();
        Integer workFlowId = saveWorkFlowHistory(receiptVoucherBasicInfo.getWorkflowId(),
            receiptVoucherBasicInfo.getSysWorkFlowRule(), receiptVoucherBasicInfo.getRemark());

        if (CommonUtils.isBlank(receiptVoucherBasicInfo.getVoucherNo())) {
            String voucherNumber = genVoucherNumber(receiptVoucherBasicInfo.getGroupCode(), ShroffConstant.VT_REC,
                SysSequenceEnum.REC_VOUCHER_SEQ_NO.name());
            receiptVoucherBasicInfo.setVoucherNo(voucherNumber);
        }

        receiptVoucherBasicInfo.setWorkflowId(workFlowId);

        Integer voucherId = saveReceiptVoucher(receiptVoucherDetail);

        List<ReceiptVoucherAccountItemDTO> receiptVoucherAccountItems
            = receiptVoucherDetail.getReceiptVoucherAccountItems();
        List<CaseAccountInfoDTO> caseAccounts = new ArrayList<CaseAccountInfoDTO>();
        for (ReceiptVoucherAccountItemDTO accountItem : receiptVoucherAccountItems) {
            CaseAccountInfoDTO caseAccount = accountItem.getAccount();
            caseAccount.setOnHoldAmountDr(caseAccount.getOnHoldAmountDr().add(accountItem.getVoucherAmount()));
            caseAccounts.add(caseAccount);
        }

        this.saveCaseAccountInfos(caseAccounts);

        log.info("submitReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer submitPaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("submitPaymentVoucher() start - " + paymentVoucherDetail);
        PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation
            = paymentVoucherDetail.getPaymentVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(paymentVoucherBasicInformation.getWorkflowId(),
            paymentVoucherBasicInformation.getSysWorkFlowRule(), paymentVoucherBasicInformation.getRemark());

        if (CommonUtils.isBlank(paymentVoucherBasicInformation.getVoucherNo())) {
            String voucherNumber = genVoucherNumber(paymentVoucherBasicInformation.getGroupCode(),
                ShroffConstant.VT_PAY, SysSequenceEnum.PAY_VOUCHER_SEQ_NO.name());
            paymentVoucherBasicInformation.setVoucherNo(voucherNumber);
        }

        paymentVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = savePaymentVoucher(paymentVoucherDetail);

        List<PaymentVoucherAccountItemDTO> paymentVoucherAccountItems = paymentVoucherDetail.getPaymentVoucherItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        paymentVoucherAccountItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            account.setOnHoldAmountCr(account.getOnHoldAmountCr().add(item.getAmount()));

        });

        saveCaseAccountInfos(accountMap.values());

        log.info("submitPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer approveReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("approvePaymentVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);
        ReceiptVoucherBasicInfoDTO receiptVoucherBasicInfo = receiptVoucherDetail.getReceiptVoucherBasicInformation();

        Integer workFlowId = this.saveWorkFlowHistory(receiptVoucherBasicInfo.getWorkflowId(),
            receiptVoucherBasicInfo.getSysWorkFlowRule(), receiptVoucherBasicInfo.getRemark());

        receiptVoucherBasicInfo.setWorkflowId(workFlowId);
        Integer voucherId = this.saveReceiptVoucher(receiptVoucherDetail);

        List<ReceiptVoucherAccountItemDTO> receiptVoucherItems = receiptVoucherDetail.getReceiptVoucherAccountItems();
        Map<Integer, CaseAccountInfoDTO> caseAccountMap = new HashMap<Integer, CaseAccountInfoDTO>();
        receiptVoucherItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO caseAccount = null;
            if (caseAccountMap.containsKey(caseAcId)) {
                caseAccount = caseAccountMap.get(caseAcId);
            } else {
                caseAccount = item.getAccount();
                caseAccountMap.put(caseAcId, caseAccount);
            }

            caseAccount.setOnHoldAmountDr(caseAccount.getOnHoldAmountDr().subtract(item.getVoucherAmount()));
            caseAccount.setLiquidCashAmount(caseAccount.getLiquidCashAmount().add(item.getVoucherAmount()));

            shrGeneralLedgerService.saveGeneralLedger(item.getAnalysisCode(), voucherId, item.getVoucherAmount(), null,
                caseAccount.getLiquidCashAmount(), caseAcId, null, item.getNature());
        });
        Collection<CaseAccountInfoDTO> caseAccountInfos = caseAccountMap.values();
        this.saveCaseAccountInfos(caseAccountInfos);

        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer approvePaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("approvePaymentVoucher() start - " + paymentVoucherDetail);
        PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation
            = paymentVoucherDetail.getPaymentVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(paymentVoucherBasicInformation.getWorkflowId(),
            paymentVoucherBasicInformation.getSysWorkFlowRule(), paymentVoucherBasicInformation.getRemark());

        paymentVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = savePaymentVoucher(paymentVoucherDetail);

        List<PaymentVoucherAccountItemDTO> paymentVoucherItems = paymentVoucherDetail.getPaymentVoucherItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        paymentVoucherItems.stream().forEach(item -> {

            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            account.setOnHoldAmountCr(account.getOnHoldAmountCr().subtract(item.getAmount()));
            account.setLiquidCashAmount(account.getLiquidCashAmount().subtract(item.getAmount()));

            shrGeneralLedgerService.saveGeneralLedger(item.getAnalysisCode(), voucherId, item.getAmount(), null,
                account.getLiquidCashAmount(), caseAcId, null, item.getNature());

        });

        saveCaseAccountInfos(accountMap.values());

        log.info("approvePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer rejectReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("rejectReceiptVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);
        ReceiptVoucherBasicInfoDTO receiptVoucherBasicInfo = receiptVoucherDetail.getReceiptVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(receiptVoucherBasicInfo.getWorkflowId(),
            receiptVoucherBasicInfo.getSysWorkFlowRule(), receiptVoucherBasicInfo.getRemark());

        receiptVoucherBasicInfo.setWorkflowId(workFlowId);

        ControlAccountDTO controlAccount = receiptVoucherBasicInfo.getControlAccount();
        controlAccount.setLiquidCashAmount(
            controlAccount.getLiquidCashAmount().subtract(receiptVoucherBasicInfo.getVoucherTotalAmount()));

        Integer voucherId = this.saveReceiptVoucher(receiptVoucherDetail);

        List<ReceiptVoucherAccountItemDTO> receiptVoucherItems = receiptVoucherDetail.getReceiptVoucherAccountItems();
        Map<Integer, CaseAccountInfoDTO> caseAccountMap = new HashMap<Integer, CaseAccountInfoDTO>();
        receiptVoucherItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO caseAccount = null;
            if (caseAccountMap.containsKey(caseAcId)) {
                caseAccount = caseAccountMap.get(caseAcId);
            } else {
                caseAccount = item.getAccount();
                caseAccountMap.put(caseAcId, caseAccount);
            }

            caseAccount.setOnHoldAmountDr(caseAccount.getOnHoldAmountDr().subtract(item.getVoucherAmount()));
        });
        saveCaseAccountInfos(caseAccountMap.values());

        log.info("rejectReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer rejectPaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("rejectPaymentVoucher() start - " + paymentVoucherDetail);
        PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation
            = paymentVoucherDetail.getPaymentVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(paymentVoucherBasicInformation.getWorkflowId(),
            paymentVoucherBasicInformation.getSysWorkFlowRule(), paymentVoucherBasicInformation.getRemark());

        paymentVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = savePaymentVoucher(paymentVoucherDetail);

        List<PaymentVoucherAccountItemDTO> paymentVoucherItems = paymentVoucherDetail.getPaymentVoucherItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        paymentVoucherItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            account.setOnHoldAmountCr(account.getOnHoldAmountCr().subtract(item.getAmount()));
        });

        saveCaseAccountInfos(accountMap.values());
        log.info("rejectPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer verifyReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("verifyReceiptVoucher() start - " + receiptVoucherDetail);

        ReceiptVoucherBasicInfoDTO receiptVoucherBasicInfo = receiptVoucherDetail.getReceiptVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(receiptVoucherBasicInfo.getWorkflowId(),
            receiptVoucherBasicInfo.getSysWorkFlowRule(), receiptVoucherBasicInfo.getRemark());

        receiptVoucherBasicInfo.setWorkflowId(workFlowId);
        receiptVoucherBasicInfo.setPaymentVerifierId(RequestContextUtils.getCurrentUserInfo().getPostId());
        receiptVoucherBasicInfo.setPaymentApproverName(RequestContextUtils.getCurrentUserInfo().getUserName());

        Integer voucherId = this.saveReceiptVoucher(receiptVoucherDetail);

        ControlAccountDTO controlAccount = receiptVoucherBasicInfo.getControlAccount();
        controlAccount.setLiquidCashAmount(
            controlAccount.getLiquidCashAmount().add(receiptVoucherBasicInfo.getVoucherTotalAmount()));
        controlAccount
            .setBalance(controlAccount.getBalance().subtract(receiptVoucherBasicInfo.getVoucherTotalAmount()));

        controlAccountService.saveControlAccount(controlAccount);

        shrGeneralLedgerService.saveGeneralLedger(null, voucherId, receiptVoucherBasicInfo.getVoucherTotalAmount(),
            null, controlAccount.getLiquidCashAmount(), null, controlAccount.getCtlAcId(), null);

        log.info("verifyReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer verifyPaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("verifyPaymentVoucher() start - " + paymentVoucherDetail);
        PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation
            = paymentVoucherDetail.getPaymentVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(paymentVoucherBasicInformation.getWorkflowId(),
            paymentVoucherBasicInformation.getSysWorkFlowRule(), paymentVoucherBasicInformation.getRemark());

        paymentVoucherBasicInformation.setWorkflowId(workFlowId);
        paymentVoucherBasicInformation.setPaymentVerifierId(RequestContextUtils.getCurrentUserInfo().getPostId());
        paymentVoucherBasicInformation.setPaymentApproverName(RequestContextUtils.getCurrentUserInfo().getUserName());
        Integer voucherId = savePaymentVoucher(paymentVoucherDetail);

        if (paymentVoucherBasicInformation.getPaymentMethod().toUpperCase().trim().contains("Cheque".toUpperCase())) {
            ChequeDTO cheque = new ChequeDTO();
            cheque.setChequeAmount(paymentVoucherBasicInformation.getVoucherTotalAmount());
            cheque.setBankCode("");
            cheque.setChequeDate(paymentVoucherBasicInformation.getVoucherDate());

            Integer currencyId = paymentVoucherBasicInformation.getCurrencyId();
            if (currencyId != null) {
                CurrencyDTO currencyDTO = currencyService.findOne(currencyId);
                if (currencyDTO != null) {
                    cheque.setCurcy(currencyDTO);
                }
            }
            cheque.setChequeTypeId(ShroffConstant.CQ_OUTGOING);
            cheque.setShrVcrInfo(DataUtils.copyProperties(paymentVoucherBasicInformation, VoucherDTO.class));
            cheque.setStatus(CoreConstant.CHEQUE_STATUS_PENDING);
            chequeService.saveOutgoingCheque(cheque);

        } else if (paymentVoucherBasicInformation.getPaymentMethod().toUpperCase().trim()
            .contains("Transfer".toUpperCase())) {
            BankTransferItemDTO transfer = new BankTransferItemDTO();
            transfer.setTransferAmount(paymentVoucherBasicInformation.getVoucherTotalAmount());
            //
        }

        ControlAccountDTO controlAccount = paymentVoucherBasicInformation.getControlAccount();
        if (controlAccount != null) {
            BigDecimal voucherTotalAmount = paymentVoucherBasicInformation.getVoucherTotalAmount();
            controlAccount.setLiquidCashAmount(controlAccount.getLiquidCashAmount().subtract(voucherTotalAmount));
            controlAccount.setBalance(controlAccount.getBalance().add(voucherTotalAmount));
            shrGeneralLedgerService.saveGeneralLedger(null, voucherId, voucherTotalAmount, null,
                controlAccount.getLiquidCashAmount(), null, controlAccount.getCtlAcId(), null);

            controlAccountService.saveControlAccount(controlAccount);
        }

        log.info("verifyPaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer reverseReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("reverseReceiptVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);
        ReceiptVoucherBasicInfoDTO receiptVoucherBasicInfo = receiptVoucherDetail.getReceiptVoucherBasicInformation();
        Integer workFlowId = saveWorkFlowHistory(receiptVoucherBasicInfo.getWorkflowId(),
            receiptVoucherBasicInfo.getSysWorkFlowRule(), receiptVoucherBasicInfo.getRemark());

        receiptVoucherBasicInfo.setWorkflowId(workFlowId);
        Integer voucherId = this.saveReceiptVoucher(receiptVoucherDetail);

        List<ReceiptVoucherAccountItemDTO> receiptVoucherItems = receiptVoucherDetail.getReceiptVoucherAccountItems();
        Map<Integer, CaseAccountInfoDTO> caseAccountMap = new HashMap<Integer, CaseAccountInfoDTO>();
        ControlAccountDTO controlAccount = receiptVoucherBasicInfo.getControlAccount();

        controlAccount.setBalance(controlAccount.getBalance().add(receiptVoucherBasicInfo.getVoucherTotalAmount()));
        controlAccount.setLiquidCashAmount(
            controlAccount.getLiquidCashAmount().subtract(receiptVoucherBasicInfo.getVoucherTotalAmount()));

        receiptVoucherItems.stream().forEach(item -> {
            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO caseAccount = null;
            if (caseAccountMap.containsKey(caseAcId)) {
                caseAccount = caseAccountMap.get(caseAcId);
            } else {
                caseAccount = item.getAccount();
                caseAccountMap.put(caseAcId, caseAccount);
            }

            caseAccount
                .setLiquidCashAmount(caseAccount.getLiquidCashAmount().subtract(caseAccount.getOnHoldAmountDr()));

            shrGeneralLedgerService.saveGeneralLedger(item.getAnalysisCode(), voucherId, null, item.getVoucherAmount(),
                caseAccount.getLiquidCashAmount(), caseAcId, null, item.getNature());
        });
        saveCaseAccountInfos(caseAccountMap.values());

        controlAccountService.saveControlAccount(controlAccount);
        shrGeneralLedgerService.saveGeneralLedger(null, voucherId, receiptVoucherBasicInfo.getVoucherTotalAmount(),
            null, controlAccount.getLiquidCashAmount(), null, controlAccount.getCtlAcId(), null);

        log.info("reverseReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer reversePaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("reversePaymentVoucher() start - " + paymentVoucherDetail);
        PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation
            = paymentVoucherDetail.getPaymentVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(paymentVoucherBasicInformation.getWorkflowId(),
            paymentVoucherBasicInformation.getSysWorkFlowRule(), paymentVoucherBasicInformation.getRemark());

        paymentVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = savePaymentVoucher(paymentVoucherDetail);

        List<PaymentVoucherAccountItemDTO> paymentVoucherItems = paymentVoucherDetail.getPaymentVoucherItems();
        Map<Integer, CaseAccountInfoDTO> accountMap = new HashMap<>();
        ControlAccountDTO controlAccount = paymentVoucherBasicInformation.getControlAccount();
        paymentVoucherItems.stream().forEach(item -> {

            Integer caseAcId = item.getAccount().getCaseAcId();
            CaseAccountInfoDTO account = null;
            if (accountMap.containsKey(caseAcId)) {
                account = accountMap.get(caseAcId);
            } else {
                account = item.getAccount();
                accountMap.put(caseAcId, account);
            }

            account.setLiquidCashAmount(account.getLiquidCashAmount().add(item.getAmount()));
            controlAccount.setLiquidCashAmount(controlAccount.getLiquidCashAmount().add(item.getAmount()));
            controlAccount.setBalance(controlAccount.getBalance().subtract(item.getAmount()));

            shrGeneralLedgerService.saveGeneralLedger(item.getAnalysisCode(), voucherId, null, item.getAmount(),
                account.getLiquidCashAmount(), caseAcId, null, item.getNature());
        });

        saveCaseAccountInfos(accountMap.values());

        shrGeneralLedgerService.saveGeneralLedger(null, voucherId,
            paymentVoucherBasicInformation.getVoucherTotalAmount(), null, controlAccount.getLiquidCashAmount(), null,
            controlAccount.getCtlAcId(), null);
        controlAccountService.saveControlAccount(controlAccount);

        log.info("reversePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer deleteReceiptVoucher(ReceiptVoucherDetailDTO receiptVoucherDetail) {
        log.info("deleteReceiptVoucher() start - ReceiptVoucherDetailDTO: " + receiptVoucherDetail);

        ReceiptVoucherBasicInfoDTO receiptVoucherBasicInfo = receiptVoucherDetail.getReceiptVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(receiptVoucherBasicInfo.getWorkflowId(),
            receiptVoucherBasicInfo.getSysWorkFlowRule(), receiptVoucherBasicInfo.getRemark());

        receiptVoucherBasicInfo.setWorkflowId(workFlowId);
        Integer voucherId = this.saveReceiptVoucher(receiptVoucherBasicInfo);

        log.info("deleteReceiptVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer deletePaymentVoucher(PaymentVoucherDetailDTO paymentVoucherDetail) {
        log.info("deletePaymentVoucher() start - " + paymentVoucherDetail);
        PaymentVoucherBasicInformationDTO paymentVoucherBasicInformation
            = paymentVoucherDetail.getPaymentVoucherBasicInformation();

        Integer workFlowId = saveWorkFlowHistory(paymentVoucherBasicInformation.getWorkflowId(),
            paymentVoucherBasicInformation.getSysWorkFlowRule(), paymentVoucherBasicInformation.getRemark());

        paymentVoucherBasicInformation.setWorkflowId(workFlowId);
        Integer voucherId = savePaymentVoucher(paymentVoucherDetail);

        log.info("deletePaymentVoucher() end - VoucherId: " + voucherId);
        return voucherId;
    }

}
