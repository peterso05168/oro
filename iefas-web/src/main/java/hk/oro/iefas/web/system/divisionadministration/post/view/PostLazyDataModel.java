package hk.oro.iefas.web.system.divisionadministration.post.view;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Configurable;

import hk.oro.iefas.core.exceptions.BaseException;
import hk.oro.iefas.domain.organization.vo.SearchPostCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchPostResultVO;
import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.web.core.jsf.component.datamodel.BaseLazyDataModel;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import lombok.Getter;
import lombok.Setter;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Configurable(preConstruction = true)
public class PostLazyDataModel extends BaseLazyDataModel<SearchPostResultVO> {

    private static final long serialVersionUID = 1L;

    @Inject
    private PostClientService postClientService;

    @Getter
    @Setter
    private SearchPostCriteriaVO criteria;

    public PostLazyDataModel(SearchPostCriteriaVO criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<SearchPostResultVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
        Map<String, Object> filters) {
        ResultPageVO<List<SearchPostResultVO>> resultPageVO;
        try {
            resultPageVO = postClientService.findByCriteria(
                new SearchVO<SearchPostCriteriaVO>(criteria, new PageVO(first, pageSize, sortField, sortOrder)));
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
