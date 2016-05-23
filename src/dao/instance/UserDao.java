package dao.instance;

import java.sql.Connection;
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
		java.sql.Statement query;
		try{
			// create connection
			connection= java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME,dB_USER, dB_PWD);
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UserModelBean> getAllUser(){
		//return value
		ArrayList<UserModelBean> userList=new ArrayList<UserModelBean>();
		try{
			// create connection
			connection= java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME,dB_USER,dB_PWD);
			//TODOA l’image de DB.java créer une réquètepermettant de récupérerl’ensemble des utilisateurs contenu dans la base et de les placer dans uneliste
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


