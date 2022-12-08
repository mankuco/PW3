<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO" %>
<jsp:useBean id="CustomerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String contrasena = request.getParameter("contrasena");
	String fechaNacimiento = request.getParameter("fechaNacimiento");
	String fechaInscripcion = request.getParameter("fechaInscripcion");
	String rol = request.getParameter("rol");
	
	if (nombre == null || apellidos == null|| contrasena == null || fechaNacimiento == null || fechaInscripcion == null || rol == null || rol == "" || nombre == ""|| apellidos == "" || contrasena == "" || fechaNacimiento == ""|| fechaInscripcion == ""){
		response.sendRedirect("/PW3/errorPage.jsp?msg=Uno o mas campos estaban incompletos");
	}
	else{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (CustomerBean.getEmail() != null) {
			try{
				LocalDate fechanacimiento = LocalDate.parse(fechaNacimiento);
				LocalDate fechainscripcion = LocalDate.parse(fechaInscripcion);
				boolean Rol = false;
				try{
					if(rol == "true"){
						Rol = true;
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
				UsuarioDTO user = new UsuarioDTO(CustomerBean.getEmail(), contrasena, nombre, apellidos, fechanacimiento, fechainscripcion, Rol);
				usuarioDAO.modificarUsuario(user);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("/PW3/");
	}
%>