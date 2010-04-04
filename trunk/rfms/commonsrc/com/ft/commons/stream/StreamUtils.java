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
 * �����������һЩ���÷���.
 *
 */
public class StreamUtils {
    /**
     * ��������д�������.
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
     * ��reader����д��writer������.
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
     * �����ļ���.
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
     * ��URL�м��ص�InputStream������.
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
     * ��һ��Reader�����м������ݵ�List.
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
     * ��һ��InputStream�����м������ݵ�List.
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
     * �ر�һ��InputStream ����.
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
     * �ر�һ��������.
     *
     * @param out
     *
     * @return ���������false
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
     * �ر�һ��InputStream ����.
     *
     * @param in
     *
     * @return ���������false
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
     * �ر�һ��InputStream ����.
     *
     * @param in
     *
     * @return ��������� false
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
