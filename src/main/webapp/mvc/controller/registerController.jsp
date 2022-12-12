<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO, data.DAO" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String email = request.getParameter("email");
	String contrasena = request.getParameter("contrasena");
	String fechanacimiento= request.getParameter("fechanacimiento");
	
	if (nombre == null || apellidos == null || email == null || contrasena == null || fechanacimiento == null || nombre == ""|| apellidos == "" || email == "" || contrasena == "" || fechanacimiento == "") { %>
		<jsp:forward page="../view/registerView.jsp">
			<jsp:param value="Campos incompletos" name="msg"/>
		</jsp:forward>
<%	}
	else{
		
		if ((userBean.getEmail() != null) && (userBean.getRol() == true)){
			response.sendRedirect("/PW3/");
		}
		else {		

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			UsuarioDTO usuario = usuarioDAO.buscarUsuario(email);
			if (usuario != null) {%>
				<jsp:forward page="../view/registerView.jsp">
					<jsp:param value="Ya existe un usuario con ese email" name="msg"/>
				</jsp:forward>
			<%}
			else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				try {
					LocalDate fechaNacimiento = LocalDate.parse(fechanacimiento, formatter);
					boolean rol = true;
					if (userBean.getEmail() != null) { rol = false; }
					usuario = new UsuarioDTO(nombre, apellidos, email, contrasena, fechaNacimiento, LocalDate.now(), rol);
					usuarioDAO.guardarUsuario(usuario);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect("/PW3/");
			}
		}
	}
	
%>