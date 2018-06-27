package hk.oro.iefas.web.system.divisionadministration.post.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import hk.oro.iefas.domain.organization.vo.SearchPostCriteriaVO;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class PostSearchBean {

    @Inject
    private PostSearchView postSearchView;

    @PostConstruct
    private void init() {
        log.info("======PostSearchView init======");
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + postSearchView.getCriteria());
        if (postSearchView.getPostResultDataModel() == null) {
            postSearchView.setPostResultDataModel(new PostLazyDataModel(postSearchView.getCriteria()));
        }
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        postSearchView.setCriteria(new SearchPostCriteriaVO());
        postSearchView.setPostResultDataModel(null);
        log.info("reset() end");
    }

}
