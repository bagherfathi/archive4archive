package com.ft.commons.obj;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtils {
    final static Log log = LogFactory.getLog(ObjectUtils.class);

    /**
     * 将一个对象保存到文件中
     *
     * @param obj
     * @param aFile
     *
     * @throws IOException
     */
    public static void saveObject(Object obj, File aFile) throws IOException {
        File parent = aFile.getParentFile();

        if ((parent != null) && !parent.exists()) {
            parent.mkdirs();
        }

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(aFile);

            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 从文件中加载对象
     *
     * @param aFile
     *
     * @return
     *
     * @throws IOException
     */
    public static Object loadObject(File aFile) throws IOException {
        FileInputStream in = null;
        Object obj = null;

        try {
            in = new FileInputStream(aFile);

            ObjectInputStream objIn = new ObjectInputStream(in);
            obj = objIn.readObject();
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e1) {
            log.error(e1.getMessage(), e1);
            throw e1;
        } finally {
            if (in != null) {
                safeClose(in);
            }
        }

        return obj;
    }

    /**
     * 关闭 文件流
     *
     * @param in
     */
    private static void safeClose(InputStream in) {
        try {
            in.close();
        } catch (IOException e) {
            log.error("safe close " + e.getMessage(), e);
        }
    }
}
