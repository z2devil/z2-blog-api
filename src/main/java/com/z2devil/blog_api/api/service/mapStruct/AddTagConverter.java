package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.Tag;
import com.z2devil.blog_api.api.entity.bo.AddTagBO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 添加标签转换器
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddTagConverter  extends MapStructMapper<AddTagBO, Tag> {
}
