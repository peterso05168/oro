package hk.oro.iefas.web.ledger.paymenthandling.roster.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DivisionConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.organization.vo.PostVO;
import hk.oro.iefas.domain.shroff.vo.RosterVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.ledger.paymenthandling.roster.service.RosterClientService;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@ViewScoped
public class RosterEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Integer rosterId;

    @Getter
    @Setter
    private RosterVO rosterVO;

    @Inject
    private RosterClientService rosterClientService;

    @Inject
    private PostClientService postClientService;

    @Getter
    @Setter
    private List<PostVO> postResultVOS;

    @Inject
    private AppResourceUtils appResourceUtils;

    @PostConstruct
    private void init() {
        log.info("rosterEditView init ======");
        postResultVOS = postClientService.findByDivisionName(DivisionConstant.DIVISION_SHROFF);
        rosterId = Faces.getRequestParameter("rosterId", Integer.class);
        if (rosterId != null) {
            rosterVO = rosterClientService.getRosterDetail(rosterId);
        } else {
            rosterVO = new RosterVO();
            rosterVO.setGroupAPost(new PostVO());
            rosterVO.setGroupBPost(new PostVO());
            rosterVO.setStatus(CoreConstant.STATUS_ACTIVE);
        }
        log.info("rosterEditView init end");
    }

    public void save() {
        log.info("save() start");
        Integer rosterId = null;
        if (rosterVO != null && rosterVO.getGroupAPost() != null && rosterVO.getGroupBPost() != null) {
            PostVO postAVO = postClientService.findOne(rosterVO.getGroupAPost().getPostId());
            PostVO postBVO = postClientService.findOne(rosterVO.getGroupBPost().getPostId());
            rosterVO.setGroupBPost(postBVO);
            rosterVO.setGroupAPost(postAVO);
            rosterId = rosterClientService.saveRoster(rosterVO);
        }
        if (rosterId != null) {
            rosterVO = rosterClientService.getRosterDetail(rosterId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        } else {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
        }
        log.info("save() end");
    }

}
