package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@RequestScoped
public class CommentModelBean implements Serializable {

	private String content;
	private int id;
	private int user_id;
	private int recipe_id;
	private Date date;
	private int rate;

	public CommentModelBean(){}

	public CommentModelBean(String content, int id, int user_id, int recipe_id, Date date, int rate) {
		this.content = content;
		this.id = id;
		this.user_id = user_id;
		this.recipe_id = recipe_id;
		this.date = date;
		this.rate = rate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
