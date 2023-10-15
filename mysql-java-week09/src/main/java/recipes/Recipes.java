package recipes;

import java.sql.Connection;

import recipes.dao.DbConnection;
import recipes.service.RecipeService;

public class Recipes {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		Connection conn = DbConnection.getConnection();
		
		RecipeService rs = new RecipeService();
		rs.createAndPopulateTables();
	}

}
