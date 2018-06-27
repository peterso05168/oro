package hk.oro.iefas.ws.configuration;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import hk.oro.iefas.core.exceptions.BaseException;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.ws.core.repository.factory.BaseRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3217 $ $Date: 2018-06-20 14:01:41 +0800 (週三, 20 六月 2018) $
 * @author $Author: scott.feng $
 */
@Slf4j
@Configuration
@EntityScan(basePackages = {"hk.oro.iefas.domain"})
@EnableJpaRepositories(basePackages = {"hk.oro.iefas.ws.*.repository"},
    repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class JpaConfig implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LocalContainerEntityManagerFactoryBean) {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = (LocalContainerEntityManagerFactoryBean)bean;
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = null;
            try {
                resources = resolver.getResources("classpath*:/META-INF/*orm.xml");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new BaseException(e);
            }

            if (CommonUtils.isNotEmpty(resources)) {
                String[] filePaths = new String[resources.length];
                for (int i = 0; i < resources.length; i++) {
                    File file = null;
                    try {
                        file = resources[i].getFile();
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                        throw new BaseException(e);
                    }
                    filePaths[i] = file.getPath();
                }
                entityManagerFactory.setMappingResources(filePaths);
            }

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
