package com.z2devil.blog_api.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.Comment;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.bo.AddCommentBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.CommentListVO;
import com.z2devil.blog_api.api.mapper.CommentMapper;
import com.z2devil.blog_api.api.service.ICommentService;
import com.z2devil.blog_api.api.service.IUserService;
import com.z2devil.blog_api.api.service.mapStruct.AddCommentConverter;
import com.z2devil.blog_api.exception.AccessException;
import com.z2devil.blog_api.exception.BaseException;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    private final AddCommentConverter addCommentConverter;

    private final CommentMapper commentMapper;

    private final IUserService userService;

    @Override
    public IPage<CommentListVO> getAllComments(Integer wCate, Integer wId, PageBO pageBO) {
        IPage<CommentListVO> result = commentMapper.getAllComments(wCate, wId, pageBO.buildPage());
        List<CommentListVO> comments = result.getRecords();
        for (int i = 0; i < comments.size(); i++) {
            CommentListVO comment = comments.get(i);
            PageBO page = new PageBO(1, 4);
            comment.setChildComments(commentMapper.getChildComments(comment.getId(), page.buildPage()));
        }
        result.setRecords(comments);
        return result;
    }

    @Override
    public CommentListVO getComment(Integer id) {
        CommentListVO result = commentMapper.getComment(id);
        PageBO page = new PageBO(1, 4);
        result.setChildComments(commentMapper.getChildComments(id, page.buildPage()));
        return result;
    }

    @Override
    public void addComment(AddCommentBO addCommentBO) {
        Comment comment = addCommentConverter.toEntity(addCommentBO);
        comment.setUId(SignUserIdUtils.get());
        commentMapper.insert(comment);
    }

    @Override
    public void removeComment(Integer id) {
        Comment comment = commentMapper.selectById(id);
        User signedUser = userService.getById(SignUserIdUtils.get());
        if (!ObjectUtils.isEmpty(comment)) {
            if (signedUser.getLv() < AccessLevel.MASTER.getLv() && !signedUser.getId().equals(comment.getUId())) {
                throw new AccessException("权限异常");
            }
            removeById(id);
        }else {
            throw new BaseException("删除失败");
        }
    }

    @Override
    public IPage<CommentListVO> getChildComments(Integer id, PageBO pageBO) {
        return commentMapper.getChildComments(id, pageBO.buildPage());
    }

}
