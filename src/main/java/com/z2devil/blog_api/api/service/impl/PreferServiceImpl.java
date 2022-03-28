package com.z2devil.blog_api.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.Prefer;
import com.z2devil.blog_api.api.mapper.PreferMapper;
import com.z2devil.blog_api.api.service.IPreferService;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户评价表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-06-27
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "prefer")
public class PreferServiceImpl extends ServiceImpl<PreferMapper, Prefer> implements IPreferService {

    private final PreferMapper preferMapper;

    @Override
    @CachePut(key = "'cate:' + #p0 + ':wid:' + #p1", value = "prefer")
    public Integer like(Integer wCate, Integer wId) {
        LambdaQueryWrapper<Prefer> queryWrapper = new LambdaQueryWrapper<>();
        Integer signUserId = SignUserIdUtils.get();
        queryWrapper.eq(Prefer::getUId, signUserId)
                .eq(Prefer::getWCate, wCate)
                .eq(Prefer::getWId, wId);
        Prefer prefer = preferMapper.selectOne(queryWrapper);
        if (!ObjectUtils.isEmpty(prefer)) {
            prefer.setIsDelete(prefer.getIsDelete() == 0 ? 1 : 0);
            preferMapper.updateById(prefer);
        }else {
            preferMapper.insert(new Prefer(signUserId, wCate, wId, 0));
        }
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Prefer::getWCate, wCate)
                .eq(Prefer::getWId, wId)
                .eq(Prefer::getIsDelete, false);
        return preferMapper.selectCount(queryWrapper);
    }
}
