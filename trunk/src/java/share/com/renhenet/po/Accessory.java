package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

/**
 * File Name             : Accessory.java
 * Author                : green.luo
 * Creation Date         : (2009-9-9) (YYYY-MM-DD)
 * Description           : Accessory
 * Reviewed by           :
 * Reviewed On           :
 * Version History       : 1.0 (2009-9-9)
 * Modified By           :
 * Date Modified         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) Infowarelab All Rights Reserved
 * *******************************************************************************************
 */
public class Accessory extends IdPersistent implements
        java.io.Serializable {
    private String oldName;
    private String newName;
    private int fileId;

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
