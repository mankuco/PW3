package business.Reserva;

import java.time.*;

public class ReservaInfantilDTO extends Reserva  {
	
	protected int NumeroNinos;
	
	
	/**
	 * Constructor parametrizado
	 */
	public ReservaInfantilDTO(String idReserva, String idUsuario, int minutosReserva, String idPista, float precioPista,
			 TipoReserva tipo, String modalidad , LocalDate fecha, LocalTime hora, int borrado, int numeroNinos) {
		super(idReserva, idUsuario, minutosReserva, precioPista, idPista, fecha, hora, modalidad);
		this.NumeroNinos = numeroNinos;
		this.tipo=TipoReserva.INFANTIL;
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
				+ ", fecha=" + fecha + ", hora=" + hora + ", minutosReserva=" + minutosReserva + ", idPista=" + idPista
				+ ", precioPista=" + precioPista + ", tipo=" + tipo + ", modalidad="
				+ modalidad + "]";
	}

}
