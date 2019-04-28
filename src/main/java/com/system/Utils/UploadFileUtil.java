package com.system.Utils;

import com.system.pojo.UploadProgram;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadFileUtil {

    /**
     * OLD FOR JSP 上传文件至服务器，并返回存储文件的完整路径
     * @param request
     * @param program
     * @return
     * @throws Exception
     */
    public static String saveFile(HttpServletRequest request, UploadProgram program)throws Exception{
        MultipartFile[] files = program.getFiles();//上传的文件数据
        Integer type = program.getPType();//节目类型
        String user = program.getPSendPerson();//节目发送人id
        String path = null;
        String[] suffix= {".jpg",".mp4",".doc"};//保存的文件后缀
        if (type == 0){
            path = request.getServletContext().getRealPath("zutimg")+"\\"+user+"\\"+RandomStringUtils.randomAlphabetic(5);// ***/zutimg/userid/随机5位字符/
        }
        else if (type == 1){
            path = request.getServletContext().getRealPath("zutav")+"\\"+user+"\\"+RandomStringUtils.randomAlphabetic(5);// ***/zutav/userid/随机5位字符/
        }
        else if (type == 2){
            path = request.getServletContext().getRealPath("zutdoc")+"\\"+user+"\\"+RandomStringUtils.randomAlphabetic(5);// ***/zutdoc/userid/随机5位字符/
        }
        for (int i=0;i<files.length;i++){
            File file = new File(path,RandomStringUtils.randomAlphabetic(5) + suffix[type]);
            file.getParentFile().mkdirs();
            files[i].transferTo(file);
        }
        Log4jUtil.loggerInfo(path);//日志
        return path;
    }

    /**
     * NEW FOR VUE UI 上传文件至服务器，并返回存储文件的完整路径
     * @param file
     * @param request
     * @return 返回存储文件的完整路径名
     * @throws Exception
     */
    public static String upload(MultipartFile file, HttpServletRequest request)throws Exception{
        // 获取文件在服务器的存储位置
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File filepath = new File(path);
        Log4jUtil.loggerInfo("+++++++++++++++++++++++++++++ file path +++++++++++++++++++++++++++" + path);
        if (!filepath.exists() && !filepath.isDirectory()) {
            System.out.println("[ 目录不存在创建目录" + filepath);
            filepath.mkdir();
        }

        // 获取原始文件名称(包含格式)
        String originalFileName = file.getOriginalFilename();
        System.out.println("[ 原始文件名称：" + originalFileName);

        // 获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("[ 文件类型：" + type);
        // 获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        // 设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("[ 新文件名称：" + fileName);

        // 在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        String result = "";
        try {
            file.transferTo(targetFile);
            result = targetFile.getPath();
            System.out.println("[ 文件完整路径名：" + targetFile);
        } catch (IOException e) {
            result = "";
        }
        return result;
    }
}
