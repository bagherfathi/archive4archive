package com.ft.spring;

import org.springframework.beans.BeansException;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;


/**
 * 配置Application的系统路径上下文
 *
 */
public class PathSystemXmlApplicationContext
    extends FileSystemXmlApplicationContext {
    File serverRoot;

    public PathSystemXmlApplicationContext(
        File root, String[] configLocations) throws BeansException {
        super(configLocations, false);
        this.serverRoot = root;
        this.refresh();
    }

    protected Resource getResourceByPath(String path) {
        if ((path != null) && path.startsWith("/")) {
            path = path.substring(1);
        }

        if (this.serverRoot != null) {
            return new FileSystemResource(new File(serverRoot, path));
        } else {
            return this.getResource(path);
        }
    }
}
