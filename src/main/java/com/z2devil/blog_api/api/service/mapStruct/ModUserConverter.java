package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.bo.ModUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 用户信息转换器
 * @author: z2devil
 * @create: 2021-05-22
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModUserConverter extends MapStructMapper<ModUserBO, User>  {
}
