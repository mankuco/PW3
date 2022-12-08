package display;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String contrasena;
	private LocalDate fechaNacimiento;
	private LocalDate fechaInscripcion;
	private boolean rol;

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

}
