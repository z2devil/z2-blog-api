package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z2devil.blog_api.api.entity.Prefer;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * 用户评价表 Mapper 接口
 * cate 0:评论; 1:动态; 2:文章;
 * </p>
 *
 * @author z2devil
 * @since 2021-06-27
 */
@CacheConfig(cacheNames = "prefer")
public interface PreferMapper extends BaseMapper<Prefer> {

    /**
     * 根据作品类型和作品id为文章统计喜欢数
     * @param cate
     * @param wId
     * @return
     */
    @Select("select count(*) from prefer where w_cate = #{param1} and w_id = #{param2} and is_delete=0")
    @Cacheable(key = "'cate:' + #p0 + ':wid:' + #p1")
    Integer count(Integer cate, Integer wId);

    /** 
     * 根据作品类型和作品id和用户id检查文章是否喜欢
     * @params [cate, wId, uId]
     * @return Boolean
     * @author z2devil
     * @date 2021/10/24
     */ 
    @Select({
            "SELECT",
            "    1",
            "FROM",
            "    prefer",
            "WHERE",
            "    w_cate = #{param1} AND w_id = #{param2} AND u_id = #{param3}",
            "        AND is_delete = 0",
            "LIMIT 1"
    })
    Boolean isLiked(Integer cate, Integer wId, Integer uId);

}
