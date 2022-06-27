package com.z2devil.blog_api.api.entity.vo;

import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: blog_api
 * @description: 文章详情VO
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Data
public class ArticleDetailVO {

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
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 发布时间
     */
    private LocalDateTime postDate;

    /**
     * 字数
     */
    private Integer wordsNum;

    /**
     * 喜欢数
     */
    private Integer likes;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 标签集合
     */
    private List<TagVO> tags;

    /**
     * 封图文件
     */
    private FileListVO cover;

    /**
     * 资源（文件列表）
     */
    private List<FileListVO> files;

    /**
     * 是否已经喜欢
     */
    private Boolean isLiked;

}
