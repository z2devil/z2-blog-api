package com.z2devil.blog_api.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.bo.AddTweetBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.enums.LimitType;
import com.z2devil.blog_api.api.entity.vo.TweetListVO;
import com.z2devil.blog_api.api.service.ITweetService;
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
 * 动态表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tweet")
@Api(tags = "动态")
public class TweetController {

    private final ITweetService tweetService;

    @ApiOperation(value = "获取动态列表")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping
    public Result<IPage<TweetListVO>> getTweetList(PageBO pageBO) {
        return Result.res(ResponseEnum.OK, tweetService.getAllTweets(pageBO));
    }

    @ApiOperation(value = "获取动态")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping("/{id}")
    public Result<TweetListVO> getTweet(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, tweetService.getTweet(id));
    }

    @ApiOperation(value="发表动态")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PostMapping
    public Result addTweet(@RequestBody AddTweetBO addTweetBO) {
        tweetService.addTweet(addTweetBO);
        return Result.res(ResponseEnum.OK, "发表成功");
    }

    @ApiOperation(value="删除动态")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @DeleteMapping("/{id}")
    public Result deleteTweet(@PathVariable Integer id) {
        tweetService.removeTweet(id);
        return Result.res(ResponseEnum.OK, "删除成功");
    }

}
