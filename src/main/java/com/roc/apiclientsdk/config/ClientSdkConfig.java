package com.roc.apiclientsdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.roc.apiclientsdk.client.ApiClient;

import lombok.Data;

/**
 * @author lipeng
 * @since 2026/2/24 16:45
 */
@Configuration
@ConfigurationProperties("api.client")
@ComponentScan
@Data
public class ClientSdkConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(accessKey, secretKey);
    }

}
