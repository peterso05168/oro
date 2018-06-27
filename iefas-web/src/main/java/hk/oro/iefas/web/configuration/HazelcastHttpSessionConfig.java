package hk.oro.iefas.web.configuration;

import static java.util.Collections.singletonList;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.web.WebFilter;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Configuration
public class HazelcastHttpSessionConfig {

    @Value(value = "${cluster.members}")
    private String clusterMembers;

    @Bean
    public HazelcastInstance hazelcastInstance() {
        MapAttributeConfig attributeConfig
            = new MapAttributeConfig().setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
                .setExtractor(PrincipalNameExtractor.class.getName());

        Config config = new Config();

        config.getMapConfig("spring:session:sessions").addMapAttributeConfig(attributeConfig)
            .addMapIndexConfig(new MapIndexConfig(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).setMembers(singletonList(clusterMembers));

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        return hazelcastInstance;
    }

    @Bean
    public WebFilter webFilter(HazelcastInstance hazelcastInstance) {

        Properties properties = new Properties();
        properties.put("instance-name", hazelcastInstance.getName());
        properties.put("sticky-session", "true");
        //
        properties.put("deferred-write", "true");

        return new WebFilter(properties);
    }
}
