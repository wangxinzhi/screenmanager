package com.system.Utils;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;
import java.net.ConnectException;

/**
 * 使用此工具类要安装OpenOffice,并在安装目录的program下启动该服务.（Windows:soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"）
 */
public class OpenOfficeUtil {

    //绝对路径 (eg. 'C:\xxx\xxx\xxx.doc')
    public static String docConvertToHtml(String path) throws ConnectException {
        String newPath=null;
        File inputFile=null;
        File outputFile=null;
        if (path.contains(".doc")) {
            newPath=path.substring(0,path.lastIndexOf("."))+".html";
        }
        inputFile=new File(path);
        outputFile=new File(newPath);
        OpenOfficeConnection openOfficeConnection=new SocketOpenOfficeConnection(8100);
        openOfficeConnection.connect();
        DocumentConverter documentConverter=new OpenOfficeDocumentConverter(openOfficeConnection);
        documentConverter.convert(inputFile,outputFile);
        openOfficeConnection.disconnect();
        return newPath;
    }
}
