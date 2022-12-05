<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO" %>
<jsp:useBean id="CustomerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%

	String nombre = request.getParameter("nombre");
	String contrasena = request.getParameter("contrasena");
	String fechaNacimiento= request.getParameter("fechaNacimiento");
	
	if (nombre == null || contrasena == null || fechaNacimiento == null || nombre == "" || contrasena == "" || fechaNacimiento == ""){
		response.sendRedirect("/P3_PW/errorPage.jsp?msg=Uno o mas campos estaban incompletos");
	}
	else{
		UsuarioDAO usuarioDAO = new UsuarioDAO(request.getServletContext());
		
		if (CustomerBean.getEmail() != null) {
			int id = CustomerBean.getID();
			try{
				LocalDate fechaNacimiento = LocalDate.parse(fechaNacimiento);
				UsuarioDTO user = new UsuarioDTO(id, CustomerBean.getEmail(), contrasena, nombre, fechaNacimiento);
				usuarioDAO.updateUser(user);
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("/P3_PW/");
	}
%>