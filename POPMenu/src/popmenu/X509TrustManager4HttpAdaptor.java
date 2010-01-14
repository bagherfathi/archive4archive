/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package popmenu;

/**
 *
 * @author Administrator
 */
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 2007-10-7
 * Time: 17:51:22
 * To change this template use File | Settings | File Templates.
 */
public class X509TrustManager4HttpAdaptor implements X509TrustManager {

 /* (non-Javadoc)
  * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
  */
 public void checkClientTrusted(X509Certificate[] arg0, String arg1)
  throws CertificateException {

 }

 /* (non-Javadoc)
  * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
  */
 public void checkServerTrusted(X509Certificate[] arg0, String arg1)
  throws CertificateException {

 }

 /* (non-Javadoc)
  * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
  */
 public X509Certificate[] getAcceptedIssuers() {
  return null;
 }

}