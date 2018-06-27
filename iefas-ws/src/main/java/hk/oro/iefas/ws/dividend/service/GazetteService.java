package hk.oro.iefas.ws.dividend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.dividend.dto.GazetteDTO;
import hk.oro.iefas.domain.dividend.dto.SearchGazetteDescriptionDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */

public interface GazetteService {

    public Page<GazetteDTO> searchGazetteList(SearchDTO<SearchGazetteDescriptionDTO> criteria);

    public Integer saveGazette(GazetteDTO gazetteDTO);

    public GazetteDTO searchByGazetteId(Integer gazetteId);

    public List<GazetteDTO> findAll();
}
