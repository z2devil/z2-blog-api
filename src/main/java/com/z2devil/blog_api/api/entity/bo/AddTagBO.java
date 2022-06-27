package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: blog_api
 * @description: 添加标签BO
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Data
@ApiModel(value = "添加标签BO")
public class AddTagBO {

    @ApiModelProperty(value = "名称")
    @NotBlank
    private String name;

}
