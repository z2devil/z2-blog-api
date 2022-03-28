package com.z2devil.blog_api.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.ConfigOptions;
import com.z2devil.blog_api.api.mapper.ConfigOptionsMapper;
import com.z2devil.blog_api.api.service.IConfigOptionsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-07-15
 */
@Service
public class ConfigOptionsServiceImpl extends ServiceImpl<ConfigOptionsMapper, ConfigOptions> implements IConfigOptionsService {

}
