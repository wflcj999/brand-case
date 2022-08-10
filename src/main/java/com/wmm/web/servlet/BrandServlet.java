package com.wmm.web.servlet;

import com.alibaba.fastjson.JSON;
import com.wmm.pojo.Brand;
import com.wmm.pojo.PageBean;
import com.wmm.service.BrandService;
import com.wmm.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private final BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 调用service查询
        List<Brand> brands = brandService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3. 写数据
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }


    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //2. 转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //3. 调用service添加
        brandService.add(brand);

        //4. 响应成功的标识
        response.getWriter().write("success");
    }


    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //2. 转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //3. 调用service修改
        brandService.update(brand);

        //4. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1. 接收 当前页码 和 每页展示条数 url？currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3. 写数据
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 批量删除
     */
    public void delByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收ids数据 [1,2,3]
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //2. 转为int[]对象
        int[] ids = JSON.parseObject(params, int[].class);

        //3. 调用service删除
        brandService.delByIds(ids);

        //4. 响应成功的标识
        response.getWriter().write("success");
    }


    /**
     * 删除
     */
    public void delById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收id数据
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);


        //2. 调用service删除
        brandService.delById(id);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 分页条件查询
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1. 接收 当前页码 和 每页展示条数 url？currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);


        //获取对应的查询条件对象
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);


        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3. 写数据
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

}
