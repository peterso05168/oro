package hk.oro.iefas.web.system.divisionadministration.role.view;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Configurable;

import hk.oro.iefas.core.exceptions.BaseException;
import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleResultVO;
import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.web.core.jsf.component.datamodel.BaseLazyDataModel;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import lombok.Getter;
import lombok.Setter;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Configurable(preConstruction = true)
public class RoleLazyDataModel extends BaseLazyDataModel<SearchRoleResultVO> {

    private static final long serialVersionUID = 1L;

    @Inject
    private RoleClientService roleClientService;

    @Getter
    @Setter
    private SearchRoleCriteriaVO criteriaDTO;

    public RoleLazyDataModel(SearchRoleCriteriaVO criteriaDTO) {
        this.criteriaDTO = criteriaDTO;
    }

    @Override
    public List<SearchRoleResultVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
        Map<String, Object> filters) {
        ResultPageVO<List<SearchRoleResultVO>> resultPageVO;
        try {
            resultPageVO = roleClientService.findByCriteria(
                new SearchVO<SearchRoleCriteriaVO>(criteriaDTO, new PageVO(first, pageSize, sortField, sortOrder)));
            if (resultPageVO != null) {
                setRowCount(resultPageVO.getTotalElements());
                return resultPageVO.getContent();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

}
