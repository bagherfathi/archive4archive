/**
 * 
 */
package com.ft.rfcs.iso8583;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.TransportType;
import org.apache.mina.transport.socket.nio.SocketSessionConfig;

import com.ft.protocol.iso8583.ISO8583Message;
import com.ft.protocol.iso8583.ISO8583MessageFactory;
import com.ft.protocol.common.FormatUtility;

/**
 * @author lch
 * 
 */
public class ISO8583Handler extends IoHandlerAdapter {

	private static final Logger log = Logger.getLogger(ISO8583Handler.class);

	private ISO8583MessageFactory messageFactory;
	private ISO8583Adapter adapter;
	
	/**
	 * @param messageFactory
	 *            the messageFactory to set
	 */
	public void setMessageFactory(ISO8583MessageFactory messageFactory) {
		log.debug("load messageFactory");
		this.messageFactory = messageFactory;
	}

	
	
	/**
	 * @param adapter the adapter to set
	 */
	public void setAdapter(ISO8583Adapter adapter) {
		this.adapter = adapter;
	}



	public void sessionCreated(IoSession session) {
		if (session.getTransportType() == TransportType.SOCKET) {
			((SocketSessionConfig) session.getConfig())
					.setReceiveBufferSize(2048);
		}

		session.setIdleTime(IdleStatus.BOTH_IDLE, 10);

		// We're going to use SSL negotiation notification.
		// session.setAttribute(SSLFilter.USE_NOTIFICATION);
	}

	public void sessionIdle(IoSession session, IdleStatus status) {
		log.info("*** IDLE #" + session.getIdleCount(IdleStatus.BOTH_IDLE)
				+ " ***");
		session.close();
	}

	public void exceptionCaught(IoSession session, Throwable cause) {

		log.error("casue",cause);
		session.close();
	}

	/**
	 * 
	 */
	private void printlnPackage(ByteBuffer byteBuffer, String headerInfo) {
		byte[] byteHex = new byte[byteBuffer.limit()];
		byteBuffer.get(byteHex);
		log.info(headerInfo + "=[" + FormatUtility.bytesToHexString(byteHex)
				+ "]");
		byteBuffer.flip();
	}

	/**
	 * 
	 */
	public void messageReceived(IoSession session, Object message)
			 {
		log.info("接入IP地址[ISO8583]："+session.getRemoteAddress().toString());
		if (!(message instanceof ByteBuffer)) {
			session.close();
			return;
		}
		try {
			ByteBuffer byteBuffer = (ByteBuffer) message;
			printlnPackage(byteBuffer, "支付平台请求报文 [ISO8583]");
			ISO8583Message response = null;
			ISO8583Message request = null;
			request = this.messageFactory.read(byteBuffer);
			log.debug("请求数据:" + request.toString());
			
			request.setIpaddress((session.getRemoteAddress().toString().split(":")[0]).split("/")[1]);	
			response = this.adapter.doAdapte(request);
			if(response != null){
				ByteBuffer byteBufferRes = this.messageFactory.write(response);
				printlnPackage(byteBufferRes, "支付平台应答报文[ISO8583]");
				session.write(byteBufferRes);
			}
			
		} catch (Exception e) {
			log.error("system Exception",e);
		}finally{
			//session.close();
		}
	}
}
