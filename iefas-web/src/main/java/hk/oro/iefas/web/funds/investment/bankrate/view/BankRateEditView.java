package hk.oro.iefas.web.funds.investment.bankrate.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.vo.BankBasicVO;
import hk.oro.iefas.domain.bank.vo.BankDepositTypeVO;
import hk.oro.iefas.domain.bank.vo.BankInfoVO;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.bank.vo.DailyBankRateListVO;
import hk.oro.iefas.domain.bank.vo.DailyBankRateVO;
import hk.oro.iefas.domain.bank.vo.UploadBankRateVO;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.investment.bankrate.service.BankRateClientService;
import hk.oro.iefas.web.funds.maintenance.bankaccount.service.BankClientService;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class BankRateEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = -2073489645540766996L;

    @Inject
    private BankRateClientService bankRateClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private BankClientService bankClientService;

    @Getter
    @Setter
    private UploadBankRateVO uploadBankRate;

    @Getter
    @Setter
    private DailyBankRateListVO dailyBankRateListVO;

    @Getter
    private Map<String, String> currencyTypes = new LinkedHashMap<>();

    @Getter
    private Map<String, String> bankInfos = new LinkedHashMap<>();

    private List<BankInfoVO> bankInfoVOs;

    private Map<Integer, BankBasicVO> bankBasicMap = new HashMap<>();

    private List<BankDepositTypeVO> bankDepositTypeVOs;

    @Setter
    @Getter
    private UploadedFile uploadedFile;

    @Setter
    @Getter
    private Integer bankRateId;

    @Setter
    @Getter
    private Integer bankInfoId;

    @Getter
    private StreamedContent downFile;

    @Setter
    @Getter
    private Integer activeIndex = 0;

    @PostConstruct
    private void init() {
        activeIndex = 0;
        List<CurrencyBasicInfoVO> currencyBasicInfoVOs = currencyClientService.findAll();
        if (CommonUtils.isNotEmpty(currencyBasicInfoVOs)) {
            this.currencyTypes.clear();
            currencyBasicInfoVOs.forEach(currency -> this.currencyTypes.put(currency.getCurcyName(),
                String.valueOf(currency.getCurcyId().intValue())));
        }
        this.bankInfoVOs = bankClientService.findAll();
        if (CommonUtils.isNotEmpty(this.bankInfoVOs)) {
            this.bankInfos.clear();
            this.bankInfoVOs.forEach(bank -> {
                Integer bankInfoId = bank.getBankInfoId();
                this.bankInfos.put(bank.getBankName(), String.valueOf(bankInfoId.intValue()));
                this.bankBasicMap.put(bankInfoId, new BankBasicVO(bankInfoId, bank.getBankName()));
            });

        }

        this.initDailyBankRateListVO();

        bankRateId = Faces.getRequestParameter("bankRateId", Integer.class);
        uploadBankRate = bankRateClientService.searchUploadBankRate(bankRateId);
        if (uploadBankRate != null) {
            List<DailyBankRateListVO> dailyBankRateLists = uploadBankRate.getDailyBankRateLists();
            if (CommonUtils.isEmpty(dailyBankRateLists)) {

            }
        }
    }

    public void initDailyBankRateListVO() {
        this.bankDepositTypeVOs = bankRateClientService.findBankDepositTypeList();
    }

    private static final String EDIT_DIALOG = "editDialog";

    public void addDailyBankRateListVO() {
        BankBasicVO bankBasicVO = this.bankBasicMap.get(this.bankInfoId);
        dailyBankRateListVO.setBankBasic(bankBasicVO);
        List<DailyBankRateListVO> dailyBankRateLists = this.uploadBankRate.getDailyBankRateLists();

        boolean flag = true;
        if (CommonUtils.isNotEmpty(dailyBankRateLists)) {
            int size = dailyBankRateLists.size();
            DailyBankRateListVO dailyBankRateListVOInList = null;
            for (int i = 0; i < size; i++) {
                dailyBankRateListVOInList = dailyBankRateLists.get(i);
                if (dailyBankRateListVOInList.getBankBasic().getBankInfoId().equals(this.bankInfoId)) {
                    dailyBankRateLists.set(i, this.dailyBankRateListVO);
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            dailyBankRateLists.add(dailyBankRateListVO);
        }
        hideComponent(EDIT_DIALOG);

    }

    public void editDailyBankRateListVO(DailyBankRateListVO dailyBankRateListVO) {
        this.dailyBankRateListVO = DataUtils.copyProperties(dailyBankRateListVO, DailyBankRateListVO.class);
        this.bankInfoId = this.dailyBankRateListVO.getBankBasic().getBankInfoId();
    }

    public void createNewDailyBankRateListVO() {
        this.dailyBankRateListVO = new DailyBankRateListVO();
        List<DailyBankRateVO> dailyBankRates = this.dailyBankRateListVO.getDailyBankRates();
        if (CommonUtils.isNotEmpty(bankDepositTypeVOs)) {
            for (BankDepositTypeVO bankDepositTypeVO : bankDepositTypeVOs) {
                DailyBankRateVO dailyBankRateVO = new DailyBankRateVO(null, bankDepositTypeVO, null, null);
                dailyBankRates.add(dailyBankRateVO);
            }
        }
        this.bankInfoId = 0;
    }

    public void deleteDailyBankRateListVO(DailyBankRateListVO dailyBankRateListVO) {
        List<DailyBankRateListVO> dailyBankRateLists = this.uploadBankRate.getDailyBankRateLists();
        if (CommonUtils.isNotEmpty(dailyBankRateLists)) {
            int size = dailyBankRateLists.size();
            DailyBankRateListVO dailyBankRateListVOInList = null;
            Integer bankInfoId2 = dailyBankRateListVO.getBankBasic().getBankInfoId();
            for (int i = 0; i < size; i++) {
                dailyBankRateListVOInList = dailyBankRateLists.get(i);
                if (dailyBankRateListVOInList.getBankBasic().getBankInfoId().equals(bankInfoId2)) {
                    dailyBankRateLists.remove(i);
                }
            }

        }

    }

    private static final String BANK_COLUMN_NAME = "Bank";
    private static final String CSV_FILE_EXTENDS = ".csv";

    public void uploadBankRateTemplate() {
        this.bankDepositTypeVOs = bankRateClientService.findBankDepositTypeList();
        CurrencyBasicInfoVO currencyBasicInfo = this.uploadBankRate.getCurrencyBasicInfo();
        int depositNumber = this.bankDepositTypeVOs.size();
        String[] headerRecords = new String[depositNumber + 1];
        int i = 0;
        headerRecords[i] = BANK_COLUMN_NAME;
        if (CommonUtils.isNotEmpty(bankDepositTypeVOs)) {
            for (BankDepositTypeVO bankDepositTypeVO : bankDepositTypeVOs) {
                i++;
                headerRecords[i] = bankDepositTypeVO.getDepositName();
            }
        }

        BufferedReader reader = null;

        Map<Integer, DailyBankRateListVO> dailyBankRateListVOMap = new LinkedHashMap<>();
        List<DailyBankRateListVO> dailyBankRateLists = this.uploadBankRate.getDailyBankRateLists();
        if (CommonUtils.isNotEmpty(dailyBankRateLists)) {
            dailyBankRateLists.forEach(dailyBankRateList -> dailyBankRateListVOMap
                .put(dailyBankRateList.getBankBasic().getBankInfoId(), dailyBankRateList));
        }

        try {
            InputStream inputstream = this.uploadedFile.getInputstream();
            if (inputstream.available() > 0) {
                String fileName = this.uploadedFile.getFileName();
                if (!fileName.endsWith(CSV_FILE_EXTENDS)) {
                    this.showErrorMsg();
                    return;
                }
                CSVFormat format = CSVFormat.DEFAULT.withHeader(headerRecords).withSkipHeaderRecord();
                try {
                    reader = new BufferedReader(new InputStreamReader(inputstream));
                    Iterable<CSVRecord> records = format.parse(reader);
                    for (CSVRecord record : records) {
                        DailyBankRateListVO dailyBankRateListVO = new DailyBankRateListVO();
                        String bankName = record.get(BANK_COLUMN_NAME);
                        BankInfoVO bankInfoVO = bankClientService.findByBankName(bankName);
                        if (bankInfoVO == null) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "Bank Name '" + bankName + "' not exist.", null));
                            Faces.renderResponse();
                            return;
                        } else {
                            dailyBankRateListVO
                                .setBankBasic(new BankBasicVO(bankInfoVO.getBankInfoId(), bankInfoVO.getBankName()));
                        }

                        List<DailyBankRateVO> dailyBankRates = new ArrayList<>();
                        for (int j = 1; j < headerRecords.length; j++) {
                            String value = record.get(headerRecords[j]);
                            BigDecimal bdValue;
                            if (value != null && !"".equals(value.trim())) {
                                try {
                                    bdValue = new BigDecimal(value);
                                } catch (Exception e) {
                                    FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid number.", null));
                                    Faces.renderResponse();
                                    return;
                                }
                            } else {
                                bdValue = new BigDecimal(0);
                            }
                            int length = value.length();
                            if ((value.contains(".") && length <= 17
                                && value.substring(value.indexOf(".") + 1).length() <= 4)
                                || (!value.contains(".") && length <= 16)) {
                                DailyBankRateVO dailyBankRateVO = new DailyBankRateVO(null,
                                    this.bankDepositTypeVOs.get(j - 1), currencyBasicInfo, bdValue);
                                dailyBankRates.add(dailyBankRateVO);
                            }
                        }
                        dailyBankRateListVO.setDailyBankRates(dailyBankRates);

                        if (dailyBankRateListVO != null) {
                            dailyBankRateListVOMap.put(dailyBankRateListVO.getBankBasic().getBankInfoId(),
                                dailyBankRateListVO);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                dailyBankRateLists.clear();
                dailyBankRateLists.addAll(dailyBankRateListVOMap.values());
                this.uploadBankRate.setDailyBankRateLists(dailyBankRateLists);

                this.activeIndex = 0;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            this.showErrorMsg();
        } finally {
            IOUtils.closeQuietly(reader);
        }

    }

    private void showErrorMsg() {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid File.", null));
        Faces.renderResponse();
    }

    public void saveDailyBankRateList() {
        bankRateId = bankRateClientService.saveDailyBankRateList(uploadBankRate);
        if (bankRateId != null && bankRateId > 0) {
            Messages.addGlobalInfo("Save Successfully!");
            uploadBankRate = bankRateClientService.searchUploadBankRate(bankRateId);
        }
    }

}
