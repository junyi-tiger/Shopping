package com.shopping.bean;

public class Order {
    private String user_account;
    private String shop_account;
    private String goods_name;
    private int goods_num;
    private double sum;
    private int status;
    private double goods_price;


    public double getGoods_price() {
        return goods_price;
    }
    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }
    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getShop_account() {
        return shop_account;
    }

    public void setShop_account(String shop_account) {
        this.shop_account = shop_account;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
