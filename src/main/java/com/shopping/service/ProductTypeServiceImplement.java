package com.shopping.service;

import com.shopping.dao.ProductTypeDao;
import com.shopping.entity.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductTypeServiceImplement implements ProductTypeService{
  @Autowired
  private  ProductTypeDao productTypeDao;
    @Override
    public List<ProductType> getAllProductTypes() {
        return productTypeDao.getAllProductTypes();
    }

    @Override
    public void addProductTypes(ProductType productType) {
        productTypeDao.addProductTypes(productType);
    }

}
