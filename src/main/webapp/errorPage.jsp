<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/styles.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error</title>
    </head>
    <body>
		<h1>ERROR</h1>
		<p><%= (String)request.getParameter("errorMsg")%> </p>
		<br/>
		<button type="button" onclick="window.location.href='/PW3/';">Volver al inicio</button>
    </body>
</html>