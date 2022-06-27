package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.File;
import com.z2devil.blog_api.api.entity.bo.FileAddBO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 新增文件转换器
 * @author: z2devil
 * @create: 2021-09-19
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileAddConverter extends MapStructMapper<FileAddBO, File>  {
}
