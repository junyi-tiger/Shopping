package com.shopping.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "page")
public class Page {
    private int id;
    private int pageSize;
    private int beginIndex;

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

     @Column(name = "page_size")
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Column(name = "begin_index")
    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }


}

