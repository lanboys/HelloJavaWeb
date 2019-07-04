package com.bing.lan.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpServlet extends BaseServlet {

    /**
     * 针对于POST请求方式:
     * request.setCharacterEncoding("UTF-8");
     * 注意:1:只对POST有效,2:必须放在获取任意参数之前.
     * 针对于GET请求方式:
     * 修改Tocmcat中的server.xml配置文件中修改端口的元素,对GET方式的默认编码.
     */

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(getInitParameter("characterEncoding"));
        response.setCharacterEncoding(getInitParameter("characterEncoding"));
        response.setContentType("text/html");
        service(request, response);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        } else if ("POST".equals(request.getMethod())) {
            doPost(request, response);
        }
    }

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException;

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
