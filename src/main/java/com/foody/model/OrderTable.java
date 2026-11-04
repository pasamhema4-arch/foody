package com.foody.model;

import java.sql.Timestamp;

public class OrderTable {

    private int orderId;
    private int id;
    private int restaurantId;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;
    private String paymentMode;

    public OrderTable() {
    }

  

    public OrderTable(int orderId, int id, int restaurantId, Timestamp orderDate, double totalAmount, String status,
			String paymentMode) {
		super();
		this.orderId = orderId;
		this.id = id;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}



	public OrderTable(int id, int restaurantId, Timestamp orderDate, double totalAmount, String status,
			String paymentMode) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id =id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
