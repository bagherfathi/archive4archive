package com.renhenet.fw.util;

/**
 * Created by IntelliJ IDEA.
 * User: tingwei
 * Date: 2009-9-17
 * Time: 17:24:48
 * To change this template use File | Settings | File Templates.
 */

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class FileManager {

	List<String> outList = new ArrayList();

	public synchronized List<String> serachFiles(String dir) {
		File root = new File(dir);
		File[] filesOrDirs = root.listFiles();
		for (int i = 0; i < filesOrDirs.length; i++) {
			if (filesOrDirs[i].isDirectory()) {
				serachFiles(filesOrDirs[i].getAbsolutePath());
			} else {
				String filePath = filesOrDirs[i].getName();
				outList.add(filePath);
			}
		}
		return outList;
	}

	/** */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileManager fm = new FileManager();
		List<String> fileList = fm.serachFiles("D://dangan//dangan//conf");
		for (int i = 0; i < fileList.size(); i++) {
			System.out.println(fileList.get(i));
		}
		System.out.print(fileList.size());

	}
}