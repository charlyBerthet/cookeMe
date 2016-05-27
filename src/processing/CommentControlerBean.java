package processing;

import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;
import model.RecipeListModelBean;
import model.RecipeModel;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class CommentControlerBean implements Serializable{

	private RecipesDao recipeDao;

}