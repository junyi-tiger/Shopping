package com.shopping.entity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
@Entity
@Table(name="card")
public class Card {
    private int id;
    private int userId;
    private String content;

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment") //设置主键自增
    @GeneratedValue(generator = "generator")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Column(name="user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name="content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
