package com.z2devil.blog_api.api.service.impl;

import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.File;
import com.z2devil.blog_api.api.entity.bo.FileAddBO;
import com.z2devil.blog_api.api.mapper.FileMapper;
import com.z2devil.blog_api.api.service.IFileService;
import com.z2devil.blog_api.api.service.mapStruct.FileAddConverter;
import com.z2devil.blog_api.utils.OSSUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    private final OSSUtil ossUtil;

    private final FileMapper fileMapper;

    private final FileAddConverter fileAddConverter;

    /**
     * 根目录
     */
    @Value("${oss.root-path}")
    private String rootPath;

    @Override
    public Map<String, String> pre() {
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, rootPath);
        return ossUtil.pre(expiration, policyConds);
    }

    @Override
    public Integer add(FileAddBO fileAddBO) {
        File file = fileAddConverter.toEntity(fileAddBO);
        fileMapper.insert(file);
        return file.getId();
    }

}
