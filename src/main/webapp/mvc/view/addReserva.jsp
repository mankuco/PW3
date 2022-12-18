<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO, business.Pista_Kart.GestorPistas, data.Pista_Kart.PistaDAO, business.Pista_Kart.PistaDTO, java.util.ArrayList"%>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	else if(userBean.getRol() == false){
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
        	<%PistaDAO dao = new PistaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass()); %>
        	<%ArrayList<PistaDTO> pistas = dao.verPistas(); %>
            <h1>Crear reserva</h1>
            <form action="<%= request.getContextPath() %>/nuevaReserva" method="POST">
                <label for="minutosReserva">Minutos reserva</label>
                <input type="number" class="input-form" name="minutosReserva">
                
                <br/><br/>
                <label for="reservaType">Tipo</label>
                <select id="reservaType" class="select-type" name="reservaType">
                    <option value="INFANTIL">Infantil</option>
                    <option value="FAMILIAR">Familiar</option>
                    <option value="ADULTOS">Adultos</option>
                </select>
                <br/><br/>
                <label for="numeroNinos">Numero niños</label>
                <input type="number" class="input-form" name="numeroNinos">
                <br/><br/>
                <label for="numeroAdultos">Numero adultos</label>
                <input type="number" class="input-form" name="numeroAdultos">
                <br/><br/>
                 <label for="fechaReserva">Fecha de reserva:</label>
            	<input type="date" style="border: solid 2px #cf74f2;" class="input-form" id="fechaReserva" name="fechaReserva">
            	<br/>
            	 <label for="horaReserva">Hora de reserva:</label>
            	<input type="time" style="border: solid 2px #cf74f2;" class="input-form" id="horaReserva" name="horaReserva">
            	<br/>
            	<select id="pistaName" class="select-type" name="pistaName">
                    <%for (PistaDTO a : pistas) { %>
                    <option value="<%= a.getNombrePista()%>"><%= a.getNombrePista()%></option>
                    <%}%>
                </select>
                <br/><br/>
                <label for="reservaBono">Incluir en bono:</label>
                <select id="reservaBono" class="select-type" name="reservaBono">
                    <option value="SI">Sí</option>
                    <option value="NO">No</option>
                </select>
                <input type="submit" class="small-button" value="Crear" style="border: solid 2px #cf74f2;">
             </form>
		</body>
	</html>
	<%} %>