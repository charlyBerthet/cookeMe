package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.UserModel;

public class DB {
	private static final String DB_HOST = "db-tp.cpe.fr";
	
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "binome25";
	private static final String DB_USER = "binome25";
	private static final String DB_PWD = "binome25";
	private Connection connection;
	public DB() {
		try
		{
			// Chargement du Driver, puis établissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME, DB_USER, DB_PWD);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}  catch
		(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<UserModel> getData(){

		ArrayList<UserModel> userList= new ArrayList<UserModel>();
		//  Création de la requête
		java.sql.Statement query;
		try
		{
			query =  connection.createStatement();
			//Création du contenu de la requête aéxécuter
			String sql="SELECT surname, lastname, login, pwd, age FROM binome25.User";
			//Exécution de la requête de modification
			ResultSet result = query.executeQuery(sql);
			while(result.next()){
				userList.add(new UserModel(result.getString("surname"), result.getString("surname"), result.getInt("age"), result.getString("login"), result.getString("pwd")));
			}
			query.close();
			//connection.close();
		} 
		catch
		(SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}
	
	
	public void addUser(UserModel user) {
		// Création de la requête
		PreparedStatement query;
		try
		{
			//Creation  de l'élément  de requète
			String sql="INSERT INTO binome25.User (surname, lastname, login, pwd, age) VALUES('?', '?', '?', '?', '?')";
			query =  connection.prepareStatement(sql);
			
			
			query.setString(1, user.getSurname());
			query.setString(2, user.getLastname());
			query.setString(3, user.getLogin());
			query.setString(4, user.getPwd());
			query.setInt(5, user.getAge());
			
			query.close();
			//connection.close();
		} 
		catch
		(SQLException e) {
			e.printStackTrace();
		}
	}
}