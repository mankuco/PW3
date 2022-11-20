package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import data.ReservaAdultosDAO;
import data.ReservaDAO;
import data.ReservaFamiliarDAO;
import data.ReservaInfatilDAO;


public class GestorReservas {
	
	
	/** Instancia singleton */

	public static GestorReservas instance = null;
	
	
	
	/**
	 * @Resumen Metodo que crea una reserva de tipo Familiar
	 * 
	 * @param idU
	 * @param minR
	 * @param precioP
	 * @param Descuento
	 * @param modalidad
	 * @param numeroNinos
	 * @param numeroAdultos
	 */

	public void crearReservaFamiliar(String idU, int minR, Float precioP, int Descuento , String modalidad,  int numeroNinos, int numeroAdultos,LocalDate fechaYhora  ) {
		ReservaFamiliarDTO reserva = new ReservaFamiliarDTO(idU, minR, precioP, Descuento, modalidad, numeroNinos, numeroAdultos, fechaYhora);
		new ReservaFamiliarDAO().insertaReservaFamiliar(reserva);
	}
	
	/**
	 * 
	 * @Resumen Metodo que crea una reserva de tipo Adulto
	 * 
	 * @param idU
	 * @param minR
	 * @param precioP
	 * @param Descuento
	 * @param modalidad
	 * @param numeroAdultos
	 */
	public void crearReservaAdulto(String idU, int minR, Float precioP, int Descuento , String modalidad, int numeroAdultos,LocalDate fechaYhora) {
		ReservaAdultosDTO reserva = new ReservaAdultosDTO(idU, minR, precioP, Descuento, modalidad, numeroAdultos, fechaYhora);
		new ReservaAdultosDAO().insertaReservaAdultos(reserva);
	}

	/**
	 * @Resumen Metodo que crea una reserva de tipo Infantil
	 * @param idU
	 * @param minR
	 * @param precioP
	 * @param Descuento
	 * @param modalidad
	 * @param numeroNinos
	 */
	
	public void crearReservaInfantil(String idU, int minR, Float precioP, int Descuento , String modalidad, int numeroNinos,LocalDate fechaYhora ) {
		ReservaInfantilDTO reserva = new ReservaInfantilDTO(idU, minR, precioP, Descuento, modalidad, numeroNinos, fechaYhora);
		new ReservaInfatilDAO().insertaReservaInfantil(reserva);
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
	
	
public void modificarReservaFamiliar(String idR, String idU, int minR, Float precioP, int Descuento , String modalidad,  int numeroNinos, int numeroAdultos,LocalDate fechaYhora  ) {
		
		ReservaFamiliarDTO reserva = new ReservaFamiliarDTO(idR, idU, minR, precioP, Descuento, modalidad, numeroNinos, numeroAdultos, fechaYhora);
		new ReservaFamiliarDAO().modificaReservaFamiliar(reserva);
	}
	
/**
 * @Resumen Metodo que modifica una reserva de tipo adulto
 * @param idR
 * @param idU
 * @param minR
 * @param precioP
 * @param Descuento
 * @param modalidad
 * @param numeroAdultos
 */
	public void modificarReservaAdulto(String idR,String idU, int minR, Float precioP, int Descuento , String modalidad, int numeroAdultos,LocalDate fechaYhora ) {
		ReservaAdultosDTO reserva = new ReservaAdultosDTO(idR,idU, minR, precioP, Descuento, modalidad, numeroAdultos, fechaYhora);
		new ReservaAdultosDAO().modificaReservaAdultos(reserva);
		
	}
	
	/**
	 * @Resumen Metodo que modifica una reserva de tipo
	 * @param idR
	 * @param idU
	 * @param minR
	 * @param precioP
	 * @param Descuento
	 * @param modalidad
	 * @param numeroNinos
	 */

	public void modificarReservaInfantil(String idR,String idU, int minR, Float precioP, int Descuento , String modalidad, int numeroNinos,LocalDate fechaYhora ) {
		ReservaInfantilDTO reserva = new ReservaInfantilDTO(idR,idU, minR, precioP, Descuento, modalidad, numeroNinos, fechaYhora);
		new ReservaInfatilDAO().modificaReservaInfantil(reserva);
		
	}
	
	/**
	 * @Resumen Metodo busca una reserva de tipo familiar
	 * @param id de la reserva
	 * @return Reserva familiar
	 */
	
	public ReservaFamiliarDTO buscarReservaF(String id) {
		
		return (new ReservaFamiliarDAO()).buscarReservaF(id);
	}
	

	/**
	 * @Resumen Metodo busca una reserva de tipo adultos
	 * @param id de la reserva
	 * @return Reserva adultos
	 */
	

	public ReservaAdultosDTO buscarReservaA(String id) {
		
		return (new ReservaAdultosDAO()).buscarReservaA(id);
	}


	/**
	 * @Resumen Metodo busca una reserva de tipo infantil
	 * @param id de la reserva
	 * @return Reserva infantil
	 */

	public ReservaInfantilDTO buscarReservaI(String id) {
		
		return (new ReservaInfatilDAO().buscarReservaI(id));
	}

	

	/**
	 * @Resumen Metodo elimina una reserva 
	 * @param id de la reserva
	 */
	
	public  void eliminaReserva( String ID){
		new ReservaDAO().borraReserva(ID);
	}
	

	/**
	 * @Resume Metodo que calcula el precio de la reserva
	 * @param MinReserva
	 * @param fechaInscripcion
	 * @return
	 */
	
	
	public float calcularPrecioReservaInd(int MinReserva , LocalDate fechaInscripcion) {
		
		
		LocalDate fecha =fechaInscripcion;

		LocalDate hoy = LocalDate.now();

		long antiguedad = ChronoUnit.YEARS.between(fecha, hoy);
		float precio = 0;	

		if(antiguedad>=2) {
			
			if(MinReserva<=60){ precio=18 ; }
			if(MinReserva>=90 && MinReserva<120){ precio=27; }
			if(MinReserva>=120 ){ precio= 36 ; }
			
		}else {
		
		if(MinReserva<=60){ precio=20 ; }
		if(MinReserva>=90 && MinReserva<120){ precio=30; }
		if(MinReserva>=120){ precio= 40 ; }
		}
		
		return precio;
	}
	
public float calcularPrecioReservaBono(int MinReserva) {
		
		float precio = 0;
		
		if(MinReserva<=60){ precio=19 ; }
		if(MinReserva>=90 && MinReserva<120){ precio=(float) 28.5; }
		if(MinReserva>=120){ precio= 38 ; }
		
		
		return precio;
	}

public void mostrarReservasFuturas() {
	ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	reservas=new ReservaDAO().verReservas();
	for(Reserva r : reservas) {
		System.out.println(r.toString());
	}
}


public void buscarReservaFechas(LocalDate Fecha) {
	
	ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	reservas=new ReservaDAO().verReservasFecha(Fecha);
	for(Reserva r : reservas) {
		System.out.println(r.toString());
	}
	
	
}
	

	
}
