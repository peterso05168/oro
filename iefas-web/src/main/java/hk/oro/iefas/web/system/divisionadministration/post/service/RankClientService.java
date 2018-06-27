package hk.oro.iefas.web.system.divisionadministration.post.service;

import java.util.List;

import hk.oro.iefas.domain.organization.vo.RankVO;

/**
 * @version $Revision: 2169 $ $Date: 2018-04-23 16:58:23 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
public interface RankClientService {

    public List<RankVO> findAll();

    public RankVO findOne(Integer rankId);

    public RankVO findByRankName(String rankName);
}
