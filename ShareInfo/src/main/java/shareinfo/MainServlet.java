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
    		System.out.println("     Usuario NO logeado: Redireccionar al usuario al loginForm.jsp");
    		
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginForm.jsp");
			request.setAttribute("error", "Sesión caducada");
			rd.forward(request, response);

			
    	} else {
    		System.out.println("     Usuario logeado");
    		
    		String message = request.getParameter("message");
    		if(message!= null) {
    			
    			HttpSession session = request.getSession();
				String username = (String) session.getAttribute("username");
				mySQLdb.setMessageInfo(message, username);

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

