package com.shopping.dao;

import com.shopping.entity.ProductType;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class ProductTypeDaoImplement implements ProductTypeDao {

    @Resource
    private SessionFactory sessionFactory;


    @Override
    public List<ProductType> getAllProductTypes() {
        String hql = "from ProductType";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void addProductTypes(ProductType productType) {
        sessionFactory.getCurrentSession().save(productType);
    }
}
