package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.Article;
import com.z2devil.blog_api.api.entity.bo.ModArticleBO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 修改文章BO
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModArticleConverter extends MapStructMapper<ModArticleBO, Article> {

}
