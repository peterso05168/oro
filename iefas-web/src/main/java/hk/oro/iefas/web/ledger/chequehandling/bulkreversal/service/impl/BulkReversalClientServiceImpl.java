package hk.oro.iefas.web.ledger.chequehandling.bulkreversal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.core.exceptions.support.ErrorMsg;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.shroff.vo.BulkReversalItemVO;
import hk.oro.iefas.domain.shroff.vo.BulkReversalResultVO;
import hk.oro.iefas.domain.shroff.vo.BulkReversalVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.domain.shroff.vo.GenerateBulkReversalVO;
import hk.oro.iefas.domain.shroff.vo.SearchBulkReversalVO;
import hk.oro.iefas.domain.system.vo.SystemParameterVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.ledger.chequehandling.bulkreversal.service.BulkReversalClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date $
 * @author $Author $
 */
@Named
@Slf4j
public class BulkReversalClientServiceImpl extends BaseClientService implements BulkReversalClientService {

	
	@Autowired
	private AppResourceUtils appResourceUtils;
	
	@Override
	public BulkReversalResultVO searchBulkReversal(SearchBulkReversalVO criteria) {
		log.info("searchBulkReversal() start - criteria: " + criteria);
		BulkReversalResultVO result = null;
		result = postForObject(RequestUriConstant.CLIENT_URI_BULK_REVESAL_SEARCH, 
				criteria, BulkReversalResultVO.class);
		log.info("searchBulkReversal() end - " + result);
		return result;
	}
	
	@Override
	public BulkReversalVO loadBulkReversalDetail(Long bulkReversalId) {
		log.info("loadBulkReversalDetail() start - bulkReversalId: " + bulkReversalId);
		BulkReversalVO result = null;
		result = postForObject(RequestUriConstant.CLIENT_URI_BULK_REVESAL_LOAD, 
				bulkReversalId, BulkReversalVO.class);
		log.info("loadBulkReversalDetail() end - " + result);
		return result;
	}
	
	@Override
	public BulkReversalVO generateBulkReversal(GenerateBulkReversalVO generateBulkReversalVO) {
		log.info("generateBulkReversal() start - generateBulkReversalVO: " + generateBulkReversalVO);
		BulkReversalVO result = null;
		result = postForObject(RequestUriConstant.CLIENT_URI_BULK_REVESAL_GENERATE, 
				generateBulkReversalVO, BulkReversalVO.class);
		if (result != null) {
			result.setCutOffDate(generateBulkReversalVO.getCutOffDate());
			result.setProcessDate(DateUtils.getCurrentDate());
		}
		log.info("generateBulkReversal() end - " + result);
		return result;
	}
	
	@Override
	public boolean insertBulkReversal(BulkReversalVO confirmedBulkReversalVo) {
		log.info("insertBulkReversal() start - confirmedBulkReversalVo: " + confirmedBulkReversalVo);
		boolean result = false;
		result = postForObject(RequestUriConstant.CLIENT_URI_BULK_REVESAL_INSERT, 
				confirmedBulkReversalVo, Boolean.class);
		log.info("insertBulkReversal() end - " + result);
		return result;
	}
	
	@Override
	public BulkReversalVO confirmBulkReversal(BulkReversalVO confirmedBulkReversalVo) {
		log.info("insertBulkReversal() start - confirmedBulkReversalVo: " + confirmedBulkReversalVo);
		List<ErrorMsg> errors = validateConfirmBulkReversal(confirmedBulkReversalVo);
		if (errors != null && !errors.isEmpty()) {
			Messages.addGlobalError("Cheques are already reversed.");
			return confirmedBulkReversalVo;
		}
		BulkReversalVO result = postForObject(RequestUriConstant.CLIENT_URI_BULK_REVESAL_CONFIRM, 
				confirmedBulkReversalVo, BulkReversalVO.class);
		Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_REVERSE_SUCCESS));
		log.info("insertBulkReversal() end - " + result);
		return result;
	}
	
	private List<ErrorMsg> validateConfirmBulkReversal(BulkReversalVO confirmedBulkReversalVo) {
		List<ErrorMsg> errorList = new ArrayList<>();
		List<BulkReversalItemVO> items = confirmedBulkReversalVo.getShrBulkRvlItems();
		boolean isAllChequesReversed = true;
		for (BulkReversalItemVO item : items) {
			ChequeVO cheque = item.getShrCheque();
			if (cheque != null && !CoreConstant.CHEQUE_STATUS_REVERSED.equals(cheque.getStatus())) {
				isAllChequesReversed = false;
			}
		}
		if (isAllChequesReversed) {
//			Should add a message to ErrorMessage. 
//			-Cheques are already reversed.
			ErrorMsg error1 = new ErrorMsg();
			errorList.add(error1);
		}
		return errorList;
	}
	
}


