package com.tap.utility;

public class CartItem {

    private int menuId;
    private String name;
    private double price;
    private int quantity;

    public CartItem() {}

    public CartItem(int menuId, String name, double price, int quantity) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + menuId +
                ", itemName='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
