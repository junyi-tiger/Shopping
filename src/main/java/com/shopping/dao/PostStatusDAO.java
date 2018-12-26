package com.shopping.dao;

import java.sql.*;

public class PostStatusDAO {
    public PostStatusDAO() {
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
    public int selectStatus() {
        int status=0;
        String sql = "select status from poststatus limit 1";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs=ps.executeQuery();
            status=rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    public void updateStatus(int num) {
        String sql = "update poststatus set status= ? where id=1";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1,num);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
