package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.Tweet;
import com.z2devil.blog_api.api.entity.bo.AddTweetBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.TweetListVO;

/**
 * <p>
 * 动态表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface ITweetService extends IService<Tweet> {

    /**
     * 获取动态列表
     * @param pageBO
     * @return
     */
    IPage<TweetListVO> getAllTweets(PageBO pageBO);

    /**
     * 获取动态
     * @param id
     * @return
     */
    TweetListVO getTweet(Integer id);

    /**
     * 获取某个用户的动态列表
     * @param pageBO
     * @param uid
     * @return
     */
    IPage<TweetListVO> getTweetsByUid(PageBO pageBO, Integer uid);

    /**
     * 通过关键词检索动态
     * @param pageBO
     * @param keyword
     * @return
     */
    IPage<TweetListVO> getTweetsByKeyword(PageBO pageBO, String keyword);

    /**
     * 添加动态
     * @param addTweetBO
     */
    void addTweet(AddTweetBO addTweetBO);

    /**
     * 删除动态
     * @param id
     */
    void removeTweet(Integer id);

}
