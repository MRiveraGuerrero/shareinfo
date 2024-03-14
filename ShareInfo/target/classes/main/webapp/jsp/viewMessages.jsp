<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@ page import="java.util.*,helper.info.*" %>
<%
    ArrayList<MessageInfo> messageList = (ArrayList) request.getAttribute("messageList");
    ServletContext context = request.getServletContext();
    HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
%>
<html>
<head>
    <title>Visor de Mensajes</title>
    <link href="/ShareInfo/css/styleSheet.css" rel="stylesheet"/>
</head>
<body>
<header>
    <h1>Web para Compartir Mensajes Cortos</h1>
    <h3>Vista de Mensasjes</h3>
</header>

<section>
    <a href="/ShareInfo/servlet/MainServlet">Actualizar</a>
</section>

<section>
    Usuarios Activos:
    <% for (Map.Entry<String, String> entry : loggedinUsers.entrySet()) { %>
    <%=entry.getKey()%>;
    <% } %>
</section>
<section>
    <form method="POST" action="/ShareInfo/servlet/MainServlet">
        <table>
            <tr>
                <td>Mensaje:</td>
                <td><textarea name="message" id="message"></textarea></td>
            </tr>
        </table>
        <button>Enviar</button>
    </form>
</section>
<section>
    <table>
        <tr>
            <th>Usuario</th>
            <th>Mensaje</th>
        </tr>
        <%
            for (MessageInfo messageInfo : messageList){
        %>
        <tr>
            <td><%=messageInfo.getUsername()%>
            </td>
            <td><%=messageInfo.getMessage()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</section>
<footer>Sistemas Web - Escuela de Ingenieria de Bilbao</footer>
</body>
</html>