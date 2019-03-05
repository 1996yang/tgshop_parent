package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.mypojo.PageResult;
import com.bigdata.mypojo.Result;
import com.bigdata.pojo.Brand;
import com.bigdata.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }
    @RequestMapping("findPage")
    public PageResult findPage(int page,int rows){
        return brandService.findPage(page,rows);
    }

    @RequestMapping("add")
    public Result add(@RequestBody Brand brand){
        try {
            brandService.add(brand);
            return new Result(true,"增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
    // 修改
    @RequestMapping("update")
    public Result update(@RequestBody Brand brand){
        try {
            brandService.update(brand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,  "修改失败");
        }
    }
    // 通过id获取实体
    @RequestMapping("findOne")
    public Brand findOne(Long id){
        return brandService.findOne(id);
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 带条件分页
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody Brand brand, int page, int rows  ){
        return brandService.findPage(brand, page, rows);
    }

    //
    @RequestMapping("selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();

    }

}
