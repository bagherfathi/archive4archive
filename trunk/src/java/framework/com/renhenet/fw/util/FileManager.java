package com.renhenet.fw.util;

/**
 * Created by IntelliJ IDEA.
 * User: tingwei
 * Date: 2009-9-17
 * Time: 17:24:48
 * To change this template use File | Settings | File Templates.
 */


import java.io.File;

public class FileManager{



    public String[] serachFiles(String dir) {


         String temp = "";
        File root = new File(dir);

        File[] filesOrDirs = root.listFiles();

        String[] result = new String[10];

        for (int i = 0; i < filesOrDirs.length; i++) {
            if (filesOrDirs[i].isDirectory()) {
                serachFiles(filesOrDirs[i].getAbsolutePath());
            } else {
                result[i] = filesOrDirs[i].getName();

                temp +=filesOrDirs[i].getAbsolutePath() + ",";

            }
        }

        return temp.split(",");

    }

    /** *//**
     * @param args
     */
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        String[] files = fm.serachFiles("/home/tingwei/");
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }

    }
}