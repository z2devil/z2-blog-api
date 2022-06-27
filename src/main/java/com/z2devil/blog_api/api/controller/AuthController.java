package com.z2devil.blog_api.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.bo.UserSignBO;
import com.z2devil.blog_api.api.entity.dto.UserInfoDTO;
import com.z2devil.blog_api.api.entity.enums.LimitType;
import com.z2devil.blog_api.api.service.IUserService;
import com.z2devil.blog_api.api.service.mapStruct.UserInfoConverter;
import com.z2devil.blog_api.config.bean.AuthProperties;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.*;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: blog_api
 * @description: 授权 控制器
 * @author: z2devil
 * @create: 2021-05-22
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Api(tags = "授权")
public class AuthController {

    private final RedisUtils redisUtils;

    private final IUserService userService;

    private final MailUtils mailUtils;

    private final UserInfoConverter userInfoConverter;

    private final AuthProperties authProperties;

    @ApiOperation("获取当前用户信息")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.LOGIN)
    @GetMapping("/info")
    public Result<UserInfoDTO> getUserInfo() {
        User user = userService.getById(SignUserIdUtils.get());
        UserInfoDTO userInfoDto = userInfoConverter.toDto(user);
        return Result.res(ResponseEnum.OK, userInfoDto);
    }

    @ApiOperation("向邮箱发送验证码")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @GetMapping("verify-code")
    public Result sendVerifyCode(String email) throws Exception {
        String sentCodeKey = authProperties.getCodePrefix()+email;
        Integer deltaTime =  authProperties.getCodeExpireTime() - (int) redisUtils.getExpire(sentCodeKey);
        if (redisUtils.get(sentCodeKey) != null && deltaTime < authProperties.getCodeCoolingTime()) {
            return Result.res(ResponseEnum.INTERNAL_SERVER_ERROR,
                    "该邮箱已发送过验证码，请"+(authProperties.getCodeCoolingTime() - deltaTime)+"秒后再试");
        }
        String code = CaptchaUtils.randomStr(authProperties.getCodeLength());
        redisUtils.set(sentCodeKey, code+"-"+authProperties.getCodeLifeNumber(), authProperties.getCodeExpireTime());
        mailUtils.sendSimpleMail(email, "【验证码】z2devil个人博客",
                "您的验证码为："+code+"，"+authProperties.getCodeExpireTime()/60+"分钟内有效。");
        return Result.res(ResponseEnum.OK);
    }

    @ApiOperation("登录or注册")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @PostMapping("sign")
    public Result<Map> sign(@RequestBody UserSignBO userSignBO) {
        // 校验验证码
        String verifyCodeKey = authProperties.getCodePrefix()+userSignBO.getEmail();
        String verifyCodeValue = (String) redisUtils.get(verifyCodeKey);
        if (verifyCodeValue == null) {
            return Result.res(ResponseEnum.INTERNAL_SERVER_ERROR,"验证码过期或错误");
        }else {
            String[] verifyCodeArr = verifyCodeValue.split("-");
            if (!verifyCodeArr[0].equals(userSignBO.getVerifyCode())) {
                int chance = Integer.parseInt(verifyCodeArr[1])-1;
                if (chance == 0) {
                    redisUtils.del(verifyCodeKey);
                    return Result.res(ResponseEnum.INTERNAL_SERVER_ERROR,"验证码失败次数过多，请重新发送验证码");
                }else {
                    redisUtils.set(verifyCodeKey, verifyCodeArr[0]+"-"+chance);
                    return Result.res(ResponseEnum.INTERNAL_SERVER_ERROR,"验证码错误，您还有" + chance + "次机会");
                }
            }
        }
        redisUtils.del(verifyCodeKey);
        // 根据邮箱查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getEmail, userSignBO.getEmail());
        User user = userService.getOne(queryWrapper);
        // 如果用户不存在，则注册
        if (ObjectUtils.isEmpty(user)) {
            user = new User(userSignBO.getEmail(), 0, StringUtils.randomNickname());
            userService.save(user);
        }
        // 获取token并保存到Redis
        String token = JwtUtil.createToken(user.getId(), userSignBO.getEmail());
        redisUtils.set(authProperties.getTokenPrefix()+user.getId(), token, authProperties.getTokenExpireTime());
        // 封装返回数据（token和用户信息）
        UserInfoDTO userInfoDto = userInfoConverter.toDto(user);
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", token);
            put("user", userInfoDto);
        }};
        return Result.res(ResponseEnum.OK, authInfo);
    }

//    @ApiOperation("登出")
//    @Access(AccessLevel.LOGIN)
//    @PostMapping("logout")
//    public Result logout() {
//        int signedUserId = SignUserIdUtils.get();
//        if (signedUserId == 0) {
//            return Result.res(ResponseEnum.INTERNAL_SERVER_ERROR, "未登录用户");
//        }
//        redisUtils.del(authProperties.getTokenPrefix()+signedUserId);
//        return Result.res(ResponseEnum.OK);
//    }
}
