package hk.oro.iefas.web.core.jsf.component.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.web.common.util.ApplicationContextUtils;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author scott.feng
 * 
 * @param <R> the class of return object
 * @param <V> the class of searchVO
 */
@Slf4j
public class SimpleLazyDataModel<R, V> extends LazyDataModel<R> {

    private static final long serialVersionUID = 4408993575428532845L;

    private BaseClientService clientService;

    private final V searchCriteriaVO;

    private String url;

    public SimpleLazyDataModel(String url, V searchCriteriaVO) {
        super();
        this.clientService = ApplicationContextUtils.getBaseClientService();
        this.url = url;
        this.searchCriteriaVO = searchCriteriaVO;

        log.info("clientService instant is " + this.clientService + ",and request url is " + this.url
            + ",and searchCriteria is " + this.searchCriteriaVO);
    }

    @Override
    public List<R> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<R> content = null;
        if (this.clientService != null) {
            SearchVO<V> criteriaVO
                = new SearchVO<V>(searchCriteriaVO, new PageVO(first, pageSize, sortField, sortOrder));
            ResponseEntity<ResultPageVO<List<R>>> responseEntity
                = this.clientService.exchange(this.url, HttpMethod.POST, new HttpEntity<SearchVO<V>>(criteriaVO),
                    new ParameterizedTypeReference<ResultPageVO<List<R>>>() {});
            ResultPageVO<List<R>> resultPageVO = responseEntity.getBody();
            if (resultPageVO != null) {
                setRowCount(resultPageVO.getTotalElements());
                content = resultPageVO.getContent();
            }
        }
        log.info("load end - return: " + content);
        return content;
    }

}
