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
 * 用户评价表
 * </p>
 *
 * @author z2devil
 * @since 2021-06-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="评价")
public class Prefer extends Model<Prefer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer uId;

    @ApiModelProperty(value = "作品类型")
    private Integer wCate;

    @ApiModelProperty(value = "作品id")
    private Integer wId;

    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    public Prefer(Integer uId, Integer cate, Integer wId, Integer isDelete) {
        this.uId = uId;
        this.wCate = cate;
        this.wId = wId;
        this.isDelete = isDelete;
    }


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
