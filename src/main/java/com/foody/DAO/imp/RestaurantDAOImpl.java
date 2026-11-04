package com.foody.DAO.imp;



import com.foody.DAO.RestaurantDAO;
import com.foody.model.Restaurant;
import com.foody.utility.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	private static final String INSERT =
			"INSERT INTO restaurant(name,cuisinetype,deliverytime,address,rating,isactive,imagepath) VALUES(?,?,?,?,?,?,?)";

	private static final String SELECT =
			"SELECT * FROM restaurant WHERE restaurantid=?";

	private static final String UPDATE =
			"UPDATE restaurant SET name=?, cuisinetype=?, deliverytime=?, address=?, rating=?, isactive=?, imagepath=? WHERE restaurantid=?";

	private static final String DELETE =
			"DELETE FROM restaurant WHERE restaurantid=?";
	private static final String SELECT_ALL =
	        "SELECT * FROM restaurant";
	
	@Override
	public int addRestaurant(Restaurant restaurant) {

		try {
			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getCuisineType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setDouble(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.isActive());
			pstmt.setString(7, restaurant.getImagePath());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public Restaurant getRestaurant(int restaurantId) {

		Restaurant restaurant = null;

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(SELECT);
			pstmt.setInt(1, restaurantId);

			res = pstmt.executeQuery();

			if (res.next()) {

				restaurant = new Restaurant();

				restaurant.setRestaurantId(res.getInt("restaurantid"));
				restaurant.setName(res.getString("name"));
				restaurant.setCuisineType(res.getString("cuisinetype"));
				restaurant.setDeliveryTime(res.getInt("deliverytime"));
				restaurant.setAddress(res.getString("address"));
				restaurant.setRating(res.getDouble("rating"));
				restaurant.setActive(res.getBoolean("isactive"));
				restaurant.setImagePath(res.getString("imagepath"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return restaurant;
	}
	@Override
	public int updateRestaurant(Restaurant restaurant) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getCuisineType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setDouble(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.isActive());
			pstmt.setString(7, restaurant.getImagePath());
			pstmt.setInt(8, restaurant.getRestaurantId());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	@Override
	public List<Restaurant> getAllRestaurants() {

	    List<Restaurant> restaurantList = new ArrayList<>();

	    try {

	        con = DBconnection.getConnection();
	        pstmt = con.prepareStatement(SELECT_ALL);

	        res = pstmt.executeQuery();

	        while (res.next()) {

	            System.out.println("Restaurant Name: " + res.getString("name"));

	            Restaurant restaurant = new Restaurant();

	            restaurant.setRestaurantId(res.getInt("restaurantid"));
	            restaurant.setName(res.getString("name"));
	            restaurant.setCuisineType(res.getString("cuisinetype"));
	            restaurant.setDeliveryTime(res.getInt("deliverytime"));
	            restaurant.setAddress(res.getString("address"));
	            restaurant.setRating(res.getDouble("rating"));
	            restaurant.setActive(res.getBoolean("isactive"));
	            restaurant.setImagePath(res.getString("imagepath"));

	            restaurantList.add(restaurant);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return restaurantList;
	}
	@Override
	public int deleteRestaurant(int restaurantId) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, restaurantId);

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
}
