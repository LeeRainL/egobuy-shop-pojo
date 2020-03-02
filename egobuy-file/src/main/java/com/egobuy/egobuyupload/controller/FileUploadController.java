package com.egobuy.egobuyupload.controller;

import com.egobuy.egobuyupload.util.FileUploadUtil;
import com.google.gson.Gson;
import com.igeekhome.egobuy.util.ResponseEntity;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author yadonghe
 * @date 2020-03-02 11:21
 */
@RequestMapping("/upload")
@RestController
public class FileUploadController {

    @RequestMapping("/item")
    public ResponseEntity item(MultipartFile file) {
        try {
            String url = FileUploadUtil.uploadByStream(file.getInputStream());
            return ResponseEntity.success(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.fail("文件上传失败");
    }

}
