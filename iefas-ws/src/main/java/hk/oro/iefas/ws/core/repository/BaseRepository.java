package hk.oro.iefas.ws.core.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
    extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T>, JpaSpecificationExecutor<T> {

    <S> Page<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate, Pageable pageable);

    <S> List<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate);

    <S> S findOne(FactoryExpression<S> factoryExpression, Predicate predicate);

    <S> List<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate, EntityPath<?>... paths);

    List<Tuple> findAll(Predicate predicate, EntityPath<?>... paths);

    <S> Page<S> findAll(FactoryExpression<S> factoryExpression, Predicate predicate, Pageable pageable,
        OrderSpecifier<?> order);

}
