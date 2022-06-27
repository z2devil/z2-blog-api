package com.z2devil.blog_api.api.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.ArticleTag;
import com.z2devil.blog_api.api.mapper.ArticleTagMapper;
import com.z2devil.blog_api.api.service.IArticleTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "tag")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements IArticleTagService {

    private final ArticleTagMapper articleTagMapper;

    @CacheEvict(key = "'wid:' + #p0")
    @Override
    public void remove(Integer aId) {
        LambdaQueryWrapper<ArticleTag> tagWrapper = new LambdaQueryWrapper<>();
        tagWrapper.eq(ArticleTag::getAId, aId);
        articleTagMapper.delete(tagWrapper);
    }
}
