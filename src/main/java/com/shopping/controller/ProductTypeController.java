package com.shopping.controller;

import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.ProductType;
import com.shopping.service.ProductTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTypeController {
@Resource
   private ProductTypeService productTypeService;
    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addType(String type) {
        String result ="fail";
        ProductType productType=new ProductType();
        productType.setType(type);
        productTypeService.addProductTypes(productType);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/getAllTypes",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllTypes(){
        List<ProductType> productTypeList = new ArrayList<>();
        productTypeList = productTypeService.getAllProductTypes();
        String allProductTypes = JSONArray.toJSONString(productTypeList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProductTypes",allProductTypes);
        return resultMap;
    }

}
