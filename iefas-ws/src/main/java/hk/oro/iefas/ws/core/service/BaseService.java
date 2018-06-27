package hk.oro.iefas.ws.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
public interface BaseService<T, ID extends Serializable> {

    long count();

    void delete(ID id);

    void delete(Iterable<? extends T> entities);

    void delete(T entity);

    void deleteInBatch(Iterable<T> entities);

    boolean exists(ID id);

    List<T> findAll();

    List<T> findAll(Iterable<ID> ids);

    Page<T> findAll(Pageable pageable);

    List<T> findAll(Sort sort);

    T findOne(ID id);

    T getOne(ID id);

    List<T> save(Iterable<T> entities);

    T save(T entity);

    T saveAndFlush(T entity);

}
