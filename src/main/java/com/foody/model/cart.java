package com.foody.model;

import java.util.HashMap;
import java.util.Map;

public class cart {
	Map<Integer,cartItem> items;
	public cart() {
		items =new HashMap<Integer,cartItem>();
	}
	public Map<Integer, cartItem> getItems() {
		return items;
	}

	public void addItem(cartItem cartItem) {
		int menuId=cartItem.getMenuId();
		if(items.containsKey(menuId))
		{
			cartItem existingCartItem=items.get(menuId);
			existingCartItem.setQty(existingCartItem.getQty()+1);
			
		}
		else
		{
			items.put(menuId, cartItem);
		}
		
	}
	public void removeItem(int menuId) {
		
		 items.remove(menuId);
		
	}
	public void updateItem(int menuId, int qty) {
		if (items.containsKey(menuId)) {

	        if (qty <= 0) {
	            items.remove(menuId);
	        } else {
	            cartItem item = items.get(menuId);
	            item.setQty(qty);
	        }

	    }

		 
		
	}
		


}
