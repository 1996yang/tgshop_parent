package com.bigdata.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.mapper.SpecificationMapper;
import com.bigdata.mapper.SpecificationOptionMapper;
import com.bigdata.mypojo.MySpecification;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.*;
import com.bigdata.service.SpecificationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public void add(MySpecification mySpecification) {
        specificationMapper.insert(mySpecification.getSpecification());
        for (SpecificationOption specificationOption : mySpecification.getSpecificationOptionList()) {
            specificationOption.setSpecId(mySpecification.getSpecification().getId());
            //2.1插入规格选项表的数据
            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public MySpecification findOne(Long id) {
        // 1.根据id查询规格表
        Specification specification = specificationMapper.selectByPrimaryKey(id);
        // 2.把id作为规格选项表的specId查询规格选项
        SpecificationOptionExample example = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<SpecificationOption> list = specificationOptionMapper.selectByExample(example);
        // 3. 把查询出来的规格组装成MySpecification返回
        MySpecification mySpecification = new MySpecification(specification, list);
        return mySpecification;
    }

    @Override
    public void update(MySpecification mySpecification) {
        // 1.更新规格表
        specificationMapper.updateByPrimaryKey(mySpecification.getSpecification());
        // 2.更新规格选项表(无法直接更新,只能删了重新插入)
        // 2.1把指定的specId的规格选项数据先删除
        SpecificationOptionExample example = new SpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(mySpecification.getSpecification().getId());
        specificationOptionMapper.deleteByExample(example);
        // 2.2重新插入规格选项数据
        for (SpecificationOption specificationOption : mySpecification.getSpecificationOptionList()) {
            specificationOption.setSpecId(mySpecification.getSpecification().getId());
            //2.插入规格选项表的数据
            specificationOptionMapper.insert(specificationOption);
        }

    }

    @Override
    public void delete(Long[] ids) {
        // 1.根据id删除规格
        for (Long id:ids){
            specificationMapper.deleteByPrimaryKey(id);
            // 2.根据id作为specId删除规格选项
            SpecificationOptionExample example = new SpecificationOptionExample();
            example.createCriteria().andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
    }

    @Override
    public PageResult findPage(Specification specification, Integer page, Integer rows) {
        SpecificationExample example= new SpecificationExample();
        if(specification != null){
            SpecificationExample.Criteria criteria = example.createCriteria();
            if (specification.getSpecName() != null && !"".equals(specification.getSpecName())){
                criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
            }
        }
        PageHelper.startPage(page, rows);
        Page<Specification> pageList = (Page<Specification>)specificationMapper.selectByExample(example);
        return new PageResult(pageList.getTotal(), pageList.getResult());
    }

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

}
