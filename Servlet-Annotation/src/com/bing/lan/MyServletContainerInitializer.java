package com.bing.lan;

import java.lang.reflect.Modifier;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;

/**
 * @author lan_bing
 * @date 2019-01-14 12:26
 * <p>
 * https://www.cnblogs.com/panxuejun/p/7090919.html
 */

@HandlesTypes(MyServletContainerInitializer.WebParameter.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webParams, ServletContext servletContext) throws ServletException {
        System.out.println("onStartup(): ");

        if (webParams != null) {
            for (Class<?> paramClass : webParams) {//SpringServletContainerInitializer 的写法
                if (!paramClass.isInterface() && !Modifier.isAbstract(paramClass.getModifiers()) &&
                        WebParameter.class.isAssignableFrom(paramClass)) {
                    try {
                        ((WebParameter) paramClass.newInstance()).onStartup(servletContext);
                    } catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate WebParam class", ex);
                    }
                }
            }
        }
    }

    public static class ServletParameter implements WebParameter {

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            ServletRegistration.Dynamic myHello = servletContext.addServlet("myHello", MyHelloServlet.class);
            myHello.setLoadOnStartup(1);
            myHello.addMapping("/myHello");
            myHello.setInitParameter("characterEncoding", "UTF-8");
        }
    }

    public static class ServletParameter2 implements WebParameter {

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            ServletRegistration.Dynamic myHello = servletContext.addServlet("myHello2", MyHelloServlet.class);
            myHello.setLoadOnStartup(1);
            myHello.addMapping("/myHello2");
            myHello.setInitParameter("characterEncoding", "UTF-8");
        }
    }

    public interface WebParameter {

        public void onStartup(ServletContext servletContext) throws ServletException;
    }
}
