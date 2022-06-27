package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.ArticleTag;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface IArticleTagService extends IService<ArticleTag> {

    /**
     * 批量删除(根据文章id)
     * @params [aId]
     * @return void
     * @author z2devil
     * @date 2022/1/27
     */
    void remove(Integer aId);

}
