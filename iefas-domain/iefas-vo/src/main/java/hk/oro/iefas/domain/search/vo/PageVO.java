package hk.oro.iefas.domain.search.vo;

import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Sort.Direction;

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
public class PageVO {

    private Integer page;
    private Integer size;
    private String sortField;
    private String sortOrder;

    public PageVO(int offset, int size, String sortField, SortOrder sortOrder) {
        if (offset != 0) {
            this.page = offset / size;
        } else {
            this.page = 0;
        }

        this.size = size;

        if (CommonUtils.isNotBlank(sortField)) {
            this.sortField = sortField;
            if (sortOrder.equals(SortOrder.ASCENDING)) {
                this.sortOrder = Direction.ASC.name();
            } else if (sortOrder.equals(SortOrder.DESCENDING)) {
                this.sortOrder = Direction.DESC.name();
            }
        }

    }
}
