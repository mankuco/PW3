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
    <title>MIS RESERVAS</title>
</head>
<body >
<% 
ArrayList<Reserva> reservas;
if(userBean.getRol()== false){
	
	if(request.getAttribute("verReservasFiltradas") != null) {
	  reservas = (ArrayList<Reserva>)request.getAttribute("verReservasFiltradas");
	} else {  reservas = (ArrayList<Reserva>)request.getAttribute("verReservasADM");}
		
	
	
//	reservas= (ArrayList<Reserva>)request.getAttribute("verReservasADM");
	%><%@ include file="../../include/headerAdministrador.jsp" %><% 
	}else{
	 if(request.getAttribute("verReservasFiltradasUsuario") != null){ reservas = (ArrayList<Reserva>)request.getAttribute("verReservasFiltradasUsuario");}
	 else{	reservas = (ArrayList<Reserva>)request.getAttribute("verReservasUsuario"); }
	 

%><%@ include file="../../include/headerCliente.jsp" %> 


<div >
<%if(userBean.getRol() == true){ %>
<button class="buttonAdd" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/addReserva.jsp'"> + Nueva Reserva</button>
			<%} %>
		</div>
<% }%>



<form class=filtro action="<%=request.getContextPath()%>/misReservasServlet" method="Post">

<label class=filtrolabel for="fecha-inicio">Inicio:</label>
	<input type="date" name="fecha-inicio" id="fecha-inicio"  >
	  <br>
<label class=filtrolabel for="fecha-fin">Fin:</label>
	  <input type="date" name="fecha-fin" id="fecha-fin" >
	  <br>
	  <input class="botonform" type="submit" value="Filtrar">
</form>

		
			
 <%  if (reservas == null || reservas.size() == 0) { %>
 <h3 style=text-align:center; >No hay reservas que mostrar.</h3>
 <% } else {
	 Integer count = 0; %>
	

    <section class="contenido">
        <div class="mostrador" id="mostrador">
            <div class="fila">
            <% for (Reserva r : reservas){ %> 
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                    <img src="https://mysouthafrica.co.za/wp-content/uploads/2021/03/two-kart-racers-enters-turn.jpg" alt="">        
                    </div>
                    <p class="descripcion">Reserva <%= r.getTipo() %></p>
                     <p id="nombre">  <%= r.getIdUsuario() %></p>
                       <p id="nombrepista"  style=display:none; >  <%= r.getIdPista() %></p>
                       <p id="precioPista"  style=display:none; >  <%= r.getPrecioPista() %> $</p>
                       <p id="NumMinutos"  style=display:none; >  <%= r.getMinutosReserva() %> minutos</p>             
                      <h4 id="Reservahora"><%= r.getHora() %>  </h4>
                      <% if(r.getFecha().compareTo(LocalDate.now())<0){ %>
                    <span class="fechaVencida"> <%= r.getFecha() %></span>
                     <%}else{%>
                     <span class="fecha"> <%= r.getFecha() %></span>
                     
                     <% }%>
                     <p id=rid style=display:none;><%=r.getIdReserva()%></p>
                </div>
                <% count++; if (count % 3 == 0) {%>  </div>  <div class="fila"> <%} }  } %>
            </div>
  
        </div>
        <!-- CONTENEDOR DEL ITEM SELECCIONADO -->
        <div class="seleccion" id="seleccion">
            <div class="cerrar" onclick="cerrar()">
                &#x2715
            </div>
            <div class="info">
	                <img src="" alt="" id="img">
	                <h2 id="reserva"></h2>
	                 <p id="usuario"></p>
	                 <h5>PISTA:</h5>
	                 <p id="pista"></p>
	                 <h5>PRECIO:</h5>
	                <p id="precio"></p>
	                <h5>DURACION:</h5>
	                <p id="minutos"></p>
	                 <h5>HORA:</h5>
	                <p id="hora"></p>       
	             	<span id="fechaR" class="fecha"> </span>
					<p id=reservaid1 style=display:none;></p>
	                <div class="fila">
	                
						 <button onclick="eliminar(this)" >CANCELAR RESERVA</button>

		                 		<button class="buttonMod"   type="submit">MODIFICAR RESERVA</button>
	                 	
                	</div>
            </div>
        </div>
    </section>
       
	<br/>
    <script src="js/scriptVisualizacionReservas.js"></script>
    
    
    
</body>
</html>