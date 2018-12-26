package com.shopping.service;

import com.shopping.entity.ProductType;

import java.util.List;

public interface ProductTypeService {
    public List<ProductType> getAllProductTypes();
    public void addProductTypes(ProductType productType);
}
