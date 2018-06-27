package hk.oro.iefas.web.release.historicalcaselist.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Configurable;

import hk.oro.iefas.core.exceptions.BaseException;
import hk.oro.iefas.domain.release.vo.HistoricalCaseListResultVO;
import hk.oro.iefas.domain.release.vo.RelHistListVO;
import hk.oro.iefas.domain.release.vo.SearchHistoricalCaseListVO;
import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.HolidayVO;
import hk.oro.iefas.domain.system.vo.SearchHolidayVO;
import hk.oro.iefas.web.common.util.LazySorter;
import hk.oro.iefas.web.core.jsf.component.datamodel.BaseLazyDataModel;
import hk.oro.iefas.web.release.historicalcaselist.service.HistoricalCaseListClientService;
import hk.oro.iefas.web.system.systemadministration.holiday.service.HolidayClientService;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (Mon, 09 Apr 2018) $
 * @author $Author: marvel.ma $
 */
@Configurable(preConstruction = true)
public class HistoricalCaseListLazyDataModel extends BaseLazyDataModel<RelHistListVO> {

    private static final long serialVersionUID = 1L;

    private final HistoricalCaseListClientService historicalCaseListClientService;
    private final SearchHistoricalCaseListVO criteriaVO;

    public HistoricalCaseListLazyDataModel(SearchHistoricalCaseListVO criteriaVO, HistoricalCaseListClientService historicalCaseListClientService) {
        this.criteriaVO = criteriaVO;
        this.historicalCaseListClientService = historicalCaseListClientService;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<RelHistListVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
        Map<String, Object> filters) {
        List<RelHistListVO> searchResults = new ArrayList<>();
        List<RelHistListVO> resultList = new ArrayList<>();
        HistoricalCaseListResultVO result = null;
        try {
            result = historicalCaseListClientService.searchHistoricalCaseList(criteriaVO);
            if (result != null) {
                searchResults = result.getResultList();
            }

            // filter
            if (filters != null) {
                for (RelHistListVO historicalCaseList : searchResults) {
                    boolean match = true;

                    for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                        try {
                            String filterProperty = it.next();
                            Object filterValue = filters.get(filterProperty);
                            String fieldValue
                                = String.valueOf(historicalCaseList.getClass().getField(filterProperty).get(historicalCaseList));

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
                        resultList.add(historicalCaseList);
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
