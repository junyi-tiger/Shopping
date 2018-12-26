package com.shopping.dao;

import com.shopping.bean.Access;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessDAO {
    public AccessDAO() {
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

    /**
     * 查询商品的所有评价
     * @param goods_name
     * @return
     */
    public List<Access> seleteAccessByGoods(String goods_name) {
        List<Access> accesses = new ArrayList<Access>();
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("select * from access where goods_name=?");) {
            ps.setString(1,goods_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Access access=new Access();
                String user_account=rs.getString(1);
                String temp_goods_name=rs.getString(2);
                String content=rs.getString(3);
                //double price=rs.getDouble(3);
                //String temp_owner=rs.getString(4);
                access.setContent(content);
                access.setUser_account(user_account);
                access.setGoods_name(temp_goods_name);
                accesses.add(access);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accesses;
    }

    /**
     * 增加商品评价
     * @param access
     */
    public void addAccess(Access access) {
        String sql = "insert into access values(?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,access.getUser_account());
            ps.setString(2,access.getGoods_name());
            ps.setString(3,access.getContent());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除商品评价
     * @param access
     */
    public void deleteAccess(Access access) {
        String sql = "delete from access where user_account = ? and goods_name = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,access.getUser_account());
            ps.setString(2,access.getGoods_name());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}