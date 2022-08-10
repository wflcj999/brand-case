package com.wmm.service.impl;

import com.wmm.mapper.BrandMapper;
import com.wmm.pojo.Brand;
import com.wmm.pojo.PageBean;
import com.wmm.service.BrandService;
import com.wmm.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    //1.创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        List<Brand> brands = mapper.selectAll();

        //5. 释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.add(brand);
        sqlSession.commit();

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.update(brand);
        sqlSession.commit();

        //5. 释放资源
        sqlSession.close();
    }


    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {

        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;

        //5.查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, pageSize);

        //6.查询总记录数
        int totalCount = mapper.selectTotalCount();


        //7.封装数据
        PageBean<Brand> pageBean = new PageBean<>();

        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public void delById(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.delById(id);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();

    }

    public void delByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.delByIds(ids);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }


    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {

        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;

        // 处理brand条件， 模糊表达式

        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }


        //5.查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, pageSize, brand);

        //6.查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);


        //7.封装数据
        PageBean<Brand> pageBean = new PageBean<>();

        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }
}
