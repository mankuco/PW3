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
    <title>MIS KARTS</title>
</head>
<body >
<%@ include file="../../include/headerAdministrador.jsp" %>
<div>		
<%if(userBean.getRol() == false){ %>
				<button class="buttonAdd" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/addKart.jsp'"> + A&ntilde;adir kart</button>
			<%} %>
		</div>
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
                    <h3 id=kartid1> Kart Nº: <%= k.getIdKart() %></h3>
                    <p class="descripcion"   style=display:none;><%= k.getIdKart() %></p>
                     <p id="nombre"><% if( k.getTipoKart()== true){ out.println("Infantil");}else {out.println("Adultos");} %> </p>
                       <p id="precioPista"  style=display:none; >  <% if(k.getnombrePista()==null){ out.println("Sin asociar");}else{ %> <%= k.getnombrePista() %><%}%> </p>
                     <% if( k.getEstado() == Estados.MANTENIMIENTO){ %>
                    <span class="fechaVencida"> <%= Estados.MANTENIMIENTO %></span>
                     <%} if( k.getEstado() == Estados.DISPONIBLE){ %>
                     <span class="fecha" > <%= Estados.DISPONIBLE %></span>
                     <%} if( k.getEstado() == Estados.RESERVADO){ %>
                     <span class="fecha" style="color: #dc7605;"> <%= Estados.RESERVADO %></span>
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
	                <h3 id=kartid></h3>
	                <h2  style=display:none; id="reserva"></h2>
	                 <p id="usuario"></p>
	                 <h5>PISTA ASOCIADA:</h5>
	                 <p id="pista"></p>
	                <span class="fecha"></span>
	             	<p id="idK"></p>
	
	                <div class="fila">
						 
	                      <button onclick="eliminar(this)" >ELIMINAR KART</button>
	               	 
	                	 
	                 	   <button class="buttonMod"  onclick="editar(this)" type="submit">MODIFICAR KART</button>
	                 	</form>
                	</div>
            </div>
        </div>

    </section>

    <script src="js/scriptVisualizacionKart.js"></script>
</body>
</html>