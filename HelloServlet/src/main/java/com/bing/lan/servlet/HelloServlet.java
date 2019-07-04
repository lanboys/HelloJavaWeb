package com.bing.lan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/hello/hello/hello", initParams = {@WebInitParam(name = "characterEncoding", value = "UTF-8")})
public class HelloServlet extends HttpServlet {

    @Override
    protected void init() {
        Enumeration<String> initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String element = initParameterNames.nextElement();
            String initParameter = getInitParameter(element);
            System.out.println("HelloServlet.init: " + initParameter);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //response.addCookie(new Cookie("bing", "lan"));
        HttpSession session = request.getSession();

        session.setAttribute("bing","lan");

        PrintWriter writer = response.getWriter();
        writer.print("HelloServlet ----------");

        System.out.println("HelloServlet.init: " + this);
    }
}
