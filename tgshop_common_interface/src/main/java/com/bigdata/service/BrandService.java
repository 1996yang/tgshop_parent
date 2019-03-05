package com.bigdata.service;

import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Brand;
import java.util.List;
import java.util.Map;

/**
 * 品牌服务层接口
 */
public interface BrandService {
   /**
    * 返回全部列表
    */
   List<Brand> findAll();

    PageResult findPage(int page, int rows);

    void add(Brand brand);

    void update(Brand brand);

    Brand findOne(Long id);

    void delete(Long[] ids);

    PageResult findPage(Brand brand, int page, int rows);

    List<Map> selectOptionList();

}