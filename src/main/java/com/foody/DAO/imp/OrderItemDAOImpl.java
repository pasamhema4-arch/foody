package com.foody.DAO.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foody.DAO.OrderItemDAO;
import com.foody.model.OrderItem;
import com.foody.utility.DBconnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	private static final String INSERT =
			"INSERT INTO orderitem(orderid,menuid,quantity,totalamount) VALUES(?,?,?,?)";

	private static final String SELECT =
			"SELECT * FROM orderitem WHERE orderitemid=?";

	private static final String UPDATE =
			"UPDATE orderitem SET orderid=?, menuid=?, quantity=?, totalamount=? WHERE orderitemid=?";

	private static final String DELETE =
			"DELETE FROM orderitem WHERE orderitemid=?";

	@Override
	public int addOrderItem(OrderItem orderItem) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getMenuId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setDouble(4, orderItem.getTotalAmount());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {

		OrderItem orderItem = null;

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(SELECT);
			pstmt.setInt(1, orderItemId);

			res = pstmt.executeQuery();

			if (res.next()) {

				orderItem = new OrderItem();

				orderItem.setOrderItemId(res.getInt("orderitemid"));
				orderItem.setOrderId(res.getInt("orderid"));
				orderItem.setMenuId(res.getInt("menuid"));
				orderItem.setQuantity(res.getInt("quantity"));
				orderItem.setTotalAmount(res.getDouble("totalamount"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderItem;
	}

	@Override
	public int updateOrderItem(OrderItem orderItem) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderItem.getOrderId());
			pstmt.setInt(2, orderItem.getMenuId());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setDouble(4, orderItem.getTotalAmount());
			pstmt.setInt(5, orderItem.getOrderItemId());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int deleteOrderItem(int orderItemId) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, orderItemId);

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
}