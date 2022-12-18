let mostrador = document.getElementById("mostrador");
let seleccion = document.getElementById("seleccion");
let imgSeleccionada = document.getElementById("img");
let reservaSeleccionado = document.getElementById("reserva");
let usuarioSeleccionado = document.getElementById("usuario");
let pistaSeleccionado = document.getElementById("pista");
let precioSeleccionado = document.getElementById("precio");
let minutosSeleccionado = document.getElementById("minutos");
let horaSeleccionado = document.getElementById("hora");
let fechaSeleccionado = document.getElementById("fechaR");
let reservaID = document.getElementById("reservaid1");



function cargar(item){
    quitarBordes();
    mostrador.style.width = "60%";
    seleccion.style.width = "40%";
    seleccion.style.opacity = "1";
    item.style.border = "2px solid red";
   

    imgSeleccionada.src = item.getElementsByTagName("img")[0].src;

    reservaSeleccionado.innerHTML =  item.getElementsByTagName("p")[0].innerHTML;
    usuarioSeleccionado.innerHTML =  item.getElementsByTagName("p")[1].innerHTML;
    pistaSeleccionado.innerHTML =  item.getElementsByTagName("p")[2].innerHTML;
    precioSeleccionado.innerHTML =  item.getElementsByTagName("p")[3].innerHTML;
    minutosSeleccionado.innerHTML =  item.getElementsByTagName("p")[4].innerHTML;
    horaSeleccionado.innerHTML =  item.getElementsByTagName("h4")[0].innerHTML;
    fechaSeleccionado.innerHTML =  item.getElementsByTagName("span")[0].innerHTML;
    reservaID.innerHTML =  item.getElementsById("nPista").innerHTML;
}

function eliminar(item) {
	
	 const kartID =document.getElementById("reservaid1").innerHTML;
	  if (confirm("¿Estás seguro de que quieres eliminar este reserva?")) {
	
  		 fetch(`misReservasServlet?id=${kartID}`, { method: 'DELETE' })
   		 .then(response =>{ response.json() 
   		  location.reload() })
  		 .then(data => {
     		
   		 })
    	.catch(error => {
      console.error(error);
    });
  
  }
 
}

function editar(item) {
 const rid =document.getElementById("reservaid1").innerHTML; 
	  if (confirm("¿Estas seguro de que quieres editar esta reserva?")) {
 
    		 window.location.replace(`/PW3/mvc/view/editarReserva.jsp?id=${rid}`);
   		 } else {
    		  // Mostrar un mensaje de error
    		  console.error("Ha ocurrido un error al procesar la solicitud");
  		  }
 
}



function cerrar(){
    mostrador.style.width = "100%";
    seleccion.style.width = "0%";
    seleccion.style.opacity = "0";
    quitarBordes();
}
function quitarBordes(){
    var items = document.getElementsByClassName("item");
    for(i=0;i <items.length; i++){
        items[i].style.border = "none";
    }
}

document.querySelector('.botonform').addEventListener('click', function(event) {
  event.preventDefault(); // Evita el envío del formulario

  // Obtener las fechas introducidas
  var fechaInicio = document.querySelector('#fecha-inicio').value;
  var fechaFin = document.querySelector('#fecha-fin').value;

  // Comprobar que ambas fechas han sido introducidas
  if (!fechaInicio || !fechaFin) {
    alert('Debes introducir ambas fechas');
    return; // Termina la función
  }

  // Comprobar que la fecha de inicio es anterior a la fecha de fin
  if (fechaInicio > fechaFin) {
    alert('La fecha de inicio debe ser anterior a la fecha de fin');
    return; // Termina la función
  }

  // Si se han cumplido ambas condiciones, envía el formulario
  document.querySelector('form').submit();
});