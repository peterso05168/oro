package hk.oro.iefas.web.ledger.voucherhandling.common.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.http.HttpHeaders;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.MimeTypeUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.common.vo.ActionVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.report.DownloadFileVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.SysAttachmentVO;
import hk.oro.iefas.domain.shroff.vo.VoucherAttachmentVO;
import hk.oro.iefas.domain.system.vo.SysWfInitialStatusVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseAccountClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysAttachmentClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysWfInitialStatusClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.SysWorkFlowRuleClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherAttachmentClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3199 $ $Date: 2018-06-19 17:20:31 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
public abstract class VoucherEditView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    protected AppResourceUtils appResourceUtils;

    @Inject
    protected SysWfInitialStatusClientService sysWfInitialStatusClientService;

    @Inject
    protected SysWorkFlowRuleClientService sysWorkFlowRuleClientService;

    @Inject
    protected AnalysisCodeClientService analysisCodeClientService;

    @Inject
    protected VoucherAttachmentClientService voucherAttachmentClientService;

    @Inject
    protected SysAttachmentClientService sysAttachmentClientService;

    @Inject
    protected VoucherClientService voucherClientService;

    @Inject
    protected CaseAccountClientService caseAccountClientService;

    protected SysWfInitialStatusVO sysWfInitialStatusVO;

    protected List<SysWorkFlowRuleVO> sysWorkFlowRuleVOs;

    public static final String ACCOUNT_NUMBER_COLUMN_NAME = "Account Number";
    public static final String ANALYSIS_CODE_COLUMN_NAME = "Analysis Code";
    public static final String NATURE_COLUMN_NAME = "Nature";
    public static final String AMOUNT_COLUMN_NAME = "Amount";
    public static final String PARTICULARS_COLUMN_NAME = "Particulars";
    public static final String AMOUNT_DR_COLUMN_NAME = "Amount(DR)";
    public static final String AMOUNT_CR_COLUMN_NAME = "Amount(CR)";
    public static final String CHEQUE_NUMBER_COLUMN_NAME = "Cheque Number";
    public static final String CHEQUE_DATE_COLUMN_NAME = "Cheque Date";
    public static final String NATURE_OF_RECEIPT_COLUMN_NAME = "Nature of Receipt";
    public static final String PAYER_NAME_COLUMN_NAME = "Payer Name";

    @Getter
    @Setter
    protected List<VoucherAttachmentVO> attachmentList;

    @Getter
    @Setter
    protected VoucherAttachmentVO attachment;

    @Getter
    @Setter
    protected Integer activeIndex = 0;

    @Getter
    @Setter
    protected UploadedFile accountFile;

    @Getter
    protected StreamedContent downloadFile;

    @Getter
    @Setter
    protected Integer voucherId;

    @Getter
    @Setter
    private Boolean isSubmitted = false;

    @Getter
    @Setter
    private Boolean isApproved = false;

    @Getter
    @Setter
    private Boolean isVerified = false;

    @Getter
    @Setter
    private Boolean saveSuccessed = false;

    @Getter
    @Setter
    protected Boolean isUploaded = false;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> statusVOs;

    @Getter
    @Setter
    private List<AnalysisCodeVO> analysisCodes;

    @Getter
    @Setter
    private AnalysisCodeVO selectedAnalysisCode = new AnalysisCodeVO();

    @Getter
    @Setter
    protected List<PaymentVoucherAccountItemVO> importPaymentItemList;

    @Getter
    @Setter
    protected List<JournalVoucherAccountItemVO> importJournalItemList;

    @Getter
    @Setter
    protected List<ReceiptVoucherAccountItemVO> importReceiptItemList;

    @Getter
    @Setter
    private ActionVO action;

    @PostConstruct
    private void init() {
        log.info("======VoucherEditView init======");
        statusVOs = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.VS.name());
        sysWorkFlowRuleVOs = sysWorkFlowRuleClientService.findByPrivilegeCode(getPrivilegeCode());
    }

    protected void initActionButton() {
        sysWfInitialStatusVO = sysWfInitialStatusClientService.findByPrivilegeCode(getPrivilegeCode());
        action = sysWorkFlowRuleClientService.findIntialAction(getPrivilegeCode());
    }

    protected SysWorkFlowRuleVO getAfterStatusByAction(String beforeStatus, String action) {
        log.info("getAfterStatusByAction() start");
        SysWorkFlowRuleVO after
            = sysWorkFlowRuleVOs.parallelStream().filter(item -> item.getAction().getCodeValue().equals(action)
                && item.getBeforeStatus().getCodeValue().equals(beforeStatus)).findFirst().get();
        log.info("getAfterStatusByAction() start");
        return after;
    }

    public List<AnalysisCodeVO> completeAnalysisCode(String query) {
        log.info("completeAnalysisCode() start");
        analysisCodes = analysisCodeClientService.findByAnalysisCode(query);
        if (CommonUtils.isNotEmpty(analysisCodes)) {
            String voucherTypeCode = getVoucherTypeCode();
            analysisCodes = analysisCodes.stream()
                .filter(item -> item.getVoucherType().getVoucherTypeCode().equals(voucherTypeCode))
                .collect(Collectors.toList());
        }
        log.info("completeAnalysisCode() end");
        return analysisCodes;
    }

    public abstract String getVoucherTypeCode();

    public abstract String getPrivilegeCode();

    protected String genCaseAccountNumberStr(List<String> str) {
        StringBuilder sb = new StringBuilder();
        sb.append(appResourceUtils.getMessageParam(MsgParamCodeConstant.CASE_ACCOUNT)).append(" ");
        str.stream().forEach(item -> {
            sb.append(item).append(", ");
        });
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    // Import Account
    protected String[] genImportHeader(String voucherType) {
        log.info("genImportHeader() start ");
        String[] header = null;
        switch (voucherType) {
            case ShroffConstant.VT_PAY:
                header = new String[4];
                header[0] = ACCOUNT_NUMBER_COLUMN_NAME;
                header[1] = ANALYSIS_CODE_COLUMN_NAME;
                header[2] = NATURE_COLUMN_NAME;
                header[3] = AMOUNT_COLUMN_NAME;
                break;
            case ShroffConstant.VT_JOU:
                header = new String[5];
                header[0] = ACCOUNT_NUMBER_COLUMN_NAME;
                header[1] = ANALYSIS_CODE_COLUMN_NAME;
                header[2] = PARTICULARS_COLUMN_NAME;
                header[3] = AMOUNT_DR_COLUMN_NAME;
                header[4] = AMOUNT_CR_COLUMN_NAME;
                break;
            case ShroffConstant.VT_REC:
                header = new String[7];
                header[0] = ACCOUNT_NUMBER_COLUMN_NAME;
                header[1] = ANALYSIS_CODE_COLUMN_NAME;
                header[2] = CHEQUE_NUMBER_COLUMN_NAME;
                header[3] = CHEQUE_DATE_COLUMN_NAME;
                header[4] = NATURE_OF_RECEIPT_COLUMN_NAME;
                header[5] = PAYER_NAME_COLUMN_NAME;
                header[5] = AMOUNT_COLUMN_NAME;
                break;
            default:
                break;
        }
        log.info("genImportHeader() end ");
        return header;
    }

    public void uploadAccountFile(String voucherType) {
        log.info("uploadAccountFile() start ");
        String[] header = genImportHeader(voucherType);
        importPaymentItemList = new ArrayList<>();
        importJournalItemList = new ArrayList<>();
        importReceiptItemList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream inputstream = this.accountFile.getInputstream();
            if (inputstream.available() > 0) {
                String fileName = this.accountFile.getFileName();
                if (!fileName.endsWith(CoreConstant.CSV_FILE_EXTENDS)) {
                    this.showUploadErrorMsg();
                    return;
                }
                CSVFormat format = CSVFormat.DEFAULT.withHeader(header);
                try {
                    reader = new BufferedReader(new InputStreamReader(inputstream));
                    String[] trueHeader = reader.readLine().split(",");

                    for (int i = 0; i < header.length; i++) {
                        if (!trueHeader[i].equals(header[i])) {
                            this.showUploadErrorMsg();
                            return;
                        }
                    }
                    Iterable<CSVRecord> records = format.parse(reader);
                    switch (voucherType) {
                        case ShroffConstant.VT_PAY:
                            handlePaymentItemList(records);
                            break;
                        case ShroffConstant.VT_JOU:
                            handleJournalItemList(records);
                            break;
                        case ShroffConstant.VT_REC:
                            handleReceiptItemList(records);
                            break;
                        default:
                            break;
                    }
                    isUploaded = true;
                    activeIndex = 1;
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    activeIndex = 1;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            this.showUploadErrorMsg();
            activeIndex = 1;
        } finally {
            IOUtils.closeQuietly(reader);
            activeIndex = 1;
        }
        log.info("uploadAccountFile() end ");
    }

    private void handlePaymentItemList(Iterable<CSVRecord> records) {
        log.info("handlePaymentItemList() start ");
        PaymentVoucherAccountItemVO csvItem = null;
        for (CSVRecord csv : records) {
            csvItem = new PaymentVoucherAccountItemVO();
            CaseAccountInfoVO caseAccount = new CaseAccountInfoVO();
            caseAccount.setCaseAcNumber(csv.get(ACCOUNT_NUMBER_COLUMN_NAME));
            csvItem.setAccount(caseAccount);
            csvItem.setAnalysisCode(csv.get(ANALYSIS_CODE_COLUMN_NAME));
            csvItem.setNature(csv.get(NATURE_COLUMN_NAME));
            csvItem.setAmount(CommonUtils.isNotBlank(csv.get(AMOUNT_COLUMN_NAME))
                ? new BigDecimal(csv.get(AMOUNT_COLUMN_NAME)) : null);
            importPaymentItemList.add(csvItem);
        }
        log.info("handlePaymentItemList() end ");
    }

    private void handleJournalItemList(Iterable<CSVRecord> records) {
        log.info("handleJournalItemList() start ");
        JournalVoucherAccountItemVO csvItem = null;
        for (CSVRecord csv : records) {
            csvItem = new JournalVoucherAccountItemVO();
            CaseAccountInfoVO caseAccount = new CaseAccountInfoVO();
            caseAccount.setCaseAcNumber(csv.get(ACCOUNT_NUMBER_COLUMN_NAME));
            csvItem.setAccount(caseAccount);
            csvItem.setAnalysisCode(csv.get(ANALYSIS_CODE_COLUMN_NAME));
            csvItem.setParticulars(csv.get(PARTICULARS_COLUMN_NAME));
            csvItem.setAmountDr(CommonUtils.isNotBlank(csv.get(AMOUNT_DR_COLUMN_NAME))
                ? new BigDecimal(csv.get(AMOUNT_DR_COLUMN_NAME)) : null);
            csvItem.setAmountCr(CommonUtils.isNotBlank(csv.get(AMOUNT_CR_COLUMN_NAME))
                ? new BigDecimal(csv.get(AMOUNT_CR_COLUMN_NAME)) : null);
            importJournalItemList.add(csvItem);
        }
        log.info("handleJournalItemList() end ");
    }

    private void handleReceiptItemList(Iterable<CSVRecord> records) {
        log.info("handleReceiptItemList() start ");
        ReceiptVoucherAccountItemVO csvItem = null;
        for (CSVRecord csv : records) {
            csvItem = new ReceiptVoucherAccountItemVO();

            String accountNumber = csv.get(ACCOUNT_NUMBER_COLUMN_NAME);
            CaseAccountInfoVO caseAccountInfoVO = this.caseAccountClientService.findByAccountNumber(accountNumber);
            csvItem.setAccount(caseAccountInfoVO);
            csvItem.setAnalysisCode(csv.get(ANALYSIS_CODE_COLUMN_NAME));
            csvItem.setChequeNo(csv.get(CHEQUE_NUMBER_COLUMN_NAME));
            try {
                csvItem.setChequeDate(
                    new SimpleDateFormat(appResourceUtils.getDateFormat()).parse(csv.get(CHEQUE_DATE_COLUMN_NAME)));
            } catch (ParseException e) {
                log.error(e.getMessage(), e);
                Messages.addGlobalError(
                    String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_DATE_PATTERN_ERROR),
                        appResourceUtils.getMessageParam(MsgParamCodeConstant.IMPORTED_RECORD)));
                return;
            }
            csvItem.setNature(csv.get(NATURE_COLUMN_NAME));
            csvItem.setPayerName(csv.get(PAYER_NAME_COLUMN_NAME));
            BigDecimal amount = CommonUtils.isNotBlank(csv.get(AMOUNT_COLUMN_NAME))
                ? new BigDecimal(csv.get(AMOUNT_COLUMN_NAME)) : null;
            csvItem.setVoucherAmount(amount);
            csvItem.setStatus(CoreConstant.STATUS_ACTIVE);

            this.importReceiptItemList.add(csvItem);
        }
        log.info("handleReceiptItemList() end ");
    }

    public void showUploadErrorMsg() {
        Messages.addError(null, "Invalid File.");
    }

    public abstract Boolean validateConfirmImportAccount();

    public abstract void confirmImportAccount();

    public void cancelImport() {
        log.info("cancelImport() start ");
        accountFile = null;
        importPaymentItemList = new ArrayList<>();
        importJournalItemList = new ArrayList<>();
        importReceiptItemList = new ArrayList<>();
        isUploaded = false;
        log.info("cancelImport() start ");
    }

    public void downloadTemplate(String voucherType) throws Exception {
        log.info("downloadTemplate() start ");
        String[] header = genImportHeader(voucherType);
        List<String> headerRecord = new ArrayList<String>();
        for (int i = 0; i < header.length; i++) {
            headerRecord.add(header[i]);
        }
        DownloadFileVO downloadFileVO = voucherClientService.downloadImportTemplate(headerRecord);
        if (downloadFileVO != null) {
            String fileRealName = downloadFileVO.getFileName();
            fileRealName = fileRealName.replace(CoreConstant.CSV_FILE_EXTENDS,
                "(" + voucherType + ")" + CoreConstant.CSV_FILE_EXTENDS);
            HttpServletResponse response
                = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="
                + new String(fileRealName.getBytes(CoreConstant.UTF_8), CoreConstant.ISO_8859_1));
            response.setContentType(MimeTypeUtils.FileExtension.CSV.name());
            ServletOutputStream outputStream = response.getOutputStream();

            if (CommonUtils.isNotEmpty(downloadFileVO.getFileResult())) {
                outputStream.write(downloadFileVO.getFileResult());
            }
            outputStream.close();
        }
        log.info("downloadTemplate() end ");
    }

    // support doc
    public void downloadDoc() throws Exception {
        log.info("downloadDoc() start");
        if (attachment != null) {
            SysAttachmentVO file = sysAttachmentClientService.getSysAttachmentDetail(attachment.getAttachmentId());

            HttpServletResponse response
                = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="
                + new String(attachment.getFileName().getBytes(CoreConstant.UTF_8), CoreConstant.ISO_8859_1));
            response.setContentType(MimeTypeUtils.FileExtension.PDF.name());
            ServletOutputStream outputStream = response.getOutputStream();

            if (file != null && CommonUtils.isNotEmpty(file.getContent())) {
                outputStream.write(file.getContent());
            }
            outputStream.close();
        }
        log.info("downloadDoc() end");

    }

    public void deleteDoc() {
        log.info("deleteDoc() start");
        if (attachment != null) {
            attachment.setStatus(CoreConstant.STATUS_DELETE);
            voucherAttachmentClientService.deleteVoucherAttachment(attachment);
            refreshDocForm();
        }
        log.info("deleteDoc() end");
    }

    public void refreshDocForm() {
        log.info("refreshDocForm() start");
        attachmentList = voucherAttachmentClientService.findVoucherAttachmentByVoucher(voucherId);
        log.info("refreshDocForm() end");
    }

}
