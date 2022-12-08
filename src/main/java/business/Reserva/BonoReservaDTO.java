package business.Reserva;

public class BonoReservaDTO {

	private int idBono;
	private int idReserva1;
	private int idReserva2;
	private int idReserva3;
	private int idReserva4;
	private int idReserva5;
	
	/**
	 * Constructor parametrizado
	 */
	public BonoReservaDTO(int idBono, int idReserva1, int idReserva2, int idReserva3, int idReserva4, int idReserva5) {
		this.idBono = idBono;
		this.idReserva1 = idReserva1;
		this.idReserva2 = idReserva2;
		this.idReserva3 = idReserva3;
		this.idReserva4 = idReserva4;
		this.idReserva5 = idReserva5;
	}

	/**
	 * Getters y setters
	 */
	public int getIdBono() {
		return idBono;
	}
	public void setIdBono(int idBono) {
		this.idBono = idBono;
	}

	public int getIdReserva1() {
		return idReserva1;
	}
	public void setIdReserva1(int idReserva) {
		this.idReserva1 = idReserva;
	}
	
	public int getIdReserva2() {
		return idReserva2;
	}
	public void setIdReserva2(int idReserva) {
		this.idReserva2 = idReserva;
	}
	
	public int getIdReserva3() {
		return idReserva3;
	}
	public void setIdReserva31(int idReserva) {
		this.idReserva3 = idReserva;
	}
	
	public int getIdReserva4() {
		return idReserva4;
	}
	public void setIdReserva4(int idReserva) {
		this.idReserva4 = idReserva;
	}

	public int getIdReserva5() {
		return idReserva5;
	}
	public void setIdReserva5(int idReserva) {
		this.idReserva5 = idReserva;
	}

	@Override
	public String toString() {
		return "BonoReserva [idBono=" + idBono + ", idReserva1=" + idReserva1 + ", idReserva2=" + idReserva2 + ", idReserva3=" + idReserva3 + ", idReserva4=" + idReserva4 + ", idReserva5=" + idReserva5 + "]";
	}
	
}
