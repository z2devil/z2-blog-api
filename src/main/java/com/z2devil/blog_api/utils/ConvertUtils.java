package com.z2devil.blog_api.utils;

import org.springframework.beans.BeanUtils;

/**
 * @program: blog_api
 * @description: 实体类转换器工具类
 * @auther: z2devil
 * @create: 2021-05-21
 **/
public class ConvertUtils {

    /**
     * 拷贝
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static final <T>T beanCopy(Object source,Class<T> targetClass){
        try {
            if(source == null || targetClass == null){
                return null;
            }
            T doInstance = targetClass.newInstance();
            BeanUtils.copyProperties(source, doInstance);
            return doInstance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
