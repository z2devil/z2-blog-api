package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: blog_api
 * @description: 用户注册DTO
 * @author: z2devil
 * @create: 2021-05-21
 **/
@Data
@ApiModel(value = "用户注册BO")
public class UserSignBO implements Serializable {

    @ApiModelProperty(value = "邮箱")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "验证码")
    @NotBlank
    private String verifyCode;

}
