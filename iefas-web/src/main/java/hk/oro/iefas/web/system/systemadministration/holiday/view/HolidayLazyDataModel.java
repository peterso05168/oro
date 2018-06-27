package hk.oro.iefas.web.system.systemadministration.holiday.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Configurable;

import hk.oro.iefas.core.exceptions.BaseException;
import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.web.common.util.LazySorter;
import hk.oro.iefas.web.core.jsf.component.datamodel.BaseLazyDataModel;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Configurable(preConstruction = true)
public class HolidayLazyDataModel extends BaseLazyDataModel<HolidayVO> {

    private static final long serialVersionUID = 1L;

    private final HolidayClientService holidayClientService;
    private final SearchHolidayVO criteriaVO;

    public HolidayLazyDataModel(SearchHolidayVO criteriaVO, HolidayClientService holidayClientService) {
        this.criteriaVO = criteriaVO;
        this.holidayClientService = holidayClientService;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<HolidayVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
        Map<String, Object> filters) {
        List<HolidayVO> searchResults = new ArrayList<>();
        List<HolidayVO> resultList = new ArrayList<>();
        HolidayResultVO result = null;
        try {
            result = holidayClientService.searchHoliday(criteriaVO);
            if (result != null) {
                searchResults = result.getResultList();
            }

            // filter
            if (filters != null) {
                for (HolidayVO holiday : searchResults) {
                    boolean match = true;

                    for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                        try {
                            String filterProperty = it.next();
                            Object filterValue = filters.get(filterProperty);
                            String fieldValue
                                = String.valueOf(holiday.getClass().getField(filterProperty).get(holiday));

                            if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                                match = true;
                            } else {
                                match = false;
                                break;
                            }
                        } catch (Exception e) {
                            match = false;
                        }
                    }

                    if (match) {
                        resultList.add(holiday);
                    }
                }
            } else {
                resultList = searchResults;
            }

            // sort
            if (sortField != null) {
                Collections.sort(resultList, new LazySorter(sortField, sortOrder));
            }

            // rowCount
            int dataSize = resultList.size();
            this.setRowCount(dataSize);

            // paginate
            if (dataSize > pageSize) {
                try {
                    return resultList.subList(first, first + pageSize);
                } catch (IndexOutOfBoundsException e) {
                    return resultList.subList(first, first + (dataSize % pageSize));
                }
            } else {
                return resultList;
            }
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }
}
