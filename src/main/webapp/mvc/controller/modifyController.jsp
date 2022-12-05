<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO" %>
<jsp:useBean id="CustomerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
//no se si se añade el rol
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String contrasena = request.getParameter("contrasena");
	String fechaNacimiento= request.getParameter("fechaNacimiento");
	String fechaIncripcion= request.getParameter("fechaInscripcion");
	
	if (nombre == null || apellidos == null|| contrasena == null || fechaNacimiento == null || fechaInscripcion == null || nombre == ""|| lastname == "" || contrasena == "" || fechaNacimiento == ""|| fechaIncripcion == ""){
		response.sendRedirect("/PW3/errorPage.jsp?msg=Uno o mas campos estaban incompletos");
	}
	else{
		UsuarioDAO usuarioDAO = new UsuarioDAO(request.getServletContext());
		
		if (CustomerBean.getEmail() != null) {
			try{
				LocalDate fechaNacimiento = LocalDate.parse(fechaNacimiento);
				LocalDate fechaInscripcion = LocalDate.parse(fechaIncripcion);
				UsuarioDTO user = new UsuarioDTO(CustomerBean.getEmail(), contrasena, nombre, apellidos, fechaNacimiento, fechaInscripcion);
				usuarioDAO.modificarUsuario(user);
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("/PW3/");
	}
%>