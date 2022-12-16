package business.Usuario;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import data.Usuario.UsuarioDAO;

public class GestorUsuario {
	
	/* 
	 * @Resumen Introduces una cadena, que contiene una fecha y la convierte al tipo LocalDate
	 * @param fecha = cadena con la fecha
	 * @return fechaDate = LocalDate con la fecha
	 */
	public LocalDate convertirFechas(String fecha){
		LocalDate fechaDate = null;
		fechaDate = LocalDate.parse(fecha);
		return fechaDate;
	}
	
	/*
	 * @Resumen Calcula la antiguedad de un usuario
	 * @param email = String
	 */
	public int CalcularAntiguedad(String email, Properties prop, String jdbc, String dbuser, String dbpass) throws ParseException {
		UsuarioDAO user = new UsuarioDAO(prop, jdbc, dbuser, dbpass);
		UsuarioDTO usuario = user.buscarUsuario(email);
		
		LocalDate fechaInscripcion = usuario.getFechaInscripcion();
		LocalDate hoy = LocalDate.now();
		long antiguedad = ChronoUnit.YEARS.between(fechaInscripcion, hoy);
		return (int)antiguedad;
	}
}
