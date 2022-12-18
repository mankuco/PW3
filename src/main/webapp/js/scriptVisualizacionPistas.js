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
let nPista = document.getElementById("nombrePista2");



function cargar(item){
    quitarBordes();
    mostrador.style.width = "60%";
    seleccion.style.width = "40%";
     seleccion.style.height="20%";
    seleccion.style.opacity = "1";
    item.style.border = "2px solid red";

    imgSeleccionada.src = item.getElementsByTagName("img")[0].src;

    reservaSeleccionado.innerHTML =  item.getElementsByTagName("p")[0].innerHTML;
    usuarioSeleccionado.innerHTML =  item.getElementsByTagName("p")[1].innerHTML;
    pistaSeleccionado.innerHTML =  item.getElementsByTagName("p")[2].innerHTML;
    precioSeleccionado.innerHTML =  item.getElementsByTagName("p")[3].innerHTML;

    horaSeleccionado.innerHTML =  item.getElementsByTagName("h4")[0].innerHTML;
    fechaSeleccionado.innerHTML =  item.getElementsByTagName("span")[0].innerHTML;
    nPista.innerHTML = item.getElementsByTagName("p")[4].innerHTML;
  
     
}

function eliminar(item) {
	
	 const nPista2 =document.getElementById("reserva").innerHTML;
	  if (confirm("¿Estas seguro de que quieres eliminar esta pista?")) {
	
  		 fetch(`misPistasServlet?id=${nPista2}`, { method: 'DELETE' })
   		 .then(response =>{ response.json() 
   		  location.reload() })
  		 .then(data => {
     		
   		 })
    	.catch(error => {
      console.error(error);
    });
  
  }
 
}

function ocultarPistasNoDisponibles() {
  // Obtén todos los elementos div que representan a las pistas
  var pistas = document.querySelectorAll('.item');
  
    pistas.forEach(pista => {
  pista.style.display = "";
  });
  
  // Itera sobre cada uno de los elementos
  pistas.forEach(pista => {
    // Obtén el elemento span que contiene el estado de la pista
    var estado = pista.querySelector('.fecha');
    // Si el estado es "mantenimiento", oculta el elemento div de la pista
    if (!estado || estado.innerHTML === "MANTENIMIENTO" ) {
      pista.style.display = "none";
    }
  });
}

function mostrarFamiliares() {
  // Obtén todos los elementos div que representan a las pistas
  var pistas = document.querySelectorAll('.item');
  
    pistas.forEach(pista => {
  pista.style.display = "";
  });
  
  // Itera sobre cada uno de los elementos
  pistas.forEach(pista => {
    // Obtén el elemento span que contiene el estado de la pista
    var fam = pista.querySelector('.nombre');
    // Si el estado es "mantenimiento", oculta el elemento div de la pista
    if ( !(fam.innerHTML === "FAMILIAR") ) {
      pista.style.display = "none";
    }
  });
}

function mostrarAdultos() {
  // Obtén todos los elementos div que representan a las pistas
  var pistas = document.querySelectorAll('.item');
  
    pistas.forEach(pista => {
  pista.style.display = "";
  });
  
  // Itera sobre cada uno de los elementos
  pistas.forEach(pista => {
    // Obtén el elemento span que contiene el estado de la pista
    var fam = pista.querySelector('.nombre');
    // Si el estado es "mantenimiento", oculta el elemento div de la pista
    if ( !(fam.innerHTML === "ADULTOS") ) {
      pista.style.display = "none";
    }
  });
}

function mostrarInfantiles() {
  // Obtén todos los elementos div que representan a las pistas
  var pistas = document.querySelectorAll('.item');
  
  pistas.forEach(pista => {
  pista.style.display = "";
  });
  
  // Itera sobre cada uno de los elementos
  pistas.forEach(pista => {
    // Obtén el elemento span que contiene el estado de la pista
    var fam = pista.querySelector('.nombre');
    // Si el estado es "mantenimiento", oculta el elemento div de la pista
    if ( !(fam.innerHTML === "INFANTIL") ) {
      pista.style.display = "none";
    }
  });
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