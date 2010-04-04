package com.ft.webui;

import java.net.MalformedURLException;
import java.net.URL;


public class RequestURLInfo {
    private URL url;

    public RequestURLInfo(String name) {
        try {
            this.url = new URL(name);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getDefaultPort() {
        return url.getDefaultPort();
    }

    public String getFile() {
        return url.getFile();
    }

    public String getHost() {
        return url.getHost();
    }

    public String getPath() {
        return url.getPath();
    }

    public int getPort() {
        return url.getPort();
    }

    public String getProtocol() {
        return url.getProtocol();
    }

    public String getQuery() {
        return url.getQuery();
    }
}
