package com.z2devil.blog_api.api.entity.vo;

import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: blog_api
 * @description: 文章列表VO
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Data
public class ArticleListVO {

    private Integer id;

    private Integer uId;

    private Integer cId;

    /**
     * 用户信息
     */
    private UserInfoDTO userInfo;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

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
     * 封图文件
     */
    private FileListVO cover;

    /**
     * 是否已经喜欢
     */
    private Boolean isLiked;

}
