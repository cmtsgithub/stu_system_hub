package com.stu.controller;

import com.stu.utils.FastDFSClient;
import com.stu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value = "/image/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadImage(MultipartFile file){
        try {
            // 首先接收页面上传的文件
            byte[] content = file.getBytes();
            // 取出文件的扩展名
            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 把这个文件内容上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
            String url = fastDFSClient.uploadFile(content, ext);
            // 从配置文件中取图片服务器的url
            // 创建返回结果对象
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("url", IMAGE_SERVER_URL + url);
            // 返回结果
            return JsonResult.build(200, "上传成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.build(400, "上传失败, 请重试");
        }
    }
}
