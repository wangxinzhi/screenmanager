package com.system.Utils;

import org.junit.Test;

public class HtmlConvertTest {

    @Test
    public void htmlToImageTest()throws Exception{
        String charset="GBK";
        String htmltext;
        HtmlConvert htmlConvert=new HtmlConvert();
        htmltext=htmlConvert.getHtmlContent("F:\\测试.html",charset);
        Log4jUtil.loggerInfo(htmltext);
        htmlConvert.htmlToImage(htmltext,"F:\\测试.jpg");
        htmlConvert.test();
    }

    @Test
    public void getResource()throws Exception{
        String path = "path";
        String path2 = "log4j2.xml";
        path = HtmlConvertTest.class.getClassLoader().getResource("config/ip2region.db").getPath();
        System.out.println("PATH = " + path);
    }

}