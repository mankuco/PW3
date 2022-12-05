<!DOCTYPE html>
<html lang="es">
    <head>
		<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro</title>
    </head>
    <body>
	    <form action="../controller/registerController.jsp" method="POST">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email"><br/>
            
            <label for="contrasena">Contrase&ntilde;a:</label>
            <input type="password" id="contrasena" name="contrasena">
            <br/>

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre">
            <br/>

            <label for="apellidos">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos">
            <br/>

            <label for="fechaNacimiento">Fecha de nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento">
            <br/>
            
            <label for="fechaInscripcion">Fecha de inscripcion:</label>
            <input type="date" id="fechaInscripcion" name="fechaInscripcion">
            <br/>
            
            <input type="submit" value="Registrarse">
		</form> 
    </body>