package com.z2devil.blog_api.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.bo.AddArticleBO;
import com.z2devil.blog_api.api.entity.bo.ModArticleBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.enums.LimitType;
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

import javax.servlet.http.HttpServletResponse;
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
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping
    public Result<IPage<ArticleListVO>> getArticleList(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "tags[]", required = false) Integer[] tags,
            PageBO pageBO
    ) {
        return Result.res(ResponseEnum.OK, articleService.getArticles(keyword, tags, pageBO));
    }

    @ApiOperation(value = "获取文章")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping("/{id}")
    public Result<ArticleListVO> getArticle(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, articleService.getArticle(id));
    }

    @ApiOperation(value = "获取文章详情")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping("/detail/{id}")
    public Result<ArticleDetailVO> getArticleDetail(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, articleService.getArticleDetail(id));
    }

    @ApiOperation(value = "发表文章")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PostMapping
    public Result addArticle(@Valid @RequestBody AddArticleBO addArticleBO) {
        articleService.addArticle(addArticleBO);
        return Result.res(ResponseEnum.OK, "发表成功");
    }

    @ApiOperation(value = "修改文章")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PutMapping
    public Result modArticle(@Valid @RequestBody ModArticleBO modArticleBO) {
        articleService.modArticle(modArticleBO);
        return Result.res(ResponseEnum.OK, "修改成功");
    }

    @ApiOperation(value = "删除文章")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @DeleteMapping("/{id}")
    public Result deleteArticle(@PathVariable Integer id) {
        articleService.removeArticle(id);
        return Result.res(ResponseEnum.OK, "删除成功");
    }

    @ApiOperation(value = "导出文章")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PostMapping("/export/{id}")
    public void exportArticle(@PathVariable Integer id, HttpServletResponse response) {
        articleService.exportArticle(id, response);
    }
}
