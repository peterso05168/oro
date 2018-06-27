package hk.oro.iefas.ws.organization.controlller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.SearchRoleCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.security.dto.PostRoleDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.organization.service.PostRoleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_POST_ROLE)
public class PostRoleController {

    @Autowired
    private PostRoleService postRoleService;

    @PostMapping(value = RequestUriConstant.URI_POST_ROLE_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer save(@Valid @RequestBody PostRoleDTO postRoleDTO) {
        log.info("savePostDetail() start - " + postRoleDTO);
        Integer postRoleId = postRoleService.save(postRoleDTO);
        log.info("savePostDetail() end and postId = " + postRoleId);
        return postRoleId;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_ROLE_LIST_BY_POST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<SearchRoleResultDTO> findByPostId(@RequestBody Integer postId) {
        log.info("searchPostList() start - " + postId);
        List<SearchRoleResultDTO> postRoleList = postRoleService.findByPostId(postId);
        log.info("searchPostList() end and result = " + postRoleList);
        return postRoleList;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_ROLE_BY_POST_AND_ROLE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public PostRoleDTO findByPostAndRole(@RequestBody SearchRoleCriteriaDTO criteria) {
        log.info("findByPostAndRole() start - " + criteria);
        PostRoleDTO postRoleDTO = postRoleService.findByPostAndRole(criteria);
        log.info("findByPostAndRole() end and postRoleDTO = " + postRoleDTO);
        return postRoleDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_POST_ROLE_BY_ROLE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<PostRoleDTO> findByRole(@RequestBody SearchRoleCriteriaDTO criteria) {
        log.info("findByRole() start - " + criteria);
        List<PostRoleDTO> list = postRoleService.findByRole(criteria);
        log.info("findByRole() end and list = " + list);
        return list;
    }
}
