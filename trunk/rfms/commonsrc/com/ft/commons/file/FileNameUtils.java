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
 * �ļ�����������.
 */
public class FileNameUtils {
	/** �ָ��� '/'. */
	public static final String split = "/";

	/** �ָ���! '.' */
	public static final String extSplit = ".";

	
	/**
	 * ���ļ��� "\" ����"/".
	 * 
	 * @param fileName
	 * 
	 * @return String fileName.
	 */
	public static String recense(String fileName) {
		return StringUtils.replace(fileName, "\\", split);
	}

	/**
	 * �õ�һ���ļ�·��.
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
	 * �õ��ļ�����չ��.
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
	 * �õ��ļ���.
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
	 * �õ�һ���ļ���.
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
	 * �ϲ��ļ���.
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
	 * �Ƿ���ͼ��.
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
	 * �Ƿ���ý���ļ�.
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
	 * �õ�һ��URL���ļ���.
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
	 * ����������·�����б���.
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
	 * ��������������·��.
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
	 * �õ�FTP��ʽ��·��.
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
	 * �õ�URI��ʽ��·��
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
	 * ����չ��ǰ�����ַ���.
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
