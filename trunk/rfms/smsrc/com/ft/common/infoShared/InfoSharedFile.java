package com.ft.common.infoShared;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.struts.upload.FormFile;
import org.springframework.core.io.Resource;

import com.ft.sm.dto.AttachDTO;
import com.ft.sm.dto.InfoSharedDTO;
import com.ft.sm.entity.InfoShared;
import com.ft.commons.stream.StreamUtils;

/**
 * 共享信息附件文件管理类
 * 
 * @spring.bean id = "infoSharedFile"
 * @version 1.0
 */
public class InfoSharedFile {

    /**
     * 附件保存地址
     */
    private Resource filePath;

    /**
     * @spring.property value="${article.file.path}"
     * @param filePath
     */
    public void setFilePath(Resource filePath) {
        this.filePath = filePath;
    }

    /**
     * 上传附件并返回附件信息
     * 
     * @param file
     * @param dirName
     * @param shared
     * @return
     * @throws Exception
     */
    public AttachDTO attachFileUpload(FormFile file, String dirName,
            InfoSharedDTO shared) throws Exception {

        // 获得保存地址
        File dirFile = filePath.getFile();
        String path = "\\";
        // 设置保存地址
        StringBuffer dirPath = new StringBuffer(dirFile.getAbsolutePath());
        // 设置目录地址
        dirPath.append(path);
        dirPath.append(dirName);
        File dir = new File(dirPath.toString());
        // 判断目录是否存在，不存在创建目录
        if (!dir.canRead()) {
            dir.mkdirs();
        }
        // 获得共享信息id
        String idStr = shared.getInfoId().toString();
        // 获得保存的原文件名
        String fileName = file.getFileName();
        // 设置保存的文件地址
        dirPath.append(path);
        // 文件名由共享信息id+原文件名组成
        dirPath.append(idStr);
        dirPath.append(fileName);
        File targetFile = new File(dirPath.toString());
        // 获得输入流
        InputStream in = file.getInputStream();
        // 获得输出流
        FileOutputStream out = new FileOutputStream(targetFile);
        // 设置缓存大小
        byte[] buffer = new byte[4096];
        // 执行上传操作
        StreamUtils.flow(in, out, buffer);
        out.close();
        in.close();
        // 设置附件信息
        AttachDTO attach = new AttachDTO();
        attach.setFileName(fileName);
        attach.setFilePath(dirPath.toString());
        attach.setInfo((InfoShared) shared.getTarget());

        return attach;
    }
}
