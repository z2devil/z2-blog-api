package com.z2devil.blog_api.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PolicyConditions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: blog_api
 * @description: OSS对象存储工具类
 * @author: z2devil
 * @create: 2021-07-14
 **/
@Slf4j
@Component
public class OSSUtil {

    /**
     * 地域节点
     */
    @Value("${oss.endpoint}")
    private String endpoint;

    /**
     * 你的AccessKeyID
     */
    @Value("${oss.access-key-id}")
    private String accessKeyId;

    /**
     * 秘钥
     */
    @Value("${oss.access-key-secret}")
    private String accessKeySecret;

    /**
     * 默认的存储桶名称
     */
    @Value("${oss.bucket-name}")
    private String bucketName;

    /**
     * 上传预请求
     * @params [expiration, policyConds]
     * @return Map<String,String>
     * @author z2devil
     * @date 2021/9/18
     */
    @SneakyThrows
    public Map<String, String> pre(Date expiration, PolicyConditions policyConds) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);
        Map<String, String> res = new HashMap<>(2);
        res.put("policy", encodedPolicy);
        res.put("signature", postSignature);
        return res;
    }

    /**
     * 存放对象
     * @param path
     * @param stream
     */
    public void putObject(String path, InputStream stream) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, path, stream);
        ossClient.shutdown();
    }

    /**
     * 获取Url
     * @param path
     * @return
     */
    public String getUrl(String path) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置这个文件地址的有效时间
        Date expiration = new Date(new Date().getTime() + 3600L * 1000);
        String url = ossClient.generatePresignedUrl(bucketName, path, expiration).toString();
        ossClient.shutdown();
        return url;
    }

    /**
     * 下载文件（处理图片）
     * @param response
     * @param path
     * @param fileName
     */
    @SneakyThrows
    public void download(HttpServletResponse response, String path, String fileName, String style) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        GetObjectRequest request = new GetObjectRequest(bucketName, path);
        if (style != null) {
            request.setProcess(style);
        }
        OSSObject ossObject = ossClient.getObject(request);

        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        // 发送给客户端的数据
        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(ossObject.getObjectContent());
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, i);
            outputStream.flush();
            i = bis.read(buff);
        }
        outputStream.close();
        bis.close();
        ossClient.shutdown();
    }

}
