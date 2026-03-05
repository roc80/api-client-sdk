package com.roc.apiclientsdk.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.roc.apiclientsdk.client.ApiClient;

import lombok.Data;

/**
 * 从 application.yml 读取 api.client 配置，自动创建 Bean
 *
 * @author lipeng
 * @since 2026/2/24 16:45
 */
@AutoConfiguration
@EnableConfigurationProperties(ClientSdkConfig.class)
@Data
@ConfigurationProperties("api.client")
public class ClientSdkConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    @ConditionalOnMissingBean
    public ApiClient apiClient() {
        return new ApiClient(accessKey, secretKey);
    }

}
