package business.Reserva;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import data.Reserva.*;
import data.Usuario.UsuarioDAO;
import business.Reserva.*;
import business.Usuario.UsuarioDTO;

public class GestorReservas {
	
	public GestorReservas() { }
	
	/**
	 * @Resumen Metodo que crea una reserva
	 */
	public void crearReserva(String idUsuario, int minutosReserva, String idPista, TipoReserva tipo, String modalidad, LocalDate fecha, LocalTime hora, int numeroNinos, int numeroAdultos) {
		UsuarioDAO user = new UsuarioDAO();
		UsuarioDTO u = user.buscarUsuario(idUsuario);
		float precioPista;
		if(modalidad != null) {
			precioPista = calcularPrecioReservaBono(minutosReserva);
		}
		else { precioPista = calcularPrecioReservaInd(minutosReserva, u.getFechaInscripcion()); }
		String idReserva = generarIdUnico();
		Reserva reserva = new Reserva(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroNinos, numeroAdultos);
		new ReservaDAO().insertaReserva(reserva);
	}
	
	/**
	 * @Resumen Metodo que modifica una reserva de tipo Familiar
	 */
	public void modificarReserva(String idReserva, String idUsuario, int minutosReserva, int idPista, TipoReserva tipo, String modalidad, LocalDate fecha, LocalTime hora, int numeroNinos, int numeroAdultos) {
		UsuarioDAO user = new UsuarioDAO();
		UsuarioDTO u = user.buscarUsuario(idUsuario);
		float precioPista;
		if(modalidad != null) {
			precioPista = calcularPrecioReservaBono(minutosReserva);
		}
		else { precioPista = calcularPrecioReservaInd(minutosReserva, u.getFechaInscripcion()); }
		Reserva reserva = new Reserva(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroNinos, numeroAdultos);
		new ReservaDAO().modificarReserva(reserva);
	}

	/**
	 * @Resumen Metodo elimina una reserva 
	 * @param id de la reserva
	 */
	public void eliminaReserva(String ID){
		new ReservaDAO().borraReserva(ID);
	}
	
	/**
	 * @Resume Metodo que calcula el precio de la reserva sin bono
	 * @param MinReserva = int
	 * @param fechaInscripcion = LocalDate
	 * @return precio = float
	 */
	public float calcularPrecioReservaInd(int MinReserva , LocalDate fechaInscripcion) {
		
		LocalDate fecha = fechaInscripcion;

		LocalDate hoy = LocalDate.now();

		long antiguedad = ChronoUnit.YEARS.between(fecha, hoy);
		float precio = 0;	

		if(antiguedad>=2) {
			if(MinReserva <= 60){ precio = 18; }
			if(MinReserva > 60 && MinReserva < 120){ precio = 27; }
			if(MinReserva >= 120){ precio = 36; }
		}
		else {
			if(MinReserva <= 60){ precio = 20; }
			if(MinReserva > 60 && MinReserva < 120){ precio = 30; }
			if(MinReserva >= 120){ precio = 40; }
		}
		
		return precio;
	}
	/**
	 * @Resume Metodo que calcula el precio de la reserva con bono
	 * @param MinReserva = int
	 * @return precio = float
	 */
	public float calcularPrecioReservaBono(int MinReserva) {
		
		float precio = 0;
		
		if(MinReserva <= 60){ precio = 19; }
		if(MinReserva >= 90 && MinReserva < 120){ precio = (float)28.5; }
		if(MinReserva >= 120){ precio = 38; }
		
		return precio;
	}
	
	/**
	 * @Resume Metodo que calcula el precio de la reserva sin bono
	 * @param email = email del usuario
	 * @return fecha = LocalDate
	 */
	public String proximaReserva(String email) {
		String fecha = "No tienes reservas proximas";
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();
		int encontrado = 0;
		
		ReservaDAO r = new ReservaDAO();
		ArrayList<Reserva> reservas = r.verReservasUsuario(email);
		int comparador;
		for (Reserva a : reservas) {
			if(encontrado == 0) {
				comparador = hoy.compareTo(a.getFecha());
				if(comparador == 0) {
					//Hoy, comparamos por horas
					if(ahora.compareTo(a.getHora()) < 0) {
						fecha = a.getFecha() + " " + a.getHora();
						encontrado++;
					}
				}
				else if(comparador > 0) {
					//Fecha futura
					fecha = a.getFecha() + " " + a.getHora();
					encontrado++;
				}
			}
		}
		
		return fecha;
	}
	
	/**
	 * Metodo que genera un id unico para cada reserva
	 * @return Id
	 */
	public String generarIdUnico(){
	    double id = Math.random()*99999+1;
	    return String.valueOf(id);
	}
}
