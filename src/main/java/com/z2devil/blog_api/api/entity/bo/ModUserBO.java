package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: blog_api
 * @description: 修改用户信息BO
 * @author: z2devil
 * @create: 2021-11-08
 **/
@Data
@ApiModel(value = "修改用户信息BO")
public class ModUserBO implements Serializable {

    @ApiModelProperty(value = "昵称")
    @NotBlank
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "签名")
    private String signature;

    @ApiModelProperty(value = "头像路径")
    private String avatarPath;

}
