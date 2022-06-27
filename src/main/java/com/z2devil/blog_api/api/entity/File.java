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
 * 文件表
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
@ApiModel(value="文件")
public class File extends Model<File> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "大小")
    private Long size;

    @ApiModelProperty(value = "上传时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime postDate;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    public File(String name, String path, String type, Long size) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
