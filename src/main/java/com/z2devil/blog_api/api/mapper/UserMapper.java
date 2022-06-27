package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@CacheConfig(cacheNames = "user")
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id获取userInfoDto
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    @Cacheable(key = "'id:' + #p0")
    UserInfoDTO getUserInfo(Integer id);

}
