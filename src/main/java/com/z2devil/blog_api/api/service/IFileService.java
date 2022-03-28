package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.File;
import com.z2devil.blog_api.api.entity.bo.FileAddBO;

import java.util.Map;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface IFileService extends IService<File> {

    /** 
     * 预请求
     * @params []
     * @return Map<String,String>
     * @author z2devil
     * @date 2021/9/18
     */ 
    Map<String, String> pre();

    /**
     * 添加文件
     * @params [fileAddBO]
     * @return Integer
     * @author z2devil
     * @date 2021/9/19
     */
    Integer add(FileAddBO fileAddBO);

//    /**
//     * 上传（OSS）
//     * @params [file]
//     * @return Map<String,String>
//     * @author z2devil
//     * @date 2021/9/16
//     */
//    Map<String, String> upload(MultipartFile file);

//    /**
//     * 获取url
//     * @params [id]
//     * @return String
//     * @author z2devil
//     * @date 2021/9/16
//     */
//    String getUrl(Integer id);

//    /**
//     * 下载文件
//     * @params [response, id, style]
//     * @return void
//     * @author z2devil
//     * @date 2021/9/16
//     */
//    void download(HttpServletResponse response, Integer id, String style);

}
