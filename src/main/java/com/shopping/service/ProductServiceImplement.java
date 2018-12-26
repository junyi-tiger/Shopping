package com.shopping.service;

import com.shopping.dao.EvaluationDao;
import com.shopping.dao.ProductDao;
import com.shopping.dao.ShoppingCarDao;
import com.shopping.dao.ShoppingRecordDao;
import com.shopping.entity.Product;
import com.shopping.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14437 on 2017/3/2.
 */

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public Product getProduct(int id) {
        System.out.println("在Product Service里面发斯蒂芬是");
        Product i=new Product();
        i=productDao.getProduct(id);
        System.out.println("product is "+i.getName());
        return productDao.getProduct(id);
    }

    @Override
    public Product getProduct(String name) {
        return productDao.getProduct(name);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    @Transactional
    public Response deleteProduct(int id) {
        try {
            evaluationDao.deleteEvaluationByProduct(id);
            shoppingCarDao.deleteShoppingCarByProduct(id);
            shoppingRecordDao.deleteShoppingRecordByProductId(id);
            productDao.deleteProduct(id);
            return new Response(1, "删除商品成功", null);
        }catch (Exception e){
            return new Response(0,"删除商品失败",null);
        }
    }
    @Override

    public Response deleteProductByBoss(int bossId) {
        try {
            List<Product> products=new ArrayList<>();
            products=productDao.getProductsByBoss(bossId);
            for(int i=0;i<products.size();i++){
                int id=products.get(i).getId();
                System.out.println("id is "+ id);
                evaluationDao.deleteEvaluationByProduct(id);
                shoppingCarDao.deleteShoppingCarByProduct(id);
                shoppingRecordDao.deleteShoppingRecordByProductId(id);
                productDao.deleteProduct(id);
            }
            return new Response(1, "删除商品成功", null);
        }catch (Exception e){
            return new Response(0,"删除商品失败",null);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        return productDao.getProductsByKeyWord(searchKeyWord);
    }

    @Override
    public List<Product> getProductsByBoss(int bossId){
        return productDao.getProductsByBoss(bossId);
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
