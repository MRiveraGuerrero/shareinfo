<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@ page import="java.util.*,helper.info.*" %>
<%
    ArrayList<MessageInfo> messageList = (ArrayList) request.getAttribute("messageList");
    ServletContext context = request.getServletContext();
    HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
    HttpSession sesion = request.getSession();
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
    <a href="/ShareInfo/servlet/LogoutServlet">Cerrar Sesion</a>
</section>
<section>
    <font>Tu usuario es:</font>
    <%=sesion.getAttribute("username")%>
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
    <input type="text" id="filtroUsuario" onkeyup="filter()" placeholder="Filtrar por Usuario">
    <script>
        function filter(){
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("filtroUsuario");
            table = document.getElementsByTagName("table")[1];
            tr = table.getElementsByTagName("tr")
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(input.value.toUpperCase()) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                
                }
            }
        }
    </script>
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
<footer>
    <section>
        <script>
            var d = new Date();
            document.write(d);
        </script>
    </section>
    <section><%=new Date()%></section>
    <section>Sistemas Web - Escuela de Ingenieria de Bilbao</section>
</footer>
</body>
</html>