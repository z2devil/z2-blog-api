package com.z2devil.blog_api.api.controller;


import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.bo.AddPreferBO;
import com.z2devil.blog_api.api.entity.enums.LimitType;
import com.z2devil.blog_api.api.service.IPreferService;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户评价文章表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-06-27
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prefer")
@Api(tags = "评价")
public class PreferController {

    private final IPreferService preferService;

    @ApiOperation(value="喜欢")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.LOGIN)
    @PostMapping
    public Result<Integer> like(@RequestBody AddPreferBO addPreferBO) {
        return Result.res(ResponseEnum.OK, preferService.like(addPreferBO.getWCate(), addPreferBO.getWId()));
    }

}
