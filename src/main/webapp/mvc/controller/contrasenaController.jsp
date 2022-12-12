<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<% 	
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	
	else {
		
		String contrasena = request.getParameter("contrasena");
		String nueva = request.getParameter("nueva");
		
		if (nueva == null || contrasena == null || nueva.equals("") || contrasena.equals("")) {%>
			<jsp:forward page="../view/cambiarContrasena.jsp">
				<jsp:param value="La contrase&ntilde;a no puede quedar vac&iacute;a" name="msg"/>
			</jsp:forward>
		<% }
		else {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			UsuarioDTO usuario = usuarioDAO.buscarUsuario(userBean.getEmail());
			if (usuario.getContrasena().equals(contrasena)) {
				usuario.setContrasena(nueva);
				usuarioDAO.modificarUsuario(usuario);
				userBean.setContrasena(nueva);%>
				<jsp:forward page="../view/editarPerfil.jsp">
					<jsp:param value="Contrase&ntilde;a actualizada" name="msg"/>
				</jsp:forward>
			<% }
			else {%>
				<jsp:forward page="../view/cambiarContrasena.jsp">
					<jsp:param value="La contrase&ntilde;a actual es incorrecta" name="msg"/>
				</jsp:forward>
			<% }
		}
	}%>