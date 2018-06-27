package hk.oro.iefas.ws.shroff.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaCriteriaDTO;
import hk.oro.iefas.domain.shroff.dto.SearchTransferToGrOrBeaResultDTO;
import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaDTO;
import hk.oro.iefas.domain.shroff.entity.ShrTxfGrBea;
import hk.oro.iefas.ws.casemgt.repository.CaseAccountTypeRepository;
import hk.oro.iefas.ws.shroff.repository.TransferToGrOrBeaRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.SearchTransferToGrOrBeaResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.TransferToGrOrBeaDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.TransferToGrOrBeaPredicate;
import hk.oro.iefas.ws.shroff.service.TransferToGrOrBeaService;
import hk.oro.iefas.ws.system.service.SysSequenceService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3189 $ $Date: 2018-06-19 13:34:32 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class TransferToGrOrBeaServiceImpl implements TransferToGrOrBeaService {

    @Autowired
    private SysSequenceService sysSequenceService;

    @Autowired
    private TransferToGrOrBeaRepository transferToGrOrBeaRepository;

    @Autowired
    private CaseAccountTypeRepository caseAccountTypeRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private TransferToGrOrBeaDTOAssembler transferToGrOrBeaDTOAssembler;

    @Autowired
    private SearchTransferToGrOrBeaResultDTOAssembler searchTransferToGrOrBeaResultDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public TransferToGrOrBeaDTO getTransferToGrOrBeaDetail(Integer transferId) {
        log.info("getTransferToGrOrBeaDetail() start ");
        TransferToGrOrBeaDTO dto = null;
        if (transferId != null) {
            ShrTxfGrBea transfer = transferToGrOrBeaRepository.findOne(transferId);
            if (transfer != null) {
                dto = transferToGrOrBeaDTOAssembler.toDTO(transfer);
            }
        }
        log.info("getTransferToGrOrBeaDetail() end and TransferToGrOrBeaDTO = " + dto);
        return dto;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SearchTransferToGrOrBeaResultDTO>
        findByCriteria(SearchDTO<SearchTransferToGrOrBeaCriteriaDTO> criteria) {
        log.info("findByCriteria() start and criteria = " + criteria);
        Page<ShrTxfGrBea> transferList = null;
        Page<SearchTransferToGrOrBeaResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                transferList = transferToGrOrBeaRepository
                    .findAll(TransferToGrOrBeaPredicate.findByCriteria(criteria.getCriteria()), pageable);
                result = searchTransferToGrOrBeaResultDTOAssembler.toResultDTO(transferList);
                if (CommonUtils.isNotEmpty(result.getContent()) && criteria.getCriteria().getAccountTypeId() != null) {
                    String accountType = caseAccountTypeRepository.findOne(criteria.getCriteria().getAccountTypeId())
                        .getCaseAcTypeName();
                    for (SearchTransferToGrOrBeaResultDTO temp : result.getContent()) {
                        temp.setAccountType(accountType);
                    }
                    return new PageImpl<>(result.getContent(),
                        new PageRequest(result.getNumber(), result.getSize(), result.getSort()),
                        result.getTotalElements());
                }
            }
        }
        log.info("findByCriteria() end ");
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveTransferToGrOrBea(TransferToGrOrBeaDTO transferToGrOrBeaDTO) {
        log.info("saveTransferToGrOrBea() start - transferToGrOrBeaDTO : " + transferToGrOrBeaDTO);
        ShrTxfGrBea shrTxfGrBea = null;
        if (transferToGrOrBeaDTO != null) {
            shrTxfGrBea = DataUtils.copyProperties(transferToGrOrBeaDTO, ShrTxfGrBea.class);
            if (transferToGrOrBeaDTO.getVoucher() != null) {
                shrTxfGrBea.setVoucher(voucherRepository.findOne(transferToGrOrBeaDTO.getVoucher().getVoucherId()));
            } else {
                shrTxfGrBea.setVoucher(null);
            }
            if (CommonUtils.isBlank(shrTxfGrBea.getTransferNo())) {
                shrTxfGrBea.setTransferNo(sysSequenceService.generateTransferNo(ShroffConstant.TRANSFER_CODE));
            }
            shrTxfGrBea = transferToGrOrBeaRepository.save(shrTxfGrBea);
        }
        log.info("saveTransferToGrOrBea end - " + shrTxfGrBea.getTransferId());
        return shrTxfGrBea.getTransferId();
    }

}
