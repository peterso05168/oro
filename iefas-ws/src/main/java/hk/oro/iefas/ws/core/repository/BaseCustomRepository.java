package hk.oro.iefas.ws.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
@NoRepositoryBean
public class BaseCustomRepository {

    @PersistenceContext
    protected EntityManager em;

}
