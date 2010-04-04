package com.ft.commons.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * 对WEB应用信能的测试Filter
 *
 *
 * @web.filter display-name="PerformancFilter" name="PerformancFilter"
 * @web.filter-mapping url-pattern="."
 */
public class PerformancFilter implements Filter {
    FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    /*
     *  (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(
        ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        long begin = System.currentTimeMillis();

        request.setAttribute("performancBegin", new Long(begin));
        chain.doFilter(request, response);

        long end = System.currentTimeMillis();
        request.setAttribute("performancEnd", new Long(end));

        int totalTime = (int) (end - begin);
        request.setAttribute("performancTime", new Integer(totalTime));
        write(httpServletRequest, totalTime);
    }

    /**
     * 将相关的数据写到控制台
     *
     * @param httpServletRequest
     * @param totalTime
     */
    public void write(HttpServletRequest httpServletRequest, int totalTime) {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        writer.println(
            "===============" + httpServletRequest.getRequestURL().toString());
        writer.println("Total elapsed time is: " + totalTime + " seconds.");
        writer.println(
            "===============" + httpServletRequest.getRequestURL().toString());
        writer.flush();
        this.config.getServletContext().log(sw.getBuffer().toString());
    }

    public void destroy() {
        config = null;
    }
}
