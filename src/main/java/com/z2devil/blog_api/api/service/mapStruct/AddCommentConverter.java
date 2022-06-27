package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.Comment;
import com.z2devil.blog_api.api.entity.bo.AddCommentBO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 添加评论转换器
 * @author: z2devil
 * @create: 2021-07-11
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddCommentConverter extends MapStructMapper<AddCommentBO, Comment> {
}
