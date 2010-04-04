package com.ft.commons.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ft.utils.RandomNum;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * 生成认证的码的Servlet
 *
 * @web.servlet name="TextIntoImageServlet"
 * @web.servlet-mapping url-pattern="authcode.jpg"
 */
public class TextIntoImageServlet extends HttpServlet {
    public static final String IMAGE_VERIFY_SESSION = "imageVerifySession";
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "image/jpeg;charset=GB2312";
    private static String defaultFont = null;
    String imageFile = ""; //被嵌的图片的虚拟路径
    int x = 0; //坐标
    int y = 0;
    int width = 57;
    int height = 17;
    String fontColor = ""; //字体颜色
    int fontSize = 0; //字体大小
    String fontStyle = ""; //字体风格(斜体,粗体等)
    String fontName = ""; //字体名称
    String sessionName = ""; //放入session的名称
    public RandomNum randomNum = new RandomNum();

    /**
     * 初始化
     */
    public void init() throws ServletException {
        ServletConfig servletConfig = getServletConfig();
        defaultFont = servletConfig.getInitParameter("defaultFont");
    }

    /**
     * Process the HTTP Get request
     */
    public void doGet(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * Process the HTTP Post request
     */
    public void doPost(
        HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        String text = ""; // 要嵌的文字
                          //	 

        try {
            // 取得参数
            randomNum.generateRandomObject();
            randomNum.setRange(1000, 9999);
            // String tmp = String.valueOf(randomNum.getRandom());
            text = String.valueOf(randomNum.getRandom());
            sessionName = getParameter(request, "sessionName");

            if ((sessionName == null) || "".equals(sessionName)) {
                sessionName = IMAGE_VERIFY_SESSION;
            }

            HttpSession session = request.getSession();

            session.setAttribute(sessionName, text);
            x = getIntParameter(request, "x", 10);
            y = getIntParameter(request, "y", 10);
            // fontColor = "FFFFFF";
            fontColor = getParameter(request, "fontColor");
            if(fontColor == null || fontColor.length()==0){
            	fontColor ="000000";
            }
            // fontSize = 14;
            // fontStyle = "bold";
            // fontName = "arial";
            fontSize = getIntParameter(request, "fontSize", 14);
            fontStyle = getParameter(request, "fontStyle");

            if ((defaultFont != null) && !defaultFont.trim().equals("")) {
                fontName = defaultFont;
            } else {
                fontName = getParameter(request, "fontName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ServletOutputStream output = response.getOutputStream();

        BufferedImage image =
            new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);

        // 设置颜色
        g.setColor(new Color(Integer.parseInt(fontColor, 16)));

        // 设置字体
        Font mFont = new Font(fontName, Font.PLAIN, fontSize); // 默认字体

        if (fontStyle.equalsIgnoreCase("italic")) {
            mFont = new Font(fontName, Font.ITALIC, fontSize);
        }

        if (fontStyle.equalsIgnoreCase("bold")) {
            mFont = new Font(fontName, Font.BOLD, fontSize);
        }

        if (fontStyle.equalsIgnoreCase("plain")) {
            mFont = new Font(fontName, Font.PLAIN, fontSize);
        }

        g.setFont(mFont);

        // 随机产生干扰点
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x2, y2, x2, y2);
        }

        // 输出文字
        g.drawString(text, x, y);

        // 输出数据流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
        encoder.encode(image);
        image.flush();
        g.dispose();
        // }
        output.close();
    }

    /**
     * 获得request中指定名称的参数值,若有中文乱码问题请增加转码部分
     *
     * @param request ServletRequest对象
     * @param paramName 参数名称
     *
     * @return 如果该变量值存在则返回该值，否则返回""
     */
    public String getParameter(ServletRequest request, String paramName) {
        String temp = request.getParameter(paramName);

        if ((temp != null) && !temp.equals("")) {
            // 若有中文问题，在此添加转码代码，例：temp = new String(temp.getBytes("8859_1"),
            // "GB2312");
            return temp;
        } else {
            return "";
        }
    }

    /**
     * 获得request中的int型参数值
     *
     * @param request ServletRequest对象
     * @param paramName 参数名称
     * @param defaultNum 默认值，如果没有返回该值
     *
     * @return 如果该参数值存在则返回其转换为int型的值，否则返回defaultNum
     */
    public int getIntParameter(
        ServletRequest request, String paramName, int defaultNum) {
        String temp = request.getParameter(paramName);

        if ((temp != null) && !temp.equals("")) {
            int num = defaultNum;

            try {
                num = Integer.parseInt(temp);
            } catch (Exception ignored) {
            }

            return num;
        } else {
            return defaultNum;
        }
    }
}
