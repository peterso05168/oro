package hk.oro.iefas.web.system.systemadministration.divisionprivillege.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.domain.organization.vo.DivisionVO;
import hk.oro.iefas.domain.organization.vo.SearchDivisionCriteriaVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.systemadministration.divisionprivillege.service.DivisionClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class DivisionPrivilegeSearchView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private DivisionClientService divisionClientService;

    @Getter
    @Setter
    private SearchDivisionCriteriaVO criteria = new SearchDivisionCriteriaVO();

    @Getter
    @Setter
    private List<DivisionVO> divisionVOs;

    @PostConstruct
    private void init() {
        log.info("=====DivisionPrivilegeSearchView init======");
    }

    public void search() {
        log.info("search() start");
        divisionVOs = divisionClientService.findByCriteria(criteria);
        log.info("search() end and result = " + divisionVOs);
    }

    public void reset() {
        log.info("reset() start");
        divisionVOs = null;
        criteria = new SearchDivisionCriteriaVO();
        log.info("reset() end");
    }
}
