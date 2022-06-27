package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.Prefer;

/**
 * <p>
 * 用户评价表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-06-27
 */
public interface IPreferService extends IService<Prefer> {

    /**
     * 喜欢
     * @param wCate
     * @param wId
     * @return
     */
    Integer like(Integer wCate, Integer wId);

}
