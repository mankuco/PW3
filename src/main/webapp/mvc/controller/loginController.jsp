<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO, java.time.format.DateTimeFormatter, java.time.LocalDate, java.time.temporal.ChronoUnit" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>

		<%UsuarioDAO usuarioDAO = new UsuarioDAO();
		String email = request.getParameter("email");
		String contrasena = request.getParameter("contrasena");
		UsuarioDTO user = usuarioDAO.buscarUsuario(email);
		
		boolean loginSuccessful = false;
		if (user != null && user.getContrasena().equals(contrasena)){
			loginSuccessful = true;
		}
		if (loginSuccessful) {

			userBean.setContrasena(user.getContrasena());
			userBean.setEmail(user.getEmail());
			userBean.setNombre(user.getNombre());
			userBean.setApellidos(user.getApellidos());
			userBean.setRol(user.getRol());
			userBean.setFechaNacimiento(user.getFechaNacimiento());
			userBean.setFechaInscripcion(user.getFechaInscripcion());
			response.sendRedirect("/PW3/index.jsp");
			
		}
		else {%>
			<jsp:forward page="/mvc/view/loginView.jsp">
				<jsp:param value="Login incorrecto" name="msg"/>
			</jsp:forward>
		<%}%>