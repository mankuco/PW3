<header class="navbarA">
	<ul class="navbarA-left">
		<li class="navbarA-item"> //Inicio Administrador
			<a href="<%=request.getContextPath()%>/misReservasServlet">Inicio</a>
		</li>
		<li class="navbarA-item"> //Karts
			<a href="<%=request.getContextPath()%>/misReservasServlet">Karts</a>
		</li>
		<li class="navbarA-item"> //Pistas
			<a href="<%=request.getContextPath()%>/nuevaReservaServlet">Pistas</a>
		</li>
		<li class="navbarA-item"> //Reservas
			<a href="<%=request.getContextPath()%>/nuevaReservaServlet">Reservas</a>
		</li>
	</ul>
	
	<ul class="navbarA-right">
		<li class="navbarA-item"> //Perfil + anadir nuevo administrador
			<a href="<%=request.getContextPath()%>/MVC/View/editProfile.jsp">Perfil</a>
		</li>
		<li class="navbarA-item" style="background-color: #db2121;"> //Cerrar sesion
			<a href="<%= request.getContextPath() %>/cerrarServlet">Cerrar sesi&oacute;n</a>
		</li>
	</ul>
</header>