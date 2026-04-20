package io.github.galaxyplatform.coruscant.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "coruscant.security")
public record ApiKeyProperties(List<ApiKey> apiKeys) {

    public ApiKeyProperties {
        apiKeys = apiKeys == null ? List.of() : List.copyOf(apiKeys);
    }

    public record ApiKey(String name, String key) {
    }
}
