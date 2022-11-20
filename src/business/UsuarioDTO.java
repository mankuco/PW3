package business;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
public class UsuarioDTO {
	
	
	private String nombre;
	private String apellidos;
	private String email;
	private LocalDate fechaNacimiento;
	private LocalDate fechaInscripcion;
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param fechaNacimiento
	 * @param fechaInscripcion
	 */
	public UsuarioDTO(String nombre, String apellidos, String email, LocalDate fechaNacimiento, LocalDate fechaInscripcion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaInscripcion = fechaInscripcion;
	}
	
		
	/**
	 * Constructor sin parametrizar
	 */
	
	public UsuarioDTO() {
		super();
		
	}
	
	/**
	 * Getters y setters
	 * 
	 */

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public long CalcularAntiguedad() throws ParseException {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate fecha = this.fechaNacimiento;

		LocalDate hoy = LocalDate.now();

		long antiguedad = ChronoUnit.YEARS.between(fecha, hoy);

		return antiguedad;
	}


	

	
	
	@Override
	public String toString() {
		return "Usuario [ nombre=" + nombre + ", apellidos=" + apellidos + ", email="
				+ email + ", fechaNacimiento=" + fechaNacimiento + ", fechaInscripcion=" + fechaInscripcion + "]";
	}



}