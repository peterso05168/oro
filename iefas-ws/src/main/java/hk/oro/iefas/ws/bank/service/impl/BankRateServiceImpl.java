package hk.oro.iefas.ws.bank.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.MimeTypeUtils;
import hk.oro.iefas.domain.bank.dto.BankBasicDTO;
import hk.oro.iefas.domain.bank.dto.BankDepositTypeDTO;
import hk.oro.iefas.domain.bank.dto.BankRateDTO;
import hk.oro.iefas.domain.bank.dto.CreateUploadBankRateDTO;
import hk.oro.iefas.domain.bank.dto.CurrencyBasicInfoDTO;
import hk.oro.iefas.domain.bank.dto.DailyBankRateDTO;
import hk.oro.iefas.domain.bank.dto.DailyBankRateListDTO;
import hk.oro.iefas.domain.bank.dto.SearchBankAccountInfoCriteriaDTO;
import hk.oro.iefas.domain.bank.dto.SearchUploadBankRateCriteriaDTO;
import hk.oro.iefas.domain.bank.dto.UploadBankRateDTO;
import hk.oro.iefas.domain.bank.entity.BankDepositType;
import hk.oro.iefas.domain.bank.entity.BankInfo;
import hk.oro.iefas.domain.bank.entity.BankRate;
import hk.oro.iefas.domain.bank.entity.BankRateItem;
import hk.oro.iefas.domain.bank.entity.Currency;
import hk.oro.iefas.domain.report.DownloadFileDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.bank.repository.BankDepositTypeRepository;
import hk.oro.iefas.ws.bank.repository.BankInfoRepository;
import hk.oro.iefas.ws.bank.repository.BankRateItemRepository;
import hk.oro.iefas.ws.bank.repository.BankRateRepository;
import hk.oro.iefas.ws.bank.repository.CurrencyRepository;
import hk.oro.iefas.ws.bank.repository.assembler.BankRateDTOAssembler;
import hk.oro.iefas.ws.bank.repository.assembler.DailyBankRateDTOAssembler;
import hk.oro.iefas.ws.bank.repository.predicate.BankAccountPredicate;
import hk.oro.iefas.ws.bank.repository.predicate.BankDepositTypePredicate;
import hk.oro.iefas.ws.bank.repository.predicate.BankInfoPredicate;
import hk.oro.iefas.ws.bank.repository.predicate.BankRateItemPredicate;
import hk.oro.iefas.ws.bank.repository.predicate.BankRatePredicate;
import hk.oro.iefas.ws.bank.repository.predicate.CurrencyPredicate;
import hk.oro.iefas.ws.bank.service.BankRateService;

