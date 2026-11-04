package com.foody.DAO.imp;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foody.DAO.OrderTableDAO;
import com.foody.model.OrderTable;
import com.foody.utility.DBconnection;

public class OrderTableDAOImpl implements OrderTableDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet res;

    private static final String INSERT =
            "INSERT INTO ordertable(id,restaurantid,orderdate,totalamount,status,paymentmode) VALUES(?,?,?,?,?,?)";

    private static final String SELECT =
            "SELECT * FROM ordertable WHERE orderid=?";

    private static final String UPDATE =
            "UPDATE ordertable SET id=?, restaurantid=?, orderdate=?, totalamount=?, status=?, paymentmode=? WHERE orderid=?";

    private static final String DELETE =
            "DELETE FROM ordertable WHERE orderid=?";
    private static final String GET_ORDERS_BY_USER =
    	    "SELECT * FROM ordertable WHERE userid=? ORDER BY orderdate DESC";

    @Override
    public int addOrder(OrderTable order) {
    	int orderId=0;

        try {

            con = DBconnection.getConnection();
            pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, order.getid());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setTimestamp(3, order.getOrderDate());
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMode());

            pstmt.executeUpdate();
            
            ResultSet res=pstmt.getGeneratedKeys();
            
            if(res.next()) {
            	orderId=res.getInt(1);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public OrderTable getOrder(int orderId) {

        OrderTable order = null;

        try {

            con = DBconnection.getConnection();
            pstmt = con.prepareStatement(SELECT);
            pstmt.setInt(1, orderId);

            res = pstmt.executeQuery();

            if (res.next()) {

                order = new OrderTable();

                order.setOrderId(res.getInt("orderid"));
                order.setid(res.getInt("id"));
                order.setRestaurantId(res.getInt("restaurantid"));
                order.setOrderDate(res.getTimestamp("orderdate"));
                order.setTotalAmount(res.getDouble("totalamount"));
                order.setStatus(res.getString("status"));
                order.setPaymentMode(res.getString("paymentmode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public int updateOrder(OrderTable order) {

        try {

            con = DBconnection.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, order.getid());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setTimestamp(3, order.getOrderDate());
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMode());
            pstmt.setInt(7, order.getOrderId());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteOrder(int orderId) {

        try {

            con = DBconnection.getConnection();
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, orderId);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
   
    	@Override
    	public List<OrderTable> getOrdersByUserId(int userId) {

    	    List<OrderTable> orders = new ArrayList<>();

    	    try {

    	        pstmt = con.prepareStatement(GET_ORDERS_BY_USER);
    	        pstmt.setInt(1, userId);

    	        res = pstmt.executeQuery();

    	        while(res.next()) {

    	            OrderTable order = new OrderTable();

    	            order.setOrderId(res.getInt("orderid"));
    	            order.setid(res.getInt("userid"));
    	            order.setRestaurantId(res.getInt("restaurantid"));
    	            order.setOrderDate(res.getTimestamp("orderdate"));
    	            order.setTotalAmount(res.getDouble("totalamount"));
    	            order.setStatus(res.getString("status"));
    	            order.setPaymentMode(res.getString("paymentmode"));

    	            orders.add(order);
    	        }

    	    } catch(Exception e) {
    	        e.printStackTrace();
    	    }

    	    return orders;
    	}
}