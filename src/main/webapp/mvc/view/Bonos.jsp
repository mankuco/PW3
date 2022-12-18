<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="business.Reserva.*, java.util.ArrayList, java.time.LocalDate, java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <title>MIS BONOS</title>
</head>
<body >
	<%if (userBean.getRol() == true){ %>
		<%@ include file="../../include/headerCliente.jsp" %>
	<%} else {%>
		<%@ include file="../../include/headerAdministrador.jsp" %>
	<%} %>
		
	<div>		
	<%if(userBean.getRol() == true){ %>
		<button class="buttonAdd" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/addBono.jsp'"> + Crear Bono</button>
	<%} %>
	</div>
	<br/>
	<% String msg = (String)request.getAttribute("msg"); %>
   	<% if (msg != null) { 
   			if (msg.equals("Ya tiene un bono activo en este momento")){%>
   				<p style="background-color: #ff9999; text-align: center;"><%= msg %></p>
   			<%} else { %>
   				<p style="background-color: #abedb2; text-align: center;"><%= msg %></p>
   	<% } } %>
	<%  ArrayList<BonoReservaDTO> bonos = null;
		bonos = (ArrayList<BonoReservaDTO>)request.getAttribute("verBonos");
	   	if (bonos == null || bonos.size() == 0) { %>
			<h3 style=text-align:center; >No hay Bonos que mostrar.</h3>
	 	<% } else {
		 	Integer count = 0; %>
	    	<section class="contenido">
	       	<div class="mostrador" id="mostrador">
		    	<div class="fila">
		            <% for (BonoReservaDTO k : bonos){ %> 
		                <div class="item" >
	                    <p class="descripcion">Bono <%= k.getIdBono() %></p>
	                    <h5 id="usuario"><%= k.getEmail() %></h5>
	                    <%if (k.getIdReserva1() != null){ %>
	                    	<p id="reserva1"  >  <%= k.getIdReserva1() %></p>
	                    	<%if (k.getIdReserva2() != null){ %>
	                    		<p id="reserva2" >  <%= k.getIdReserva2() %></p>
	                    		<%if (k.getIdReserva3() != null){ %>
	                    			<p id="reserva3"   >  <%= k.getIdReserva3() %></p>
	                    			<%if (k.getIdReserva4() != null){ %>
	                    				<p id="reserva4"  >  <%= k.getIdReserva4() %></p>
	                    				<%if (k.getIdReserva5() != null){ %>
	                    					<p id="reserva5"  >  <%= k.getIdReserva5() %></p>
	                    <%}}}} %>
	                    <span class="fechaVencida"> <%= k.getFecha() %></span>
	                <%} %>
	         	</div>
                <% count++; if (count % 3 == 0) {%>  </div>  <div class="fila"> <%}}} %>
	            </div>
		  
		        </div>
	        <!-- CONTENEDOR DEL ITEM SELECCIONADO -->
	        <div class="seleccion" id="seleccion">
	            <div class="cerrar" onclick="cerrar()">
	                &#x2715
	            </div>
	            <div class="info">
		                <img src="" alt="" id="img">
		                <h3 id=kartid></h3>
		                <h2  style=display:none; id="reserva"></h2>
		                 <p id="usuario"></p>
		                 <h5>Bono:</h5>
		                 <p id="pista"></p>
		                <span class="fecha"></span>
		             	<p id="idK"></p>
	            </div>
	        </div>
	
	    </section>
	
	    <script src="js/scriptVisualizacionBono.js"></script>
</body>
</html>