package com.z2devil.blog_api.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.api.entity.bo.AddCommentBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.CommentListVO;
import com.z2devil.blog_api.api.service.ICommentService;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Api(tags = "评论")
public class CommentController {

    private final ICommentService commentService;

    @ApiOperation(value = "获取评论列表")
    @GetMapping
    public Result<IPage<CommentListVO>> getCommentList(Integer wCate, Integer wId, PageBO pageBO) {
        return Result.res(ResponseEnum.OK, commentService.getAllComments(wCate, wId, pageBO));
    }

    @ApiOperation(value = "获取评论")
    @GetMapping("/{id}")
    public Result<CommentListVO> getComment(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, commentService.getComment(id));
    }

    @ApiOperation(value="发表评论")
    @Access(AccessLevel.LOGIN)
    @PostMapping
    public Result addComment(@RequestBody AddCommentBO addCommentBO) {
        commentService.addComment(addCommentBO);
        return Result.res(ResponseEnum.OK, "发表成功");
    }

    @ApiOperation(value="删除评论")
    @Access(AccessLevel.LOGIN)
    @DeleteMapping("/{id}")
    public Result deleteComment(@PathVariable Integer id) {
        commentService.removeComment(id);
        return Result.res(ResponseEnum.OK, "删除成功");
    }

    @ApiOperation(value = "获取子评论列表")
    @GetMapping("child/{id}")
    public Result<IPage<CommentListVO>> getChildCommentList(@PathVariable Integer id, PageBO pageBO) {
        return Result.res(ResponseEnum.OK, commentService.getChildComments(id, pageBO));
    }
}
