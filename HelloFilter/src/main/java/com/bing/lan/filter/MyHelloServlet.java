package com.bing.lan.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/hello/hello/myhello", initParams = {@WebInitParam(name = "characterEncoding", value = "UTF-8")})
public class MyHelloServlet extends HttpServlet {

    @Override
    protected void init() {
        Enumeration<String> initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String element = initParameterNames.nextElement();
            String initParameter = getInitParameter(element);
            System.out.println("MyHelloServlet.init: " + initParameter);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //response.addCookie(new Cookie("bing","lan"));
        PrintWriter writer = response.getWriter();
        writer.println("MyHelloServlet ----------");

        try {
            HttpSession session = request.getSession();
            writer.println("session: " + session.getAttribute("bing"));

            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                writer.println(cookie.getName());
                writer.println(cookie.getValue());
                writer.println(cookie.getDomain());
                writer.println(cookie.getMaxAge());
                writer.println(cookie.getPath());
                writer.println(cookie.getVersion());
                writer.println(cookie.getComment());
            }
        } catch (Exception e) {
            writer.println(e);
        }
        writer.println("MyHelloServlet ----------");
    }
}
