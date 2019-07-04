package com.bing.lan.servlet;

import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class BaseServlet implements Servlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        init();
    }

    protected void init() {
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    protected String getInitParameter(String paramName) {
        return servletConfig.getInitParameter(paramName);
    }

    protected Enumeration<String> getInitParameterNames() {
        return servletConfig.getInitParameterNames();
    }

    @Override
    public void destroy() {

    }
}
