<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	if (userBean.getEmail() != null){
		response.sendRedirect(request.getContextPath() + "/usuarioServlet");
	}
%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/styles.css"></head>
	<title>PR&Aacute;CTICA 3</title>
</head>
<body>
		<div class="cuadro">
			<div class="container-center">
				<div class="header">
					<h1 class="page-title">Programa Reservas</h1>
					<p id="bienvenida">Bienvenido</p>
				</div>
				<div class="main">
					<button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/loginView.jsp'">Iniciar sesi&oacute;n</button>
					<button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/registerView.jsp'">Registro</button>
				</div>
			</div>
		</div>
</body>
</html>