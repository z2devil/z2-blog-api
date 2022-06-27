package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.bo.ModUserBO;
import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface IUserService extends IService<User> {

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    UserInfoDTO getUserInfo(Integer id);

    /** 
     * 修改用户信息
     * @params [modUserBO]
     * @return void
     * @author z2devil
     * @date 2021/11/8
     */ 
    void modUserInfo(Integer id, ModUserBO modUserBO);

}
