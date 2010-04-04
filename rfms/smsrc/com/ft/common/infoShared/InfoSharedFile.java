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
 * ������Ϣ�����ļ�������
 * 
 * @spring.bean id = "infoSharedFile"
 * @version 1.0
 */
public class InfoSharedFile {

    /**
     * ���������ַ
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
     * �ϴ����������ظ�����Ϣ
     * 
     * @param file
     * @param dirName
     * @param shared
     * @return
     * @throws Exception
     */
    public AttachDTO attachFileUpload(FormFile file, String dirName,
            InfoSharedDTO shared) throws Exception {

        // ��ñ����ַ
        File dirFile = filePath.getFile();
        String path = "\\";
        // ���ñ����ַ
        StringBuffer dirPath = new StringBuffer(dirFile.getAbsolutePath());
        // ����Ŀ¼��ַ
        dirPath.append(path);
        dirPath.append(dirName);
        File dir = new File(dirPath.toString());
        // �ж�Ŀ¼�Ƿ���ڣ������ڴ���Ŀ¼
        if (!dir.canRead()) {
            dir.mkdirs();
        }
        // ��ù�����Ϣid
        String idStr = shared.getInfoId().toString();
        // ��ñ����ԭ�ļ���
        String fileName = file.getFileName();
        // ���ñ�����ļ���ַ
        dirPath.append(path);
        // �ļ����ɹ�����Ϣid+ԭ�ļ������
        dirPath.append(idStr);
        dirPath.append(fileName);
        File targetFile = new File(dirPath.toString());
        // ���������
        InputStream in = file.getInputStream();
        // ��������
        FileOutputStream out = new FileOutputStream(targetFile);
        // ���û����С
        byte[] buffer = new byte[4096];
        // ִ���ϴ�����
        StreamUtils.flow(in, out, buffer);
        out.close();
        in.close();
        // ���ø�����Ϣ
        AttachDTO attach = new AttachDTO();
        attach.setFileName(fileName);
        attach.setFilePath(dirPath.toString());
        attach.setInfo((InfoShared) shared.getTarget());

        return attach;
    }
}
