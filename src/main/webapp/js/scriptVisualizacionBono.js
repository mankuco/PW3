let mostrador = document.getElementById("mostrador");
let seleccion = document.getElementById("seleccion");
let reserva1Seleccionado = document.getElementById("reserva1");
let reserva4Seleccionado = document.getElementById("reserva4");
let reserva2Seleccionado = document.getElementById("reserva2");
let reserva3Seleccionado = document.getElementById("reserva3");
let reserva5Seleccionado = document.getElementById("reserva5");
let usuarioSeleccionado = document.getElementById("usuario");
let fechaSeleccionado = document.getElementById("fechaVencida");



function cargar(item){
    quitarBordes();
    mostrador.style.width = "60%";
    seleccion.style.width = "40%";
    seleccion.style.opacity = "1";
    item.style.border = "2px solid red";

    reserva1Seleccionado.innerHTML =  item.getElementsByTagName("p")[0].innerHTML;
    reserva2Seleccionado.innerHTML =  item.getElementsByTagName("p")[0].innerHTML;
    reserva3Seleccionado.innerHTML =  item.getElementsByTagName("p")[0].innerHTML;
    reserva4Seleccionado.innerHTML =  item.getElementsByTagName("p")[0].innerHTML;
    reserva5Seleccionado.innerHTML =  item.getElementsByTagName("p")[0].innerHTML;
    usuarioSeleccionado.innerHTML =  item.getElementsByTagName("p")[1].innerHTML;
    fechaSeleccionado.innerHTML =  item.getElementsByTagName("span")[0].innerHTML;
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