<header class="navbar">
	<ul class="navbar-left">
		<li class="navbar-item">
			<a href="<%=request.getContextPath()%>/misReservasServlet">Mis Reservas</a>
		</li>
		<li class="navbar-item">
			<a href="<%=request.getContextPath()%>/nuevaReservaServlet">Nueva Reserva</a>
		</li>
	
		
	</ul>
	<ul class="navbar-right">
		<li class="navbar-item">
			<a href="<%=request.getContextPath()%>/MVC/View/editProfile.jsp">Editar perfil</a>
		</li>
		<li class="navbar-item" style="background-color: #db2121;">
			<a href="<%= request.getContextPath() %>/cerrarServlet">Cerrar sesi&oacute;n</a>
		</li>
	</ul>
</header>