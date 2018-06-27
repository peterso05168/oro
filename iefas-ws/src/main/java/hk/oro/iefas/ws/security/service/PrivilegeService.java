package hk.oro.iefas.ws.security.service;

import java.util.List;

import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;

public interface PrivilegeService {

    Integer save(PrivilegeDTO privilegeDTO);

    PrivilegeDTO findOne(Integer privilegeId);

    List<SearchPrivilegeResultDTO> findAll();

    List<PrivilegeDTO> findByPostId(Integer postId);
}
