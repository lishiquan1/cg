package com.changgou.file.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.file.file.FastDFSFile;
import com.changgou.file.util.FastDFSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/2 1:46 下午
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        // 封装文件信息
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );
        // 调用FastDFSUtil
        String[] uploads = FastDFSUtil.upload(fastDFSFile);
        // 拼接访问地址 url = http://10.211.55.7:8080/group1/M00/00/00/abc.jpg
        String url = FastDFSUtil.getTrackerInfo() + "/" + uploads[0] + "/" + uploads[1];
        return new Result<>(true, StatusCode.OK, "上传成功", url);
    }
}
