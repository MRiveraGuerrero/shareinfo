package shareinfo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import helper.db.*;
import helper.info.*;

public class MainServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private MySQLdb mySQLdb;
	
	public void init(ServletConfig config) {
		System.out.println("---> Entrando en init() de MainServlet");
		mySQLdb = new MySQLdb();
		System.out.println("---> Saliendo de init() de MainServlet");
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	
        System.out.println("---> Entrando en doGet() de MainServlet");
        
    	doPost(request, response);
    	
        System.out.println("---> Saliendo de doGet() de MainServlet");
    }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		
    	System.out.println("---> Entrando en doPost() de MainServlet"+ request.getSession(false));
		
    	if(request.getSession(false) == null) {
    		System.out.println("     Usuario NO logeado: Redireccionar al usuario al loginForm.html");
    		
			RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
			rd.forward(request, response);
			
    	} else {
    		System.out.println("     Usuario logeado");
    		
    		String message = request.getParameter("message");
    		if(message!= null) {
    			
    			HttpSession session = request.getSession();
    			String sessionID = session.getId();
    			ServletContext context = request.getServletContext();
    			
    			HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
    			System.out.println("     Usuarios Activos: " + loggedinUsers.toString());
    			
    			for(Map.Entry<String, String> entry : loggedinUsers.entrySet()) {
    	            if(entry.getValue().equals(sessionID)) {
    	            	String username = entry.getKey();
    	            	mySQLdb.setMessageInfo(message, username);
    	            	break;
    	            }
    			}
    		}
    		
    		ArrayList<MessageInfo> messageList = mySQLdb.getAllMessages();
    		request.setAttribute("messageList", messageList);
    		
    		System.out.println("     Redireccionando el usuario a viewMessages.jsp");
    		RequestDispatcher rd = request.getRequestDispatcher("/jsp/viewMessages.jsp");
			rd.forward(request, response);
    		
    	}
    	
        System.out.println("---> Saliendo de doPost() MainServlet");
    }
    
}

