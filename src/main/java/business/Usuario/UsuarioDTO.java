package business.Usuario;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class UsuarioDTO {
	
	
	private String nombre;
	private String apellidos;
	private String email;
	private String contrasena;
	private LocalDate fechaNacimiento;
	private LocalDate fechaInscripcion;
	private boolean rol; //true=cliente false=administrador
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param contrasena
	 * @param fechaNacimiento
	 * @param fechaInscripcion
	 * @param rol
	 */
	public UsuarioDTO(String nombre, String apellidos, String email, String contrasena, LocalDate fechaNacimiento, LocalDate fechaInscripcion, boolean rol) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaInscripcion = fechaInscripcion;
		this.rol = rol;
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
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
	public boolean getRol() {
		return rol;
	}
	public void setRol(boolean rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return "Usuario [ nombre=" + nombre + ", apellidos=" + apellidos + ", email="
				+ email + ", fechaNacimiento=" + fechaNacimiento + ", fechaInscripcion=" + fechaInscripcion + ", rol=" + rol + "]";
	}

}