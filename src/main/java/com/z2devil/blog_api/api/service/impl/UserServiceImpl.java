package com.z2devil.blog_api.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.bo.ModUserBO;
import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import com.z2devil.blog_api.api.mapper.UserMapper;
import com.z2devil.blog_api.api.service.IUserService;
import com.z2devil.blog_api.api.service.mapStruct.ModUserConverter;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    private final ModUserConverter modUserConverter;

    @Override
    @Cacheable(key = "'id:' + #p0")
    public UserInfoDTO getUserInfo(Integer id) {
        return userMapper.getUserInfo(id);
    }

    @Override
    @CacheEvict(key = "'id:' + #p0")
    public void modUserInfo(Integer id, ModUserBO modUserBO) {
        User user = modUserConverter.toEntity(modUserBO);
        user.setId(SignUserIdUtils.get());
        userMapper.updateById(user);
    }
}
