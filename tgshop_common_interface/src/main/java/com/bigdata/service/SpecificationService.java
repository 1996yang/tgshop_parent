package com.bigdata.service;

import com.bigdata.mypojo.MySpecification;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Brand;
import com.bigdata.pojo.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    // 添加
    void add(MySpecification mySpecification);

    /**
     * 根据ID获取实体
     */
    MySpecification findOne(Long id);
    /**
     * 修改
     */
    void update(MySpecification MySpecification);
    // 删除
    void delete(Long[] ids);
    // 搜索分页
    PageResult findPage(Specification specification, Integer page, Integer rows);

    List<Map> selectOptionList();

}
