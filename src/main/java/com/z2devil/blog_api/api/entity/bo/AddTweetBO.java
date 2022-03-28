package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @program: blog_api
 * @description: 添加动态BO
 * @author: z2devil
 * @create: 2021-05-24
 **/
@Data
@ApiModel(value = "添加动态BO")
public class AddTweetBO {

    @ApiModelProperty(value = "用户id")
    @Min(value = 1, message = "用户id不能小于1")
    private Integer uId;

    @ApiModelProperty(value = "内容")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "资源")
    private Integer[] resources;
}
