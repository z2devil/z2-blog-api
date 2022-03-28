package com.z2devil.blog_api.api.entity.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: blog_api
 * @description: 评论列表VO
 * @author: z2devil
 * @create: 2021-07-11
 **/
@Data
public class CommentListVO {

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
     * 子评论
     */
    private IPage<CommentListVO> childComments;

    /**
     * 是否已经喜欢
     */
    private Boolean isLiked;

}
