package hk.oro.iefas.ws.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3217 $ $Date: 2018-06-20 14:01:41 +0800 (週三, 20 六月 2018) $
 * @author $Author: scott.feng $
 */
@Slf4j
@ServletComponentScan
@ComponentScan(basePackages = {"hk.oro.iefas"})
@Configuration
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        try {
            String filePath = System.getProperty("iefas.ws.config.location");
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(fileSystemResource);
            configurer.setProperties(yaml.getObject());
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        configurer.setIgnoreResourceNotFound(true);
        configurer.setLocalOverride(true);
        return configurer;
    }
    
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(12);
        return executor;
    }
}
