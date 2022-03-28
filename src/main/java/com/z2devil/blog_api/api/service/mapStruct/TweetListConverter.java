package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.Tweet;
import com.z2devil.blog_api.api.entity.vo.TweetListVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 动态列表转换器
 * @author: z2devil
 * @create: 2021-05-21
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TweetListConverter extends MapStructMapper<TweetListVO, Tweet> {

}
