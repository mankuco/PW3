package business;

import java.time.LocalDate;
import java.util.Date;

public class ReservaFamiliarDTO extends Reserva {
	
	protected int NumeroNinos;
	protected int NumeroAdultos;
	
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param idUsuario
	 * @param minutosReserva
	 * @param precioPista
	 * @param descuento
	 * @param modalidad
	 * @param numeroNinos
	 * @param numeroAdultos
	 */

	public ReservaFamiliarDTO(String idUsuario, int minutosReserva, float precioPista, int descuento,
			String modalidad, int numeroNinos, int numeroAdultos, LocalDate fechaYhora) {
		super(idUsuario, minutosReserva, precioPista, descuento, modalidad,fechaYhora);
		NumeroNinos = numeroNinos;
		NumeroAdultos = numeroAdultos; 
		this.tipo=TipoReserva.FAMILIAR;
		this.idPista=01;
		
	}
	
	/**
	 * Constructor parametrizado 2
	 * 
	 * @param idR
	 * @param idUsuario
	 * @param minutosReserva
	 * @param precioPista
	 * @param descuento
	 * @param modalidad
	 * @param numeroNinos
	 * @param numeroAdultos
	 */
	
	public ReservaFamiliarDTO(String idR, String idUsuario, int minutosReserva, float precioPista, int descuento,
			String modalidad, int numeroNinos, int numeroAdultos,LocalDate fechaYhora) {
		super(idUsuario, minutosReserva, precioPista, descuento, modalidad, fechaYhora);
		this.idReserva = idR;
		NumeroNinos = numeroNinos;
		NumeroAdultos = numeroAdultos;
		this.tipo=TipoReserva.FAMILIAR;
		this.idPista=01;
	}


/**
 * Constructor 
 */
	public ReservaFamiliarDTO() {

	}

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
