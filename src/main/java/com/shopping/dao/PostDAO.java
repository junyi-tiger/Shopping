package com.shopping.dao;

import com.shopping.bean.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    public PostDAO() {
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
    public void addPost(Post post) {
        String sql = "insert into post values(?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,post.getUser_account());
            ps.setString(2,post.getContent());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Post> seleteAll() {
        List<Post> posts = new ArrayList<Post>();
        String sql = "select * from post";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post=new Post();
                String user_account=rs.getString(1);
                String content=rs.getString(2);
                post.setUser_account(user_account);
                post.setContent(content);
                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
}
