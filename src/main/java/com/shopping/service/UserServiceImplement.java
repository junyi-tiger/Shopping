package com.shopping.service;

import com.shopping.dao.*;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.entity.UserDetail;
import com.shopping.utils.Response;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 14437 on 2017/3/1.
 */
@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDetailDao userDetailDao;
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Autowired
    private EvaluationDao evaluationDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public User getUser(int id) {
        System.out.println("This is UserService ");
        System.out.println("userId is "+userDao.getUser(id).getId());

        return userDao.getUser(id);
    }

    @Override
    public User getUser(String nameOrEmail) {
        return userDao.getUser(nameOrEmail);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    //推荐写法，具体业务逻辑放在Service实现方法里面
    @Override
    @Transactional
    public Response deleteUser(int id) {
        //判断此用户是否存在购买记录、评价记录、购物车记录，如果存在，则应该先删除对应的记录，否则后续删除会出错
        try {
            evaluationDao.deleteEvaluationByUser(id);
            shoppingCarDao.deleteShoppingCarByUser(id);
            shoppingRecordDao.deleteShoppingRecordByUser(id);
            userDetailDao.deleteUserDetail(id);
            userDao.deleteUser(id);
            return new Response(1, "删除成功", null);
        }catch (Exception e) {
            return new Response(0, "删除失败", null);
        }
    }




    @Override
    @Transactional
    public Response deleteBoss(int id) {
        //判断此用户是否存在购买记录、评价记录、购物车记录，如果存在，则应该先删除对应的记录，否则后续删除会出错
        try {
            evaluationDao.deleteEvaluationByUser(id);
            shoppingCarDao.deleteShoppingCarByUser(id);
            shoppingRecordDao.deleteShoppingRecordByUser(id);
            userDetailDao.deleteUserDetail(id);
            List<Product> products=productDao.getProductsByBoss(id);
            for(int i=0;i<products.size();i++){
                int tempId=products.get(i).getId();
                productDao.deleteProduct(tempId);
            }
            userDao.deleteUser(id);
            return new Response(1, "删除成功", null);
        }catch (Exception e) {
            return new Response(0, "删除失败", null);
        }
    }



    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public boolean becomeVip(int userId){
        return userDao.becomeVip(userId);
    }
}
