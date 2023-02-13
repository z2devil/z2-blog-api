## z2-blog-api 个人网站
[![version](https://img.shields.io/badge/-v1.0-f15642)](https://github.com/z2devil/z2-blog-web/) [![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/z2devil/z2-blog-web/blob/main/LICENSE)
</br>个人博客前端项目，主要包含无感用户登录注册、文字图片内容社区、Markdown 编辑器、OSS 文件上传等功能。

## 示例
[我的个人网站](https://z2devil.cn?_blank)

## 技术栈
java、springboot、mybatis、mybatis plus、mysql、redis、、oss、log4j、swagger

## 功能

### 安全
1. 权限过滤、权限拦截（注解方式）
2. 请求限流（注解方式）

### 用户
1. 登录或注册、随机产生用户昵称
2. 邮箱发送验证码、readis 实现验证码过期时间
3. token 校验、自动续期
4. RequestContextHolder 保存用户信息

### 异常处理
1. 统一异常处理
2. 权限异常处理

### 增删改查
1. 基础的增删改查、多表查询、条件查询等
2. 自定义关联(集合)查询（注解方式）
3. 统一返回数据结构
4. redis 缓存

