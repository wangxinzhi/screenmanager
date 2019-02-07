package com.system.pojo;

import org.springframework.web.multipart.MultipartFile;

public class UpdateProgram extends Program {
    public Object[] getFiles() {
        return files;
    }

    public void setFiles(Object[] files) {
        this.files = files;
    }

    private Object[] files=null;
}
