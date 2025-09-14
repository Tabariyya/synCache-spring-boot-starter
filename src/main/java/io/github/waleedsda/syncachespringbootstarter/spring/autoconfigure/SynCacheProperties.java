package io.github.waleedsda.syncachespringbootstarter.spring.autoconfigure;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "spring.data.syncache")
public class SynCacheProperties {

    @NotNull
    private String rabbitMQURI;

    @NotNull
    private Integer maxNoOfEntries;

    @NotNull
    private boolean async;

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public Integer getMaxNoOfEntries() {
        return maxNoOfEntries;
    }

    public void setMaxNoOfEntries(Integer maxNoOfEntries) {
        this.maxNoOfEntries = maxNoOfEntries;
    }

    public String getRabbitMQURI() {
        return rabbitMQURI;
    }

    public void setRabbitMQURI(String rabbitMQURI) {
        this.rabbitMQURI = rabbitMQURI;
    }
}
