package com.foody.DAO;

import java.util.List;

import com.foody.model.user;

public interface userDAO {
	
	int adduser(user user);
	user getuser(int id);
	void updateuser(user user);
	user deleteuser(int id);
	user getUserByEmail(String email);
	List<user> getalluser();

}
