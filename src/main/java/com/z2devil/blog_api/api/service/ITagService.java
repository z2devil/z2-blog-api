package com.z2devil.blog_api.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z2devil.blog_api.api.entity.Tag;
import com.z2devil.blog_api.api.entity.bo.AddTagBO;
import com.z2devil.blog_api.api.entity.vo.TagVO;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
public interface ITagService extends IService<Tag> {

    /**
     * 获取所有标签
     * @params []
     * @return IPage<Tag>
     * @author z2devil
     * @date 2021/10/24
     */
    List<TagVO> getTags();

    /**
     * 新增标签
     * @params [addTagBO]
     * @return void
     * @author z2devil
     * @date 2021/12/18
     */
    Integer addTag(AddTagBO addTagBO);

    /**
     * 删除标签
     * @params [id]
     * @return Integer
     * @author z2devil
     * @date 2022/4/12
     */
    void removeTag(Integer id);

}
