package com.z2devil.blog_api.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 标签表
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
@ApiModel(value="文章标签")
public class ArticleTag extends Model<ArticleTag> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文章id")
    private Integer aId;

    @ApiModelProperty(value = "标签id")
    private Integer tId;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
