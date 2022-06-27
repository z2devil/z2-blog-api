package com.z2devil.blog_api.api.controller;


import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.bo.AddTagBO;
import com.z2devil.blog_api.api.entity.enums.LimitType;
import com.z2devil.blog_api.api.entity.vo.TagVO;
import com.z2devil.blog_api.api.service.ITagService;
import com.z2devil.blog_api.api.service.mapStruct.AddTagConverter;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
@Api(tags = "标签")
public class TagController {

    private final ITagService tagService;

    private final AddTagConverter addTagConverter;

    @ApiOperation(value = "获取标签列表")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping
    public Result<List<TagVO>> getTagList() {
        return Result.res(ResponseEnum.OK, tagService.getTags());
    }

    @ApiOperation(value="新增标签")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PostMapping
    public Result addTag(@RequestBody AddTagBO addTagBO) {
        return Result.res(ResponseEnum.OK, "新增成功", tagService.addTag(addTagBO));
    }

    @ApiOperation(value="删除标签")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @DeleteMapping("/{id}")
    public Result deleteTag(@PathVariable Integer id) {
        tagService.removeTag(id);
        return Result.res(ResponseEnum.OK, "删除成功");
    }
}
