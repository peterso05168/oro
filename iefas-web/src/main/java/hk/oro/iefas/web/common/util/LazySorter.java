package hk.oro.iefas.web.common.util;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 * @param sortField The field Name that you want to sort.
 * @param sortOrder The sort order. <br/>
 *        <br/>
 *        LazySorter is used for performing sort to a {@link List}.Designed for {@link LazyDataModel}.
 */
public class LazySorter<T> implements Comparator<T> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public int compare(T obj1, T obj2) {
        try {
            Field field1 = obj1.getClass().getDeclaredField(this.sortField);
            Field field2 = obj2.getClass().getDeclaredField(this.sortField);
            field1.setAccessible(true);
            field2.setAccessible(true);
            // Object value1 = T.class.getField(this.sortField).get(car1);
            Object value1 = field1.get(obj1);
            Object value2 = field2.get(obj2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}