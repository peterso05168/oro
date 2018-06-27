package hk.oro.iefas.ws.core.assembler;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * 
 * @author scott.feng
 * 
 * @param <R> the class of DTO
 * @param <T> the class of entity
 */
public abstract class PagingAssemblerSupport<R, T> extends AssemblerSupport<R, T> {

    public Page<R> toResultDTO(Page<T> page) {
        List<R> dtos = null;
        if (page != null) {
            List<T> content = page.getContent();
            if (content != null) {
                dtos = toDTOList(content);
            }
        }

        return new PageImpl<>(dtos, new PageRequest(page.getNumber(), page.getSize(), page.getSort()),
            page.getTotalElements());
    }
}
