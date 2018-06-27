package hk.oro.iefas.web.system.divisionadministration.post.delegation.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import hk.oro.iefas.domain.organization.vo.SearchPostCriteriaVO;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.system.divisionadministration.post.view.PostLazyDataModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class PostDelegationSearchBean {

    @Inject
    private PostDelegationSearchView postDelegationSearchView;

    @PostConstruct
    private void init() {
        log.info("======PostDelegationSearchView init======");
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + postDelegationSearchView.getCriteria());
        if (postDelegationSearchView.getPostResultDataModel() == null) {
            postDelegationSearchView
                .setPostResultDataModel(new PostLazyDataModel(postDelegationSearchView.getCriteria()));
        }
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        postDelegationSearchView.setCriteria(new SearchPostCriteriaVO());
        postDelegationSearchView.setPostResultDataModel(null);
        log.info("reset() end");
    }

}
