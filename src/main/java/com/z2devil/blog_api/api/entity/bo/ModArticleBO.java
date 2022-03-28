package com.z2devil.blog_api.api.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @program: blog_api
 * @description: 添加文章BO
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Data
@ApiModel(value = "修改文章BO")
public class ModArticleBO {

    @ApiModelProperty(value = "文章id")
    private Integer id;

    @ApiModelProperty(value = "标题")
    @NotBlank
    private String title;

    @ApiModelProperty(value = "内容")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "摘要")
    @NotBlank
    private String summary;

    @ApiModelProperty(value = "字数")
    @Min(value = 1, message = "字数不能小于1")
    private Integer wordsNum;

    @ApiModelProperty(value = "封图")
    private Integer cover;

    @ApiModelProperty(value = "资源")
    private Integer[] resources;

    @ApiModelProperty(value = "标签id集合")
    private List<Integer> tags;
}
