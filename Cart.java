package com.tap.utility;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private int restaurantId = -1; // -1 indicates no restaurant is associated yet
    private Map<Integer, CartItem> items = new HashMap<>();

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void clearCart() {
        items.clear();
        restaurantId = -1; // Reset the restaurant ID
    }

    public void addCartItem(int restaurantId, int menuId, String itemName, double price, int quantity) {
        if (this.restaurantId != -1 && restaurantId != this.restaurantId) {
            clearCart(); // Clear cart if the restaurant changes
        }
        this.restaurantId = restaurantId;
        if (items.containsKey(menuId)) {
            CartItem existingItem = items.get(menuId);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            items.put(menuId, new CartItem(menuId, itemName, price, quantity));
        }
    }

    public void removeCartItem(int menuId) {
        items.remove(menuId);
    }

    public void updateCartItem(int menuId, int newQuantity) {
        CartItem item = items.get(menuId);
        if (item != null) {
            if (newQuantity > 0) {
                item.setQuantity(newQuantity);
            } else {
                items.remove(menuId);
            }
        }
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    // Calculate total price
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem item : items.values()) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    // Get total number of items in the cart
    public int getTotalItems() {
        int total = 0;
        for (CartItem item : items.values()) {
            total += item.getQuantity();
        }
        return total;
    }
}
