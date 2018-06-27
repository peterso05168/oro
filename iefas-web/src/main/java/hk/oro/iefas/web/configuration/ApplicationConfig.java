package hk.oro.iefas.web.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import hk.oro.iefas.web.core.interceptor.CurrentUserInfoInterceptor;
import hk.oro.iefas.web.core.interceptor.CustomResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2666 $ $Date: 2018-05-28 16:01:15 +0800 (週一, 28 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@ServletComponentScan
@ComponentScan(basePackages = {"hk.oro.iefas"})
@Configuration
public class ApplicationConfig {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setInterceptors(Arrays.asList(currentUserInfoInterceptor()));
        restTemplate.setErrorHandler(customResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestInterceptor currentUserInfoInterceptor() {
        return new CurrentUserInfoInterceptor();
    }

    @Bean
    public CustomResponseErrorHandler customResponseErrorHandler() {
        return new CustomResponseErrorHandler();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        try {
            String filePath = System.getProperty("iefas.web.config.location");
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

}
