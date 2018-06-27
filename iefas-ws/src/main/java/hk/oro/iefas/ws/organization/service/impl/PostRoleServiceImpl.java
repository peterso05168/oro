package hk.oro.iefas.ws.organization.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.SearchPostCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchRoleCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.security.dto.PostRoleDTO;
import hk.oro.iefas.domain.security.entity.PostRole;
import hk.oro.iefas.ws.organization.repository.PostRepository;
import hk.oro.iefas.ws.organization.repository.PostRoleRepository;
import hk.oro.iefas.ws.organization.repository.assembler.PostDTOAssembler;
import hk.oro.iefas.ws.organization.repository.assembler.PostRoleDTOAssembler;
import hk.oro.iefas.ws.organization.repository.predicate.PostRolePredicate;
import hk.oro.iefas.ws.organization.service.PostRoleService;
import hk.oro.iefas.ws.security.repository.RoleRepository;
import hk.oro.iefas.ws.security.repository.assembler.RoleDTOAssembler;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class PostRoleServiceImpl implements PostRoleService {

    @Autowired
    private PostRoleRepository postRoleRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(PostRoleDTO postRoleDTO) {
        log.info("save() start - and postRoleDTO = " + postRoleDTO);
        Integer postRoleId;
        if (postRoleDTO.getPostRoleId() != null && postRoleDTO.getPostRoleId() != 0) {
            PostRole postRole = DataUtils.copyProperties(postRoleDTO, PostRole.class);
            postRoleRepository.save(postRole);
            postRoleId = postRole.getPostRoleId();
        } else {
            postRoleDTO.setStatus(CoreConstant.STATUS_ACTIVE);
            if (postRoleDTO.getPost().getPostId() != null && postRoleDTO.getPost().getPostId() > 0) {
                postRoleDTO.setPost(PostDTOAssembler.toDTO(postRepository.findOne(postRoleDTO.getPost().getPostId())));
            }
            if (postRoleDTO.getRole().getRoleId() != null && postRoleDTO.getRole().getRoleId() > 0) {
                postRoleDTO.setRole(RoleDTOAssembler.toDTO(roleRepository.findOne(postRoleDTO.getRole().getRoleId())));
            }
            PostRole postRole = DataUtils.copyProperties(postRoleDTO, PostRole.class);
            postRoleRepository.save(postRole);
            postRoleId = postRole.getPostRoleId();
        }
        log.info("save end - and returnVal = " + postRoleId);
        return postRoleId;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SearchRoleResultDTO> findByPostId(Integer postId) {
        log.info("findByDivisionId() start - postId: " + postId);
        List<SearchRoleResultDTO> resultList
            = postRoleRepository.findAll(PostRoleDTOAssembler.toDTO(), PostRolePredicate
                .findByCriteria(new SearchPostCriteriaDTO(null, postId, null, null, CoreConstant.STATUS_ACTIVE)));
        log.info("findByDivisionId() end - " + resultList);
        return resultList;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PostRoleDTO findByPostAndRole(SearchRoleCriteriaDTO criteria) {
        log.info("findByPostAndRole() start - and criteria = " + criteria);
        PostRole postRole = postRoleRepository.findOne(PostRolePredicate.findByPostAndRole(criteria.getPostId(),
            criteria.getRoleId(), criteria.getStatus(), CoreConstant.STATUS_ACTIVE));
        PostRoleDTO result = PostRoleDTOAssembler.toDTO(postRole);
        log.info("findByPostAndRole end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<PostRoleDTO> findByRole(SearchRoleCriteriaDTO criteria) {
        log.info("findByRole() start - and criteria = " + criteria);
        Iterable<PostRole> list = postRoleRepository.findAll(PostRolePredicate.findByPostAndRole(criteria.getPostId(),
            criteria.getRoleId(), CoreConstant.STATUS_ACTIVE, CoreConstant.STATUS_ACTIVE));
        List<PostRoleDTO> result = null;
        if (list != null) {
            result = new ArrayList<>();
            for (Iterator<PostRole> is = list.iterator(); is.hasNext();) {
                result.add(PostRoleDTOAssembler.toDTO(is.next()));
            }
        }
        log.info("findByRole end - and returnVal = " + result);
        return result;
    }
}
