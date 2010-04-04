 
package com.ft.common.exception;

/**
 * 业务异常基类。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
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
     * 异常错误代码。
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
     * 异常错误参数，用于格式化错误信息。
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
