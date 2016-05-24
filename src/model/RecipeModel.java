package model;

import java.io.Serializable;

public class RecipeModel implements Serializable{
	private String title;
	private String description;
	private int expertise;
	private int nbpeople;
	private int duration;
	private String type;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExpertise() {
		return expertise;
	}
	public void setExpertise(int expertise) {
		this.expertise = expertise;
	}
	public int getNbpeople() {
		return nbpeople;
	}
	public void setNbpeople(int nbpeople) {
		this.nbpeople = nbpeople;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public RecipeModel(){}
	public RecipeModel(String title, String description, int expertise,int nbpeople, int duration, String type) {
		super();
		this.title = title;
		this.description = description;
		this.expertise = expertise;
		this.nbpeople = nbpeople;
		this.duration = duration;
		this.type = type;
	}
	
	
	public String toString(){
		return"[TITLE]:"+this.getTitle()+",[DESCRIPTION]:"+this.getDescription()
				+",[EXPERTISE]:"+this.getExpertise()+",[NBPEOPLE]:"+this.getNbpeople()
				+",[DURATION]:"+this.getDuration()+",[TYPE]:"+this.getType();
	}
	
	
	
}
