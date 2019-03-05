package com.bigdata.service;

import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Seller;

public interface SellerService {


    void add(Seller seller);

    PageResult findPage(Seller seller, Integer page, Integer rows);

    Seller findOne(String id);

    void updateStatus(String sellerId, String status);
}
