package com.taotao.pojo;

public class Tb_shop {
    private String user_id;
    private String book_id;
    private Integer unit_price;
    private Integer inventory;
    private Integer quantity_purchased;
    private Integer shipments;

    public String getId() {
        return user_id;
    }

    public void setId(String id) {
        this.user_id = id;
    }

    public String getUsername() {
        return book_id;
    }

    public void setUsername(String username) {
        this.book_id = username;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setPassword(Integer unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setPhone_num(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getQuantity_purchased() {
        return quantity_purchased;
    }

    public void setAddress(Integer quantity_purchased) {
        this.quantity_purchased = quantity_purchased;
    }

    public Integer getShipments() {
        return shipments;
    }

    public void setBirth(Integer shipments) {this.shipments = shipments;
    }

    @Override
    public String toString() {
        return "Tb_Shop{" +
                "user_id=" + user_id +
                ", book_id='" + book_id + '\'' +
                ", unit_price='" + unit_price + '\'' +
                ", inventory='" + inventory + '\'' +
                ", quantity_purchased='" + quantity_purchased + '\'' +
                ", shipments='" + shipments + '\'' +
                '}';
    }

    public String getBookId() {
        return book_id;
    }

    public Integer getQuantityPurchased() {
        return quantity_purchased;
    }

}

