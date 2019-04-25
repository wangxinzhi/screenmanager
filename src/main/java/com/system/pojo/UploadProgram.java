package com.system.pojo;

import com.system.Utils.Log4jUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 发送节目实体
 */
public class UploadProgram extends Program{

    private MultipartFile[] files = null;

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
