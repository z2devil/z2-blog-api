package com.z2devil.blog_api.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 专栏文章表
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="专栏文章")
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer uId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "发布时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime postDate;

    @ApiModelProperty(value = "字数")
    private Integer wordsNum;

    @ApiModelProperty(value = "封图")
    private Integer cover;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
