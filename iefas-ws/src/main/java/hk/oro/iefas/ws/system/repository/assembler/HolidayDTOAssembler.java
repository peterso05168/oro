package hk.oro.iefas.ws.system.repository.assembler;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.entity.Holiday;
import hk.oro.iefas.domain.system.entity.QHoliday;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class HolidayDTOAssembler {

    public static HolidayDTO toDTO(Holiday holiday) {
        HolidayDTO dto = null;
        if (holiday != null) {
            dto = DataUtils.copyProperties(holiday, HolidayDTO.class);
        }
        return dto;
    }

    public static QBean<HolidayDTO> toDTO() {
        QHoliday h = QHoliday.holiday;
        QBean<HolidayDTO> dto = Projections.bean(HolidayDTO.class, h.holidayId, h.holidayDate, h.holidayDesc, h.status);
        return dto;
    }

    public static List<HolidayDTO> toDTOList(List<Holiday> holidayList) {
        List<HolidayDTO> list = null;
        if (CommonUtils.isNotEmpty(holidayList)) {
            list = new ArrayList<HolidayDTO>();
            for (Holiday holiday : holidayList) {
                list.add(toDTO(holiday));
            }
        }
        return list;
    }
}
