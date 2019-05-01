package com.system.Utils;

import gui.ava.html.image.generator.HtmlImageGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.*;

/**
 * html转image
 * 用到的是 html2image
 * 需要添加maven依赖
 * <dependency>
 * <groupId>com.github.xuwei-k</groupId>
 * <artifactId>html2image</artifactId>
 * <version>0.1.0</version>
 * </dependency>
 */
public class HtmlConvert {

    /**
     * 读取html文件，获取字符内容
     * @param filepath
     * @param charset
     * @return
     */
    public String getHtmlContent(String filepath,String charset){
        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath)),charset));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取HTML文件，获取字符内容异常");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭流异常");
            }
        }
        return sb.toString();
    }

    /**
     * HTML转Image
     * @param htmltext
     * @param saveImageLocation
     * @return
     */
    public String htmlToImage(String htmltext,String saveImageLocation){
        HtmlImageGenerator imageGenerator=new HtmlImageGenerator();
        try {
            imageGenerator.loadHtml(htmltext);
            Thread.sleep(100);
            //imageGenerator.getBufferedImage();
            Thread.sleep(200);
            imageGenerator.saveAsImage(saveImageLocation);
            //imageGenerator.saveAsHtmlWithMap("F:\\测试.html", saveImageLocation);
            //不需要转换位图的，下面三行可以不要
            BufferedImage sourceImg = ImageIO.read(new File(saveImageLocation));
            sourceImg = transform_Gray24BitMap(sourceImg);
            ImageIO.write(sourceImg, "BMP", new File(saveImageLocation));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("将HTML文件转换成图片异常");
        }
        return saveImageLocation;
    }

    /**
     * 转换成24位图的jpg
     * @param image
     * @return
     */
    private BufferedImage transform_Gray24BitMap(BufferedImage image){

        int h = image.getHeight();
        int w = image.getWidth();
        int[] pixels = new int[w * h]; // 定义数组，用来存储图片的像素
        int gray;
        PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
        try {
            pg.grabPixels(); // 读取像素值
        } catch (InterruptedException e) {
            throw new RuntimeException("转换成24位图的jpg时，处理像素值异常");
        }

        for (int j = 0; j < h; j++){ // 扫描列
            for (int i = 0; i < w; i++) { // 扫描行
                // 由红，绿，蓝值得到灰度值
                gray = (int) (((pixels[w * j + i] >> 16) & 0xff) * 0.8);
                gray += (int) (((pixels[w * j + i] >> 8) & 0xff) * 0.1);
                gray += (int) (((pixels[w * j + i]) & 0xff) * 0.1);
                pixels[w * j + i] = (255 << 24) | (gray << 16) | (gray << 8) | gray;
            }
        }

        MemoryImageSource s= new MemoryImageSource(w,h,pixels,0,w);
        Image img =Toolkit.getDefaultToolkit().createImage(s);
        BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);//如果要转换成别的位图，改这个常量即可
        buf.createGraphics().drawImage(img, 0, 0, null);
        return buf;
    }

    public void test()throws Exception{

    }

}
