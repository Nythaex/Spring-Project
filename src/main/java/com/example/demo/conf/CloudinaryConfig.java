package com.example.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    private String cloudName="dlr1eys6i";
    private String apiKey="673797899796979";
    private String apiSecret="zYiPCwsa4zjsTOVBPvHJ6hAmm5c";

    public String getCloudName() {
        return cloudName;
    }

    /**
     * Sets the cloud name associated with the cloudinary account.
     * @param cloudName the cloud name associated with the cloudinary account.
     * @return this
     */
    public CloudinaryConfig setCloudName(String cloudName) {
        this.cloudName = cloudName;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public CloudinaryConfig setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public CloudinaryConfig setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }
}
