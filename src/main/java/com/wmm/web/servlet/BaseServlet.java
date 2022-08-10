package com.wmm.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet，根据请求的最后一段路径来进行方法分发
 */
public class BaseServlet extends HttpServlet {

    // 根据请求的最后一段路径来进行方法分发

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 获取请求路径
        String uri = req.getRequestURI();   //brand/brand/selectAll

        //2. 获取最后一段路径，方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1); // /selectAll   or  selectAll

        //3. 执行方法
        //3.1 获取BrandServlet or UserServlet 字节码对象Class
        // 谁调用我（this 所在的方法），我（this）代表谁
        //System.out.println(this); //BrandServlet or HttpServlet ?  BrandServlet!

        Class<? extends BaseServlet> cls = this.getClass();


        //3.2 获取方法Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //3.3 执行方法
            method.invoke(this, req, resp);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
