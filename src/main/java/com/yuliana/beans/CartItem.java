package com.yuliana.beans;

import java.util.Objects;

public class CartItem {

    private int userId;
    private int productId;
    private int count;
    private String title;
    private float price;

    public CartItem(int userId, int productId, int count, String title, float price) {
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.title = title;
        this.price = price;
    }

    public CartItem(int count, String title, float price) {
        this.count = count;
        this.title = title;
        this.price = price;
    }

    public CartItem(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return userId == cartItem.userId &&
                productId == cartItem.productId &&
                count == cartItem.count &&
                Float.compare(cartItem.price, price) == 0 &&
                Objects.equals(title, cartItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId, count, title, price);
    }
}
