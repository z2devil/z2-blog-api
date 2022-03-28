package com.z2devil.blog_api.api.entity.vo;

import lombok.Data;

/**
 * @program: blog_api
 * @description: 标签VO
 * @author: z2devil
 * @create: 2021-12-18
 **/
@Data
public class TagVO {

    private Integer id;

    /**
     * 名称
     */
    private String name;
}
