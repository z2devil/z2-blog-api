package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z2devil.blog_api.annotation.Column;
import com.z2devil.blog_api.annotation.Queries;
import com.z2devil.blog_api.annotation.Query;
import com.z2devil.blog_api.api.entity.Comment;
import com.z2devil.blog_api.api.entity.enums.ColumnType;
import com.z2devil.blog_api.api.entity.vo.CommentListVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;

/**
 * <p>
 * 评论表 Mapper 接口
 * cate 1:动态; 2:文章;
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@CacheConfig(cacheNames = "comment")
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 计算评论个数
     * @params [cate, id]
     * @return Integer
     * @author z2devil
     * @date 2021/10/19
     */
    @Select({
            "<script>",
                "SELECT",
                "    COUNT(*)",
                "FROM",
                "    comment",
                "WHERE",
                "    is_delete = 0",
                "<trim prefix=\"AND\" prefixOverrides=\"and | or\">",
                "   <if test=\"cate == 0\">",
                "       AND r_id = #{id}",
                "   </if>",
                "   <if test=\"cate == 1 or cate == 2\">",
                "       AND w_cate = #{cate} AND w_id = #{id} AND r_id = 0",
                "   </if>",
                "</trim>",
            "</script>"
    })
    Integer count(@Param("cate") Integer cate, @Param("id") Integer id);


    /**
     * 获取评论列表
     * @param cate
     * @param id
     * @param page
     * @return
     */
    @Select("select * from comment where w_cate = #{param1} and w_id = #{param2} and r_id=0 and is_delete=0")
    @Results({
            @Result(property = "id", column="id")
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "comments",
                    select = "com.z2devil.blog_api.api.mapper.CommentMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
    })
    IPage<CommentListVO> getAllComments(Integer cate, Integer id, Page<Comment> page);

    /**
     * 获取评论
     * @param id
     * @return
     */
    @Select("select * from comment where id = #{id} and is_delete=0")
    @Results({
            @Result(property = "id", column="id")
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "comments",
                    select = "com.z2devil.blog_api.api.mapper.CommentMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
    })
    CommentListVO getComment(Integer id);

    /**
     * 获取子评论列表（分页）
     * @param id
     * @param page
     * @return
     */
    @Select("select * from comment where r_id = #{id} and is_delete=0")
    @Results({
            @Result(property = "id", column="id"),
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "0"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
    })
    IPage<CommentListVO> getChildComments(Integer id, Page<Comment> page);

}
