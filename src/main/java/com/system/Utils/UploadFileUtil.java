package com.system.Utils;

import com.system.pojo.UploadProgram;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class UploadFileUtil {

    public static String saveFile(HttpServletRequest request, UploadProgram program)throws Exception{
        MultipartFile[] files=program.getFiles();//上传的文件数据
        Integer type = program.getPType();//节目类型
        String user = program.getPSendPerson();//节目发送人id
        String path = null;
        String[] suffix= {".jpg",".mp4",".doc"};//保存的文件后缀
        if (type==0){
            path=request.getServletContext().getRealPath("zutimg")+"\\"+user+"\\"+RandomStringUtils.randomAlphabetic(5);// ***/zutimg/userid/随机5位字符/
        }
        else if (type==1){
            path=request.getServletContext().getRealPath("zutav")+"\\"+user+"\\"+RandomStringUtils.randomAlphabetic(5);// ***/zutav/userid/随机5位字符/
        }
        else if (type==2){
            path=request.getServletContext().getRealPath("zutdoc")+"\\"+user+"\\"+RandomStringUtils.randomAlphabetic(5);// ***/zutdoc/userid/随机5位字符/
        }
        for (int i=0;i<files.length;i++){
            File file=new File(path,RandomStringUtils.randomAlphabetic(5) + suffix[type]);
            file.getParentFile().mkdirs();
            files[i].transferTo(file);
        }
        Log4jUtil.loggerInfo(path);//日志
        return path;
    }
}
