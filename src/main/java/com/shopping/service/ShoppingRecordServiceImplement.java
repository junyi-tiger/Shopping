package com.shopping.service;

import com.shopping.dao.ShoppingRecordDao;
import com.shopping.entity.ShoppingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
@Service
public class ShoppingRecordServiceImplement implements ShoppingRecordService {
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId,String time) {
        return shoppingRecordDao.getShoppingRecord(userId,productId,time);
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        System.out.println("传到Service中的shopId is "+shoppingRecord.getShopId());
        shoppingRecordDao.addShoppingRecord(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return shoppingRecordDao.deleteShoppingRecord(userId,productId);
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        return shoppingRecordDao.updateShoppingRecord(shoppingRecord);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus) {
        return shoppingRecordDao.getShoppingRecordsByOrderStatus(orderStatus);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {

        List<ShoppingRecord> shoppingRecordList=shoppingRecordDao.getShoppingRecords(userId);
        shoppingRecordList.forEach(e->{
            System.out.println("在Service中的productId,userId(买家),shopId(卖家)是："+e.getProductId()+"  "
                    +e.getUserId()+" "+e.getShopId()+" "+e.getTime()+" "+e.getProductPrice()+" "+e.getCounts()+e.getOrderStatus());
        });
        return shoppingRecordDao.getShoppingRecords(userId);
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        System.out.println("这里是Service");
        List<ShoppingRecord> records=new ArrayList<>();
        records=shoppingRecordDao.getAllShoppingRecords();
        for(int i=0;i<records.size();i++){
            System.out.println("user is "+records.get(i).getUserId());
        }
        return shoppingRecordDao.getAllShoppingRecords();
    }

    @Override
    public boolean getUserProductRecord(int userId,int productId) {
        return shoppingRecordDao.getUserProductRecord(userId,productId);
    }
}
