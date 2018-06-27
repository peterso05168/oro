package hk.oro.iefas.ws.funds.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.funds.dto.InvestmentTypeDTO;
import hk.oro.iefas.domain.funds.entity.InvType;
import hk.oro.iefas.ws.funds.repository.InvTypeRepository;
import hk.oro.iefas.ws.funds.repository.assembler.InvestmentTypeDTOAssembler;
import hk.oro.iefas.ws.funds.repository.predicate.InvestTypePredicate;
import hk.oro.iefas.ws.funds.service.FundsCommonService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Service
public class FundsCommonServiceImpl implements FundsCommonService {

    @Autowired
    private InvTypeRepository investmentTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InvestmentTypeDTO> searchInvestmentType() {
        Iterable<InvType> iterableInvestmentType = investmentTypeRepository.findAll(InvestTypePredicate.findAll(),
            InvestTypePredicate.sortByInvestTypeCode());
        List<InvType> investmentTypeList = IterableUtils.toList(iterableInvestmentType);
        List<InvestmentTypeDTO> dtoList = null;
        if (CommonUtils.isNotEmpty(investmentTypeList)) {
            dtoList = InvestmentTypeDTOAssembler.toDtoList(investmentTypeList);
        }
        return dtoList;
    }
}
