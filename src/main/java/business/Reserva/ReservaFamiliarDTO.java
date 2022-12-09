package business.Reserva;

import java.time.*;

public class ReservaFamiliarDTO extends Reserva {
	
	protected int NumeroNinos;
	protected int NumeroAdultos;
	
	/**
	 * Constructor parametrizado
	 */
	public ReservaFamiliarDTO(String idReserva, String idUsuario, int minutosReserva, String idPista, float precioPista,
			 TipoReserva tipo, String modalidad , LocalDate fecha, LocalTime hora, int borrado, int numeroAdultos, int numeroNinos) {
		super(idReserva, idUsuario, minutosReserva, precioPista, idPista, fecha, hora, modalidad);
		this.NumeroAdultos = numeroAdultos;
		this.NumeroNinos = numeroNinos;
		this.tipo=TipoReserva.FAMILIAR;
	}

	/**
	 * Constructor 
	 */
	public ReservaFamiliarDTO() { }

	/**
	 * Getters y setters
	 */
	public int getNumeroNinos() {
		return NumeroNinos;
	}
	public void setNumeroNinos(int numeroNinos) {
		NumeroNinos = numeroNinos;
	}

	public int getNumeroAdultos() {
		return NumeroAdultos;
	}
	public void setNumeroAdultos(int numeroAdultos) {
		NumeroAdultos = numeroAdultos;
	}	
	
}
