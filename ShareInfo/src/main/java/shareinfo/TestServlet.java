package shareinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helper.db.*;
import helper.info.MessageInfo;

public class 	TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MySQLdb mySQLdb;

	public void init(ServletConfig config) {
		System.out.println("---> init() de TestServlet");
		mySQLdb = new MySQLdb();
		System.out.println("<--- init() de TestServlet");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---> doGet() de TestServlet");
		
		PrintWriter http_out=response.getWriter(); //clase para escribir contenido respuesta
    	
    	String type = request.getParameter("type"); //leemos el parámetro tipo de la respuesta
    	System.out.println("---- type: "+ type );
    	
    	// registerUser
    	if(type != null ) {
    		if(type.equals("registerUser")) {
    			System.out.println("---- Solicitado registrar un usuario");
    			String email = request.getParameter("email"); 
    			String password = request.getParameter("password"); 
    			String username = request.getParameter("username"); 
    			if(email != null && password != null&& username != null ) {
    				System.out.println("---- Parametros: "+ email + " - " + password + " - " + username );	
    				mySQLdb.setUserInfo(email, password, username);
    				http_out.println("El almacenamiento de datos se ha realizado correctamente: Usuario registrado");
    			}
    			else {
    				http_out.println("No se han enviado bien los parametros");
					System.out.println(email + " - " + password + " - " + username );
    			}
    		 }
    		// getUsername
    		if(type.equals("getUsername")) {
    			
    		}
    		// registerMessage
    		if(type.equals("registerMessage")) {}
    		
    		// getMessage
    		if(type.equals("getMessages")) {}
    		 		
    		}
    	else {
			http_out.println("No se han enviado el parámetro type");   
    	
    	}
    	System.out.println("<--- doPost() de TestServlet");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---> doPost() de TestServlet");
		doGet(request, response);
	}
	

}
