package com.foody.DAO;

import com.foody.model.OrderItem;

public interface OrderItemDAO {

	int addOrderItem(OrderItem orderItem);

	OrderItem getOrderItem(int orderItemId);

	int updateOrderItem(OrderItem orderItem);

	int deleteOrderItem(int orderItemId);

}