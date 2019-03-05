package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.mypojo.MySpecification;
import com.bigdata.mypojo.PageResult;
import com.bigdata.mypojo.Result;
import com.bigdata.pojo.Brand;
import com.bigdata.pojo.Specification;
import com.bigdata.service.SpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("specification")
public class SpecificationController {
    @Reference
    private SpecificationService specificationService;


    @RequestMapping("add")
    public Result add(@RequestBody MySpecification mySpecification){
        try {
            specificationService.add(mySpecification);
            return new Result(true,"增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 根据ID查询
     */
    @RequestMapping("/findOne")
    public MySpecification findOne(Long id){
        return specificationService.findOne(id);
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody MySpecification mySpecification){
        try {
            specificationService.update(mySpecification);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            specificationService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("search")
    public PageResult search(@RequestBody Specification specification, Integer page, Integer rows  ){
        return specificationService.findPage(specification, page, rows);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() throws Exception {
        return specificationService.selectOptionList();
    }
}
