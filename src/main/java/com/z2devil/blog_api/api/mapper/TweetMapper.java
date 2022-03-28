package com.z2devil.blog_api.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z2devil.blog_api.annotation.Column;
import com.z2devil.blog_api.annotation.Queries;
import com.z2devil.blog_api.annotation.Query;
import com.z2devil.blog_api.api.entity.Tweet;
import com.z2devil.blog_api.api.entity.enums.ColumnType;
import com.z2devil.blog_api.api.entity.vo.TweetListVO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 动态表 Mapper 接口
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface TweetMapper extends BaseMapper<Tweet> {

    /**
     * 获取动态列表
     * @param page
     * @return
     */
    @Select("select * from tweet where is_delete=0")
    @Results({
            @Result(property = "id", column="id")
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "comments",
                    select = "com.z2devil.blog_api.api.mapper.CommentMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "files",
                    select = "com.z2devil.blog_api.api.mapper.ResourceMapper.getFiles"),
    })
    IPage<TweetListVO> getAllTweets(Page<Tweet> page);


    /**
     * 获取动态
     * @param id
     * @return
     */
    @Select("select * from tweet where id = #{id} and is_delete=0")
    @Results({
            @Result(property = "id", column="id")
    })
    @Queries({
            @Query(column = "uId", property = "userInfo",
                    select = "com.z2devil.blog_api.api.mapper.UserMapper.getUserInfo"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "likes",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "comments",
                    select = "com.z2devil.blog_api.api.mapper.CommentMapper.count"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id"),
                    @Column(type = ColumnType.LOGGED_USER, customMethod = true)
            },
                    property = "isLiked",
                    select = "com.z2devil.blog_api.api.mapper.PreferMapper.isLiked"),
            @Query(columns = {
                    @Column(type = ColumnType.INT, name = "cate", value = "1"),
                    @Column(type = ColumnType.INT, name = "id")
            },
                    property = "files",
                    select = "com.z2devil.blog_api.api.mapper.ResourceMapper.getFiles"),
    })
    TweetListVO getTweet(Integer id);

}
