package com.z2devil.blog_api.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.api.entity.bo.AddArticleBO;
import com.z2devil.blog_api.api.entity.bo.ModArticleBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.ArticleDetailVO;
import com.z2devil.blog_api.api.entity.vo.ArticleListVO;
import com.z2devil.blog_api.api.service.IArticleService;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 专栏文章表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
@Api(tags = "文章")
public class ArticleController {

    private final IArticleService articleService;

    @ApiOperation(value = "获取文章列表")
    @GetMapping
    public Result<IPage<ArticleListVO>> getArticleList(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "tags[]", required = false) Integer[] tags,
            PageBO pageBO
        ) {
        return Result.res(ResponseEnum.OK, articleService.getArticles(keyword, tags, pageBO));
    }

    @ApiOperation(value = "获取文章")
    @GetMapping("/{id}")
    public Result<ArticleListVO> getArticle(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, articleService.getArticle(id));
    }

    @ApiOperation(value = "获取文章详情")
    @GetMapping("/detail/{id}")
    public Result<ArticleDetailVO> getArticleDetail(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, articleService.getArticleDetail(id));
    }

    @ApiOperation(value="发表文章")
    @Access(AccessLevel.ADMIN)
    @PostMapping
    public Result addArticle(@Valid @RequestBody AddArticleBO addArticleBO) {
        articleService.addArticle(addArticleBO);
        return Result.res(ResponseEnum.OK, "发表成功");
    }

    @ApiOperation(value="修改文章")
    @Access(AccessLevel.ADMIN)
    @PutMapping
    public Result modArticle(@Valid @RequestBody ModArticleBO modArticleBO) {
        articleService.modArticle(modArticleBO);
        return Result.res(ResponseEnum.OK, "修改成功");
    }

    @ApiOperation(value="删除文章")
    @Access(AccessLevel.ADMIN)
    @DeleteMapping("/{id}")
    public Result deleteArticle(@PathVariable Integer id) {
        articleService.removeArticle(id);
        return Result.res(ResponseEnum.OK, "删除成功");
    }

}
