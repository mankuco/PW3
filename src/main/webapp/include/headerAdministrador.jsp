<header class="navbar">
	<ul class="navbar-left">
		<li class="navbar-item">
			<a href="<%=request.getContextPath()%>/usuarioServlet">Inicio</a>
		</li>
		<li class="navbar-item">
			<a href="<%=request.getContextPath()%>/misKartsServlet">Karts</a>
		</li>
		<li class="navbar-item">
			<a href="<%=request.getContextPath()%>/misPistasServlet">Pistas</a>
		</li>
		<li class="navbar-item"> 
			<a href="<%=request.getContextPath()%>/misReservasServlet">Reservas</a>
		</li>
	</ul>
	
	<ul class="navbar-right">
		<li class="navbar-item">
			<a href="<%=request.getContextPath()%>/mvc/view/editarPerfil.jsp">Editar Perfil</a>
		</li>
		<li class="navbar-item" style="background-color: #cf74f2;">
			<a href="<%= request.getContextPath() %>/mvc/controller/logoutController.jsp">Cerrar sesi&oacute;n</a>
		</li>
	</ul>
</header>