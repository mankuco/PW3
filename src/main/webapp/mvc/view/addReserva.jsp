<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO"%>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	else if(userBean.getRol() == true){
		response.sendRedirect(request.getContextPath() + "/usuarioServlet");
	}
	else {
%>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
            <title>Nueva reserva</title>
        </head>
        <body style="text-align: center">
        	<%@ include file="../../include/headerAdministrador.jsp" %>
            <h1>Crear reserva</h1>
            <form action="<%= request.getContextPath() %>/nuevaReserva" method="POST">
                <br/>
                <label for="pistaName">Nombre</label>
                <input type="text" class="input-form" name="pistaName">
                
                <br/>
                
                <label for="minutosReserva">Minutos reserva</label>
                <input type="number" class="input-form" name="minutosReserva">
                
                <br/><br/>
                <label for="pistaType">Tipo</label>
                <select id="pistaType" class="select-type" name="pistaType">
                    <option value="INFANTIL">Infantil</option>
                    <option value="FAMILIAR">Familiar</option>
                    <option value="ADULTOS">Adultos</option>
                </select>
                <select id="" class="select-type" name="pistaEstado">
                    <option value="true">Disponible</option>
                    <option value="false">Mantenimiento</option>
                </select>
                <label for="numeroNinos">Numero ni�os</label>
                <input type="number" class="input-form" name="minutosReserva">
                <br/><br/>
                <label for="numeroAdultos">Numero adultos</label>
                <input type="number" class="input-form" name="minutosReserva">
                <br/><br/>
                 <label for="fechaReserva">Fecha de reserva:</label>
            	<input type="date" style="border: solid 2px #cf74f2;" class="input-form" id="fechaReserva" name="fechaReserva">
            	<br/>
            	 <label for="horaReserva">Hora de reserva:</label>
            	<input type="time" style="border: solid 2px #cf74f2;" class="input-form" id="horaReserva" name="horaReserva">
            	<br/>
                <input type="submit" class="small-button" value="Crear" style="border: solid 2px #cf74f2;">
             </form>
		</body>
	</html>
	<%} %>