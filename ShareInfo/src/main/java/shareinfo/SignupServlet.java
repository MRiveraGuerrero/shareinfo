package shareinfo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import helper.db.*;

public class SignupServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private MySQLdb mySQLdb;
	
	public void init(final ServletConfig config) {
		System.out.println("---> Entrando en init() de SignupServlet");
		
		mySQLdb = new MySQLdb();
		
		System.out.println("---> Saliendo de init() de SignupServlet");
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	System.out.println("---> Entrando en doPost() de SignupServlet");
    	
    	String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		System.out.println("    Estrayendo parámetros de la petición: " + email + " " + password + " " + nickname);
		
		mySQLdb.setUserInfo(email, password, nickname);
		System.out.println("     Actualizando la tabla users de ls base de datos");
		
		System.out.println("     Redireccionando el usuario a loginForm.html");
		RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
		rd.forward(request, response);
		
        System.out.println("---> Saliendo de doPost() de SignupServlet");
    }
}

