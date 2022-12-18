<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO, business.Pista_Kart.GestorPistas, data.Pista_Kart.PistaDAO, business.Pista_Kart.PistaDTO, java.util.ArrayList"%>
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
		String id = request.getParameter("id");
%>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
            <title>Modificar kart</title>
        </head>
        <body style="text-align: center">
        	<%@ include file="../../include/headerAdministrador.jsp" %>
        	<%PistaDAO dao = new PistaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass()); %>
        	<%ArrayList<PistaDTO> pistas = dao.verPistas(); %>
            <h1>Modificar Kart</h1>
            <form action="<%= request.getContextPath() %>/editarKart" method="POST">
                <br/>
                <h3 for="kartEstado">KART Nº <%=id%> </h3>
                <label for="kartEstado">Estado</label>
                <select id="kartEstado" class="select-type" name="kartEstado">
                    <option value="DISPONIBLE">Disponible</option>
                    <option value="MANTENIMIENTO">Mantenimiento</option>
                </select>
                <br/>                
                <br/><br/>
                <label for="kartType">Tipo</label>
                <select id="kartType" class="select-type" name="kartType">
                    <option value="false">Infantil</option>
                    <option value="true">Adulto</option>
                </select>
                <select id="pistaName" class="select-type" name="pistaName">
                    <option value="NoAsociado">Sin asociar</option>
                    <%for (PistaDTO a : pistas) { %>
                    <option value="<%= a.getNombrePista()%>"><%= a.getNombrePista()%></option>
                    <%}%>
                </select>
                <br/><br/>
                <input type="submit" class="small-button" value="Modificar" style="border: solid 2px #cf74f2;">
             </form>
		</body>
	</html>
	<%} %>