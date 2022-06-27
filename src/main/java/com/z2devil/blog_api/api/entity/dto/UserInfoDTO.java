package com.z2devil.blog_api.api.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: blog_api
 * @description: 用户信息DTO
 * @author: z2devil
 * @create: 2021-05-21
 **/
@Data
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String email;

    private Integer lv;

    private String avatarPath;

    private String nickname;

    private String signature;

}
