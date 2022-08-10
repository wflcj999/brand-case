package com.wmm.service;

import com.wmm.pojo.Brand;
import com.wmm.pojo.PageBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有
     *
     * @return List<Brand>
     */
    List<Brand> selectAll();

    /**
     * 添加
     */
    void add(Brand brand);

    /**
     * 修改
     */

    void update(Brand brand);

    /**
     * 删除
     *
     * @param id 需要删除的id
     */
    void delById(int id);


    /**
     * 分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    每页记录数
     * @return 当前页记录
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);


    /**
     * 批量删除
     *
     * @param ids 需要删除的id数组
     */
    void delByIds(int[] ids);

    /**
     * 根据条件 分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    每页记录数
     * @param brand       条件
     * @return 符合条件的记录
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

}
