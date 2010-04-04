package com.ft.common.infoShared;


/**
 * 共享信息附件文件管理类
 * @spring.bean id = "afficheUrl"
 * @version 1.0
 */
public class AfficheUrl {
    
    private String url;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @spring.property value="${sm.get.affiche.url}"
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
