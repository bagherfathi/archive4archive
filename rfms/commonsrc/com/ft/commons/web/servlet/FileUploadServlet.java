/**
 * 
 */
package com.ft.commons.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author soler
 * 
 */
public class FileUploadServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(FileUploadServlet.class);

	private static int tagNum=0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -65009608475758351L;

	private String uploadPath = "/"; // 上传文件的目录

	private String tempPath = "/"; // 临时文件目录

	File tempPathFile;

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("GBK");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();

				// Set factory constraints
				factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
				factory.setRepository(tempPathFile);// 设置缓冲区目录

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);

				// Set overall request size constraint
				upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
				Iterator<FileItem> i = items.iterator();
				String afterPath = "";
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if(fi.isFormField()){
                        // 如果item是正常的表单域
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        System.out.print("表单域名为:"+name+"表单域值为:"+value);
                    }
                    else{

					String fileName = fi.getName();
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						String subfix=fullFile.getName().substring(fullFile.getName().lastIndexOf("."));
						String xfileName=System.currentTimeMillis()+""+tagNum+subfix;
						tagNum+=1;
						if(tagNum==10){
							tagNum=0;
						}
						File savedFile = new File(this.getServletContext()
								.getRealPath(uploadPath), xfileName);
						fi.write(savedFile);
						afterPath = uploadPath+"/"+xfileName;
					}
                    }
				}
				log.info("upload succeed"+afterPath);
				response.setCharacterEncoding("GBK");
				response.getWriter().write(
						"<script>window.returnValue='" + afterPath
								+ "';alert('上传文件成功！');self.close();</script>");
			} catch (Exception e) {
				// 可以跳转出错页面
				e.printStackTrace();
			}
		}

	}

	public void init() throws ServletException {
		uploadPath = this.getInitParameter("uploadPath");
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		String strDate=df.format(new Date());
		uploadPath=uploadPath+"/"+strDate;
		tempPath = this.getInitParameter("tempPath");
		String uploadPath1 = this.getServletContext().getRealPath(uploadPath);
		File uploadFile = new File(uploadPath1);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		if (tempPath != null && tempPath.length() > 0) {
			File tempPathFile = new File(tempPath);
			if (!tempPathFile.exists()) {
				tempPathFile.mkdirs();
			}
		}
	}
}
