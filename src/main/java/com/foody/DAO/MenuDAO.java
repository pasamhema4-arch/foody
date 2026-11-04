package com.foody.DAO;

import java.util.List;

import com.foody.model.Menu;

public interface MenuDAO {

	int addMenu(Menu menu);

	Menu getMenu(int menuId);

	int updateMenu(Menu menu);

	int deleteMenu(int menuId);

	List<Menu> getMenuByRestaurantId(int restaurantId);

}
