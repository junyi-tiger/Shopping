package com.shopping.dao;


import com.shopping.bean.AdminContent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminContentDAO {
    public AdminContentDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/finaltest?characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT",
                "root",
                "root");
    }
    public void addContent(AdminContent adminContent) {
        String sql = "insert into admincontent values(?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, adminContent.getContent());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<AdminContent> seleteAll() {
        List<AdminContent> contents = new ArrayList<AdminContent>();
        String sql = "select * from admincontent";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdminContent content=new AdminContent();
                String tempContent=rs.getString(1);
                content.setContent(tempContent);
                contents.add(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }
}
