package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: blog_api
 * @description: 添加评论BO
 * @author: z2devil
 * @create: 2021-07-11
 **/
@Data
@ApiModel(value = "添加评论BO")
public class AddCommentBO {

    @ApiModelProperty(value = "作品类型")
    @NotNull
    private Integer wCate;

    @ApiModelProperty(value = "作品id")
    @Min(value = 1, message = "作品id不能小于1")
    private Integer wId;

    @ApiModelProperty(value = "根级id")
    private Integer rId;

    @ApiModelProperty(value = "父级id")
    private Integer pId;

    @ApiModelProperty(value = "内容")
    @NotBlank
    private String content;

}
