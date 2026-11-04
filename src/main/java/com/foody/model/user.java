package com.foody.model;

public class user {
	
	private int Id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String role;
	private java.sql.Timestamp createdate;
	private java.sql.Timestamp lastlogindate;
	
	public user() {
		
	}
	
	
	public user(int id, String name, String email, String password, String address, String role, java.sql.Timestamp createdate,
			java.sql.Timestamp lastlogindate) {
		super();
		this.Id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.createdate = createdate;
		this.lastlogindate = lastlogindate;
	}
	



	public user(String name, String email, String password, String address, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	public user(String name, String email, String password, String address, String role, java.sql.Timestamp createdate,
			java.sql.Timestamp lastlogindate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.createdate = createdate;
		this.lastlogindate = lastlogindate;
	}
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public java.sql.Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.sql.Timestamp createdate) {
		this.createdate = createdate;
	}

	public java.sql.Timestamp getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(java.sql.Timestamp lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	@Override
	public String toString() {
		return "user [Id=" + Id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", role=" + role + ", createdate=" + createdate + ", lastlogindate=" + lastlogindate + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
