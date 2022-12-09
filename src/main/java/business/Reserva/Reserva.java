package business.Reserva;

import java.time.*;

import java.util.Date;
import java.util.UUID;

public abstract class Reserva {

	protected String idReserva;
	protected String idUsuario;
	protected LocalDate fecha;
	protected LocalTime hora;
	protected int minutosReserva;
	protected String idPista;
	protected float precioPista;
	protected TipoReserva tipo;
	protected String modalidad;
	protected int borrado;
	
	/**
	 * Constructor parametrizado
	 * @param idUsuario
	 * @param minutosReserva
	 * @param precioPista
	 * @param descuento
	 * @param modalidad
	 */
	public Reserva(String idReserva, String idUsuario, int minutosReserva, float precioPista, String idPista,
			 LocalDate fecha, LocalTime hora, String modalidad) {
				
		this.idReserva = idReserva;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.hora = hora;
		this.minutosReserva = minutosReserva;
		this.precioPista = precioPista;
		this.modalidad = modalidad;
		this.borrado = 0;
	}


	/**
	 * Constructor sin parametros
	 */
	
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getters y setters
	 */

	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}
	
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public int getMinutosReserva() {
		return minutosReserva;
	}


	public void setMinutosReserva(int minutosReserva) {
		this.minutosReserva = minutosReserva;
	}


	public String getIdPista() {
		return idPista;
	}


	public void setIdPista(String idPista) {
		this.idPista = idPista;
	}


	public float getPrecioPista() {
		return precioPista;
	}


	public void setPrecioPista(float precioPista) {
		this.precioPista = precioPista;
	}


	public TipoReserva getTipo() {
		return tipo;
	}


	public void setTipo(TipoReserva tipo) {
		this.tipo = tipo;
	}


	public String getModalidad() {
		return modalidad;
	}


	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	
	public String getIdReserva() {
		return idReserva;
	}


	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	
	public int getBorrado() {
		return borrado;
	}


	public void setBorrado(int borrado) {
		this.borrado = borrado;
	}


	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", idUsuario=" + idUsuario + ", fecha=" + fecha + ", hora=" + hora
				+ ", minutosReserva=" + minutosReserva + ", idPista=" + idPista + ", precioPista=" + precioPista
				+ ", tipo=" + tipo + ", modalidad=" + modalidad + "]";
	}


	
	
	
}

