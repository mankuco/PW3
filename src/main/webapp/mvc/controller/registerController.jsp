<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO, data.DAO" %>
<jsp:useBean id="CustomerBean" scope="session" class="display.CustomerBean"></jsp:useBean>

<%
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String email = request.getParameter("email");
	String contrasena = request.getParameter("contrasena");
	String fechaNacimiento= request.getParameter("fechanacimiento");
	String fechaInscripcion= request.getParameter("fechainscripcion");
	//me falta rol
	
	if (nombre == null|| apellidos == null || fechaInscripcion == null || email == null || contrasena == null || fechaNacimiento == null || nombre == ""|| apellidos == "" || fechaInscripcion == "" || email == "" || contrasena == "" || fechaNacimiento == "") {
		response.sendRedirect("/PW3/errorPage.jsp?msg=Uno o mas campos de registro estaban incompletos");
	}
	else{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioDTO user = usuarioDAO.buscarUsuario(request.getParameter("email"));
		
		if (CustomerBean.getEmail() != null) {
			response.sendRedirect("/PW3/errorPage.jsp?msg=Debe cerrar la sesion antes de registrar un usuario");
		}
		else {		
			if (user != null) {
				response.sendRedirect("/PW3/errorPage.jsp?msg=Ya existe un usuario con ese email");
			}
			else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				try {
					LocalDate fechanacimiento = LocalDate.parse(fechaNacimiento, formatter);
					LocalDate fechainscripcion = LocalDate.parse(fechaInscripcion, formatter);
					user = new UsuarioDTO(email, contrasena, nombre,apellidos, fechanacimiento, fechainscripcion, true);
					usuarioDAO.guardarUsuario(user);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				
				response.sendRedirect("/PW3/");
			}
		}
	}
	
%>