/**
 * @version $Revision: 3030 $ $Date: 2018-06-11 18:10:24 +0800 (週一, 11 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Service
public class BankRateServiceImpl implements BankRateService {

    @Autowired
    private BankRateRepository bankRateRepository;

    @Autowired
    private BankRateItemRepository bankRateItemRepository;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private BankDepositTypeRepository bankDepositTypeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<BankRateDTO> searchUploadBankRateList(SearchDTO<SearchUploadBankRateCriteriaDTO> searchCriteria) {
        Page<BankRateDTO> resultDTO = null;
        if (searchCriteria != null) {
            PageRequest pageable = null;
            if (searchCriteria.getPage() != null) {
                pageable = searchCriteria.getPage().toPageable();
            }
            SearchUploadBankRateCriteriaDTO criteria = searchCriteria.getCriteria();
            if (criteria != null) {
                Page<BankRate> page = bankRateRepository.findAll(BankRatePredicate.findAll(criteria), pageable);
                if (page != null) {
                    resultDTO = BankRateDTOAssembler.toResultDTO(page);
                }
            }
        }

        return resultDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer createUploadBankRate(CreateUploadBankRateDTO createUploadBankRateDTO) {
        if (createUploadBankRateDTO != null && createUploadBankRateDTO.getUploadDate() != null
            && createUploadBankRateDTO.getCurrencyBasicInfo() != null) {
            BankRate bankRate = new BankRate();
            bankRate.setInvestDate(createUploadBankRateDTO.getUploadDate());
            bankRate.setStatus(CoreConstant.STATUS_ACTIVE);

            Currency currency = currencyRepository
                .findOne(CurrencyPredicate.findOne(createUploadBankRateDTO.getCurrencyBasicInfo().getCurcyId()));
            bankRate.setCurrency(currency);
            bankRate = bankRateRepository.save(bankRate);
            return bankRate.getBankRateId();
        }

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public UploadBankRateDTO searchUploadBankRate(Integer bankRateId) {
        if (bankRateId != null && bankRateId > 0) {
            BankRate bankRate = bankRateRepository.findOne(BankRatePredicate.searchBankRate(bankRateId));
            if (bankRate != null) {
                UploadBankRateDTO uploadBankRateDTO = new UploadBankRateDTO();
                uploadBankRateDTO.setBankRateId(bankRateId);
                uploadBankRateDTO.setInvestmentDate(bankRate.getInvestDate());
                uploadBankRateDTO.setStatus(bankRate.getStatus());
                Currency currency = bankRate.getCurrency();
                if (currency != null) {
                    uploadBankRateDTO.setCurrencyBasicInfo(new CurrencyBasicInfoDTO(currency.getCurcyId(),
                        currency.getCurcyName(), currency.getCurcyCode()));
                }
                List<DailyBankRateListDTO> dailyBankRateLists = uploadBankRateDTO.getDailyBankRateLists();

                Iterable<BankInfo> bankInfos = bankInfoRepository.findAll(BankInfoPredicate.findActiveBank(),
                    BankInfoPredicate.orderByBankName());
                if (!IterableUtils.isEmpty(bankInfos)) {
                    DailyBankRateListDTO dailyBankRateListDTO = null;
                    for (BankInfo bankInfo : bankInfos) {
                        Integer bankInfoId = bankInfo.getBankInfoId();
                        Iterable<BankRateItem> bankRateItems = bankRateItemRepository.findAll(
                            BankRateItemPredicate.searchByBankRateAndBankInfo(bankRateId, bankInfoId),
                            BankRateItemPredicate.orderById());
                        if (!IterableUtils.isEmpty(bankRateItems)) {
                            dailyBankRateListDTO = new DailyBankRateListDTO();
                            dailyBankRateListDTO.setBankBasic(new BankBasicDTO(bankInfoId, bankInfo.getBankName()));

                            List<DailyBankRateDTO> dailyBankRateDTOs = DailyBankRateDTOAssembler.toDTOs(bankRateItems);
                            dailyBankRateListDTO.setDailyBankRates(dailyBankRateDTOs);
                            dailyBankRateLists.add(dailyBankRateListDTO);
                        }

                    }
                }

                return uploadBankRateDTO;
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveDailyBankRateList(UploadBankRateDTO uploadBankRateDTO) {
        if (uploadBankRateDTO != null) {
            BankRate bankRate
                = bankRateRepository.findOne(BankRatePredicate.searchBankRate(uploadBankRateDTO.getBankRateId()));
            if (bankRate != null) {
                String uploadBankRateStatus = uploadBankRateDTO.getStatus();
                bankRate.setStatus(uploadBankRateStatus);
                bankRate.setVersionNo(uploadBankRateDTO.getVersionNo());
                bankRateRepository.save(bankRate);
                Integer bankRateId = bankRate.getBankRateId();
                Iterable<BankInfo> bankInfos = bankInfoRepository.findAll(BankInfoPredicate.findActiveBank());
                Map<Integer, Iterable<BankRateItem>> bankRateItemsMap = new HashMap<>();
                Iterable<BankRateItem> bankRateItems = null;
                if (!IterableUtils.isEmpty(bankInfos)) {
                    for (BankInfo bankInfo : bankInfos) {
                        Integer bankInfoId = bankInfo.getBankInfoId();
                        bankRateItems = bankRateItemRepository.findAll(
                            BankRateItemPredicate.searchByBankRateAndBankInfo(bankRateId, bankInfoId),
                            BankRateItemPredicate.orderById());
                        if (!IterableUtils.isEmpty(bankRateItems)) {
                            bankRateItemsMap.put(bankInfoId, bankRateItems);
                        }

                    }
                }

                if (CoreConstant.STATUS_ACTIVE.equals(uploadBankRateStatus)) {
                    Currency currency = bankRate.getCurrency();
                    List<DailyBankRateListDTO> dailyBankRateLists = uploadBankRateDTO.getDailyBankRateLists();
                    Iterable<BankDepositType> bankDepositTypeList
                        = bankDepositTypeRepository.findAll(BankDepositTypePredicate.findAll());
                    if (CommonUtils.isNotEmpty(dailyBankRateLists) && !IterableUtils.isEmpty(bankDepositTypeList)) {
                        Map<Integer, BankDepositType> bankDepositTypeMap = new HashMap<>();
                        bankDepositTypeList.forEach(
                            depositType -> bankDepositTypeMap.put(depositType.getBankDepositTypeId(), depositType));

                        BankBasicDTO bankBasic = null;
                        BankInfo bankInfo = null;
                        BankDepositType bankDepositType = null;
                        BankRateItem bankRateItem = null;
                        Integer bankInfoId = null;

                        for (DailyBankRateListDTO dailyBankRateListDTO : dailyBankRateLists) {
                            bankBasic = dailyBankRateListDTO.getBankBasic();
                            if (bankBasic != null && bankBasic.getBankInfoId() != null) {
                                bankInfo
                                    = bankInfoRepository.findOne(BankInfoPredicate.findOne(bankBasic.getBankInfoId()));
                                bankInfoId = bankInfo.getBankInfoId();
                            }
                            bankRateItems = bankRateItemsMap.remove(bankInfoId);
                            List<DailyBankRateDTO> dailyBankRates = dailyBankRateListDTO.getDailyBankRates();
                            if (IterableUtils.isEmpty(bankRateItems)) {
                                for (DailyBankRateDTO dailyBankRateDTO : dailyBankRates) {
                                    bankDepositType = bankDepositTypeMap
                                        .get(dailyBankRateDTO.getBankDepositType().getBankDepositTypeId());
                                    bankRateItem = new BankRateItem(null, bankInfo, bankDepositType, currency, bankRate,
                                        bankRate.getInvestDate(), dailyBankRateDTO.getPercentage(),
                                        CoreConstant.STATUS_ACTIVE);

                                    bankRateItemRepository.save(bankRateItem);
                                }
                            } else {
                                int index = 0;
                                BankRateItem next = null;
                                for (Iterator<BankRateItem> iterator = bankRateItems.iterator(); iterator.hasNext();) {
                                    next = iterator.next();
                                    next.setPercentage(dailyBankRates.get(index++).getPercentage());
                                    bankRateItemRepository.save(next);
                                }
                            }

                        }

                        if (CommonUtils.isNotEmpty(bankRateItemsMap)) {
                            ArrayList<Iterable<BankRateItem>> list = new ArrayList<>(bankRateItemsMap.values());
                            for (Iterable<BankRateItem> iterable : list) {
                                iterable.forEach(bri -> {
                                    bri.setStatus(CoreConstant.STATUS_INACTIVE);
                                    bankRateItemRepository.save(bri);
                                });
                            }
                        }

                    }
                    return bankRateId;
                } else {
                    bankRate.setStatus(CoreConstant.STATUS_INACTIVE);
                    bankRateRepository.save(bankRate);
                    bankRateItems = bankRateItemRepository.findAll(BankRateItemPredicate.searchByBankRate(bankRateId));
                    if (!IterableUtils.isEmpty(bankRateItems)) {
                        bankRateItems.forEach(item -> {
                            item.setStatus(CoreConstant.STATUS_INACTIVE);
                            bankRateItemRepository.save(item);
                        });
                    }
                    return bankRateId;
                }
            }

        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean createUploadBankRateValidate(Date investDate) {
        if (investDate != null) {
            boolean exists = bankRateRepository.exists(BankRatePredicate.searchBankRateByInvestDate(investDate));
            return !exists;
        }
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BankDepositTypeDTO> findBankDepositTypeList() {
        List<BankDepositTypeDTO> bankDepositTypeDTOs = bankDepositTypeRepository
            .findAll(BankDepositTypePredicate.getQBean(), BankDepositTypePredicate.findAll());
        return bankDepositTypeDTOs;
    }

    private static final String BANK_COLUMN_NAME = "Bank";
    private static final String LINE_END = "\r\n";
    private static final String BANK_RATE_FILE_NAME = "Bank Rate.csv";

    @Transactional(readOnly = true)
    @Override
    public DownloadFileDTO downloadBankRateTemplate() {
        StringBuilder sb = new StringBuilder();
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_END);
        try {
            List<BankDepositTypeDTO> bankDepositTypeList = this.findBankDepositTypeList();
            csvFilePrinter = new CSVPrinter(sb, csvFileFormat);
            List<String> headerRecords = new ArrayList<String>();
            headerRecords.add(BANK_COLUMN_NAME);
            if (CommonUtils.isNotEmpty(bankDepositTypeList)) {
                for (BankDepositTypeDTO bankDepositTypeDTO : bankDepositTypeList) {
                    headerRecords.add(bankDepositTypeDTO.getDepositName());
                }
            }
            csvFilePrinter.printRecord(headerRecords.toArray());

            SearchBankAccountInfoCriteriaDTO criteriaDTO = new SearchBankAccountInfoCriteriaDTO();
            criteriaDTO.setStatus(CoreConstant.STATUS_ACTIVE);
            List<BankInfo> bankInfos = (List<BankInfo>)bankInfoRepository
                .findAll(BankAccountPredicate.findByCriteria(criteriaDTO), BankInfoPredicate.orderByBankName());
            if (CommonUtils.isNotEmpty(bankInfos)) {
                for (BankInfo bankInfo : bankInfos) {
                    Object[] strings = new Object[bankDepositTypeList.size() + 1];
                    strings[0] = bankInfo.getBankName();
                    csvFilePrinter.printRecord(strings);
                }
            }
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
        downloadFileDTO.setFileName(BANK_RATE_FILE_NAME);
        downloadFileDTO.setFileResult(sb.toString().getBytes());
        return downloadFileDTO;
    }

}
