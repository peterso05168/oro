package hk.oro.iefas.web.dividend.maintenance.gazette.service;

import java.util.List;

import hk.oro.iefas.domain.dividend.vo.GazetteVO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public interface GazetteDescriptionClientService {

    public Integer saveGazetteDescription(GazetteVO GazetteVO);

    public GazetteVO searchGazetteById(Integer gazetteId);

    public List<GazetteVO> findAll();

}
