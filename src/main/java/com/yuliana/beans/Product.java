package com.yuliana.beans;

import java.util.Objects;

public class Product {
    private int productId;
    private String title;
    private String pictureName;
    private float price;
    private int count;
    private String category;

    public Product(int productId, String title, String pictureName, float price, int count, String category) {
        this.title = title;
        this.pictureName = pictureName;
        this.price = price;
        this.count = count;
        this.category = category;
        this.productId = productId;
    }

    public Product(){}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Float.compare(product.price, price) == 0 &&
                count == product.count &&
                Objects.equals(title, product.title) &&
                Objects.equals(pictureName, product.pictureName) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, pictureName, price, count, category);
    }
}
