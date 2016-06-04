package processing;

import dao.fabric.DaoFabric;
import dao.instance.CommentDao;
import model.CommentModelBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class CommentControlerBean implements Serializable{

	private CommentDao commentDao;
	public CommentControlerBean() {
		this.commentDao=DaoFabric.getInstance().createCommentDao();
	}



	public void addComment(CommentModelBean comment, String user_login, String recipe_title){
		comment.setUser_login(user_login);
		comment.setRecipe_title(recipe_title);
		this.commentDao.addComment(comment);
	}

	public List<CommentModelBean> getAllCommentsFromRecipeTitle(String recipeTitle){
		return  this.commentDao.getAllCommentsFromRecipeTitle(recipeTitle);
	}

}