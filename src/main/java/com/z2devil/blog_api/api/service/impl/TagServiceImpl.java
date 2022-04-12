package com.z2devil.blog_api.api.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.Tag;
import com.z2devil.blog_api.api.entity.bo.AddTagBO;
import com.z2devil.blog_api.api.entity.vo.TagVO;
import com.z2devil.blog_api.api.mapper.TagMapper;
import com.z2devil.blog_api.api.service.ITagService;
import com.z2devil.blog_api.api.service.mapStruct.AddTagConverter;
import com.z2devil.blog_api.api.service.mapStruct.TagConverter;
import com.z2devil.blog_api.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "tag")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    private final TagMapper tagMapper;

    private final AddTagConverter addTagConverter;

    private final TagConverter tagConverter;

    @Override
    @Cacheable(key = "'all'")
    public List<TagVO> getTags() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        return tagConverter.toDto(tagMapper.selectList(wrapper));
    }

    @Override
    @CacheEvict(key = "'all'")
    public Integer addTag(AddTagBO addTagBO) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getName, addTagBO.getName());
        Tag getTag = tagMapper.selectOne(wrapper);
        if (ObjectUtil.isEmpty(getTag)) {
            Tag tag = addTagConverter.toEntity(addTagBO);
            tagMapper.insert(tag);
            return tag.getId();
        }else {
            throw new BaseException("标签已存在");
        }
    }

    @Override
    @CacheEvict(key = "'all'")
    public void removeTag(Integer id) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getId, id);
        tagMapper.delete(wrapper);
    }

}
