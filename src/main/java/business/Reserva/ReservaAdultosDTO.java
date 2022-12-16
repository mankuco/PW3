package business.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaAdultosDTO extends Reserva {
	
	protected int NumeroAdultos;

	/**
	 * Constructor parametrizado
	 */
	public ReservaAdultosDTO(String idReserva, String idUsuario, int minutosReserva, String idPista, float precioPista,
			 TipoReserva tipo, String modalidad , LocalDate fecha, LocalTime hora, int borrado, int numeroAdultos) {
		super(idReserva, idUsuario, minutosReserva, precioPista, idPista, fecha, hora, modalidad);
		this.NumeroAdultos = numeroAdultos;
		this.tipo=TipoReserva.ADULTOS;
	}
	
	/**
	 * Constructor sin parametros
	 */
    public ReservaAdultosDTO() { }

    /**
     * Getters y setters
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
				+ idUsuario + ", fecha=" + fecha + ", minutosReserva=" + minutosReserva + ", idPista="
				+ idPista + ", precioPista=" + precioPista + ", tipo=" + tipo
				+ ", modalidad=" + modalidad + "]";
	}

	
	
	
	
}
