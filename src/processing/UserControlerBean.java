package processing;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.*;
import dao.fabric.DaoFabric;
import dao.instance.UserDao;

@ManagedBean
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients
public class UserControlerBean implements Serializable{
	private UserDao userDao;
	
	public UserControlerBean() {
		this.userDao=DaoFabric.getInstance().createUserDao();
	}
	
	public boolean checkUser(LoginBean loginBean){
		boolean userExist = false;
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),loginBean.getPwd());
		FacesContext context	=	FacesContext.getCurrentInstance();

		try{
			if( user!=null){
				//récupère l'espace de mémoire de JSF
				ExternalContext externalContext =	FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();
				//place l'utilisateur dans l'espace de mémoire de JSF
				sessionMap.put("loggedUser", user);
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful",  "Connection success") );
		        userExist = true;
			}else{
				throw new Exception("User not found");
			}
		}catch(Exception e){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hmmm....",  "Connection fail") );
		}
		return userExist;
	}

	
	public void checkAndAddUser(UserSubmissionModelBean userSubmitted){

		if(userDao.checkUser(userSubmitted.getLogin(),userSubmitted.getPwd()) != null)
			this.userDao.addUser(userSubmitted);

		//TODO : redirection en cas d'erreur ?
	}

	
	
	public boolean isLogged(){
		ExternalContext externalContext =
				FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		UserModelBean user = (UserModelBean)sessionMap.get("loggedUser");
		if(user == null) return false;
		if(user.getLogin() == null) return false;
		return true;
	}
	
	
	public void logout(){
		ExternalContext externalContext =
				FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("loggedUser", null);
	}
	
	
	public UserModelBean getLoggedUser(){
		ExternalContext externalContext =
				FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		UserModelBean user = (UserModelBean)sessionMap.get("loggedUser");
		return user;
	}


	public void logAdminUser(LoginBean loginBean){
		UserModelBean userFound = userDao.checkAdminUser(loginBean.getLogin(), loginBean.getPwd());
		FacesContext context	=	FacesContext.getCurrentInstance();

		ExternalContext externalContext =	context.getExternalContext();
		Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();

		sessionMap.put("loggedAdminUser", userFound);
		try{
			if( userFound != null)
				externalContext.redirect("adminMenu.xhtml");
			else
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hmmm....",  "Connection fail") );
		}catch (Exception e){
			e.printStackTrace();
		}

	}


	public void loadAllUser(){
		List<UserModelBean> list = this.userDao.getAllUser();
		ExternalContext externalContext =	FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();
		sessionMap.put("allUser", list);
	}

	public void deleteUser(UserModelBean user){
		this.userDao.deleteUser(user);
		FacesContext context	=	FacesContext.getCurrentInstance();
		ExternalContext externalContext =	context.getExternalContext();
		Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();
		sessionMap.put("userEditing", null);
		this.loadAllUser();
	}

	public void editUser(UserModelBean user){
		FacesContext context	=	FacesContext.getCurrentInstance();
		ExternalContext externalContext =	context.getExternalContext();
		Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();
		sessionMap.put("userEditing", user);
	}

	public void addNewUser(){
		FacesContext context	=	FacesContext.getCurrentInstance();
		ExternalContext externalContext =	context.getExternalContext();
		Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();
		sessionMap.put("userEditing", new UserModelBean());
	}
	public void cancelEditUser(){
		FacesContext context	=	FacesContext.getCurrentInstance();
		ExternalContext externalContext =	context.getExternalContext();
		Map<String, Object> sessionMap 	= 	externalContext.getSessionMap();
		sessionMap.put("userEditing", null);
	}
	public void saveEditUser(UserModelBean user){
		if ( this.userDao.checkUser(user.getLogin(), user.getPwd()) != null)
			this.userDao.updateUser(user);
		else
			this.userDao.addUserWithAdmin(user);
		this.loadAllUser();
	}
}
