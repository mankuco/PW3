package business.Reserva;

import java.time.*;
import java.util.UUID;

public abstract class ReservaDTO {

	protected String idReserva;
	protected String idUsuario;
	protected LocalDate fecha;
	protected LocalTime hora;
	protected int minutosReserva;
	protected int idPista;
	protected float precioPista;
	protected int descuento;
	protected TipoReserva tipo;
	protected String modalidad;
	protected int borrado;
	protected int numeroAdultos;
	protected int numeroNinos;
	
	/**
	 * Constructor parametrizado
	 */
	public ReservaDTO(String idUsuario, int minutosReserva, int idPista, float precioPista, int descuento,
			 TipoReserva tipo, String modalidad , LocalDate fecha, LocalTime hora, int borrado, int numeroNinos, int numeroAdultos) {
				
		this.idReserva = generarIdUnico();
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.hora = hora;
		this.minutosReserva = minutosReserva;
		this.idPista = idPista;
		this.precioPista = precioPista;
		this.descuento = descuento;
		this.tipo = tipo;
		this.modalidad = modalidad;
		this.borrado = borrado;
		this.numeroAdultos = numeroAdultos;
		this.numeroNinos = numeroNinos;
		
	}


	/**
	 * Constructor sin parametros
	 */
	
	public ReservaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que genera un id unico para cada reserva
	 * @return Id
	 */
	
	public String generarIdUnico(){
	    UUID uuid = UUID.randomUUID();
	    return uuid.toString();
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
	public void setFechaYhora(LocalDate fecha) {
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

	public int getIdPista() {
		return idPista;
	}
	public void setIdPista(int idPista) {
		this.idPista = idPista;
	}

	public float getPrecioPista() {
		return precioPista;
	}
	public void setPrecioPista(float precioPista) {
		this.precioPista = precioPista;
	}

	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
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
	
	public int getNumeroNinos() {
		return numeroNinos;
	}
	public void setNumeroNinos(int numeroNinos) {
		this.numeroNinos = numeroNinos;
	}
	
	public int getNumeroAdultos() {
		return numeroAdultos;
	}
	public void setNumeroAdultos(int numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", idUsuario=" + idUsuario + ", fecha=" + fecha + ", hora=" + hora 
				+ ", minutosReserva=" + minutosReserva + ", idPista=" + idPista + ", precioPista=" + precioPista
				+ ", descuento=" + descuento + ", tipo=" + tipo + ", modalidad=" + modalidad + "]";
	}
	
}

