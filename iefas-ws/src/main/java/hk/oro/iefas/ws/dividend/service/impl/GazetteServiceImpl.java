package hk.oro.iefas.ws.dividend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.dividend.dto.GazetteDTO;
import hk.oro.iefas.domain.dividend.dto.SearchGazetteDescriptionDTO;
import hk.oro.iefas.domain.dividend.entity.Gazette;
import hk.oro.iefas.domain.search.dto.PageDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.dividend.repository.GazetteRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.GazetteDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.GazettePredicate;
import hk.oro.iefas.ws.dividend.service.GazetteService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */

@Slf4j
@Service
public class GazetteServiceImpl implements GazetteService {

    @Autowired
    private GazetteRepository gazetteRepository;

    @Autowired
    private GazetteDTOAssembler gazetteDTOAssembler;

    @Transactional(readOnly = true)
    public Page<GazetteDTO> searchGazetteList(SearchDTO<SearchGazetteDescriptionDTO> criteria) {
        log.info("loadGazette - start and criteria =" + criteria);
        Page<GazetteDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            PageDTO pageDTO = criteria.getPage();
            if (pageDTO != null) {
                if (pageDTO.getSortField() == null) {
                    pageDTO.setSortField("gazetteCode");
                }
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                Page<Gazette> gazettes
                    = gazetteRepository.findAll(GazettePredicate.findByCriteria(criteria.getCriteria()), pageable);
                result = gazetteDTOAssembler.toResultDTO(gazettes);
            }
        }
        log.info("loadGazette - start and result =" + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveGazette(GazetteDTO gazetteDTO) {
        log.info("saveGazette - start and gazetteDTO" + gazetteDTO);
        Integer result = null;
        Gazette gazette = null;
        if (gazetteDTO != null) {
            gazette = DataUtils.copyProperties(gazetteDTO, Gazette.class);
            result = gazetteRepository.save(gazette).getGazetteId();
        }
        log.info("saveGazette - end and result" + result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public GazetteDTO searchByGazetteId(Integer gazetteId) {
        log.info("searchByGazetteId - start and gazetteId" + gazetteId);
        GazetteDTO gazetteDTO = null;
        if (gazetteId != null) {
            Gazette gazette = gazetteRepository.findOne(gazetteId);
            if (gazette != null) {
                gazetteDTO = gazetteDTOAssembler.toDTO(gazette);
            }
        }
        log.info("searchByGazetteId - end and gazetteDTO" + gazetteDTO);
        return gazetteDTO;
    }

    @Override
    public List<GazetteDTO> findAll() {
        log.info("findAll - start ");
        List<GazetteDTO> list = new ArrayList<GazetteDTO>();
        List<Gazette> gazettes = gazetteRepository.findByStatus(CoreConstant.STATUS_ACTIVE);
        if (CommonUtils.isNotEmpty(gazettes)) {
            list = gazetteDTOAssembler.toDTOList(gazettes);
        }
        log.info("findAll - end and list" + list);
        return list;
    }

}
