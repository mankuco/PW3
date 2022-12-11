<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="business.Usuario.UsuarioDTO,data.Usuario.UsuarioDAO" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	if (userBean.getEmail() != null) {
		userBean.setEmail(null);
		userBean.setNombre(null);
		userBean.setApellidos(null);
		userBean.setContrasena(null);
	}

	response.sendRedirect("/PW3/");
%>