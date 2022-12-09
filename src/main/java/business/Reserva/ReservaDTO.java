package business.Reserva;

import java.io.Serializable;
import java.time.*;


public class ReservaDTO extends Reserva implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor parametrizado
	 */
	public ReservaDTO(String idReserva, String idUsuario, int minutosReserva, float precioPista, String idPista, int descuento,
			 String modalidad, LocalDate fecha, LocalTime hora, int borrado) {
		super(idReserva, idUsuario, minutosReserva, precioPista, idPista, fecha, hora, modalidad);
	}
	
}
