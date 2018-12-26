package com.shopping.dao;

import com.shopping.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public UserDAO() {
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
     * 添加用户
     * @param user
     */
    public void addUser(User user) {
        String sql = "insert into user values(?,?,0)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<User> seleteAll() {
        List<User> users = new ArrayList<User>();
        String sql = "select * from user";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                String account=rs.getString(1);
                String password=rs.getString(2);
                int member=rs.getInt(3);
                user.setPassword(password);
                user.setAccount(account);
                user.setMember(member);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 用户注册成为会员
     * @param account
     */
    public void updateMember(String account) {
        String sql = "update user set member=1 where account = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, account);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户是否是会员
     * @param account 用户账号
     * @return true 如果是会员
     */
    public boolean isMember(String account) {
        String sql = "select * from user where account = ? and member = 1";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, account);
            ResultSet resultSet = ps.executeQuery();
            resultSet.last();
            if (resultSet.getRow()!=0)return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int selectMember(String account){
        int temp=0;
        String sql = "select * from user where account = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, account);
            //ps.execute();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                temp=rs.getInt(3);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 查询用户是否存在
     * @param account
     * @return
     */
    public boolean userExist(String account){
        boolean hasUser=false;
        String sql = "select * from user where account = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, account);
            ResultSet rs=ps.executeQuery();
            rs.last();
            if (rs.getRow()!=0)hasUser=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasUser;
    }
}
