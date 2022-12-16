<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate,business.Usuario.UsuarioDTO, data.Usuario.UsuarioDAO" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<% 	
	if (userBean.getEmail() == null){
		response.sendRedirect(request.getContextPath());
	}
	
	else {
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		
		if (nombre == null || apellidos == null|| fechaNacimiento == null || nombre == ""|| apellidos == "" || fechaNacimiento == "") { %>
			<jsp:forward page="../view/editarPerfil.jsp">
				<jsp:param value="Campos incompletos" name="msg"/>
			</jsp:forward>
		<%}
		else{
			
			if (userBean.getEmail() != null) {
				try{
				
					LocalDate fecha = LocalDate.parse(fechaNacimiento);
					
					UsuarioDAO usuarioDAO = new UsuarioDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
					UsuarioDTO usuario = usuarioDAO.buscarUsuario(userBean.getEmail());
					
					usuario.setFechaNacimiento(fecha);
					usuario.setApellidos(apellidos);
					usuario.setNombre(nombre);
					
					usuarioDAO.modificarUsuario(usuario);
					
					userBean.setApellidos(apellidos);
					userBean.setNombre(nombre);
					userBean.setFechaNacimiento(fecha);
				}
				catch (Exception e){
					e.printStackTrace();
				}
				
			}%>
			<jsp:forward page="../view/editarPerfil.jsp">
				<jsp:param value="Perfil editado de forma correcta" name="msg"/>
			</jsp:forward>
			<%} 
		}%>