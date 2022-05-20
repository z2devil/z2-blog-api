package com.z2devil.blog_api.api.controller;


import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.bo.ModUserBO;
import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import com.z2devil.blog_api.api.entity.enums.LimitType;
import com.z2devil.blog_api.api.service.IUserService;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Api(tags = "用户")
public class UserController {

    private final IUserService userService;

    @ApiOperation(value = "获取用户信息")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping("/{id}")
    public Result<UserInfoDTO> getUserInfo(@PathVariable Integer id) {
        return Result.res(ResponseEnum.OK, userService.getUserInfo(id));
    }

    @ApiOperation(value = "修改用户信息")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.LOGIN)
    @PutMapping
    public Result modUserInfo(@RequestBody ModUserBO modUserBO) {
        userService.modUserInfo(SignUserIdUtils.get(), modUserBO);
        return Result.res(ResponseEnum.OK);
    }

}
