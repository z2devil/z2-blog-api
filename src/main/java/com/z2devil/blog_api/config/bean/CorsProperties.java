package com.z2devil.blog_api.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: blog_api
 * @description: cors配置
 * @author: z2devil
 * @create: 2022-05-20
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private String origin;

}
