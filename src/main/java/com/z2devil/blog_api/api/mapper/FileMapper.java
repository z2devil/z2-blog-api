package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z2devil.blog_api.api.entity.File;
import com.z2devil.blog_api.api.entity.vo.FileListVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@CacheConfig(cacheNames = "file")
public interface FileMapper extends BaseMapper<File> {

    /**
     * 根据作品类型和作品id为文章统计喜欢数
     * @param cate
     * @param wId
     * @return
     */
    @Select({
            "SELECT",
            "    id, name, path, type, size, post_date",
            "FROM",
            "    file",
            "WHERE",
            "    id = #{param1} AND is_delete = 0",
            "LIMIT 1"
    })
    @Cacheable(key = "'id:' + #p0")
    FileListVO getOne(Integer id);

}
