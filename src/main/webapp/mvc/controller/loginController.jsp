<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO" %>
<jsp:useBean id="CustomerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
		UsuarioDAO usuarioDAO = new UsuarioDAO(request.getServletContext());
		String email = request.getParameter("email");
		String contrasena = request.getParameter("contrasena");
		UsuarioDTO user = usuarioDAO.buscarUsuario(email);
		
		boolean loginSuccessful = false;
		if (user != null && user.getContrasena().equals(contrasena)){
			loginSuccessful = true;
		}
		
		if (!loginSuccessful) {
			response.sendRedirect("http://www.uco.es/");
		}
		else {
			if (loginSuccessful) {
				CustomerBean.setID(user.getID());//no hay id en dto no se si añadirlo o quitar la linea
				CustomerBean.setEmail(user.getEmail());
				CustomerBean.setNombre(user.getNombre());
				CustomerBean.setApellidos(user.getApellidos());
				response.sendRedirect("/PW3/src/main/webapp/mvc/view/loginView.jsp");
			}
			else { 
			%>
				<jsp:forward page="/mvc/view/loginView.jsp">
					<jsp:param value="Login incorrecto" name="msg"/>
				</jsp:forward>
		<%}
		}
%>