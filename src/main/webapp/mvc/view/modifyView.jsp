<jsp:useBean id="CustomerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	if (CustomerBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
%>
<!DOCTYPE html>
<html lang="es">
    <head>
		<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modificar usuario</title>
    </head>
    <body>
	    <form action="../controller/modifyController.jsp" method="POST">
            <label for="nombre">Nuevo nombre:</label>
            <input type="text" id="nombre" name="nombre">
            <br/>

            <label for="apellidos">Nuevos apellidos:</label>
            <input type="text" id="apellidos" name="apellidos">
            <br/>

            <label for="fechaNacimiento">Nueva fecha de nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento">
            <br/>
            
            <label for="fechaNacimiento">Nueva fecha de nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento">
            <br/>
            
            <label for="contrasena">Nueva contrase&ntilde;a:</label>
            <input type="password" id="contrasena" name="contrasena">
            <br/>

            <input type="submit" value="Modificar">
		</form>
    </body>
</html>