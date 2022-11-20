package business;

import java.time.LocalDate;
import java.util.Date;

public class ReservaAdultosDTO extends Reserva {
	
	protected int NumeroAdultos;

	/**
	 * Constructor parametrizado
	 * 
	 * @param idUsuario
	 * @param minutosReserva
	 * @param precioPista
	 * @param descuento
	 * @param modalidad
	 * @param numeroAdultos
	 */
	
	public ReservaAdultosDTO(
			String idUsuario,
			int minutosReserva, 
			float precioPista,
			int descuento,
			String modalidad, 
			int numeroAdultos,LocalDate fechaYhora) {
		super(idUsuario, minutosReserva, precioPista, descuento, modalidad, fechaYhora);
		this.NumeroAdultos = numeroAdultos;
		this.tipo=TipoReserva.ADULTOS;
		this.idPista=02;
	}
	
	/**
	 * Constructor parametrizado2
	 * 
	 * @param idReserva
	 * @param idUsuario
	 * @param minutosReserva
	 * @param precioPista
	 * @param descuento
	 * @param modalidad
	 * @param numeroAdultos
	 */
	
	public ReservaAdultosDTO(String idReserva,
			String idUsuario,
			int minutosReserva, 
			float precioPista,
			int descuento,
			String modalidad, 
			int numeroAdultos,LocalDate fechaYhora) {
		super(idUsuario, minutosReserva, precioPista, descuento, modalidad, fechaYhora);
		this.idReserva=idReserva;
		this.NumeroAdultos = numeroAdultos;
		this.tipo=TipoReserva.ADULTOS;
		this.idPista=02;
	}

	/**
	 * Constructor sin parametros
	 */

    public ReservaAdultosDTO() {

    }

    /**
     * Getters y setters
     * @return
     */
    
	public int getNumeroAdultos() {
		return NumeroAdultos;
	}



	public void setNumeroAdultos(int numeroAdultos) {
		NumeroAdultos = numeroAdultos;
	}

	@Override
	public String toString() {
		return "ReservaAdultos [NumeroAdultos=" + NumeroAdultos + ", idReserva=" + idReserva + ", idUsuario="
				+ idUsuario + ", fechaYhora=" + fechaYhora + ", minutosReserva=" + minutosReserva + ", idPista="
				+ idPista + ", precioPista=" + precioPista + ", descuento=" + descuento + ", tipo=" + tipo
				+ ", modalidad=" + modalidad + "]";
	}

	
	
	
	
}
