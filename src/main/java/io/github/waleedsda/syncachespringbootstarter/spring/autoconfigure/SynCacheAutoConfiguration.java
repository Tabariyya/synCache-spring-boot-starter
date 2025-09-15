package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;


import com.synCache.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SynCacheProperties.class)
@ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "syncache")
public class SynCacheAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public Controller synCacheController(SynCacheProperties props) {
        return new Controller(props.getRabbitMQURI(), props.getMaxNoOfEntries(), props.isAsync());
    }

    @Bean
    @ConditionalOnMissingBean(CacheManager.class)
    public CacheManager cacheManager(Controller controller) {
        return new SynCacheCacheManager(controller);
    }
}
