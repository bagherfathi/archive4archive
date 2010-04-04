package com.ft.sm.rules.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.ft.busi.sm.rule.RuleRepository;

/**
 * 实现一个基于文件的规则管理。
 * 
 */
public class FileRuleRepository implements RuleRepository {
    private Map ruleFiles;

    private Resource resource;

    /**
     * 读出一个规则。
     * 
     * @param code
     *                规则代码
     * @return 规则的读取器对象
     */
    public Reader getRuleReader(String code) throws IOException {
        String fileName = (String) ruleFiles.get(code);
        File dir = resource.getFile();
        File aFile = new File(dir, fileName);
        FileReader fileReader = new FileReader(aFile);
        StringWriter writer = new StringWriter();
        try {
            flow(fileReader, writer, new char[512]);
        } catch (IOException e) {
            if (fileReader != null) {
                fileReader.close();
            }
            throw e;
        }
        StringReader result = new StringReader(writer.toString());
        return result;
    }

    /**
     * 得到所有的规则。
     * 
     * @return Returns the ruleFiles.
     */
    public Map getRuleFiles() {
        return ruleFiles;
    }

    /**
     * 设置所有的规则。
     * 
     * @param ruleFiles
     *                The ruleFiles to set.
     */
    public void setRuleFiles(Map ruleFiles) {
        this.ruleFiles = ruleFiles;
    }

    /**
     * 从Reader 对象写到 Writer对象。
     * 
     * @param input
     *                读文件流
     * @param out
     *                输入流
     * @param buf
     *                缓冲区
     * @throws IOException
     *                 IO异常
     */
    protected void flow(Reader input, Writer out, char[] buf)
            throws IOException {
        int numRead;

        while ((numRead = input.read(buf)) >= 0) {
            out.write(buf, 0, numRead);
        }

        out.flush();
    }

    /**
     * @return Returns the resource.
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * @param resource
     *                The resource to set.
     */
    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
