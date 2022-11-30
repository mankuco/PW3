<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>MIS RESERVAS</title>
</head>
<body >
		<%@ include file="include/header.jsp" %>

    <section class="contenido">
        <div class="mostrador" id="mostrador">
            <div class="fila">
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                        <img src="images/Reservas/reservaInfantil.jpg" alt="">
                    </div>
                    <p class="descripcion">Reserva Infantil</p>
                    <span class="fecha">26/06/2022</span>
                </div>
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                        <img src="images/Reservas/reservaAdultos.jpg" alt="">
                    </div>
                    <p class="descripcion">Reserva Adultos</p>
                    <span class="fecha">29/06/2022</span>
                </div>
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                        <img src="images/Reservas/reservaFamiliar.jpg" alt="">
                    </div>
                    <p class="descripcion">Reserva Familiar</p>
                    <span class="fecha">31/06/2022</span>
                </div>

            </div>
            <div class="fila">
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                        <img src="images/Reservas/reservaInfantil.jpg" alt="">
                    </div>
                    <p class="descripcion">Reserva Infantil</p>
                    <span class="fecha">01/01/2023</span>
                </div>
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                        <img src="images/Reservas/reservaAdultos.jpg" alt="">
                    </div>
                    <p class="descripcion">Reserva Adultos</p>
                    <span class="fecha">26/09/2022</span>
                </div>
                <div class="item" onclick="cargar(this)">
                    <div class="contenedor-foto">
                        <img src="images/Reservas/reservaFamiliar.jpg" alt="">
                    </div>
                    <p class="descripcion">Reserva Familiar</p>
                    <span class="fecha">17/06/2022</span>
                </div>

            </div>
        </div>
        <!-- CONTENEDOR DEL ITEM SELECCIONADO -->
        <div class="seleccion" id="seleccion">
            <div class="cerrar" onclick="cerrar()">
                &#x2715
            </div>
            <div class="info">
                <img src="img/1.png" alt="" id="img">
                <h2 id="reserva"></h2>
                <p id="descripcion"></p>
                <h5 id="reserva">PARTICIPANTES</h5>
                <p id="descripcion"> 12 Personas </p>
                <h5 id="reserva">DURACION</h5>
                <p id="descripcion"> 90m </p>
                <h5 id="reserva">PRECIO</h5>
                <p id="descripcion"> 130€ </p>
                <h5 id="reserva">PISTA</h5>
                <p id="descripcion"> GrandPrix </p>
                <span class="fecha" id="fecha"></span>

                <div class="fila">

                    <button>CANCELAR RESERVA</button>
                </div>
            </div>
        </div>
    </section>

    <script src="js/scriptVisualizacion.js"></script>
</body>
</html>