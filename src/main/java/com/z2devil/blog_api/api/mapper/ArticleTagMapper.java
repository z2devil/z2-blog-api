package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z2devil.blog_api.api.entity.ArticleTag;
import org.springframework.cache.annotation.CacheConfig;

/**
 * <p>
 * 文章标签表 Mapper 接口
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@CacheConfig(cacheNames = "tag")
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}
