package hk.oro.iefas.web.system.systemadministration.systemsetting.view;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Configurable;

import hk.oro.iefas.core.exceptions.BaseException;
import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.SystemParameterVO;
import hk.oro.iefas.web.common.service.SystemParameterClientService;
import hk.oro.iefas.web.core.jsf.component.datamodel.BaseLazyDataModel;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;
import hk.oro.iefas.web.system.systemadministration.systemsetting.service.SystemSettingClientService;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Configurable(preConstruction = true)
public class SystemParametersLazyDataModel extends BaseLazyDataModel<SystemParameterVO> {

    private static final long serialVersionUID = 1L;

    private final SystemSettingClientService systemSettingClientService;

    public SystemParametersLazyDataModel(SystemSettingClientService systemSettingClientService) {
        this.systemSettingClientService = systemSettingClientService;
    }

    @Override
    public List<SystemParameterVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
        Map<String, Object> filters) {
        ResultPageVO<List<SystemParameterVO>> resultPageVO;
        try {
            resultPageVO = systemSettingClientService.findAllSystemParameters();
            setRowCount(resultPageVO.getTotalElements());
            return resultPageVO.getContent();
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

}
