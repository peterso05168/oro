package hk.oro.iefas.ws.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import hk.oro.iefas.core.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "#{'${remote.allowedAddress}'.split(',')}")
    private List<String> allowedAddress;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        if (CommonUtils.isNotEmpty(allowedAddress)) {
            allowedAddress.parallelStream().forEach(ip -> {
                try {
                    http.authorizeRequests().anyRequest().hasIpAddress(ip);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
    }
}
