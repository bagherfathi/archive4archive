package com.ft.commons.file;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.provider.GenericFileName;
import org.apache.commons.vfs.provider.UriParser;

import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * 文件名分析对象.
 */
public class FileNameUtils {
	/** 分隔符 '/'. */
	public static final String split = "/";

	/** 分隔符! '.' */
	public static final String extSplit = ".";

	
	/**
	 * 将文件中 "\" 换成"/".
	 * 
	 * @param fileName
	 * 
	 * @return String fileName.
	 */
	public static String recense(String fileName) {
		return StringUtils.replace(fileName, "\\", split);
	}

	/**
	 * 得到一个文件路径.
	 * 
	 * @param fileName
	 * 
	 * @return fileName .
	 */
	public static String getPath(String fileName) {
		fileName = FileNameUtils.recense(fileName);
		fileName.replaceAll("//", "/");

		int idx = fileName.lastIndexOf(split);

		if (idx > 0) {
			return fileName.substring(0, idx);
		} else {
			return fileName;
		}
	}

	/**
	 * 得到文件的扩展名.
	 * 
	 * @param fileName
	 * 
	 * @return extName
	 */
	public static String getExtName(String fileName) {
		fileName = FileNameUtils.recense(fileName);

		int idx = fileName.lastIndexOf(extSplit);

		if (idx > 0) {
			return fileName.substring(idx + 1);
		} else {
			return "";
		}
	}

	/**
	 * 得到文件名.
	 * 
	 * @param fileName
	 * 
	 * @return fileName
	 */
	public static String getName(String fileName) {
		fileName = FileNameUtils.recense(fileName);

		int idx = fileName.lastIndexOf(split);

		if (idx > 0) {
			return fileName.substring(idx + 1);
		} else {
			return fileName;
		}
	}

	/**
	 * 得到一个文件名.
	 * 
	 * @param fileName
	 * 
	 * @return
	 */
	public static String getSimpleName(String fileName) {
		String name = getName(fileName);
		int idx = name.lastIndexOf(extSplit);

		if (idx > 0) {
			return name.substring(0, idx);
		} else {
			return name;
		}
	}

	/**
	 * 合并文件名.
	 * 
	 * @param path
	 * @param name
	 * 
	 * @return
	 */
	public static String join(String path, String name) {
		String aName = name.startsWith("/") ? name.substring(1) : name;

		if (path.endsWith(split)) {
			return path + aName;
		} else {
			return path + split + aName;
		}
	}

	/**
	 * 是否是图型.
	 * 
	 * @param fileName
	 * 
	 * @return
	 */
	public boolean isImage(String fileName) {
		String aName = fileName.toLowerCase();

		return (aName.endsWith("gif") || aName.endsWith("jpg")
				|| aName.endsWith("png") || aName.endsWith("bmp"));
	}

	/**
	 * 是否是媒体文件.
	 * 
	 * @param fileName
	 * 
	 * @return
	 */
	public boolean isMedia(String fileName) {
		String aName = fileName.toLowerCase();

		return (aName.endsWith("rm") || aName.endsWith("wmv")
				|| aName.endsWith("mp4") || aName.endsWith("mp3") || aName
				.endsWith("3gp"));
	}

	/**
	 * 得到一个URL的文件名.
	 * 
	 * @param fileName
	 * @param host
	 * @param port
	 * 
	 * @return
	 */
	public static String getURLFileName(String fileName, String host, int port) {
		FileSystemManager manager;

		try {
			manager = VFS.getManager();

			GenericFileName aFileName = (GenericFileName) manager
					.resolveURI(fileName);
			StringBuffer buffer = new StringBuffer();
			buffer.append(aFileName.getScheme()).append("://");
			buffer.append(aFileName.getUserName()).append("%40").append(
					aFileName.getHostName());
			buffer.append(":").append(aFileName.getPassword());
			buffer.append("@").append(host).append(":");
			buffer.append(port);
			buffer.append(aFileName.getPath());

			return buffer.toString();
		} catch (FileSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileName;
	}

	/**
	 * 给传进来的路径进行编码.
	 * 
	 * @param path
	 * 
	 * @return
	 */
	public static String pathEncode(String path) {
		String[] paths = path.split("/");

		for (int i = 0; i < paths.length; i++) {
			try {
				paths[i] = URLEncoder.encode(paths[i], "GBK");
			} catch (UnsupportedEncodingException e) {
			}
		}

		return StringUtils.join(paths, "/");
	}

	/**
	 * 解码所传进来的路径.
	 * 
	 * @param path
	 * 
	 * @return
	 */
	public static String pathDecode(String path) {
		String[] paths = path.split("/");

		for (int i = 0; i < paths.length; i++) {
			try {
				paths[i] = UriParser.decode(paths[i]);
			} catch (FileSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return StringUtils.join(paths, "/");
	}

	/**
	 * 得到FTP形式的路径.
	 * 
	 * @param name
	 * 
	 * @return ftpPath
	 */
	public static String getFtpPath(String name) {
		String result = pathEncode(name);
		result = pathDecode(result);

		return result;
	}

	/**
	 * 得到URI形式的路径
	 * 
	 * @param source
	 * 
	 * @return uriPath
	 */
	public static String getURIPath(String source) {
		try {
			URI aUri = new URI(source);

			return aUri.getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return source;
	}

	/**
	 * 在扩展名前插入字符串.
	 * 
	 * @param name
	 * @param string
	 * 
	 * @return name
	 */
	public static String insertExt(String name, String string) {
		String simpleName = FileNameUtils.getSimpleName(name);
		String ext = FileNameUtils.getExtName(name);
		String path = FileNameUtils.getPath(name);
		name = path + "/" + simpleName + string + "." + ext;

		return name;
	}
}
