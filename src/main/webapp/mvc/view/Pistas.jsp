<jsp:useBean id="userBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="business.Reserva.*, business.Pista_Kart.*, java.util.ArrayList, java.time.LocalDate, java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <title>MIS PISTAS</title>
</head>
<body >
<%if(userBean.getRol()== false){ %>
<%@ include file="../../include/headerAdministrador.jsp" %>
<%}else{%><%@ include file="../../include/headerCliente.jsp" %> <%} %>
<div >
		
			<%if(userBean.getRol() == false){ %>
				<button class="buttonAdd" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/addPista.jsp'"> + A&ntilde;adir Pista</button>
			<%} %>
			<button class="buttonFilt" style="background-color: #008040" onclick="ocultarPistasNoDisponibles()">Disponibles</button>
			<button class="buttonFilt"  onclick="mostrarFamiliares()">Familiares</button>
			<button class="buttonFilt"  onclick="mostrarAdultos()">Adultos</button>
			<button class="buttonFilt"  onclick="mostrarInfantiles()">Infantiles</button>
			<button class="buttonFilt"  style="background-color: #4b4745" onclick="location.reload();">Quitar Filtro</button>
		</div>
<%  ArrayList<PistaDTO> pistas = null;
 pistas = (ArrayList<PistaDTO>)request.getAttribute("verPistas"); 		
   if (pistas == null || pistas.size() == 0) { %> 
 <h3 style=text-align:center; >No hay pistas que mostrar.</h3>
 <% } else {
	 Integer count = 0; %>
    <section class="contenido">
        <div class="mostrador" id="mostrador">
            <div class="fila">
            <% for (PistaDTO p : pistas){ %> 
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">      
                    <img src="https://www.diariodeleon.es/media/diariodeleon/images/2021/07/21/2021072109360110806.jpg" alt="">        
                    </div>
                    <p class="descripcion"><%=p.getNombrePista()%></p>
                     <p class="nombre" id="nombre"><%=p.getDificultad()%></p>
                       <p id="nombrepista"  style=display:none; >  <%= p.getnkartsasociados() %></p>
                       <p id="precioPista"  style=display:none; >  <%= p.getMaxKarts() %> </p>
                        <p id="nPista" style=display:none;><%=p.getNombrePista()%></p>
                     <% if( p.getTipoEstado() == false){ %>
                    <span class="fechaVencida"> <%= Estados.MANTENIMIENTO %></span>
                     <%}else{%>
                     <span class="fecha"> <%= Estados.DISPONIBLE %></span>
                     <% }%>    
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
	                 <h5>KARTS ASOCIADOS:</h5>
	                 <p id="pista"></p>
	                 <h5>KARTS MAXIMOS:</h5>
	                <p id="precio"></p>
	               
	          
	             
	<% if(userBean.getRol()== false){ %>
	                <div class="fila">
						
	                      <button onclick="eliminar(this)" >ELIMINAR PISTA</button>
	               	 
	                	
	                 	   <button class="buttonMod"   onclick="editar(this)" type="submit">MODIFICAR PISTA</button>
	                 	
                	</div>
                	<% } %>
            </div>
        </div>

    </section>

    <script src="js/scriptVisualizacionPistas.js"></script>
</body>
</html>