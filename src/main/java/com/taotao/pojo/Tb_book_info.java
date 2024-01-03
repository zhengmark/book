package com.taotao.pojo;

public class Tb_book_info {
    private String book_id;
    private String book_name;
    private String category;
    private Integer unit_price;
    private String author;
    private String introduce;
    private Integer inventory;

    public String getId() {
        return book_id;
    }

    public void setId(String id) {
        this.book_id = id;
    }

    public String getUsername() {
        return book_name;
    }

    public void setUsername(String username) {
        this.book_name = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Tb_user{" +
                "id=" + book_id +
                ", name='" + book_name + '\'' +
                ", password='" + category + '\'' +
                ", address='" + author + '\'' +
                ", address='" + introduce + '\'' +
                '}';
    }
}

