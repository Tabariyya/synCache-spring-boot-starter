package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;


import io.github.waleedsda.synCache.Controller;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SynCacheProperties.class)
@ConditionalOnProperty(prefix = "spring.cache", name = "type", havingValue = "syncache")
public class SynCacheAutoConfiguration implements CachingConfigurer {
    private final SynCacheProperties props;

    public SynCacheAutoConfiguration(SynCacheProperties props) {
        this.props = props;
    }


    @Bean
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        Controller controller = new Controller(props.getBrokerUrl(), props.getBrokerAuthToken(), props.getMaxNoOfEntries());
        return new SynCacheCacheManager(controller);
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new TypedCacheResolver(cacheManager());
    }
}
