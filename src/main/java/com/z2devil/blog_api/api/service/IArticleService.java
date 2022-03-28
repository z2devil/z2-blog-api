package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.Article;
import com.z2devil.blog_api.api.entity.bo.AddArticleBO;
import com.z2devil.blog_api.api.entity.bo.ModArticleBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.ArticleDetailVO;
import com.z2devil.blog_api.api.entity.vo.ArticleListVO;

/**
 * <p>
 * 专栏文章表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface IArticleService extends IService<Article> {

    /**
     * 获取文章列表
     * @param getArticleBO
     * @return
     */
    IPage<ArticleListVO> getArticles(String keyword, Integer[] tags, PageBO pageBO);

    /**
     * 获取文章
     * @param id
     * @return
     */
    ArticleListVO getArticle(Integer id);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    ArticleDetailVO getArticleDetail(Integer id);

    /**
     * 添加文章
     * @param addArticleBO
     */
    void addArticle(AddArticleBO addArticleBO);

    /**
     * 修改文章
     * @param modArticleBO
     */
    void modArticle(ModArticleBO modArticleBO);

    /**
     * 删除文章
     * @param id
     */
    void removeArticle(Integer id);
}
