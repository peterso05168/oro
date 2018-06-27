package hk.oro.iefas.ws.shroff.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.bank.dto.CurrencyDTO;
import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.*;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.ws.bank.repository.CurrencyRepository;
import hk.oro.iefas.ws.bank.service.CurrencyService;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.casemgt.service.CaseService;
import hk.oro.iefas.ws.shroff.repository.ChequeRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.ChequeDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.ChequeFileResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.IncomingChequeResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.OutgoingChequeResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.ChequePredicate;
import hk.oro.iefas.ws.shroff.service.ChequeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3257 $ $Date: 2018-06-22 10:09:33 +0800 (週五, 22 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Service
public class ChequeServiceImpl implements ChequeService {

    @Autowired
    private ChequeRepository chequeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private ChequeDTOAssembler chequeDTOAssembler;

    @Autowired
    private IncomingChequeResultDTOAssembler incomingChequeResultDTOAssembler;

    @Autowired
    private OutgoingChequeResultDTOAssembler outgoingChequeResultDTOAssembler;

    @Autowired
    private ChequeFileResultDTOAssembler chequeFileResultDTOAssembler;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CaseService caseService;

    @Override
    @Transactional(readOnly = true)
    public Page<IncomingChequeResultDTO> searchIncomingChequeByCriteria(SearchDTO<SearchIncomingChequeDTO> criteria) {
        log.info("searchIncomingChequeByCriteria() start - criteria : " + criteria);
        Page<ShrCheque> page;
        Page<IncomingChequeResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = chequeRepository.findAll(ChequePredicate.findIncomingChequeByCriteria(criteria.getCriteria()),
                    pageable);
                result = incomingChequeResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("searchIncomingChequeByCriteria end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ChequeDTO getIncomingChequeDetail(Integer chequeId) {
        log.info("getIncomingChequeDetail() start - chequeId : " + chequeId);
        ChequeDTO chequeDTO = null;
        ShrCheque cheque;
        if (chequeId != null) {
            cheque = chequeRepository.findOne(chequeId);
            chequeDTO = chequeDTOAssembler.toDTO(cheque);
        }
        log.info("getIncomingChequeDetail end - " + chequeDTO);
        return chequeDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveIncomingCheque(ChequeDTO chequeDTO) {
        log.info("saveIncomingCheque() start - chequeDTO : " + chequeDTO);
        if (chequeDTO != null && chequeDTO.getCurcy() != null) {
            CurrencyDTO currency = currencyService.findOne(chequeDTO.getCurcy().getCurcyId());
            chequeDTO.setCurcy(currency);
        }
        if (chequeDTO != null && chequeDTO.getCaseInfo() != null) {
            CaseDTO caseDTO = caseService.findOne(chequeDTO.getCaseInfo().getCaseId());
            chequeDTO.setCaseInfo(caseDTO);
        }
        ShrCheque shrCheque;
        Integer chequeId = null;
        if (chequeDTO != null) {
            shrCheque = DataUtils.copyProperties(chequeDTO, ShrCheque.class);
            chequeId = chequeRepository.save(shrCheque).getChequeId();
        }
        log.info("saveIncomingCheque end - " + chequeId);
        return chequeId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveOutgoingCheque(ChequeDTO chequeDTO) {
        log.info("saveOutgoingCheque() start - chequeDTO : " + chequeDTO);
        Integer chequeId = null;
        if (chequeDTO != null) {
            ShrCheque shrCheque = DataUtils.copyProperties(chequeDTO, ShrCheque.class);
            if (chequeDTO.getCurcy() != null) {
                shrCheque.setCurcy(currencyRepository.findOne(chequeDTO.getCurcy().getCurcyId()));
            }
            if (chequeDTO.getCaseInfo() != null) {
                shrCheque.setCaseInfo(caseRepository.findOne(chequeDTO.getCaseInfo().getCaseId()));
            }
            shrCheque = chequeRepository.save(shrCheque);
            chequeId = shrCheque.getChequeId();
        }

        log.info("saveOutgoingCheque end - " + chequeId);
        return chequeId;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByChequeNo(SearchIncomingChequeDTO searchIncomingChequeDTO) {
        log.info("existsByChequeNo start - chequeNo: " + searchIncomingChequeDTO);
        Boolean exists = null;
        if (searchIncomingChequeDTO != null) {
            exists = chequeRepository.existsByChequeNoAndChequeIdNot(
                searchIncomingChequeDTO.getChequeNumber().toString(), searchIncomingChequeDTO.getChequeId());
        }
        log.info("existsByChequeNo end - " + exists);
        return exists;
    }

    @Override
    public ChequeDTO getByChequeNo(String chequeNo) {
        log.info("getByChequeNo start - chequeNo: " + chequeNo);
        ChequeDTO result = null;
        ShrCheque shrCheque;
        if (chequeNo != null) {
            shrCheque = chequeRepository.getByChequeNo(chequeNo);
            if (shrCheque != null)
                result = chequeDTOAssembler.toDTO(shrCheque);
        }
        log.info("getByChequeNo end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OutgoingChequeResultDTO> searchOutgoingChequeByCriteria(SearchDTO<SearchOutgoingChequeDTO> criteria) {
        log.info("outgoingChequeByCriteria() start - criteria : " + criteria);
        Page<ShrCheque> page;
        Page<OutgoingChequeResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = chequeRepository.findAll(ChequePredicate.findOutgoingChequeByCriteria(criteria.getCriteria()),
                    pageable);
                result = outgoingChequeResultDTOAssembler.toResultDTO(page);
                List<OutgoingChequeResultDTO> outgoingChequeResultDTOS = result.getContent();
                for (OutgoingChequeResultDTO outgoingChequeResultDTO : outgoingChequeResultDTOS) {
                    if (outgoingChequeResultDTO.getVoucherNumber() == null) {
                        List<ChequeDTO> chequeDTOS
                            = getOutgoingChequeListByParentId(outgoingChequeResultDTO.getChequeId());
                        for (ChequeDTO child : chequeDTOS) {
                            if (child.getShrVcrInfo() != null) {
                                if (outgoingChequeResultDTO.getVoucherNumber() == null) {
                                    outgoingChequeResultDTO.setVoucherNumber(child.getShrVcrInfo().getVoucherNo());
                                } else if (child.getShrVcrInfo().getVoucherNo() != null) {
                                    outgoingChequeResultDTO.setVoucherNumber(outgoingChequeResultDTO.getVoucherNumber()
                                        + ",\n" + child.getShrVcrInfo().getVoucherNo());
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("searchOutgoingChequeByCriteria end - " + result);
        return result;
    }

    @Override
    public ChequeDTO getOutgoingChequeDetail(Integer outgoingChequeId) {
        log.info("getOutgoingChequeDetail() start - chequeId : " + outgoingChequeId);
        ChequeDTO chequeDTO = null;
        ShrCheque cheque;
        if (outgoingChequeId != null) {
            cheque = chequeRepository.findOne(outgoingChequeId);
            chequeDTO = chequeDTOAssembler.toDTO(cheque);
        }
        log.info("getOutgoingChequeDetail end - " + chequeDTO);
        return chequeDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer combineOutgoingCheque(Set<Integer> outgoingChequeIds) {
        log.info("combineOutgoingCheque start - outgoingChequeIds: " + outgoingChequeIds);
        Integer outgoingChequeId = null;
        List<ChequeDTO> childCheques;
        ChequeDTO tmp = null;
        ChequeDTO parent;
        if (outgoingChequeIds != null && outgoingChequeIds.size() != 0) {
            childCheques = new ArrayList<>();
            BigDecimal chequeAmount = BigDecimal.ZERO;
            for (Integer chequeId : outgoingChequeIds) {
                ChequeDTO current = this.getOutgoingChequeDetail(chequeId);
                chequeAmount = chequeAmount.add(current.getChequeAmount());
                if (tmp == null) {
                    tmp = current;
                }
                if ((!current.getChequeDate().equals(tmp.getChequeDate()))
                    || (!current.getPayee().equals(tmp.getPayee()))
                    || (!current.getCurcy().getCurcyName().equals(tmp.getCurcy().getCurcyName()))) {
                    return null;
                }
                childCheques.add(current);
            }
            parent = new ChequeDTO();
            parent.setChequeTypeId(ShroffConstant.CQ_COM_OUTGOING);
            parent.setChequeAmount(chequeAmount);
            parent.setChequeDate(tmp.getChequeDate());
            parent.setPayee(tmp.getPayee());
            parent.setCurcy(tmp.getCurcy());
            parent.setStatus(CoreConstant.CHEQUE_STATUS_DRAFT);
            outgoingChequeId = this.saveOutgoingCheque(parent);
            for (ChequeDTO chequeDTO : childCheques) {
                chequeDTO.setParentChequeId(outgoingChequeId);
                chequeDTO.setStatus(CoreConstant.CHEQUE_STATUS_COMBINED);
                this.saveOutgoingCheque(chequeDTO);
            }

        }
        log.info("combineOutgoingCheque end - " + outgoingChequeId);
        return outgoingChequeId;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChequeDTO> getOutgoingChequeListByParentId(Integer parentId) {
        log.info("getOutgoingChequeListByParentId start - parentId: " + parentId);
        List<ChequeDTO> result = null;
        Iterable<ShrCheque> shrCheques;
        if (parentId != null) {
            shrCheques = chequeRepository.findAll(ChequePredicate.getOutgoingChequeListByParentId(parentId));
            if (shrCheques != null) {
                result = new ArrayList<>();
                for (ShrCheque shrCheque : shrCheques) {
                    result.add(chequeDTOAssembler.toDTO(shrCheque));
                }
            }
        }
        log.info("getOutgoingChequeListByParentId end - " + result);
        return result;
    }

    @Override
    public Page<ChequeFileResultDTO> searchChequeForChequeFile(SearchDTO<SearchChequeFileDTO> criteria) {
        log.info("searchChequeForChequeFile start - criteria: " + criteria);
        Page<ShrCheque> page;
        Page<ChequeFileResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = chequeRepository.findAll(ChequePredicate.searchChequeForChequeFile(criteria.getCriteria()),
                    pageable);
                result = chequeFileResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("searchChequeForChequeFile end - " + result);
        return result;
    }
}
