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
	 * @Resumen Pide los datos para un nuevo usuario y lo a�ade a la lista
	 */
	
	public void altaUsuario () {
		Scanner esc = new Scanner(System.in);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioDTO nuevoUsuario = new UsuarioDTO();
		System.out.println("Introduzca el email");
		String nuevoEmail = esc.nextLine();
		while (usuarioDAO.existeUsuario(nuevoEmail)) {
            System.out.println("Usuario ya registrado.");
            System.out.println("Introduzca el email");
            nuevoEmail = esc.nextLine();
        }
		System.out.println("Introduzca el nombre");
		String nuevoNombre = esc.nextLine();
		System.out.println("Introduzca los apellidos");
		String nuevoApellidos = esc.nextLine();
		System.out.println("Introduzca la fecha de nacimiento (AAAA-MM-DD)");
		String nuevaFechaNacimiento = esc.nextLine();
		nuevoUsuario.setFechaInscripcion(LocalDate.now());
		nuevoUsuario.setEmail(nuevoEmail);
		nuevoUsuario.setNombre(nuevoNombre);
		nuevoUsuario.setApellidos(nuevoApellidos);
		nuevoUsuario.setFechaNacimiento(convertirFechas(nuevaFechaNacimiento));
		usuarioDAO.guardarUsuario(nuevoUsuario);
		System.out.println("Usuario registrado correctamente.");
		
	}


	
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
     * @Resumen Pide los datos para un usuario y lo modifica en la base de datos
     */

    public void modificarUsuario () {
        Scanner esc = new Scanner(System.in);
        GestorUsuario g = new GestorUsuario();
        UsuarioDTO u = new UsuarioDTO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO nuevoUsuario = new UsuarioDTO();
        System.out.println("Introduzca el email del usuario a modificar");
        String nuevoEmail = esc.nextLine();
        while (!usuarioDAO.existeUsuario(nuevoEmail)) {
            System.out.println("Usuario no registrado.");
            System.out.println("Introduzca el email");
            nuevoEmail = esc.nextLine();
        }

                u = g.buscarUsuario(nuevoEmail);
                System.out.println("Introduzca el nuevo nombre");
                String nuevoNombre = esc.nextLine();
                System.out.println("Introduzca los nuevos apellidos");
                String nuevoApellidos = esc.nextLine();
                System.out.println("Introduzca la nueva fecha de nacimiento (AAAA-MM-DD)");
                String nuevaFechaNacimiento = esc.nextLine();
                nuevoUsuario.setFechaInscripcion(u.getFechaInscripcion());
                nuevoUsuario.setEmail(nuevoEmail);
                nuevoUsuario.setNombre(nuevoNombre);
                nuevoUsuario.setApellidos(nuevoApellidos);
                nuevoUsuario.setFechaNacimiento(convertirFechas(nuevaFechaNacimiento));
                usuarioDAO.modificarUsuario(nuevoUsuario);
                System.out.println("Usuario modificado correctamente.");

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
