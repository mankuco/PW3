package business.Reserva;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import data.Reserva.ReservaDAO;
import business.Reserva.ReservaDTO;

public class GestorReservas {
	
	/** Instancia singleton */
	public static GestorReservas instance = null;
	
	/**
	 * @Resumen Metodo que crea una reserva
	 * 
	 * @param idU
	 * @param minR
	 * @param precioP
	 * @param Descuento
	 * @param modalidad
	 * @param numeroNinos
	 * @param numeroAdultos
	 */
	public void crearReserva(String idUsuario, int minutosReserva, int idPista, float precioPista, int descuento, TipoReserva tipo, String modalidad, LocalDateTime fechaYhora, int borrado, int numeroNinos, int numeroAdultos) {
		ReservaDTO reserva = new ReservaDTO(idUsuario, minutosReserva, idPista, precioPista, descuento, tipo, modalidad, fechaYhora, borrado, numeroNinos, numeroAdultos);
		new ReservaDAO().insertaReserva(reserva);
	}
	
	/**
	 * @Resumen Metodo que modifica una reserva de tipo Familiar
	 * @param idR
	 * @param idU
	 * @param minR
	 * @param precioP
	 * @param Descuento
	 * @param modalidad
	 * @param numeroNinos
	 * @param numeroAdultos
	 */
	public void modificarReserva(String idUsuario, int minutosReserva, int idPista, float precioPista, int descuento, TipoReserva tipo, String modalidad, LocalDateTime fechaYhora, int borrado, int numeroNinos, int numeroAdultos) {
		ReservaDTO reserva = new ReservaDTO(idUsuario, minutosReserva, idPista, precioPista, descuento, tipo, modalidad, fechaYhora, borrado, numeroNinos, numeroAdultos);
		new ReservaDAO().modificarReserva(reserva);
	}

	/**
	 * @Resumen Metodo elimina una reserva 
	 * @param id de la reserva
	 */
	public  void eliminaReserva(String ID){
		new ReservaDAO().borraReserva(ID);
	}
	
	/**
	 * @Resume Metodo que calcula el precio de la reserva
	 * @param MinReserva
	 * @param fechaInscripcion
	 * @return
	 */
	public float calcularPrecioReservaInd(int MinReserva , LocalDate fechaInscripcion) {
		
		LocalDate fecha = fechaInscripcion;

		LocalDate hoy = LocalDate.now();

		long antiguedad = ChronoUnit.YEARS.between(fecha, hoy);
		float precio = 0;	

		if(antiguedad>=2) {
			if(MinReserva <= 60){ precio = 18; }
			if(MinReserva >= 90 && MinReserva < 120){ precio = 27; }
			if(MinReserva >= 120){ precio = 36; }
		}
		else {
			if(MinReserva <= 60){ precio = 20; }
			if(MinReserva >= 90 && MinReserva < 120){ precio = 30; }
			if(MinReserva >= 120){ precio = 40; }
		}
		
		return precio;
	}
	
	public float calcularPrecioReservaBono(int MinReserva) {
		
		float precio = 0;
		
		if(MinReserva <= 60){ precio = 19; }
		if(MinReserva >= 90 && MinReserva < 120){ precio = (float)28.5; }
		if(MinReserva >= 120){ precio = 38; }
		
		return precio;
	}
	
}
