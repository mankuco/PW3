<header class="navbar">
	<ul class="navbar-left">
		<li class="navbar-item"> 
			<a href="<%=request.getContextPath()%>/usuarioServlet">Inicio</a>
		</li>
		<li class="navbar-item"> 
			<a href="<%=request.getContextPath()%>/misReservasServlet">Reservas</a>
		</li>
	</ul>
	
	<ul class="navbar-right">
		<li class="navbar-item"> 
			<a href="<%=request.getContextPath()%>/mvc/view/editProfile.jsp">Perfil</a>
		</li>
		<li class="navbar-item" style="background-color: #db2121;"> 
			<a href="<%= request.getContextPath() %>/cerrarServlet">Cerrar sesi&oacute;n</a>
		</li>
	</ul>
</header>