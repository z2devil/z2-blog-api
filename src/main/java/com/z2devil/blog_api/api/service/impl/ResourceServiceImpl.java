package com.z2devil.blog_api.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.Resource;
import com.z2devil.blog_api.api.mapper.ResourceMapper;
import com.z2devil.blog_api.api.service.IResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源（附件）表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "resource")
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    private final ResourceMapper resourceMapper;

    @CacheEvict(key = "'cate:' + #p0 + ':wid:' + #p1")
    @Override
    public void remove(Integer wCate, Integer wId) {
        LambdaQueryWrapper<Resource> fileWrapper = new LambdaQueryWrapper<>();
        fileWrapper.eq(Resource::getWCate, wCate)
                .eq(Resource::getWId, wId);
        resourceMapper.delete(fileWrapper);
    }
}
