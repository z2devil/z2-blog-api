package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @program: blog_api
 * @description: 喜欢BO
 * @author: z2devil
 * @create: 2021-08-02
 **/
@Data
@ApiModel(value = "喜欢BO")
public class AddPreferBO {

    @ApiModelProperty(value = "作品类型")
    @NotNull
    private Integer wCate;

    @ApiModelProperty(value = "作品id")
    @Min(value = 1, message = "作品id不能小于1")
    private Integer wId;
}
