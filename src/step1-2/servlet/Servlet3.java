package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class Servlet3
 */
@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DB db;
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	//VérifiesiDBexistedansl'espacedemémoirepartagéentrelesservlet//siouionlesrécupére,sinon onlecréeeton l'ajoutedansl'espacedemémoire//partagéentrelesservlet
    	if(getServletContext().getAttribute("BD")!=null){
    		db=(DB)getServletContext().getAttribute("BD");
    		}else{
    			db=new DB();
    			getServletContext().setAttribute("BD",db);
    			}
    	}
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
