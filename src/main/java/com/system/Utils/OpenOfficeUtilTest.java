package com.system.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class OpenOfficeUtilTest {
    @Test
    public void docConvertToHtml() throws Exception {
        OpenOfficeUtil.docConvertToHtml("F:\\测试.doc");
    }

}