package business.Usuario;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Scanner;

import data.Usuario.UsuarioDAO;
import business.Usuario.UsuarioDTO;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class GestorUsuario {
	
	public GestorUsuario() {};
	
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
	 * @Resumen Imprime en pantalla todos los usuarios y su informacion 
	 */
	public void listarUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioDTO> usuarios = usuarioDAO.listarUsuarios();
		for (UsuarioDTO u : usuarios) {
			System.out.println(u.toString());
		}
	}
	
	/* 
	 * @Resumen Busca un usuario en la lista segun su email
	 * @param email = cadena con el string
	 * @return u = devuelve un usuario
	 * @return null = si no lo encuentra
	 */
	public UsuarioDTO buscarUsuario(String email) {
		UsuarioDTO u = new UsuarioDTO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		u= usuarioDAO.buscarUsuario(email);
		return u;
	}

	/* 
	 * @Resumen Pide los datos para un usuario y lo elimina de la base de datos
	 */
	
	public void eliminarUsuario(String email) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();       
        usuarioDAO.eliminarUsuario(email);
    }
	
	/*
	 * @Resumen Calcula la antiguedad de un usuario
	 * @param email = String
	 */
	public int CalcularAntiguedad(String email) throws ParseException {
		UsuarioDAO user = new UsuarioDAO();
		UsuarioDTO usuario = user.buscarUsuario(email);
		
		LocalDate fechaInscripcion = usuario.getFechaInscripcion();
		LocalDate hoy = LocalDate.now();
		long antiguedad = ChronoUnit.YEARS.between(fechaInscripcion, hoy);
		return (int)antiguedad;
	}
}
