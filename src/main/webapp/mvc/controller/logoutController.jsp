<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="business.Usuario.UsuarioDTO,data.Usuario.UsuarioDAO" %>
<jsp:useBean id="CustomerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	if (CustomerBean.getEmail() != null) {
		CustomerBean.setEmail(null);
		CustomerBean.setNombre(null);
		CustomerBean.setApellidos(null);
		CustomerBean.setContrasena(null);
	}

	response.sendRedirect("/PW3/");
%>