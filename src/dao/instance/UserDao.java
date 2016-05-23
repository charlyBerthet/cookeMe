package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModelBean;



public class UserDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public UserDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST= DB_HOST;
		dB_PORT= DB_PORT;
		dB_NAME= DB_NAME;
		dB_USER= DB_USER;
		dB_PWD= DB_PWD;
	}

	public void addUser(UserModelBean user) {
		//Création de la requête
		PreparedStatement query;
		try{
			// create connection
			connection= java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME,dB_USER, dB_PWD);
			String sql="INSERT INTO User (surname, lastname, login, pwd, age) VALUES(?, ?, ?, ?, ?)";
			query =  connection.prepareStatement(sql);
			query.setString(1, user.getSurname());
			query.setString(2, user.getLastname());
			query.setString(3, user.getLogin());
			query.setString(4, user.getPwd());
			query.setInt(5, user.getAge());
			query.executeUpdate();
			//connection.commit();
			query.close();
			//connection.close();
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<UserModelBean> getAllUser(){
		//return value
		ArrayList<UserModelBean> userList=new ArrayList<UserModelBean>();
		java.sql.Statement query;
		try{
			// create connection
			connection= java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME,dB_USER,dB_PWD);
			query =  connection.createStatement();
			String sql="SELECT surname, lastname, login, pwd, age FROM User";
			ResultSet result = query.executeQuery(sql);
			while(result.next()){
				userList.add(new UserModelBean(result.getString("lastname"), result.getString("surname"), result.getInt("age"), result.getString("login"), result.getString("pwd")));
			}
			query.close();
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public UserModelBean checkUser(String login, String pwd) {
		// TODO 
		return null;
	}

}


