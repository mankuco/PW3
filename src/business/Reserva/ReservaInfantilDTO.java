package business.Reserva;

import java.time.LocalDate;
import java.util.Date;

public class ReservaInfantilDTO extends Reserva  {
	
	protected int NumeroNinos;
	
	
/**
 * Constructor parametrizado
 * 
 * @param idUsuario
 * @param minutosReserva
 * @param precioPista
 * @param descuento
 * @param modalidad
 * @param numeroNinos
 */
	public ReservaInfantilDTO(
			String idUsuario,
			int minutosReserva, 
			float precioPista,
			int descuento,
			String modalidad, 
			int numeroNinos,LocalDate fechaYhora) {
		super(idUsuario, minutosReserva, precioPista, descuento, modalidad, fechaYhora);
		this.NumeroNinos = numeroNinos;
		this.tipo=TipoReserva.INFANTIL;
		this.idPista=03;
	}
	/**
	 * Constructor parametrizado 2
	 * @param idReserva
	 * @param idUsuario
	 * @param minutosReserva
	 * @param precioPista
	 * @param descuento
	 * @param modalidad
	 * @param numeroNinos
	 */

	public ReservaInfantilDTO(String idReserva,
			String idUsuario,
			int minutosReserva, 
			float precioPista,
			int descuento,
			String modalidad, 
			int numeroNinos,LocalDate fechaYhora) {
		super(idUsuario, minutosReserva, precioPista, descuento, modalidad, fechaYhora);
		this.idReserva = idReserva;
		this.NumeroNinos = numeroNinos;
		this.tipo=TipoReserva.INFANTIL;
		this.idPista=03;
	}

	/**
	 * Constructor sin parametros
	 */
	
	public ReservaInfantilDTO() {

	}

	/**
	 * Getters y setters
	 * 
	 */

	public int getNumeroNinos() {
		return NumeroNinos;
	}

	public void setNumeroNinos(int numeroNinos) {
		NumeroNinos = numeroNinos;
	}

	@Override
	public String toString() {
		return "ReservaInfantil [NumeroNinos=" + NumeroNinos + ", idReserva=" + idReserva + ", idUsuario=" + idUsuario
				+ ", fechaYhora=" + fechaYhora + ", minutosReserva=" + minutosReserva + ", idPista=" + idPista
				+ ", precioPista=" + precioPista + ", descuento=" + descuento + ", tipo=" + tipo + ", modalidad="
				+ modalidad + "]";
	}

	
	
	

}
