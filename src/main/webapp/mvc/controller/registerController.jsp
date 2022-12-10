<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO, data.DAO" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String email = request.getParameter("email");
	String contrasena = request.getParameter("contrasena");
	String fechanacimiento= request.getParameter("fechanacimiento");
	
	if (nombre == null || apellidos == null || email == null || contrasena == null || fechanacimiento == null || nombre == ""|| apellidos == "" || email == "" || contrasena == "" || fechanacimiento == "") {
		response.sendRedirect("/PW3/errorPage.jsp?msg=Uno o mas campos de registro estaban incompletos");
	}
	else{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioDTO usuario = usuarioDAO.buscarUsuario(request.getParameter("email"));
		
		if ((userBean.getEmail() != null) && (userBean.getRol() == true)){
			response.sendRedirect("/PW3/errorPage.jsp?msg=Debe cerrar la sesion antes de registrar un usuario");
		}
		else {		
			if (usuario != null) {
				response.sendRedirect("/PW3/errorPage.jsp?msg=Ya existe un usuario con ese email");
			}
			else {
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				try {
					LocalDate fechaNacimiento = LocalDate.parse(fechanacimiento, formatter);
					boolean rol = true;
					//if (userBean.getRol() == false) { rol = false; }
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