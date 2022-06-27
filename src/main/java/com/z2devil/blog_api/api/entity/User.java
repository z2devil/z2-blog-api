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
 * 用户表
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
@ApiModel(value="用户")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "等级，0：普通用户；1：管理员；2：博主")
    private Integer lv;

    @ApiModelProperty(value = "头像路径")
    private String avatarPath;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "签名")
    private String signature;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    public User(String email, Integer lv, String nickname) {
        this.email = email;
        this.lv = lv;
        this.nickname = nickname;
    }


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
