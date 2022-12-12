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
<%@ include file="../../include/headerAdministrador.jsp" %>
<%  ArrayList<PistaDTO> pistas = null;
if(userBean.getRol()== false){ pistas = (ArrayList<PistaDTO>)request.getAttribute("verPistas"); }			
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
                    <p class="descripcion">Pista <%= p.getNombrePista() %></p>
                     <p id="nombre">  <%= p.getDificultad() %></p>
                       <p id="nombrepista"  style=display:none; >  <%= p.getnkartsasociados() %></p>
                       <p id="precioPista"  style=display:none; >  <%= p.getMaxKarts() %> </p>
                
                 
                    <span class="fecha"> <% if( p.getTipoEstado() == true){ out.println("Disponible");}else {out.println("Mantenimiento");} %></span>
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
	          
	             
	
	                <div class="fila">
						 <form action="cancelarReserva" method="POST">
	                      <button  type="submit" >ELIMINAR PISTA</button>
	               	  </form>
	                	 <form action="modificarReservaServlet" method="POST">
	                 	   <button class="buttonMod"  type="submit">MODIFICAR PISTA</button>
	                 	</form>
                	</div>
            </div>
        </div>
        <div style="text-align: center">
			<br/><br/>
			<%if(userBean.getRol() == false){ %>
				<button type="button" class="big-button" style="width: 300px; border: solid 2px #cf74f2;" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/addPista.jsp'">A&ntilde;adir Pista</button>
			<%} %>
		</div>
    </section>

    <script src="js/scriptVisualizacionPistas.js"></script>
</body>
</html>