package com.ft.sm.dto;

import java.io.Serializable;
import java.util.Date;

import com.ft.sm.entity.InfoCategory;
import com.ft.sm.entity.InfoShared;
import com.ft.sm.entity.Operator;

/**
 * ��Ϣ����ʵ���װ��
 * 
 */
public class InfoCategoryBeanDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long sum;

    private InfoCategory category;

    private InfoShared info;

    private Operator op;

    public InfoCategoryBeanDTO() {
        this.category = new InfoCategory();
        this.info = new InfoShared();
        this.op = new Operator();
    }

    public InfoCategoryBeanDTO(InfoCategory category, InfoShared info,
            Operator op) {
        this.category = category;
        this.info = info;
        this.op = op;
    }

    /**
     * ���ID��
     * 
     * @return
     */
    public long getCategoryInfoId() {
        return this.category.getCategoryId();
    }

    /**
     * ������ơ�
     * 
     * @return
     */
    public String getCategoryName() {
        return this.category.getCategoryName();
    }

    /**
     * ��󷢲���Ϣ��ʱ�䡣
     * 
     * @return
     */
    public Date getLastPublishTime() {
        return this.info.getPublishTime();
    }

    /**
     * ��󷢲���Ϣ�ķ����ߡ�
     * 
     * @return
     */
    public String getPublisher() {
        return this.op.getOpName();
    }

    /**
     * ��󷢲���Ϣ�ı��⡣
     * 
     * @return
     */
    public String getTitle() {
        return this.info.getInfoTitle();
    }

    /**
     * ��Ϣ������
     * 
     * @return
     */
    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    /**
     * ��󷢲�����ϢID��
     * 
     * @return
     */
    public long getSharedId() {
        return this.info.getInfoId();
    }
}
