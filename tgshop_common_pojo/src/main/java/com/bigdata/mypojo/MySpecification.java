package com.bigdata.mypojo;

import com.bigdata.pojo.Specification;
import com.bigdata.pojo.SpecificationOption;
import java.io.Serializable;
import java.util.List;
// 规格组合实体类
public class MySpecification implements Serializable {
    //规格
    private Specification specification;
    //规格选项
    private List<SpecificationOption> specificationOptionList;

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }

    public Specification getSpecification() {

        return specification;
    }

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public MySpecification() {

    }

    public MySpecification(Specification specification, List<SpecificationOption> specificationOptionList) {

        this.specification = specification;
        this.specificationOptionList = specificationOptionList;
    }
}