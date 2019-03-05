package com.bigdata.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.mapper.BrandMapper;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Brand;
import com.bigdata.pojo.BrandExample;
import com.bigdata.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
   @Autowired
   private BrandMapper brandMapper;
   @Override
   public List<Brand> findAll() {
       List<Brand> brands = brandMapper.selectByExample(null);
       System.out.println(brands);
       return brands;
   }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        //原来的方式可以直接返回PageInfo
        //List<Brand> brands = brandMapper.selectByExample(null);
        //PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        //PageResult pageResult = new PageResult(brandPageInfo.getTotal(), brandPageInfo.getList());

        //根据前端的接口返回格式封装一个PageResult返回
        Page<Brand> rows = (Page<Brand>) brandMapper.selectByExample(null);
        PageResult pageResult = new PageResult(rows.getTotal(), rows);

        return pageResult;
    }

    @Override
    public void add(Brand brand) {

       brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public Brand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id:ids){
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(Brand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //构建查询条件
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        if(brand.getName()!=null && brand.getName().length()>0){
            criteria.andNameLike("%"+brand.getName()+"%");
        }
        if(brand.getFirstChar()!=null && brand.getFirstChar().length()==1){
            criteria.andFirstCharEqualTo(brand.getFirstChar());
        }
        //根据前端的接口返回格式封装一个PageResult返回
        Page<Brand> rows = (Page<Brand>) brandMapper.selectByExample(example);
        PageResult pageResult = new PageResult(rows.getTotal(), rows);

        return pageResult;
    }
    // 品牌下拉框数据
    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();


    }

}