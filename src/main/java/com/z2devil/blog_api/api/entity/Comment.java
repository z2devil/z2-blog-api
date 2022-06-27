package com.z2devil.blog_api.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论表
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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="评论")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer uId;

    @ApiModelProperty(value = "作品类型, 0：评论 1：推文 2：文章")
    private Integer wCate;

    @ApiModelProperty(value = "作品id")
    private Integer wId;

    @ApiModelProperty(value = "根级id")
    private Integer rId;

    @ApiModelProperty(value = "父级id")
    private Integer pId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "发表时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime postDate;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
