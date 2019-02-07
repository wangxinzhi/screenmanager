package com.system.pojo;

import org.springframework.web.multipart.MultipartFile;

/**
 * 弃用
 * 上传文件实体
 */
public class UploadImageFile {
    MultipartFile file;

    public void setFile(MultipartFile file){
        this.file=file;
    }

    public MultipartFile getFile(){
        return this.file;
    }
}
