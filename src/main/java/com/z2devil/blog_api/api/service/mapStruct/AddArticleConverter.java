package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.Article;
import com.z2devil.blog_api.api.entity.bo.AddArticleBO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 添加文章BO
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddArticleConverter extends MapStructMapper<AddArticleBO, Article> {

}
