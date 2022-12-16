package data.Reserva;

import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.*;
import java.util.ArrayList;
import java.util.Properties;

import business.Reserva.*;
import data.DAO;

/**
 * Clase que accede a la base de datos de Reservas
 */

public class ReservaDAO extends DAO {
	
	/**
	 * Constructor 
	 */
	public ReservaDAO(Properties prop, String jdbc, String dbuser, String dbpass) {
		super(prop, jdbc, dbuser, dbpass);
	}
	
	/* 
	 * @Resumen Introduce en la base de datos la reserva 
	 * @Param Reserva 
	 */
	
	public String insertaReserva(Reserva r) {
		String id =r.getIdReserva();
		
		try{
			PreparedStatement ps = con.prepareStatement(prop.getProperty("inserta-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdReserva());
			ps.setString(2,r.getIdUsuario());
			ps.setInt(3,r.getMinutosReserva());
			ps.setFloat(4, r.getPrecioPista());
			ps.setString(5, r.getModalidad());
			ps.setString(6, r.getIdPista());
			ps.setNull(7, Types.NULL);
			ps.setNull(8, Types.NULL );
			ps.setDate(9, Date.valueOf(r.getFecha()));
			ps.setTime(10, Time.valueOf(r.getHora()));
			ps.setString(11, r.getTipo().toString());
			ps.executeUpdate();
			
		}
		catch(SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return id;
	}

	/* 
	 * @Resumen Eliminade la base de datos una reserva 
	 * @Param id de la Reserva 
	 */
	
	public void borraReserva(String id ) {
		
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(prop.getProperty("elimina-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,id);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		
	}

	/* 
	 * @Resumen Muestra las reservas futuras de la base de datos
	 * @Return ArrayList de reservas
	 */
	
	public ArrayList<Reserva> verReservas() {
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		ReservaFamiliarDTO rf= null;
		ReservaAdultosDTO ra= null;
		ReservaInfantilDTO ri= null;
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(prop.getProperty("ver-reservas"), PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idReserva = rs.getString(1);
				String idUsuario = rs.getString(2);
				LocalDate fecha = rs.getDate(8).toLocalDate();
				LocalTime hora = rs.getTime(11).toLocalTime();
				int minutosReserva = rs.getInt(3);
				String idPista = rs.getString(5);
				float precioPista = rs.getFloat(4);	
				int numeroNinos = rs.getInt(6);
				int numeroAdultos = rs.getInt(7);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(9));
				String modalidad = rs.getString(12);	
				 
				if(tipoReserva == TipoReserva.FAMILIAR) {
					 rf = new ReservaFamiliarDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos, numeroNinos);
					 reservas.add(rf);	 
				}
				if(tipoReserva == TipoReserva.INFANTIL) {
					 ri = new  ReservaInfantilDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroNinos);
						reservas.add(ri);	 
				}	
				if(tipoReserva == TipoReserva.ADULTOS) {
					 ra = new  ReservaAdultosDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos);
					 reservas.add(ra);	 
				}
			}
		
		}
		catch (SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return reservas;
	}

	/* 
	 * @Resumen Muestra las reservas de una fecha en concreto de la base de datos
	 * @Return ArrayList de reservas
	 */
	
	public ArrayList<Reserva> verReservasFecha(LocalDate Fecha) {
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		ReservaFamiliarDTO rf= null;
		ReservaAdultosDTO ra= null;
		ReservaInfantilDTO ri= null;
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(prop.getProperty("ver-reservas-fecha"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,LocalDate.now().toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idReserva = rs.getString(1);
				String idUsuario = rs.getString(2);
				LocalDate fecha = rs.getDate(8).toLocalDate();
				LocalTime hora = rs.getTime(11).toLocalTime();
				int minutosReserva = rs.getInt(3);
				String idPista = rs.getString(5);
				float precioPista = rs.getFloat(4);	
				int numeroNinos = rs.getInt(6);
				int numeroAdultos = rs.getInt(7);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(9));
				String modalidad = rs.getString(12);	
				 
				if(tipoReserva == TipoReserva.FAMILIAR) {
					 rf = new ReservaFamiliarDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos, numeroNinos);
					 reservas.add(rf);	 
				}
				if(tipoReserva == TipoReserva.INFANTIL) {
					 ri = new  ReservaInfantilDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroNinos);
						reservas.add(ri);	 
				}	
				if(tipoReserva == TipoReserva.ADULTOS) {
					 ra = new  ReservaAdultosDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos);
					 reservas.add(ra);	 
				}
			}
		
		}
		catch (SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return reservas;
		
	}



	public ArrayList<Reserva> verReservasUsuario(String IdUsuario) {
	
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		ReservaFamiliarDTO rf= null;
		ReservaAdultosDTO ra= null;
		ReservaInfantilDTO ri= null;
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(prop.getProperty("ver-reservas-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,IdUsuario);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idReserva = rs.getString(1);
				String idUsuario = rs.getString(2);
				LocalDate fecha = rs.getDate(8).toLocalDate();
				LocalTime hora = rs.getTime(11).toLocalTime();
				int minutosReserva = rs.getInt(3);
				String idPista = rs.getString(5);
				float precioPista = rs.getFloat(4);	
				int numeroNinos = rs.getInt(6);
				int numeroAdultos = rs.getInt(7);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(9));
				String modalidad = rs.getString(12);	
				 
				if(tipoReserva == TipoReserva.FAMILIAR) {
					 rf = new ReservaFamiliarDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos, numeroNinos);
					 reservas.add(rf);	 
				}
				if(tipoReserva == TipoReserva.INFANTIL) {
					 ri = new  ReservaInfantilDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroNinos);
						reservas.add(ri);	 
				}	
				if(tipoReserva == TipoReserva.ADULTOS) {
					 ra = new  ReservaAdultosDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos);
					 reservas.add(ra);	 
				}
			}
		
		}
		catch (SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return reservas;
		
	}

	public Reserva getreserva(String id) {
		
		Reserva reservas = null;
		ReservaFamiliarDTO rf= null;
		ReservaAdultosDTO ra= null;
		ReservaInfantilDTO ri= null;
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(prop.getProperty("buscar-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idReserva = rs.getString(1);
				String idUsuario = rs.getString(2);
				LocalDate fecha = rs.getDate(8).toLocalDate();
				LocalTime hora = rs.getTime(11).toLocalTime();
				int minutosReserva = rs.getInt(3);
				String idPista = rs.getString(5);
				float precioPista = rs.getFloat(4);	
				int numeroNinos = rs.getInt(6);
				int numeroAdultos = rs.getInt(7);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(9));
				String modalidad = rs.getString(12);	
				 
				if(tipoReserva == TipoReserva.FAMILIAR) {
					 rf = new ReservaFamiliarDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos, numeroNinos);
					 reservas=rf;	 
				}
				if(tipoReserva == TipoReserva.INFANTIL) {
					 ri = new  ReservaInfantilDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroNinos);
						reservas=ri;	 
				}	
				if(tipoReserva == TipoReserva.ADULTOS) {
					 ra = new  ReservaAdultosDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroAdultos);
					 reservas=ra;	 
				}
			}
		
		}
		catch (SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return reservas;
		
	}
	
}
	

