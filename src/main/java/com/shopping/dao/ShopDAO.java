package com.shopping.dao;


import com.shopping.bean.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {
    public ShopDAO() {
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
    public void addShop(Shop shop) {
        String sql = "insert into shop values(?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, shop.getAccount());
            ps.setString(2, shop.getPassword());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询商家是否存在
     * @return boolean
     */
    public boolean shopExists(String account){
            boolean hasShop=false;
            String sql = "select * from shop where account = ?";
            try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1,account);
                ResultSet rs = ps.executeQuery();
                rs.last();
                if (rs.getRow()!=0)hasShop=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return hasShop;
    }

    public List<Shop> seleteAll() {
        List<Shop> shops = new ArrayList<Shop>();
        String sql = "select * from shop";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shop shop = new Shop();
                String account=rs.getString(1);
                String password=rs.getString(2);
                shop.setPassword(password);
                shop.setAccount(account);
                shops.add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shops;
    }
    public void deleteShop(String account) {
        try (Connection c=getConnection();
             PreparedStatement ps=c.prepareStatement("delete from shop where account=?");){
            ps.setString(1,account);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
