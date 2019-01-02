package com.czechowski.fromnewsapitoownapiclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Configuration
@PropertySource("classpath:application.properties")
public class RestServiceProperties {

    @Value( "${restservice.host}" )
    private String host;

    @Value( "${restservice.port}" )
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
