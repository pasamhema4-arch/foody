package com.foody.DAO;
import java.util.List;
import com.foody.model.OrderTable;

public interface OrderTableDAO {

	int addOrder(OrderTable order);

	OrderTable getOrder(int orderId);

	int updateOrder(OrderTable order);

	int deleteOrder(int orderId);
	List<OrderTable> getOrdersByUserId(int userId);

}