package hk.oro.iefas.ws.core.repository.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.AbstractJPAQuery;

import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends QueryDslJpaRepository<T, ID>
    implements BaseRepository<T, ID> {

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final EntityPath<T> path;
    private final PathBuilder<T> builder;
    private final Querydsl querydsl;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
    }

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager,
        EntityPathResolver resolver) {

        super(entityInformation, entityManager);

        this.path = resolver.createPath(entityInformation.getJavaType());
        this.builder = new PathBuilder<T>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
    }

    protected JPQLQuery<?> createMultiPathQuery(Predicate predicate, EntityPath<?>... paths) {
        AbstractJPAQuery<?, ?> query = querydsl.createQuery(paths).where(predicate);
        CrudMethodMetadata metadata = getRepositoryMethodMetadata();

        if (metadata == null) {
            return query;
        }

        LockModeType type = metadata.getLockModeType();
        query = type == null ? query : query.setLockMode(type);

        for (Entry<String, Object> hint : getQueryHints().entrySet()) {
            query.setHint(hint.getKey(), hint.getValue());
        }

        return query;
    }

    @Override
    public <S> Page<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate, Pageable pageable) {
        final JPQLQuery<?> countQuery = createCountQuery(predicate);
        JPQLQuery<S> query = querydsl.applyPagination(pageable, createQuery(predicate).select(factoryExpression));

        return PageableExecutionUtils.getPage(query.fetch(), pageable, new TotalSupplier() {

            @Override
            public long get() {
                return countQuery.fetchCount();
            }
        });
    }

    @Override
    public <S> Page<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate, Pageable pageable,
        OrderSpecifier<?> order) {
        final JPQLQuery<?> countQuery = createCountQuery(predicate);
        JPQLQuery<S> query
            = querydsl.applyPagination(pageable, createQuery(predicate).select(factoryExpression).orderBy(order));

        return PageableExecutionUtils.getPage(query.fetch(), pageable, new TotalSupplier() {

            @Override
            public long get() {
                return countQuery.fetchCount();
            }
        });
    }

    @Override
    public <S> List<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate) {
        JPQLQuery<S> query = createQuery(predicate).select(factoryExpression);
        QueryResults<S> fetchResults = query.fetchResults();

        return fetchResults.getResults();
    }

    @Override
    public <S> S findOne(FactoryExpression<S> factoryExpression, Predicate predicate) {
        return createQuery(predicate).select(factoryExpression).fetchOne();
    }

    @Override
    public <S> List<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate, EntityPath<?>... paths) {
        JPQLQuery<S> query = createMultiPathQuery(predicate, paths).select(factoryExpression);
        QueryResults<S> fetchResults = query.fetchResults();

        return fetchResults.getResults();
    }

    @Override
    public List<Tuple> findAll(Predicate predicate, EntityPath<?>... paths) {
        JPQLQuery<Tuple> query = createMultiPathQuery(predicate, paths).select(paths);
        QueryResults<Tuple> fetchResults = query.fetchResults();

        return fetchResults.getResults();
    }

}
