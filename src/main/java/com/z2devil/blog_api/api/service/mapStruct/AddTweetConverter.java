package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.Tweet;
import com.z2devil.blog_api.api.entity.bo.AddTweetBO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 添加动态转换器
 * @author: z2devil
 * @create: 2021-06-27
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddTweetConverter extends MapStructMapper<AddTweetBO, Tweet> {
}
