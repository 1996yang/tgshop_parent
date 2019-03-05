package com.bigdata.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.mapper.SellerMapper;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Seller;
import com.bigdata.pojo.SellerExample;
import com.bigdata.service.SellerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerMapper sellerMapper;
    // 商家入驻
    @Override
    public void add(Seller seller) {
        // 初始化审核状态
        seller.setStatus("0");
        seller.setCreateTime(new Date());
        sellerMapper.insert(seller);
    }
    // 搜索商家
    @Override
    public PageResult findPage(Seller seller, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        SellerExample example = new SellerExample();
        SellerExample.Criteria criteria = example.createCriteria();
        if (seller!=null){
            if (seller.getNickName()!=null&&"".equals(seller.getNickName())){
                criteria.andNickNameLike("%"+seller.getNickName()+"%");
            }
        }
        Page<Seller> pages =(Page<Seller>)sellerMapper.selectByExample(example);
        return new PageResult(pages.getTotal(),pages.getResult());
    }
    // 通过商家id查找商家信息
    @Override
    public Seller findOne(String id) {
        return sellerMapper.selectByPrimaryKey(id);
    }
    // 修改商家状态(商家审核)
    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = new Seller();
        seller.setSellerId(sellerId);
        seller.setStatus(status);
        sellerMapper.updateByPrimaryKeySelective(seller);
    }
}
