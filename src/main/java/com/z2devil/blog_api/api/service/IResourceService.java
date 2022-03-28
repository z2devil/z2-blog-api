package com.z2devil.blog_api.api.service;

import com.z2devil.blog_api.api.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资源（附件）表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 批量删除(根据作品类型、作品id)
     * @params [wCate, wId]
     * @return void
     * @author z2devil
     * @date 2022/1/27
     */
    void remove(Integer wCate, Integer wId);

}
