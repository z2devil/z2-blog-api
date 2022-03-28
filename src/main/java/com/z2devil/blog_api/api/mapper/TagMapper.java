package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z2devil.blog_api.api.entity.Tag;
import com.z2devil.blog_api.api.entity.vo.TagVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@CacheConfig(cacheNames = "tag")
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 获取文章标签列表(根据文章id)
     * @param wId
     * @return
     */
    @Select({
            "SELECT",
            "   tag.id, tag.name",
            "FROM" +
            "   article_tag, tag",
            "WHERE" +
            "   article_tag.t_id = tag.id",
            "       AND article_tag.a_id = #{wId} ",
            "       AND article_tag.is_delete = 0",
            "       AND tag.is_delete = 0"
    })
    @Cacheable(key = "'wid:' + #p0")
    List<TagVO> getTagsByWId(Integer wId);

    /**
     * 插入文章标签
     * @param aId
     * @param tId
     */
    @Insert({
            "INSERT INTO",
            "   article_tag",
            "   (a_id, t_id, is_delete)",
            "VALUES",
            "   (#{param1}, #{param2}, 0)"
    })
    void insertArticleTag(Integer aId, Integer tId);

}
