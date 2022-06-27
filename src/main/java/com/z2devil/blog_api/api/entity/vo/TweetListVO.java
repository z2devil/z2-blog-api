package com.z2devil.blog_api.api.entity.vo;

import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: blog_api
 * @description: 动态列表
 * @author: z2devil
 * @create: 2021-05-21
 **/
@Data
public class TweetListVO {

    private Integer id;

    private Integer uId;

    /**
     * 用户信息
     */
    private UserInfoDTO userInfo;

    /**
     * 内容
     */
    private String content;

    /**
     * 喜欢数
     */
    private Integer likes;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 发布时间
     */
    private LocalDateTime postDate;

    /**
     * 资源（文件列表）
     */
    private List<FileListVO> files;

    /**
     * 是否已经喜欢
     */
    private Boolean isLiked;

}
