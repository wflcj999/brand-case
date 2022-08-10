package com.wmm.mapper;

import com.wmm.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有
     *
     * @return List<Brand>
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    /**
     * 添加
     */
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 修改
     */
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void update(Brand brand);


    /**
     * 删除
     *
     * @param id 需要删除的id
     */
    @Delete("delete from tb_brand where id=#{id}")
    void delById(int id);

    /**
     * 查询总记录数
     *
     * @return 数据总记录数
     */
    @Select("select count(id) from tb_brand")
    int selectTotalCount();


    /**
     * 分页查询
     *
     * @param begin 开始索引
     * @param size  每页记录数
     * @return 当前页记录
     */
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);


    /**
     * 批量删除
     *
     * @param ids 需要删除的id数组
     */
    void delByIds(@Param("ids") int[] ids);


    /**
     * 分页条件查询
     *
     * @param begin 开始索引
     * @param size  每页记录数
     * @param brand 条件
     * @return 当前页记录
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand);


    /**
     * 查询条件总记录数
     *
     * @return 数据总记录数
     */
    int selectTotalCountByCondition(Brand brand);
}
