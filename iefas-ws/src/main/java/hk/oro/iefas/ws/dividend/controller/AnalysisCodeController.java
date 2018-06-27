package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeDTO;
import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeCriteriaDTO;
import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeResultDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.AnalysisCodeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3057 $ $Date: 2018-06-11 21:25:40 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_ANALYSIS_CODE)
public class AnalysisCodeController {

    @Autowired
    private AnalysisCodeService analysisCodeService;

    @PostMapping(value = RequestUriConstant.URI_FIND_ANALYSIS_CODE_BY_ANALYSIS_CODE)
    public List<AnalysisCodeDTO> findByAnalysisCode(@RequestBody String analysisCode) {
        log.info("findByAnalysisCode() start - AnalysisCode: " + analysisCode);
        List<AnalysisCodeDTO> dtoList = analysisCodeService.findByAnalysisCode(analysisCode);
        log.info("findByAnalysisCode() end - " + dtoList);
        return dtoList;
    }

    @PostMapping(value = RequestUriConstant.URI_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer save(@RequestBody AnalysisCodeDTO analysisCodeDTO) {
        log.info("save() start - " + analysisCodeDTO);
        Integer analysisCodeId = analysisCodeService.save(analysisCodeDTO);
        log.info("save() end return : " + analysisCodeId);
        return analysisCodeId;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public AnalysisCodeDTO findOne(@RequestBody Integer analysisCodeId) {
        log.info("getAnalysisCodeDetail() start - analysisCodeId = " + analysisCodeId);
        AnalysisCodeDTO analysisCodeDTO = analysisCodeService.findOne(analysisCodeId);
        log.info("getAnalysisCodeDetail() end and result = " + analysisCodeDTO);
        return analysisCodeDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_ANALYSIS_CODE_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<SearchAnalysisCodeResultDTO>
        findByCriteria(@RequestBody SearchDTO<SearchAnalysisCodeCriteriaDTO> criteria) {
        log.info("findByCriteria() start - criteria = " + criteria);
        Page<SearchAnalysisCodeResultDTO> result = analysisCodeService.findByCriteria(criteria);
        log.info("findByCriteria() end - result = " + result);
        return result;
    }
    
    @PostMapping(value = RequestUriConstant.URI_IS_ANALYSIS_CODE_EXISTED_BY_CRITERIA)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Boolean isAnalysisCodeExistedByCriteria(@RequestBody SearchAnalysisCodeCriteriaDTO criteria) {
        log.info("isAnalysisCodeExistedByCriteria() start - criteria: " + criteria);
        
        Boolean result = analysisCodeService.isAnalysisCodeExistedByCriteria(criteria);
        
        log.info("existsByAnalysisCode() end - result = " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_EXISTS_BY_ANALYSIS_CODE)
    public Boolean existsByAnalysisCode(@RequestBody SearchAnalysisCodeCriteriaDTO criteria) {
        log.info("existsByAnalysisCode() start - criteria = " + criteria);
        Boolean result = analysisCodeService.existsByAnalysisCode(criteria);
        log.info("existsByAnalysisCode() end - result = " + result);
        return result;
    }

}
