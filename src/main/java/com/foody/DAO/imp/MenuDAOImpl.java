package com.foody.DAO.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foody.DAO.MenuDAO;
import com.foody.model.Menu;
import com.foody.utility.DBconnection;

public class MenuDAOImpl implements MenuDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	private static final String INSERT =
			"INSERT INTO menu(restaurantid,itemname,description,price,isavailable,imagepath) VALUES(?,?,?,?,?,?)";

	private static final String SELECT =
			"SELECT * FROM menu WHERE menuid=?";

	private static final String UPDATE =
			"UPDATE menu SET restaurantid=?, itemname=?, description=?, price=?, isavailable=?, imagepath=? WHERE menuid=?";

	private static final String DELETE =
			"DELETE FROM menu WHERE menuid=?";
	private static final String SELECT_BY_RESTAURANT =
	        "SELECT * FROM menu WHERE restaurantid=?";
	@Override
	public int addMenu(Menu menu) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setDouble(4, menu.getPrice());
			pstmt.setBoolean(5, menu.getisAvailable());
			pstmt.setString(6, menu.getImagePath());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {

	    List<Menu> menuList = new ArrayList<>();

	    try {

	        con = DBconnection.getConnection();
	        pstmt = con.prepareStatement(SELECT_BY_RESTAURANT);
	        pstmt.setInt(1, restaurantId);

	        res = pstmt.executeQuery();

	        while (res.next()) {

	            Menu menu = new Menu();

	            menu.setMenuId(res.getInt("menuid"));
	            menu.setRestaurantId(res.getInt("restaurantid"));
	            menu.setItemName(res.getString("itemname"));
	            menu.setDescription(res.getString("description"));
	            menu.setPrice(res.getDouble("price"));
	            menu.setAvailable(res.getBoolean("isavailable"));
	            menu.setImagePath(res.getString("imagepath"));

	            menuList.add(menu);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return menuList;
	}
	@Override
	public Menu getMenu(int menuId) {

		Menu menu = null;

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(SELECT);
			pstmt.setInt(1, menuId);

			res = pstmt.executeQuery();

			if (res.next()) {

				menu = new Menu();

				menu.setMenuId(res.getInt("menuid"));
				menu.setRestaurantId(res.getInt("restaurantid"));
				menu.setItemName(res.getString("itemname"));
				menu.setDescription(res.getString("description"));
				menu.setPrice(res.getDouble("price"));
				menu.setAvailable(res.getBoolean("isavailable"));
				menu.setImagePath(res.getString("imagepath"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return menu;
	}
	@Override
	public int updateMenu(Menu menu) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setDouble(4, menu.getPrice());
			pstmt.setBoolean(5, menu.getisAvailable());
			pstmt.setString(6, menu.getImagePath());
			pstmt.setInt(7, menu.getMenuId());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	@Override
	public int deleteMenu(int menuId) {

		try {

			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, menuId);

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
}