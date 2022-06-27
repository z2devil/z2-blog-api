package com.z2devil.blog_api.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.Resource;
import com.z2devil.blog_api.api.entity.Tweet;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.bo.AddTweetBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.TweetListVO;
import com.z2devil.blog_api.api.mapper.ResourceMapper;
import com.z2devil.blog_api.api.mapper.TweetMapper;
import com.z2devil.blog_api.api.service.ITweetService;
import com.z2devil.blog_api.api.service.IUserService;
import com.z2devil.blog_api.api.service.mapStruct.AddTweetConverter;
import com.z2devil.blog_api.exception.AccessException;
import com.z2devil.blog_api.exception.BaseException;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 动态表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TweetServiceImpl extends ServiceImpl<TweetMapper, Tweet> implements ITweetService {

    private final AddTweetConverter addTweetConverter;

    private final TweetMapper tweetMapper;

    private final ResourceMapper resourceMapper;

    private final IUserService userService;

    @Override
    public IPage<TweetListVO> getAllTweets(PageBO pageBO) {
        IPage<TweetListVO> result = tweetMapper.getAllTweets(pageBO.buildPage());
        return result;
    }

    @Override
    public TweetListVO getTweet(Integer id) {
        TweetListVO result = tweetMapper.getTweet(id);
        return result;
    }

    @Override
    public IPage<TweetListVO> getTweetsByUid(PageBO pageBO, Integer uid) {
        return null;
    }

    @Override
    public IPage<TweetListVO> getTweetsByKeyword(PageBO pageBO, String keyword) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTweet(AddTweetBO addTweetBO) {
        addTweetBO.setUId(SignUserIdUtils.get());
        Tweet tweet = addTweetConverter.toEntity(addTweetBO);
        tweetMapper.insert(tweet);
        Integer[] resources = addTweetBO.getResources();
        for (Integer resource : resources) {
            resourceMapper.insert(new Resource(1, tweet.getId(), resource));
        }
    }

    @Override
    public void removeTweet(Integer id) {
        Tweet tweet = tweetMapper.selectById(id);
        User signedUser = userService.getById(SignUserIdUtils.get());
        if (!ObjectUtils.isEmpty(tweet)) {
            if (signedUser.getLv() < AccessLevel.MASTER.getLv() && !signedUser.getId().equals(tweet.getUId())) {
                throw new AccessException("权限异常");
            }
            removeById(id);
        }else {
            throw new BaseException("删除失败");
        }
    }

}
