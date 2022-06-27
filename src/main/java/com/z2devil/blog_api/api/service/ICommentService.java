package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.Comment;
import com.z2devil.blog_api.api.entity.bo.AddCommentBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.CommentListVO;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 获取评论列表
     * @param wCate
     * @param wId
     * @param pageBO
     * @return
     */
    IPage<CommentListVO> getAllComments(Integer wCate, Integer wId, PageBO pageBO);

    /**
     * 获取评论
     * @param id
     * @return
     */
    CommentListVO getComment(Integer id);

    /**
     * 添加评论
     * @param addCommentBO
     */
    void addComment(AddCommentBO addCommentBO);

    /**
     * 删除评论
     * @param id
     */
    void removeComment(Integer id);

    /**
     * 获取子评论列表
     * @param id
     * @param pageBO
     * @return
     */
    IPage<CommentListVO> getChildComments(Integer id, PageBO pageBO);

}
