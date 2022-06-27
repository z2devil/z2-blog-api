package com.z2devil.blog_api.api.service.mapStruct;

import com.z2devil.blog_api.api.entity.File;
import com.z2devil.blog_api.api.entity.vo.FileListVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: blog_api
 * @description: 文件列表转换器
 * @author: z2devil
 * @create: 2021-07-15
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileListConverter extends MapStructMapper<FileListVO, File> {
}
