package com.foody.DAO.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.foody.DAO.userDAO;
import com.foody.model.user;
import com.foody.utility.DBconnection;

public class userDAOimp implements userDAO {
	String Insert_Query= "INSERT INTO user(name,email,password,address,role,createdate, lastlogindate)"		
			+ " VALUES (?,?,?,?,?,?,?) ";
	String GET_QUERY="SELECT * FROM user where id = ? ";
	String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
	
	
	public int adduser(user user) {

	    Connection con = DBconnection.getConnection();

	    try {
	        PreparedStatement psmt = con.prepareStatement(Insert_Query);

	        psmt.setString(1, user.getName());
	        psmt.setString(2, user.getEmail());
	        psmt.setString(3, user.getPassword());
	        psmt.setString(4, user.getAddress());
	        psmt.setString(5, user.getRole());
	        psmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
	        psmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

	        int i = psmt.executeUpdate();   // i will be 1 if insert is successful

	        System.out.println(i);

	        return i;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return 0;   // Return 0 if insertion fails
	}
	
	public user getUserByEmail(String email) {

	    Connection con = DBconnection.getConnection();
	    user user = null;

	    try {
	        PreparedStatement psmt = con.prepareStatement(GET_USER_BY_EMAIL);
	        psmt.setString(1, email);

	        ResultSet res = psmt.executeQuery();

	        if (res.next()) {

	            int id = res.getInt("id");
	            String name = res.getString("name");
	            String password = res.getString("password");
	            String address = res.getString("address");
	            String role = res.getString("role");
	            Timestamp createdate = res.getTimestamp("createdate");
	            Timestamp lastlogindate = res.getTimestamp("lastlogindate");

	            user = new user(id, name, email, password, address, role, createdate, lastlogindate);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	
	@Override
	public user getuser(int id) {
		Connection con=DBconnection.getConnection();
		user user=null;
		try {
			PreparedStatement psmt=con.prepareCall(GET_QUERY);
			psmt.setInt(1, id);
			ResultSet res=psmt.executeQuery();
			while(res.next()) {
				int Id=res.getInt("id");
				String name=res.getString("name");
				String email=res.getString("email");
				String password=res.getString("password");
				String address=res.getString("address");
				String role=res.getString("role");
				Timestamp createdate=res.getTimestamp("createdate");
				Timestamp lastlogindate=res.getTimestamp("lastlogindate");
				
				user= new user(Id,name,email,password,address,role,createdate,lastlogindate);
			}
			return user;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	@Override
	public void updateuser(user user) {
		String UPDATE_USER="update user set name = ?, password = ?, email = ?, address = ?, lastlogindate = ?"
				+ " where id = ?";
		Connection con=DBconnection.getConnection();
		try {
			PreparedStatement psmt=con.prepareStatement(UPDATE_USER);
			psmt.setString(1,user.getName());
			psmt.setString(2,user.getEmail());
			psmt.setString(3,user.getPassword());
			psmt.setString(4,user.getAddress());
			psmt.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
			psmt.setInt(6, user.getId());
			
			int i=psmt.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public user deleteuser(int id) {
		
		return null;
	}

	@Override
	public List<user> getalluser()
	{
		String GET_ALL_USER ="SELECT * from user ";
		ArrayList<user> list=new ArrayList<user>();
		Connection con=DBconnection.getConnection();
		try {
			Statement stmt=con.createStatement();
			ResultSet res=stmt.executeQuery(GET_ALL_USER);
			while(res.next()) {
				user user=extractresultfromuserset(res);
			    list.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static user extractresultfromuserset(ResultSet res) throws SQLException
	{
		
			int Id=res.getInt("id");
			String name=res.getString("name");
			String email=res.getString("email");
			String password=res.getString("password");
			String address=res.getString("address");
			String role=res.getString("role");
			Timestamp createdate=res.getTimestamp("createdate");
			Timestamp lastlogindate=res.getTimestamp("lastlogindate");
			
			user user= new user(Id,name,email,password,address,role,createdate,lastlogindate);
			return user;
		
	}
	

}
