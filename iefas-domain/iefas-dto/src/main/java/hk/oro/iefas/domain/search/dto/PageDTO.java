package hk.oro.iefas.domain.search.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import hk.oro.iefas.core.util.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 927 $ $Date: 2018-01-23 11:05:54 +0800 (週二, 23 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    private Integer page;
    private Integer size;
    private String sortField;
    private String sortOrder;

    public PageRequest toPageable() {
        Sort sort = null;
        if (CommonUtils.isNotBlank(sortField)) {
            sort = new Sort(new Order(Direction.fromStringOrNull(sortOrder), sortField));
        }

        return new PageRequest(page, size, sort);
    }

}
