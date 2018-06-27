/**
 * 
 */
package hk.oro.iefas.ws.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.system.dto.DashboardInfoDTO;
import hk.oro.iefas.domain.system.entity.DashboardInfo;
import hk.oro.iefas.domain.system.entity.DashboardType;
import hk.oro.iefas.ws.security.repository.PrivilegeRepository;
import hk.oro.iefas.ws.system.repository.DashboardInfoRepository;
import hk.oro.iefas.ws.system.repository.DashboardTypeRepository;
import hk.oro.iefas.ws.system.repository.assembler.DashboardInfoDTOAssembler;
import hk.oro.iefas.ws.system.service.DashboardInfoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2040 $ $Date: 2018-04-12 14:37:25 +0800 (週四, 12 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class DashboardInfoServiceImpl implements DashboardInfoService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private DashboardInfoRepository dashboardInfoRepository;

    @Autowired
    private DashboardTypeRepository dashboardTypeRepository;

    @Autowired
    private DashboardInfoDTOAssembler dashboardInfoDTOAssembler;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void generateDashboardInfo(Integer postId) {
        log.info("generateDashboardInfo() start - PostId: " + postId);
        List<PrivilegeDTO> privileges = privilegeRepository.findByPostId(postId);
        List<DashboardInfo> dashboardInfos = dashboardInfoRepository.findByPostId(postId);
        if (CommonUtils.isNotEmpty(privileges)) {
            privileges.stream()
                .filter(privilege -> dashboardInfos.stream()
                    .allMatch(dashboardInfo -> !privilege.getPrivilegeId()
                        .equals(dashboardInfo.getDashboardType().getPrivilege().getPrivilegeId())))
                .forEach(privilege -> {
                    DashboardType dashboardType
                        = dashboardTypeRepository.findByPrivilegePrivilegeId(privilege.getPrivilegeId());
                    if (dashboardType != null) {
                        dashboardInfoRepository.save(new DashboardInfo(null, "0", postId, CoreConstant.STATUS_ACTIVE,
                            null, false, dashboardType));
                    }
                });
        }

        if (CommonUtils.isNotEmpty(dashboardInfos)) {
            dashboardInfos.stream().filter(di -> privileges.stream().allMatch(
                privilege -> !privilege.getPrivilegeId().equals(di.getDashboardType().getPrivilege().getPrivilegeId())))
                .forEach(di -> {
                    dashboardInfoRepository.delete(di.getDashboardId());
                });
        }

        log.info("generateDashboardInfo() end");
    }

    @Transactional(readOnly = true)
    @Override
    public List<DashboardInfoDTO> findByPostId(Integer postId) {
        log.info("findByPostId() start - PostId: " + postId);
        List<DashboardInfo> dashboardInfos = dashboardInfoRepository.findByPostId(postId);
        List<DashboardInfoDTO> dtoList = dashboardInfoDTOAssembler.toDTOList(dashboardInfos);
        log.info("findByPostId() end - " + dtoList);
        return dtoList;
    }

}
