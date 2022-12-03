package business.Reserva;

import java.io.Serializable;
import java.time.LocalDate;


public class ReservaDTO extends Reserva implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor parametrizado
	 * 
	 * @param idUsuario
	 * @param minutosReserva
	 * @param precioPista
	 * @param descuento
	 * @param modalidad
	 */
	public ReservaDTO(String idUsuario, int minutosReserva, float precioPista, int descuento,
			 String modalidad, LocalDate fechaYhora, int borrado) {
		super(idUsuario,minutosReserva,precioPista,descuento,modalidad,fechaYhora,borrado);
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
	 * @param numeroAdultos
	 */

	public ReservaDTO(String idReserva, String idUsuario, int minutosReserva, float precioPista, int descuento,
			String modalidad, int numeroNinos, int numeroAdultos) {
		// TODO Auto-generated constructor stub
	}
}
