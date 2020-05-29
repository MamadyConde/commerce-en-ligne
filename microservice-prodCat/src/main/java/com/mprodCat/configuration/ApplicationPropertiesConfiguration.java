package com.mprodCat.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    private int limitProducts;

    public int getLimitProducts() {
        return limitProducts;
    }

    public void setLimitProducts(int limitProducts) {
        this.limitProducts = limitProducts;
    }
}
