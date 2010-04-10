package com.ft.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

	public static File[] renameToFiles(String[] fileNames, File[] files) {
		File[] retFiles = null;
		if (fileNames != null && fileNames.length > 0) {
			retFiles = new File[fileNames.length];

			for (int i = 0, n = fileNames.length; i < n; i++) {
				File dist = new File(fileNames[i]);
				files[i].renameTo(dist);
				retFiles[i] = dist;
			}
		}
		return retFiles;
	}

	// save文件
	public static long saveFile(File file, String fileName, String filePath)
			throws Exception {
		if (file == null) {
			return 0;
		}

		File filepath = new File(filePath);
		if (!filepath.isDirectory())
			filepath.mkdirs();
		File filedesc = new File(filepath, fileName);

		return copyFile(file, filedesc);
	}

	public static long copyFile(File fromFile, File toFile) {
		long len = 0;

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			byte[] t = new byte[1024];
			int ii = 0;
			while ((ii = in.read(t)) > 0) {
				out.write(t, 0, ii);
				len += ii;
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return len;
	}

	public static boolean verifyFile(File file, String[] exts) throws Exception {
		boolean flag = false;
		if (file != null) {
			String ext = getExtension(file.getName());
			if (ext == null) {
				return false;
			}
			if (exts != null && exts.length > 0) {

				if (exts[0].equals("*.*"))
					return true;
				for (int i = 0; i < exts.length; i++) {

					if (ext.equalsIgnoreCase(exts[i])) {
						flag = true;
						break;
					}
				}
			}
		}

		return flag;

	}

	public static String getExtension(String fileName) {

		int newEnd = fileName.length();
		int i = fileName.lastIndexOf('.', newEnd);
		if (i != -1) {
			return fileName.substring(i + 1, newEnd);
		} else {
			return null;
		}
	}
}