package com.bigdata.service;

import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.TypeTemplate;

public interface TypeTemplateService {


    PageResult findPage(TypeTemplate typeTemplate, int page, int rows);

    void add(TypeTemplate typeTemplate);
    /**
     * 修改
     */
    void update(TypeTemplate typeTemplate);
    /**
     * 根据ID获取实体
     */
    TypeTemplate findOne(Long id);
    /**
     * 批量删除
     */
    void delete(Long[] ids);
}
