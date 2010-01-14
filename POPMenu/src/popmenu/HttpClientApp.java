/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popmenu;

import java.io.IOException;
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

    public String httpRequest() {

        String outString = getUrlResponse("http://localhost/test/test.html", 3, 3);
        return outString;
    }

    private String getUrlResponse(String url, int timeOut, final int retryTimes) {
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
            return null;
        } finally {
            Logger.getLogger(HttpClientApp.class.getName()).log(Level.SEVERE, null, "Finally Error");
        }
        try {
            return getMethod.getResponseBodyAsString();
        } catch (IOException ex) {
            Logger.getLogger(HttpClientApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.print(new HttpClientApp().httpRequest());
    }
}
