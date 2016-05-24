package processing;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.LoginBean;
import model.UserModelBean;
import model.UserSubmissionModelBean;
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
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(),
				loginBean.getPwd());
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			if( user!=null){
				//récupère l'espace de mémoire de JSF
				ExternalContext externalContext =
						FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap = externalContext.getSessionMap();
				//place l'utilisateur dans l'espace de mémoire de JSF
				sessionMap.put("loggedUser", user);
				//redirect the current page
				
		         
		        context.addMessage(null, new FacesMessage("Successful",  "Connection success") );
		        return true;
			}else{
				throw new Exception("User not found");
			}
		}catch(Exception e){
			context.addMessage(null, new FacesMessage("Hmmm....",  "Connection fail") );
			return false;
		}
	}

	
	public void checkAndAddUser(UserSubmissionModelBean userSubmitted){

			this.userDao.addUser(userSubmitted);

		//TODO
		//ajout de l'utilisateur à la base de données
		
	}
}