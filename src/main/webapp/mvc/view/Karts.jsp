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
<%  ArrayList<KartDTO> karts = null;
if(userBean.getRol()== false){ karts = (ArrayList<KartDTO>)request.getAttribute("verPistas"); }			
   if (karts == null || karts.size() == 0) { %>
 <h3 style=text-align:center; >No hay karts que mostrar.</h3>
 <% } else {
	 Integer count = 0; %>
    <section class="contenido">
        <div class="mostrador" id="mostrador">
            <div class="fila">
            <% for (KartDTO k : karts){ %> 
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                    <img src="https://www.kartingangelburgueno.com/wp-content/uploads/2020/07/Rotax-2T-2020.jpg" alt="">        
                    </div>
                    <p class="descripcion">KART Nº<%= k.getIdKart() %></p>
                     <p id="nombre"><% if( k.getTipoKart()== true){ out.println("Infantil");}else {out.println("Adultos");} %> </p>
                       <p id="precioPista"  style=display:none; >  <% if(k.getnombrePista()==null){ out.println("Sin asociar");}else{ %> <%= k.getnombrePista() %><%}%> </p>
                    <span class="fecha"><%= k.getEstado() %> </span>
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
	                 <h5>PISTA ASOCIADA:</h5>
	                 <p id="pista"></p>
	                <span class="fecha"></span>
	             
	
	                <div class="fila">
						 <form action="cancelarReserva" method="POST">
	                      <button  type="submit" >ELIMINAR KART</button>
	               	  </form>
	                	 <form action="modificarReservaServlet" method="POST">
	                 	   <button class="buttonMod"  type="submit">MODIFICAR KART</button>
	                 	</form>
                	</div>
            </div>
        </div>
        
        <div style="text-align: center">
			<br/><br/>
			<%if(userBean.getRol() == false){ %>
				<button type="button" class="big-button" style="width: 300px; border: solid 2px #cf74f2;" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/addKart.jsp'">A&ntilde;adir kart</button>
			<%} %>
		</div>
    </section>

    <script src="js/scriptVisualizacionPistas.js"></script>
</body>
</html>