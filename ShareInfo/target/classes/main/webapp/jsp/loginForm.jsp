<!DOCTYPE html >
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Formulario de Acceso</title>
		<link href="/ShareInfo/css/styleSheet.css" rel="stylesheet" />
	</head>
	<body>
		<header>
			<h1>Web para Compartir Mensajes Cortos</h1>
			<h2>Formulario Login</h2>
		</header>
		<section>
			<% if(request.getAttribute("error") != null) { %>
				<p>Error: <%= request.getAttribute("error") %></p>
			<% } %>
		</section>
		<section>
			<form method="POST" action="/ShareInfo/servlet/LoginServlet">
				<table>
	   				<tr><td>Email:</td><td><input name="email"/></td></tr>
	   				<tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
	 			</table>
				<button>Enviar</button>
			</form>
		</section>
		<section>
			<a href="/ShareInfo/html/signupForm.html">Registrarse</a>
		</section>
		<footer>Sistemas Web - Escuela Ingeniería de Bilbao</footer>
	</body>
</html>