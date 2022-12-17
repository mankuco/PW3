package business.Reserva;

import java.time.LocalTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Properties;

import data.Reserva.*;
import data.Usuario.UsuarioDAO;
import business.Usuario.UsuarioDTO;

public class GestorReservas {
	
	public GestorReservas() { }
	
	/**
	 * @Resumen Metodo que crea una reserva
	 */
	public void crearReserva(String idUsuario, int minutosReserva, String idPista, TipoReserva tipo, String modalidad, LocalDate fecha, LocalTime hora, int numeroNinos, int numeroAdultos, Properties prop, String jdbc, String dbuser, String dbpass) {
		UsuarioDAO user = new UsuarioDAO(prop, jdbc, dbuser, dbpass);
		UsuarioDTO u = user.buscarUsuario(idUsuario);
		float precioPista;
		if(modalidad != null) {
			precioPista = calcularPrecioReservaBono(minutosReserva);
		}
		else { precioPista = calcularPrecioReservaInd(minutosReserva, u.getFechaInscripcion()); }
		String idReserva = generarIdUnico();
		
		if(tipo == TipoReserva.FAMILIAR) {
			ReservaFamiliarDTO reserva = new ReservaFamiliarDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroNinos, numeroAdultos);
			new ReservaFamiliarDAO(prop, jdbc, dbuser, dbpass).insertaReserva(reserva);
		}
		else if(tipo == TipoReserva.ADULTOS) {
			ReservaAdultosDTO reserva = new ReservaAdultosDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroAdultos);
			new ReservaAdultosDAO(prop, jdbc, dbuser, dbpass).insertaReserva(reserva);
		}
		else {
			ReservaInfantilDTO reserva = new ReservaInfantilDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroNinos);
			new ReservaInfatilDAO(prop, jdbc, dbuser, dbpass).insertaReserva(reserva);
		}
	}
	
	/**
	 * @Resumen Metodo que modifica una reserva de tipo Familiar
	 */
	public void modificarReserva(String idReserva, String idUsuario, int minutosReserva, String idPista, TipoReserva tipo, String modalidad, LocalDate fecha, LocalTime hora, int numeroNinos, int numeroAdultos, Properties prop, String jdbc, String dbuser, String dbpass) {
		UsuarioDAO user = new UsuarioDAO(prop, jdbc, dbuser, dbpass);
		UsuarioDTO u = user.buscarUsuario(idUsuario);
		float precioPista;
		if(modalidad != null) {
			precioPista = calcularPrecioReservaBono(minutosReserva);
		}
		else { precioPista = calcularPrecioReservaInd(minutosReserva, u.getFechaInscripcion()); }
		
		if(tipo == TipoReserva.FAMILIAR) {
			ReservaFamiliarDTO reserva = new ReservaFamiliarDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroNinos, numeroAdultos);
			new ReservaFamiliarDAO(prop, jdbc, dbuser, dbpass).modificarReserva(reserva);
		}
		else if(tipo == TipoReserva.ADULTOS) {
			ReservaAdultosDTO reserva = new ReservaAdultosDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroAdultos);
			new ReservaAdultosDAO(prop, jdbc, dbuser, dbpass).modificarReserva(reserva);
		}
		else {
			ReservaInfantilDTO reserva = new ReservaInfantilDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipo, modalidad , fecha, hora, 0, numeroNinos);
			new ReservaInfatilDAO(prop, jdbc, dbuser, dbpass).modificarReserva(reserva);
		}
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
	public String proximaReserva(String email, Properties prop, String jdbc, String dbuser, String dbpass) {
		String fecha = "No tienes reservas proximas";
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();
		
		ReservaDAO r = new ReservaDAO(prop, jdbc, dbuser, dbpass);
		ArrayList<Reserva> reservas = r.verReservasUsuario(email);
		ArrayList<Reserva> validas = new ArrayList<Reserva>();
		int comparador;
		for (Reserva a : reservas) {
			comparador = hoy.compareTo(a.getFecha());
			if(comparador == 0) {
				//Hoy, comparamos por horas
				if(ahora.compareTo(a.getHora()) < 0) {
					validas.add(a);
				}
			}
			else if(comparador < 0) {
				//Fecha futura
				validas.add(a);
			}
		}
		if(!reservas.isEmpty()) {
			Reserva reserva = reservas.get(0);
			for(Reserva b : validas) {
				comparador = (reserva.getFecha()).compareTo(b.getFecha());
				if(comparador == 0) {
					if((reserva.getHora()).compareTo(b.getHora()) > 0) {
						reserva = b;
					}
				}
				else if(comparador > 0) {
					reserva = b;
				}
			}
			fecha = reserva.getFecha() + " " + reserva.getHora();
		}

		return fecha;
	}
	
	/**
	 * @Resume Metodo que calcula el numero de reservas completadas
	 * @param email = email del usuario
	 * @return N = int
	 */
	public int reservasCompletadas(String email, Properties prop, String jdbc, String dbuser, String dbpass) {
		int N = 0;
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();
		
		ReservaDAO r = new ReservaDAO(prop, jdbc, dbuser, dbpass);
		ArrayList<Reserva> reservas = r.verReservasUsuario(email);
		int comparador;
		for (Reserva a : reservas) {
			comparador = hoy.compareTo(a.getFecha());
			if(comparador == 0) {
				//Hoy, comparamos por horas
				if(ahora.compareTo(a.getHora()) >= 0) {
					N++;
				}
			}
			else if(comparador > 0) {
				//Fecha pasada
				N++;
			}
		}
		
		return N;
	}
	
	/**
	 * @Resumen Metodo que crea bono
	 */
	public boolean crearBono(String r1, String r2, String r3, String r4, String r5, TipoReserva tipo, LocalDate fecha, String email, Properties prop, String jdbc, String dbuser, String dbpass) {
		BonoDAO dao = new BonoDAO(prop, jdbc, dbuser, dbpass);
		ArrayList<BonoReservaDTO> lista = dao.listarBonosUsuario(email);
		if(lista != null) {
			for(BonoReservaDTO a : lista) {
				if(a.getIdReserva5() != null) {
					return false;
				}
			}
		}
		BonoReservaDTO bono = new BonoReservaDTO(generarIdUnico(),r1,r2,r3,r4,r5,tipo,fecha,email);
		dao.insertar(bono);
		return true;
	}
	
	/**
	 * @Resumen Modifica un bono
	 */
	public boolean modificarBono(String id, String r1, String r2, String r3, String r4, String r5, TipoReserva tipo, LocalDate fecha, String email, Properties prop, String jdbc, String dbuser, String dbpass) {
		BonoDAO dao = new BonoDAO(prop, jdbc, dbuser, dbpass);
		ArrayList<BonoReservaDTO> lista = dao.listarBonosUsuario(email);
		if(lista != null) {
			for(BonoReservaDTO a : lista) {
				if(a.getIdReserva5() != null) {
					return false;
				}
			}
		}
		BonoReservaDTO bono = new BonoReservaDTO(id,r1,r2,r3,r4,r5,tipo,fecha,email);
		dao.insertar(bono);
		return true;
	}
	
	/**
	 * @Resumen Devuelve una lista con todos los bonos de un usuario
	 */
	public ArrayList<BonoReservaDTO> listarBonosUsuario(String email, Properties prop, String jdbc, String dbuser, String dbpass) {
		BonoDAO dao = new BonoDAO(prop, jdbc, dbuser, dbpass);
		ArrayList<BonoReservaDTO> lista = dao.listarBonosUsuario(email);
		return lista;
	}
	
	/**
	 * @Resumen Devuelve un bono si existe
	 */
	public BonoReservaDTO buscabono(String id, Properties prop, String jdbc, String dbuser, String dbpass) {
		BonoDAO dao = new BonoDAO(prop, jdbc, dbuser, dbpass);
		BonoReservaDTO bono = dao.existeBono(id);
		return bono;
	}
	
	/**
	 * @Resumen Devuelve un el bono activo de un usuario si lo tiene
	 */
	public BonoReservaDTO bonoActivo(String email, Properties prop, String jdbc, String dbuser, String dbpass) {
		BonoDAO dao = new BonoDAO(prop, jdbc, dbuser, dbpass);
		BonoReservaDTO bono = dao.bonoActivo(email);
		return bono;
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
