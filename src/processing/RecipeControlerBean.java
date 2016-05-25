package processing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.RecipeListModelBean;
import model.RecipeModel;
import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;

@ManagedBean
@ApplicationScoped
public class RecipeControlerBean implements Serializable{

	private RecipesDao recipeDao;

	public RecipeControlerBean() {
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
	}

	public void loadAllRecipe(){

		List<RecipeModel> list = this.recipeDao.getAllRecipes();
		RecipeListModelBean recipeList=new RecipeListModelBean();

		for(RecipeModel recipe:list){
			recipeList.addRecipeList(recipe);
		}
		//récupère l'espace de mémoire de JSF
		ExternalContext externalContext =	FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();

		//place la liste de recette dans l'espace de mémoire de JSF
		sessionMap.put("recipeList", recipeList);
	}


	public String addRecipe(RecipeModel recipe){
		this.recipeDao.addRecipe(recipe);
		//Redirection vers une page de confirmation
		return "successfulRegister.xhtml";
	}
	
	
	public String searchRecipe(RecipeModel recipe){
		//TODO effectuer une recherche des recettes répondant aux critères passés en
		//parametre, récupérer la liste des recettes correspondantes et demander à
		//recipeResultList.xhtml d’afficher les recettes trouvées

		// Get recipe list
		List<RecipeModel> recipes = this.recipeDao.searchRecipes(recipe.getDuration(),recipe.getExpertise(),recipe.getNbpeople(),recipe.getType());

		RecipeListModelBean recipeListModelBean = new RecipeListModelBean();

		for (RecipeModel r : recipes)
			recipeListModelBean.addRecipeList(r);

		return "recipeResultList.xhtml";
	}
	
	
	public String displayRecipeDetail(RecipeModel recipe){
		System.out.println("COUCOU");
		System.out.println(recipe);
		recipe = this.recipeDao.getRecipeByTitle(recipe.getTitle());

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("recipeDetail", recipe);
		System.out.println(recipe);
		return "recipeDetail.xhtml";
	}
}
