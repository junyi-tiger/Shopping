package com.shopping.dao;

import com.shopping.entity.ProductType;

import java.util.List;

public interface ProductTypeDao {
    public List<ProductType> getAllProductTypes();
    public void addProductTypes(ProductType productType);
}
