<header class="navbarC">
	<ul class="navbarC-left">
		<li class="navbarC-item"> //Inicio Cliente
			<a href="<%=request.getContextPath()%>/misReservasServlet">Inicio</a>
		</li>
		<li class="navbarC-item"> //Reservas
			<a href="<%=request.getContextPath()%>/misReservasServlet">Reservas</a>
		</li>
	</ul>
	
	<ul class="navbarC-right">
		<li class="navbarC-item"> //Perfil
			<a href="<%=request.getContextPath()%>/MVC/View/editProfile.jsp">Perfil</a>
		</li>
		<li class="navbarC-item" style="background-color: #db2121;"> //Cerrar sesion
			<a href="<%= request.getContextPath() %>/cerrarServlet">Cerrar sesi&oacute;n</a>
		</li>
	</ul>
</header>