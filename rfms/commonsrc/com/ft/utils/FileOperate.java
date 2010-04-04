/******************************************************************************
 * 类　　　　　     ： OperateFile
 ******************************************************************************/
package com.ft.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * 对文件进行读写操作
 */
public class FileOperate {

	/**
	 * 构造器方法
	 */
	public FileOperate() {
	}

	/**
	 * 写文件操作
	 * 
	 * @throws IOException
	 */
	public static void writeFile(String fileName, String str) {
		try {

			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName,
					true));
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(str);
			printWriter.println(stringBuffer.toString());

			printWriter.flush();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 读文件操作
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void readFile(String fileName, String mode) {
		try {
			// String filePath="d:\\test.txt";
			java.io.File file = new java.io.File(fileName);
			if (file.exists() == false) {
				System.out.println("file not find");
				return;
			}
			RandomAccessFile sourFile = new RandomAccessFile(file, mode);
			String aa = sourFile.readLine().toString();
			System.out.println(aa);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * 删除文件夹
	 * 
	 * @param dir
	 */
	public static void deleteFolder(java.io.File dir) {

		java.io.File filelist[] = dir.listFiles();
		int listlen = filelist.length;
		for (int i = 0; i < listlen; i++) {
			if (filelist[i].isDirectory()) {
				deleteFolder(filelist[i]);
			} else {
				filelist[i].delete();
			}
		}
		dir.delete();// 删除当前目录
	}

	/**
	 * 删除文件夹中的所有文件
	 * 
	 * @param dir
	 */
	public static void deleteFolderFile(java.io.File dir) {
		java.io.File filelist[] = dir.listFiles();
		int listlen = filelist.length;
		for (int i = 0; i < listlen; i++) {
			if (filelist[i].isDirectory()) {
				deleteFolder(filelist[i]);
			} else {
				filelist[i].delete();
			}
		}
	}

	/*
	 * 创建一个目录 @param sysPath Description of the Parameter @param newDirName
	 * Description of the Parameter @return Description of the Return Value
	 * @exception Exception Description of the Exception
	 */
	public static boolean newDir(String sysPath, String newDirName) {
		java.io.File dir_ = new java.io.File(sysPath + newDirName);
		if (!dir_.isDirectory()) {
			return dir_.mkdir();
		}
		return false;
	}

	/**
	 * copy a specified file to object folder
	 * 
	 * @param sourceFile
	 * @return
	 */
	public static boolean copyFile(String sourFile, String desFile)
			throws Exception {
		int b = 0;
		FileInputStream inputStream = new FileInputStream(sourFile);
		FileOutputStream outputStream = new FileOutputStream(desFile);
		while ((b = inputStream.read()) != -1) {
			outputStream.write(b);
		}
		inputStream.close();
		outputStream.close();
		return true;
	}

	/**
	 * 读取文本文件
	 * 
	 * @param fileName
	 *            相对路径+文件名
	 * @return
	 */
	public static Hashtable readTextFile(String fileName) {
		String filename = fileName;
		Hashtable<String, String> h = new Hashtable<String, String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String str = in.readLine();
			while (str != null) {
				if (str.indexOf("=") != -1) {
					StringTokenizer st = new StringTokenizer(str, "=");
					while (st.hasMoreTokens()) {
						String id = st.nextToken();
						if (st.hasMoreTokens()) {
							String name = st.nextToken();
							h.put(id, name);
						}
					}
				}
				str = in.readLine();
			}
			in.close();
			return h;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}
		// test if get right
		// String result = (String)h.get("1111");
		// if (result != null) {
		// System.out.println("1111 = " + result);
		// }
	}

}