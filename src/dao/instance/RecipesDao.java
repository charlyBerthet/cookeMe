package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.RecipeModel;

public class RecipesDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public RecipesDao(String DB_HOST, String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST= DB_HOST;
		dB_PORT= DB_PORT;
		dB_NAME= DB_NAME;
		dB_USER= DB_USER;
		dB_PWD= DB_PWD;
	}

	public void addRecipe(RecipeModel recipe) {
		PreparedStatement query;
		try{
			connection = connect();
			String sql = "INSERT INTO cookbcf.recipe"
						+"(description, title, expertise, nbpeople, duration, type)"
						+"VALUES(?, ?, ?, ?, ?, ?)";
			query = connection.prepareStatement(sql);
			query.setString(1, recipe.getDescription());
			query.setString(2, recipe.getTitle());
			query.setInt(3, recipe.getExpertise());
			query.setInt(4, recipe.getNbpeople());
			query.setInt(5, recipe.getDuration());
			query.setString(6, recipe.getType());
			query.executeUpdate();
			query.close();
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<RecipeModel> getAllRecipes() {
		ArrayList<RecipeModel> recipeList=new ArrayList<RecipeModel>();
		java.sql.Statement query;
		try{
			// create connection
			connection = connect();
			query =  connection.createStatement();
			String sql="SELECT description, title, expertise, nbpeople, duration, type FROM cookbcf.recipe";
			ResultSet result = query.executeQuery(sql);
			while(result.next()){
				recipeList.add(new RecipeModel(result.getString("title"), result.getString("description"), result.getInt("expertise"), result.getInt("nbpeople"), result.getInt("duration"), result.getString("type")));
			}
			query.close();
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private Connection connect(){
		try {
			return java.sql.DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME,dB_USER, dB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
