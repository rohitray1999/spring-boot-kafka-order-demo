package com.example.kafkademo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class OrderEvent {
    @NotBlank
    private String orderId;

    @NotBlank
    private String productName;

    @PositiveOrZero
    private double amount;

    public OrderEvent() {}

    public OrderEvent(String orderId, String productName, double amount) {
        this.orderId = orderId;
        this.productName = productName;
        this.amount = amount;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "OrderEvent{orderId='" + orderId + "', productName='" +
                productName + "', amount=" + amount + "}";
    }
}
