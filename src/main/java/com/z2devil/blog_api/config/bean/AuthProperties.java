package com.z2devil.blog_api.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: blog_api
 * @description: jwt配置
 * @author: z2devil
 * @create: 2021-09-17
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private String header;

    private String secret;

    private String codePrefix;

    private String tokenPrefix;

    private Integer codeCoolingTime;

    private Integer codeExpireTime;

    private Integer codeLength;

    private Integer codeLifeNumber;

    private Integer tokenExpireTime;

    private Integer tokenDetectScope;

}
