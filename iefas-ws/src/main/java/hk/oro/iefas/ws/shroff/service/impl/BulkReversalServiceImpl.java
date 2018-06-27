package hk.oro.iefas.ws.shroff.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.BulkReversalDTO;
import hk.oro.iefas.domain.shroff.dto.BulkReversalItemDTO;
import hk.oro.iefas.domain.shroff.dto.BulkReversalResultDTO;
import hk.oro.iefas.domain.shroff.dto.ChequeDTO;
import hk.oro.iefas.domain.shroff.dto.GenerateBulkReversalDTO;
import hk.oro.iefas.domain.shroff.dto.SearchBulkReversalDTO;
import hk.oro.iefas.domain.shroff.entity.ShrBulkRvl;
import hk.oro.iefas.domain.shroff.entity.ShrBulkRvlItem;
import hk.oro.iefas.domain.shroff.entity.ShrCheque;
import hk.oro.iefas.domain.shroff.entity.Voucher;
import hk.oro.iefas.ws.shroff.repository.BulkReversalItemRepository;
import hk.oro.iefas.ws.shroff.repository.BulkReversalRepository;
import hk.oro.iefas.ws.shroff.repository.ChequeRepository;
import hk.oro.iefas.ws.shroff.repository.VoucherRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.BulkReversalDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.ChequeDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.BulkReversePredicate;
import hk.oro.iefas.ws.shroff.repository.predicate.ChequePredicate;
import hk.oro.iefas.ws.shroff.service.BulkReversalService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
@Slf4j
@Service
public class BulkReversalServiceImpl implements BulkReversalService {

    @Autowired
    private BulkReversalRepository bulkReversalRepository;
    
    @Autowired
    private BulkReversalItemRepository bulkReversalItemRepository;
    
    @Autowired
    private ChequeRepository chequeRepository;
    
    @Autowired
    private ChequeDTOAssembler chequeDTOAssembler;
    
    @Autowired
    private VoucherRepository voucherRepository;
    
    @Override
    @Transactional(readOnly=true)
    public BulkReversalResultDTO searchBulkReversal(SearchBulkReversalDTO criteria) {
		log.info("searchBulkReversal() start - criteria: " + criteria);
		BulkReversalResultDTO result = new BulkReversalResultDTO();
		List<BulkReversalDTO> bulkReversalList = new ArrayList<>();
		bulkReversalList = bulkReversalRepository.findAll(BulkReversalDTOAssembler.toDTO(), BulkReversePredicate.findByCriteria(criteria));
		result.setBulkReversalList(bulkReversalList);
		log.info("searchBulkReversal() end - " + result);
		return result;
	}
    
    @Override
    @Transactional(readOnly=true)
    public BulkReversalDTO loadBulkReversalDetail(Long bulkReversalId) {
		log.info("loadBulkReversalDetail() start - bul"
				+ "kReversalId: " + bulkReversalId);
		ShrBulkRvl selected  = bulkReversalRepository.findOne(BulkReversePredicate.findById(bulkReversalId));
		BulkReversalDTO result = null;
		if (selected != null) {
			result = BulkReversalDTOAssembler.toDTO(selected);
		}
		log.info("loadBulkReversalDetail() end - " + result);
		return result;
	}
    
    @Override
    @Transactional(readOnly=true)
    public BulkReversalDTO generateBulkReversal(GenerateBulkReversalDTO generateBulkReversalDTO) {
		log.info("generateBulkReversal() start - generateBulkReversalDTO: " + generateBulkReversalDTO);
		BulkReversalDTO result = new BulkReversalDTO();
		Date cutOffDate = generateBulkReversalDTO.getCutOffDate();
		List<BulkReversalItemDTO> itemList = new ArrayList<>();
		List<ShrCheque> chequeList = (List<ShrCheque>) chequeRepository.findAll(ChequePredicate.findOutgoingChequeForBulkReversal(cutOffDate));
//		List<ShrCheque> chequeList = (List<ShrCheque>) chequeRepository.findAll();
		if (chequeList != null && !chequeList.isEmpty()) {
			List<ChequeDTO> chequeDtoList = chequeDTOAssembler.toDTOList(chequeList);
			chequeDtoList.stream().forEach(cheque -> {
				BulkReversalItemDTO item = new BulkReversalItemDTO();
				item.setShrCheque(cheque);
				itemList.add(item);
			});
		}
		result.setShrBulkRvlItems(itemList);
		log.info("generateBulkReversal() end - " + result);
		return result;
	}
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
	public boolean insertBulkReversal(BulkReversalDTO confirmedBulkReversalDto) {
		log.info("insertBulkReversal() start - confirmedBulkReversalVo: " + confirmedBulkReversalDto);
		boolean result = false;
		/** Do validation before save, Not yet implemented*/
		//validateSaveBulkReversal();
		Voucher voucher = voucherRepository.findOne(5);
		ShrBulkRvl bulkReversal = DataUtils.copyProperties(confirmedBulkReversalDto, ShrBulkRvl.class);
		/** HardCode the voucher(Id = 5) as it is not yet confirmed*/
		/** HardCode the total amount because it is set as a foreign key by mistake.*/
		bulkReversal.setShrVcrInfo(voucher);
		bulkReversal.setTotalAmount(new BigDecimal(1));
		bulkReversal.setStatus(CoreConstant.STATUS_ACTIVE);
		ShrBulkRvl savedBulkReversal = bulkReversalRepository.save(bulkReversal);
		
		List<ShrBulkRvlItem> itemList = bulkReversal.getShrBulkRvlItems();
		List<ShrCheque> cheques = new ArrayList<>();
		itemList.forEach(item -> {
			item.setStatus(CoreConstant.STATUS_ACTIVE);
			item.setShrBulkRvl(savedBulkReversal);
			ShrCheque cheque = item.getShrCheque();
			if(CoreConstant.CHEQUE_STATUS_GENERATED.equals(cheque.getStatus())) {
				cheque.setStatus(CoreConstant.CHEQUE_STATUS_REVERSED);
				cheques.add(cheque);
			}
		});
		chequeRepository.save(cheques);
		bulkReversalItemRepository.save(itemList);
		if (savedBulkReversal != null) {
			result = true;
		}
		
		
		log.info("insertBulkReversal() end - " + result);
		return result;
	}
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public BulkReversalDTO confirmBulkReversal(BulkReversalDTO confirmedBulkReversal) {
		log.info("confirmBulkReversal() start - confirmedBulkReversalVo: " + confirmedBulkReversal);
		/** Do validation before save, Not yet implemented*/
		BulkReversalDTO result = null;
		ShrBulkRvl bulkReversalentity = DataUtils.copyProperties(confirmedBulkReversal, ShrBulkRvl.class);
		List<ShrBulkRvlItem> itemList = bulkReversalentity.getShrBulkRvlItems();
		List<ShrCheque> chequeList = new ArrayList<>();
		/** Update the selected cheques' status*/
		itemList.stream().forEach(item -> {
			ShrCheque cheque = item.getShrCheque();
			cheque.setStatus(CoreConstant.CHEQUE_STATUS_REVERSED);
			chequeList.add(cheque);
		});
		chequeRepository.save(chequeList);
		result = loadBulkReversalDetail(confirmedBulkReversal.getBulkReversalId());
		log.info("confirmBulkReversal() end - " + result);
		return result;
	}
}
