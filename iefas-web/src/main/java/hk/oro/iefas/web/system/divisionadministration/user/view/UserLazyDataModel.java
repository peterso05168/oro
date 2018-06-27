/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.view;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.SearchUserCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchUserResultVO;
import hk.oro.iefas.web.core.jsf.component.datamodel.BaseLazyDataModel;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class UserLazyDataModel extends BaseLazyDataModel<SearchUserResultVO> {

    private static final long serialVersionUID = 1L;

    private final UserAccountClientService userAccountClientService;

    private final SearchUserCriteriaVO searchUserCriteriaVO;

    public UserLazyDataModel(UserAccountClientService userAccountClientService,
        SearchUserCriteriaVO searchUserCriteriaVO) {
        super();
        this.userAccountClientService = userAccountClientService;
        this.searchUserCriteriaVO = searchUserCriteriaVO;
    }

    @Override
    public List<SearchUserResultVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
        Map<String, Object> filters) {
        ResultPageVO<List<SearchUserResultVO>> resultPageVO
            = userAccountClientService.searchUserList(new SearchVO<SearchUserCriteriaVO>(searchUserCriteriaVO,
                new PageVO(first, pageSize, sortField, sortOrder)));
        setRowCount(resultPageVO.getTotalElements());
        return resultPageVO.getContent();
    }

}
