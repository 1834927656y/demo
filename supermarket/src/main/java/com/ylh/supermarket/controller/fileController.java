package com.ylh.supermarket.controller;

import com.ylh.supermarket.dto.R;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@CrossOrigin
@Tag(name="文件接口",description = "图片相关接口:增删改查")
@RestController
public class fileController {
    final static String PIC_PATH = "static/images/firms/"; // 图片存放的相对于项目的相对位置

    /**
     * 上传图片
     */
    @PostMapping("/api/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile image, HttpServletRequest request){
        System.out.println("上传图片");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //生成日期格式

        String savePath = "src/main/resources/" + PIC_PATH; // 存储路径
        File folder = new File(savePath); //生成带当前日期的文件路径

        if(!folder.isDirectory()){
            folder.mkdirs();
        }

        String randomName = image.getOriginalFilename(); //获取图片名
        //生成随机数确保唯一性，并加上图片后缀
        assert randomName != null;
        String saveName = UUID.randomUUID().toString() + randomName.substring(randomName.lastIndexOf("."),randomName.length());
        String absolutePath = folder.getAbsolutePath(); //转换成绝对路径

        try {
            File fileToSave = new File(absolutePath + File.separator + saveName);
            image.transferTo(fileToSave); // 图片存储到服务端
            String returnPath = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort() + "/images/firms/"  +"/"+ saveName;

            return returnPath;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
