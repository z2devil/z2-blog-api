package com.z2devil.blog_api.api.controller;


import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.annotation.Limit;
import com.z2devil.blog_api.api.entity.bo.FileAddBO;
import com.z2devil.blog_api.api.entity.enums.LimitType;
import com.z2devil.blog_api.api.service.IFileService;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
@Api(tags = "文件")
public class FileController {

    private final IFileService fileService;

    @ApiOperation(value = "上传预请求")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PostMapping("/pre")
    public Result<Map<String, String>> pre() {
        return Result.res(ResponseEnum.OK, "获取成功", fileService.pre());
    }

//    @SneakyThrows
//    @ApiOperation(value = "上传")
//    @Access(AccessLevel.ADMIN)
//    @PostMapping("/upload")
//    public Result<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
//        return Result.res(ResponseEnum.OK, "上传成功", fileService.upload(file));
//    }

    @SneakyThrows
    @ApiOperation(value = "新增")
    @Limit(period = 10, count = 10, limitType = LimitType.IP)
    @Access(AccessLevel.ADMIN)
    @PostMapping
    public Result<Integer> add(@RequestBody FileAddBO fileAddBO) {
        return Result.res(ResponseEnum.OK, "上传成功", fileService.add(fileAddBO));
    }

//    @ApiOperation(value = "获取文件")
//    @GetMapping
//    public Result<FileListVO> getOne(Integer id) {
//        return Result.res(ResponseEnum.OK, "上传成功", fileListConverter.toDto(fileService.getById(id)));
//    }

//    @SneakyThrows
//    @ApiOperation(value = "获取url")
//    @GetMapping("/getUrl")
//    public Result<String> getUrl(Integer id) {
//        return Result.res(ResponseEnum.OK,"获取成功", fileService.getUrl(id));
//    }
//
//    @SneakyThrows
//    @ApiOperation(value = "下载")
//    @GetMapping("/download/{id}")
//    public void download(HttpServletResponse response, @PathVariable Integer id, String style) {
//        fileService.download(response, id, style);
//    }

}
