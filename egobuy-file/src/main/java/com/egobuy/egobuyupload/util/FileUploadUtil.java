package com.egobuy.egobuyupload.util;

import com.egobuy.egobuyupload.constant.Constant;
import com.google.gson.Gson;
import com.igeekhome.egobuy.util.ResponseEntity;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 七牛云文件上传工具类
 * @author yadonghe
 * @date 2020-03-02 13:35
 */
public class FileUploadUtil {
    private static Configuration cfg = null;
    private static UploadManager uploadManager = null;
    private static Auth auth = null;
    static{
        cfg = new Configuration(Region.region0());
        uploadManager = new UploadManager(cfg);
        auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
    }

    /**
     * 基于输入流实现文件上传
     * @param inputStream
     * @return
     * @throws QiniuException
     */
    public static String uploadByStream(InputStream inputStream) throws QiniuException {

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        //基于文件file对象构建输入流对象
        String upToken = auth.uploadToken(Constant.BUCKET);

        Response response = uploadManager.put(inputStream, key, upToken, null, null);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);//其实上传后保存的文件名

        return Constant.IMAGE_BASE_URL + putRet.hash;
    }
}
