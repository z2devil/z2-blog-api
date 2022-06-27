package com.z2devil.blog_api.api.controller;


import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.ConfigOptions;
import com.z2devil.blog_api.api.entity.enums.LimitType;
import com.z2devil.blog_api.api.service.IConfigOptionsService;
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
 * 配置表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-07-15
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/config-options")
@Api(tags = "配置选项")
public class ConfigOptionsController {

    private final IConfigOptionsService configOptionsService;

    @ApiOperation(value = "获取配置列表")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping
    public Result<List<ConfigOptions>> getOptions() {
        return Result.res(ResponseEnum.OK, configOptionsService.list());
    }

    @ApiOperation(value = "获取配置")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping("/{id}")
    public Result<ConfigOptions> getOption(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, "请求成功", configOptionsService.getById(id));
    }

    @ApiOperation(value = "添加或修改配置")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PostMapping
    public Result saveOrUpdateOption(@RequestBody ConfigOptions option) {
        configOptionsService.saveOrUpdate(option);
        return Result.res(ResponseEnum.OK);
    }

    @ApiOperation(value = "删除配置")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @DeleteMapping("/{id}")
    public Result deleteOption(Integer id) {
        configOptionsService.removeById(id);
        return Result.res(ResponseEnum.OK, "删除成功");
    }
}
