package dao.instance;

import dao.fabric.DaoFabric;
import model.CommentModelBean;
import model.RecipeModel;
import model.UserModelBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CommentDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public CommentDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
		dB_HOST= DB_HOST;
		dB_PORT= DB_PORT;
		dB_NAME= DB_NAME;
		dB_USER= DB_USER;
		dB_PWD= DB_PWD;
	}

	public void addComment(CommentModelBean comment) {
		//Création de la requête
		PreparedStatement query;
		try{
			// create connection
			connection= connect();
			String sql="INSERT INTO comment" +
					" (id, content, rate, `date`, user_id, recipe_id)" +
					" VALUES(?, ?, ?, ?, ?, ?)";
			query =  connection.prepareStatement(sql);
			query.setString(1, null);
			query.setString(2, comment.getContent());
			query.setInt(3, comment.getRate());
			query.setDate(4,  new java.sql.Date(new java.util.Date().getTime()));
			query.setInt(5, comment.getUser_id());
			query.setInt(6, comment.getRecipe_id());
			query.executeUpdate();
			query.close();
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection connect(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME,dB_USER, dB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] main){

	}
}


