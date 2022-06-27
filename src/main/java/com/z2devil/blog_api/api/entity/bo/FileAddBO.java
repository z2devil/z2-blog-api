package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: blog_api
 * @description: 新增文件BO
 * @author: z2devil
 * @create: 2021-09-19
 **/
@Data
@ApiModel(value = "新增文件BO")
public class FileAddBO {

    @ApiModelProperty(value = "名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "路径")
    @NotBlank
    private String path;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "大小")
    @NotBlank
    private String size;

}
