/**
 * 
 */
package hk.oro.iefas.ws.system.service;

import java.util.List;

import hk.oro.iefas.domain.system.dto.DashboardInfoDTO;

/**
 * @version $Revision: 2040 $ $Date: 2018-04-12 14:37:25 +0800 (週四, 12 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface DashboardInfoService {

    void generateDashboardInfo(Integer postId);

    List<DashboardInfoDTO> findByPostId(Integer postId);

}
