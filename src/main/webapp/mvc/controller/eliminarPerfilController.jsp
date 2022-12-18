<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="business.Usuario.UsuarioDTO, business.Reserva.Reserva, data.Usuario.UsuarioDAO, data.Reserva.ReservaDAO, java.util.ArrayList" %>
<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
	if (userBean.getEmail() != null) {
		UsuarioDAO user = new UsuarioDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
		if((userBean.getRol() == false) && (user.nAdmin() == 1)) {//Si es administrador y Solo hay un administrador, no se elimina%>
			<jsp:forward page="../view/editarPerfil.jsp">
				<jsp:param value="No se puede eliminar este prefil, debe de haber al menos un administrador" name="msg"/>
			</jsp:forward>
		<%}
		else {
			ReservaDAO r = new ReservaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
			ArrayList<Reserva> reservas = r.verReservasUsuario(userBean.getEmail(),null,null);
			if((reservas != null) && (reservas.size() != 0)) {
				for(Reserva a : reservas){
					r.borraReserva(a.getIdReserva());
				}
			}
			user.eliminarUsuario(userBean.getEmail());
			userBean.setEmail(null);
			userBean.setNombre(null);
			userBean.setApellidos(null);
			userBean.setContrasena(null);
		}
	}

	response.sendRedirect("/PW3/");
%>