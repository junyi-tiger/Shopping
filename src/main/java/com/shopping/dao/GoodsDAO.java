package com.shopping.dao;

import com.shopping.bean.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
    public GoodsDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/finaltest?characterEncoding=UTF-8&serverTimezone=GMT",
                "root",
                "root");
    }
    public void addGoods(Goods goods) {

        String sql = "insert into goods values(?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,goods.getName());
            ps.setString(2,goods.getPhoto());
            ps.setDouble(3,goods.getPrice());
            ps.setString(4,goods.getOwner());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有商品
     * @return
     */
    public List<Goods> seleteAll() {
        List<Goods> goodss = new ArrayList<Goods>();
        String sql = "select * from goods";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods=new Goods();
                /*String account=rs.getString(1);
                String password=rs.getString(2);
                shop.setPassword(password);
                shop.setAccount(account);
                shops.add(shop);*/
                String name=rs.getString(1);
                String photo=rs.getString(2);
                double price=rs.getDouble(3);
                String owner=rs.getString(4);
                goods.setPrice(price);
                goods.setPhoto(photo);
                goods.setOwner(owner);
                goods.setName(name);
                goodss.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodss;
    }
    public List<Goods> seleteShopGoods(String owner) {
        List<Goods> goodss = new ArrayList<Goods>();
        //String sql = "select * from goods owner=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("select * from goods where owner=?");) {
            ps.setString(1,owner);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods=new Goods();
                String name=rs.getString(1);
                String photo=rs.getString(2);
                double price=rs.getDouble(3);
                //String temp_owner=rs.getString(4);
                goods.setPrice(price);
                goods.setPhoto(photo);
                goods.setOwner(owner);
                goods.setName(name);
                goodss.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodss;
    }

    /**
     * 删除商品
     * @param name
     */
    public void deleteGoods(String name) {
        try (Connection c=getConnection();
             PreparedStatement ps=c.prepareStatement("delete from goods where name=?");){
            ps.setString(1,name);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除商家的所有商品
     * @param owner
     */
    public void deleteOwnerGoods(String owner) {
        try (Connection c=getConnection();
             PreparedStatement ps=c.prepareStatement("delete from goods where owner=?");){
            ps.setString(1,owner);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模糊查找商品
     * @param temp_name
     * @return
     */
    public List<Goods> seleteGoods(String temp_name) {
        List<Goods> goodss = new ArrayList<Goods>();
        //String sql = "select * from goods owner=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "select * ,(length(name)-length(?)) as rn from goods where name like ? order by rn");) {
            ps.setString(1,temp_name);
            ps.setString(2,"%"+temp_name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods=new Goods();
                String name=rs.getString(1);
                String photo=rs.getString(2);
                double price=rs.getDouble(3);
                String owner=rs.getString(4);
                goods.setPrice(price);
                goods.setPhoto(photo);
                goods.setOwner(owner);
                goods.setName(name);
                goodss.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodss;
    }
}
