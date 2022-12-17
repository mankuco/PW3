package business.Reserva;

import java.time.LocalDate;

public class BonoReservaDTO {

	private String idBono;
	private String idReserva1;
	private String idReserva2;
	private String idReserva3;
	private String idReserva4;
	private String idReserva5;
	private TipoReserva tipo;
	private LocalDate fecha;
	private String email;
	
	/**
	 * Constructor parametrizado
	 */
	public BonoReservaDTO(String idBono, String idReserva1, String idReserva2, String idReserva3, String idReserva4, String idReserva5, TipoReserva tipo, LocalDate fecha, String email) {
		this.idBono = idBono;
		this.idReserva1 = idReserva1;
		this.idReserva2 = idReserva2;
		this.idReserva3 = idReserva3;
		this.idReserva4 = idReserva4;
		this.idReserva5 = idReserva5;
		this.tipo = tipo;
		this.fecha = fecha;
		this.email = email;
	}

	/**
	 * Getters y setters
	 */
	public String getIdBono() {
		return idBono;
	}
	public void setIdBono(String idBono) {
		this.idBono = idBono;
	}

	public String getIdReserva1() {
		return idReserva1;
	}
	public void setIdReserva1(String idReserva) {
		this.idReserva1 = idReserva;
	}
	
	public String getIdReserva2() {
		return idReserva2;
	}
	public void setIdReserva2(String idReserva) {
		this.idReserva2 = idReserva;
	}
	
	public String getIdReserva3() {
		return idReserva3;
	}
	public void setIdReserva3(String idReserva) {
		this.idReserva3 = idReserva;
	}
	
	public String getIdReserva4() {
		return idReserva4;
	}
	public void setIdReserva4(String idReserva) {
		this.idReserva4 = idReserva;
	}

	public String getIdReserva5() {
		return idReserva5;
	}
	public void setIdReserva5(String idReserva) {
		this.idReserva5 = idReserva;
	}
	
	public TipoReserva getTipo() {
		return tipo;
	}
	public void setTipo(TipoReserva tipo) {
		this.tipo = tipo;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
