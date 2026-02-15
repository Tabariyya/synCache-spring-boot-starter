package com.tabariyya.syncachespringbootstarter.spring.autoconfigure;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "spring.data.syncache")
public class SynCacheProperties {

    @NotNull
    private String brokerAuthToken;

    @NotNull
    private Integer maxNoOfEntries;

    public Integer getMaxNoOfEntries() {
        return maxNoOfEntries;
    }

    public void setMaxNoOfEntries(Integer maxNoOfEntries) {
        this.maxNoOfEntries = maxNoOfEntries;
    }

    public String getBrokerAuthToken() {
        return brokerAuthToken;
    }

    public void setBrokerAuthToken(String brokerAuthToken) {
        this.brokerAuthToken = brokerAuthToken;
    }
}
