package hk.oro.iefas.ws.system.repository;

import java.util.List;

import hk.oro.iefas.domain.system.entity.SystemParameter;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SystemParameterRepository extends BaseRepository<SystemParameter, Integer> {
    List<SystemParameter> findByModuleCodeAndParameterValueTypeAndStatus(String moduleCode, String parameterValueType,
        String status);
}
