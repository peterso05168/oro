package hk.oro.iefas.domain.search.vo;

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
public class SearchVO<T> {

    private T criteria;
    private PageVO page;

}
