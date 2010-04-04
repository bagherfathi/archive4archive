package com.ft.commons.tree.exception;

/*
 *$$Source: /data/cvs/IT_CODE/002.COMMONS/java/src/com/huashu/commons/tree/exception/UpdateException.java,v $
 * -----------------------------------------------------------------------------
 * Copyright (c) 2003 by Onewave Inc.
 * Author: ChengGong
 * Creat Date: $Date
 * -----------------------------------------------------------------------------
 * $$Id: UpdateException.java,v 1.1 2006/07/27 01:22:28 tangf Exp $
 * $$Log: UpdateException.java,v $
 * $Revision 1.1  2006/07/27 01:22:28  tangf
 * $import
 * $
 * $Revision 1.1  2006/07/27 01:11:24  tangf
 * $move to java
 * $
 * $Revision 1.1  2006/07/25 02:16:55  tangf
 * $import to commons
 * $
 * $Revision 1.2  2006/07/15 12:00:54  tangfeng
 * $add id
 * $
 * $Revision 1.1  2006/07/10 08:37:32  libf
 * $*** empty log message ***
 * $
 * $Revision 1.1  2005/12/07 07:00:26  chengong
 * $init
 * $
 * $Revision 1.1.1.1  2003/10/31 11:16:20  chengong
 * $
 * $
 * $Revision 1.1  2003/10/22 06:12:58  chenggong
 * $*** empty log message ***
 * $
 */

public class UpdateException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public UpdateException(String msg) {
        super(msg);
    }
    
    public UpdateException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public UpdateException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
