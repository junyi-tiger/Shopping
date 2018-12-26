package com.shopping.dao;


import com.shopping.bean.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public OrderDAO() {
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
    public void addOrder(Order temp_order) {
        String sql = "insert into `order` values(?,?,?,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,temp_order.getUser_account());
            ps.setString(2,temp_order.getShop_account());
            ps.setString(3,temp_order.getGoods_name());
            ps.setInt(4,temp_order.getGoods_num());
            ps.setDouble(5,temp_order.getSum());
            ps.setDouble(7,temp_order.getGoods_price());
            ps.setInt(6,0);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Order> seleteOrderByUser(String user_account) {
        List<Order> orders = new ArrayList<Order>();
        String sql = "select * from `order` where user_account=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,user_account);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order=new Order();
                //String user_account=rs.getString(1);
                String temp_user_account=rs.getString(1);
                String shop_account=rs.getString(2);
                String goods_name=rs.getString(3);
                int goods_num=rs.getInt(4);
                double sum=rs.getDouble(5);
                int status=rs.getInt(6);
                double goods_price=rs.getDouble(7);
                //post.setUser_account(user_account);
                order.setUser_account(temp_user_account);
                order.setShop_account(shop_account);
                order.setGoods_name(goods_name);
                order.setGoods_num(goods_num);
                order.setSum(sum);
                order.setStatus(status);
                order.setGoods_price(goods_price);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}
