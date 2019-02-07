package com.system.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

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

}