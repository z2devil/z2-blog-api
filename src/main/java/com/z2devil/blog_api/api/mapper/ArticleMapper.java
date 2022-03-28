package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z2devil.blog_api.annotation.Column;
import com.z2devil.blog_api.annotation.Queries;
import com.z2devil.blog_api.annotation.Query;
import com.z2devil.blog_api.api.entity.Article;
import com.z2devil.blog_api.api.entity.enums.ColumnType;
import com.z2devil.blog_api.api.entity.vo.ArticleDetailVO;
import com.z2devil.blog_api.api.entity.vo.ArticleListVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 专栏文章表 Mapper 接口
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Component
@CacheConfig(cacheNames = "article")
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 全查询文章列表
     * @param keyword
     * @param tags
     * @param page
     * @return
     */
    @Select({
            "<script>",
                "SELECT article.id, u_id, title, summary, post_date, cover",
                "FROM article, article_tag",
                "WHERE article.id = a_id",
                "AND article.is_delete = 0",
                "<trim prefix=\"AND\" prefixOverrides=\"and | or\">",
                "   <if test=\"keyword != null\">",
                "       AND (title LIKE CONCAT('%', #{keyword},'%') OR summary LIKE CONCAT('%', #{keyword},'%') )",
                "   </if>",
                "   <if test=\"tags != null\">",
                "       AND t_id IN",
                "       <foreach collection='tags' item='tag' open='(' separator=',' close=')'>",
                "           #{tag}",
                "       </foreach>",
                "   </if>",
                "</trim>",
                "GROUP BY a_id",
            "</script>"
            })
    @Results({
            @Result(property = "id", column="id"),
            @Result(property = "uId", column="u_id"),
            @Result(property = "cId", column="cover"),
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "comments",
                    select = "com.z2devil.blog_api.api.mapper.CommentMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cId")
            },
                    property = "cover",
                    select = "com.z2devil.blog_api.api.mapper.FileMapper.getOne"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
    })
    IPage<ArticleListVO> getArticles(@Param("keyword") String keyword, @Param("tags") Integer[] tags, Page<Article> page);


    /**
     * 获取文章
     * @param id
     * @return
     */
    @Select("SELECT id, u_id, title, summary, post_date, cover " +
            "FROM " +
            "article " +
            "WHERE id = #{id} " +
            "AND is_delete=0")
    @Results({
            @Result(property = "id", column="id"),
            @Result(property = "uId", column="u_id"),
            @Result(property = "cId", column="cover"),
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "comments",
                    select = "com.z2devil.blog_api.api.mapper.CommentMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cId")
            },
                    property = "cover",
                    select = "com.z2devil.blog_api.api.mapper.FileMapper.getOne"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
    })
    ArticleListVO getArticle(Integer id);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Select("SELECT id, u_id, title, summary, content, post_date, words_num, cover " +
            "FROM " +
            "article " +
            "WHERE id = #{id} " +
            "AND is_delete=0")
    @Results({
            @Result(property = "id", column="id"),
            @Result(property = "uId", column="u_id"),
            @Result(property = "cId", column="cover"),
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "comments",
                    select = "com.z2devil.blog_api.api.mapper.CommentMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cId")
            },
                    property = "cover",
                    select = "com.z2devil.blog_api.api.mapper.FileMapper.getOne"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "tags",
                    select = "com.z2devil.blog_api.api.mapper.TagMapper.getTagsByWId"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "files",
                    select = "com.z2devil.blog_api.api.mapper.ResourceMapper.getFiles"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "2"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
    })
    ArticleDetailVO getArticleDetail(Integer id);

}
