 
package com.ft.common.exception;

/**
 * ҵ���쳣���ࡣ
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public class CommonRuntimeException extends RuntimeException{
    private static final long serialVersionUID = 7990606761483790138L;

    private String errorCode;
    private Object[] params;
    
    public CommonRuntimeException() {
	super();
    }
    
    public CommonRuntimeException(String code){
	super();
	this.errorCode = code;
    }

    public CommonRuntimeException(Throwable cause){
        super(cause);
    }
    
    public CommonRuntimeException(String code, Throwable cause) {
	super(cause);
	this.errorCode = code;
    }
    
    public CommonRuntimeException(String code,Object[] params){
        super();
        this.errorCode = code;
        this.params = params;
    }
    
    public CommonRuntimeException(String code,Object[] params,Throwable cause){
        super(cause);
        this.errorCode = code;
        this.params = params;
    }

    /**
     * �쳣������롣
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * �쳣������������ڸ�ʽ��������Ϣ��
     * @return the params
     */
    public Object[] getParams() {
	if(params == null){
	    params = new Object[0];
	}
	
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Object[] params) {
        this.params = params;
    }
}
