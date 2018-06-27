package hk.oro.iefas.web.system.divisionadministration.role.service;

import java.util.List;

import hk.oro.iefas.domain.security.vo.PrivilegeVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;

public interface PrivilegeClientService {

    Integer save(PrivilegeVO privilegeVO);

    List<SearchPrivilegeResultVO> findAll();

    List<PrivilegeVO> findByPostId(Integer postId);

}
