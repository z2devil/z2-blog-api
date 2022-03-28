package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z2devil.blog_api.api.entity.Resource;
import com.z2devil.blog_api.api.entity.vo.FileListVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 资源（附件）表 Mapper 接口
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@CacheConfig(cacheNames = "resource")
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 获取文件列表
     * @param wId
     * @return
     */
    @Select({
            "SELECT",
            "    file.id, name, path, type, size, post_date",
            "FROM",
            "    resource,",
            "    file",
            "WHERE",
            "    f_id = file.id AND w_cate = #{param1}",
            "        AND w_id = #{param2}",
            "        AND file.is_delete = 0"
    })
    @Cacheable(key = "'cate:' + #p0 + ':wid:' + #p1")
    List<FileListVO> getFiles(Integer wCate, Integer wId);

}
