package com.z2devil.blog_api.api.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: blog_api
 * @description: 文件列表VO
 * @author: z2devil
 * @create: 2021-07-15
 **/
@Data
public class FileListVO {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 类型
     */
    private String type;

    /**
     * 大小
     */
    private Long size;

    /**
     * 上传时间
     */
    private LocalDateTime postDate;

}
