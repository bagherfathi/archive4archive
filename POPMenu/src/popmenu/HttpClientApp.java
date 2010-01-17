/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popmenu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import org.apache.commons.httpclient.*;

/**
 *
 * @author Administrator
 */
public class HttpClientApp {

    private int times = 1;

    public String httpRequest(String url) {

        String outString = getUrlResponse(url, ServerUrl.urlTimeout, ServerUrl.urlRetryTimes);
        return outString;
    }

    private String getUrlResponse(String url, int timeOut, final int retryTimes) {
        System.out.print(url+"\n");
        int errorCode = -200;
        Protocol myhttps = new Protocol("https", new ProtocolSocketFactory4HttpAdaprot(), 443);

        Protocol.unregisterProtocol("https");
        Protocol.registerProtocol("https", myhttps);
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        HttpMethodRetryHandler myretryhandler = new HttpMethodRetryHandler() {

            public boolean retryMethod(
                    final HttpMethod method,
                    final IOException exception,
                    int executionCount) {
                if (executionCount >= retryTimes) {
                    times++;
                    return false;
                }
                return true;
            }
        };
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myretryhandler);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeOut * 1000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut * 1000);

        try {
            errorCode = httpClient.executeMethod(getMethod);
        } catch (IOException e) {
            System.out.print(e);
            return null;
        } finally {
//            Logger.getLogger(HttpClientApp.class.getName()).log(Level.SEVERE, null, "Finally Error");
        }
        try {
            StringBuffer outputString = new StringBuffer(new String(getMethod.getResponseBody(), "GBK").replace("\\n", "\n"));
            System.out.print(outputString+"\n");
            return outputString.toString();
        } catch (IOException ex) {
//            Logger.getLogger(HttpClientApp.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);
            return null;
        }
    }

    public static void main(String[] args) {
        String output = new HttpClientApp().httpRequest("http://bbs.dachengxi.com/top/dq.asp?jx_username=admin&jx_password=123456");
        System.out.print(output + "next");
        try {
            System.out.print(new String(output.getBytes("utf8")));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
