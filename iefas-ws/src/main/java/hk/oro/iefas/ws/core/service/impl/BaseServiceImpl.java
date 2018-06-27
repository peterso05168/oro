package hk.oro.iefas.ws.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.ws.core.repository.BaseRepository;
import hk.oro.iefas.ws.core.service.BaseService;
import lombok.Data;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@Data
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public long count() {
        return baseRepository.count();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(Iterable<? extends T> entities) {
        baseRepository.delete(entities);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteInBatch(Iterable<T> entities) {
        baseRepository.deleteInBatch(entities);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public boolean exists(ID id) {
        return baseRepository.exists(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<T> findAll(Iterable<ID> ids) {
        return baseRepository.findAll(ids);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public T findOne(ID id) {
        return baseRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public T getOne(ID id) {
        return baseRepository.getOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<T> save(Iterable<T> entities) {
        return baseRepository.save(entities);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public T saveAndFlush(T entity) {
        return baseRepository.saveAndFlush(entity);
    }

}
