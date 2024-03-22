package shareinfo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import helper.db.*;

public class LogoutServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private MySQLdb mySQLdb;

    public void init(ServletConfig config) {
        System.out.println("---> Entrando en init()de LogoutServlet");
        mySQLdb = new MySQLdb();
        System.out.println("---> Saliendo de init() de LogoutServlet");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        System.out.println("---> Entrando en doGet() de LogoutServlet");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println("     Cogiendo username de la sesion: " + username);
        session.invalidate();
        System.out.println("     Invalidando la sesion");
        ServletContext context = request.getServletContext();
        HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
        loggedinUsers.remove(username);
        System.out.println("     Eliminando " + username + " de loggedin_users");
        context.setAttribute("loggedin_users", loggedinUsers);
        System.out.println("     Loggedin users: " + loggedinUsers);
        System.out.println("     Redireccionando al usuario a loginForm.html");
        request.setAttribute("error", "Sesion cerrada");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginForm.jsp");
        rd.forward(request, response);
    }

}
