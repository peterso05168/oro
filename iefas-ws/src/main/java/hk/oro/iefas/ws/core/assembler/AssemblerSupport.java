package hk.oro.iefas.ws.core.assembler;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author scott.feng
 * 
 * @param <R> the class of DTO
 * @param <T> the class of entity
 */
public abstract class AssemblerSupport<R, T> {

    /**
     * this method need to implement
     * 
     * @param entity
     * @return DTO
     */
    public abstract R toDTO(T entity);

    public List<R> toDTOList(List<T> content) {
        List<R> list = null;
        if (content != null) {
            list = content.stream().map(type -> toDTO(type)).filter(dto -> dto != null).collect(Collectors.toList());
        }

        return list;
    }

    public List<R> toDTOList(Iterable<T> iterable) {
        List<R> list = null;
        if (iterable != null) {
            list = new ArrayList<>();
            for (Iterator<T> iterator = iterable.iterator(); iterator.hasNext();) {
                list.add(toDTO(iterator.next()));
            }
        }
        return list;
    }
}
