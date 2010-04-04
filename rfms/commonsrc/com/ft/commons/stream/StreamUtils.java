package com.ft.commons.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;


/**
 * 对于流处理的一些公用方法.
 *
 */
public class StreamUtils {
    /**
     * 将输入流写到输出流.
     *
     * @param input
     * @param out
     * @param buf
     *
     * @throws IOException
     */
    public static void flow(InputStream input, OutputStream out, byte[] buf)
        throws IOException {
        int numRead;

        while ((numRead = input.read(buf)) >= 0) {
            out.write(buf, 0, numRead);
        }

        out.flush();
    }

    /**
     * 将reader对象写到writer对象中.
     *
     * @param input
     * @param out
     * @param buf
     *
     * @throws IOException
     */
    public static void flow(Reader input, Writer out, char[] buf)
        throws IOException {
        int numRead;

        while ((numRead = input.read(buf)) >= 0) {
            out.write(buf, 0, numRead);
        }

        out.flush();
    }

    /**
     * 开打文件流.
     *
     * @param file
     *
     * @return
     */
    public static InputStream getInputStream(File file) {
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inputStream;
    }

    /**
     * 从URL中加载到InputStream对象中.
     *
     * @param urlStr
     *
     * @return
     *
     * @throws IOException
     */
    public static InputStream getURLInputStream(String urlStr)
        throws IOException {
        InputStream in = null;
        URL url = new URL(urlStr);
        in = url.openStream();

        return in;
    }

    /**
     * 从一个Reader对象中加载内容到List.
     *
     * @param reader
     *
     * @return
     *
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public static List loadLine(Reader reader) throws IOException {
        List items = new ArrayList();
        BufferedReader bufReader = new BufferedReader(reader);

        while (true) {
            String aLine = bufReader.readLine();

            if (aLine == null) {
                break;
            }

            items.add(aLine.trim());
        }

        return items;
    }

    /**
     * 从一个InputStream对象中加载内容到List.
     *
     * @param in
     *
     * @return
     *
     * @throws IOException
     */
    public static List loadLine(InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in);

        return loadLine(reader);
    }

    /**
     * 关闭一个InputStream 对象.
     *
     * @param in
     *
     * @return
     */
    public static boolean safeClose(InputStream in) {
        boolean result = true;

        try {
            in.close();
        } catch (IOException e) {
            result = false;
        }

        return result;
    }

    /**
     * 关闭一下输入流.
     *
     * @param out
     *
     * @return 如果出错返回false
     */
    public static boolean safeClose(OutputStream out) {
        boolean result = true;

        try {
            out.close();
        } catch (IOException e) {
            result = false;
        }

        return result;
    }

    /**
     * 关闭一个InputStream 对象.
     *
     * @param in
     *
     * @return 如果出错返回false
     */
    public static boolean safeClose(Writer writer) {
        boolean result = true;

        try {
            writer.close();
        } catch (IOException e) {
            result = false;
        }

        return result;
    }

    /**
     * 关闭一个InputStream 对象.
     *
     * @param in
     *
     * @return 如果出错返回 false
     */
    public static boolean safeClose(Reader reader) {
        boolean result = true;

        try {
            reader.close();
        } catch (IOException e) {
            result = false;
        }

        return result;
    }
}
