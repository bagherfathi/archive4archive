/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popmenu;

/**
 *
 * @author Administrator
 */
public class ServerUrl {
    public static int urlTimeout=3000;
    public static int urlRetryTimes=3;
    public static String checkPasswordUrl = "http://fz2010.hz22.host724.cn/jx/check.asp";
    public static String checkListUrl = "http://fz2010.hz22.host724.cn/jx/dq.asp";
    public static String homePageUrl = "http://fz2010.hz22.host724.cn/jx/login.asp";
    public static String logoutUrl = "http://fz2010.hz22.host724.cn/jx/loginout.asp";

    //For test
//    public static String checkPasswordUrl = "http://127.0.0.1/test/login.html";
//    public static String checkListUrl = "http://127.0.0.1/test/test.html";
//    public static String homePageUrl = "http://www.google.com";
//    public static String logoutUrl = "http://127.0.0.1/test/logout.html";
}
