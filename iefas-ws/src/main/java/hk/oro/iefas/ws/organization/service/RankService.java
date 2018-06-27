package hk.oro.iefas.ws.organization.service;

import java.util.List;

import hk.oro.iefas.domain.organization.dto.RankDTO;

/**
 * @version $Revision: 2120 $ $Date: 2018-04-19 10:25:23 +0800 (週四, 19 四月 2018) $
 * @author $Author: dante.fang $
 */
public interface RankService {

    List<RankDTO> findAll();

    RankDTO findOne(Integer rankId);

    RankDTO findByRankName(String rankName);
}
