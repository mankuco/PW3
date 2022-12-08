package data.Reserva;

import java.sql.Connection;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;

import business.Reserva.*;
import business.Pista_Kart.Estados;
import business.Pista_Kart.KartDTO;
import business.Usuario.UsuarioDTO;
import data.DAO;

/**
 * Clase que accede a la base de datos de Reservas
 */

public class ReservaDAO extends DAO {
	
	/* 
	 * @Resumen Introduce en la base de datos la reserva 
	 * @Param Reserva 
	 */
	
	public String insertaReserva(ReservaDTO r) {
		String id =r.getIdReserva();
		
		try{
			PreparedStatement ps = con.prepareStatement("INSERT into Reservas (id,idUsuario,minutos,precio,descuento,modalidad,idPista,NumeroNinos,NumeroAdultos,fecha,hora,tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdReserva());
			ps.setString(2,r.getIdUsuario());
			ps.setInt(3,r.getMinutosReserva());
			ps.setFloat(4, r.getPrecioPista());
			ps.setInt(5, r.getDescuento());
			ps.setString(6, r.getModalidad());
			ps.setInt(7, r.getIdPista());
			ps.setNull(8, Types.NULL);
			ps.setNull(9, Types.NULL );
			ps.setDate(10, Date.valueOf(r.getFecha()));
			ps.setTime(11, Time.valueOf(r.getHora()));
			ps.setString(12, r.getTipo().toString());
			ps.executeUpdate();
			
		} catch(SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return id;
	}

	/* 
	 * @Resumen Elimina de la base de datos una reserva 
	 * @Param id de la Reserva 
	 */
	public void borraReserva(String id) {
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("Update Reservas set borrado = 1 WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
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
	public ArrayList<ReservaDTO> verReservas() {
	
		ArrayList<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
		ReservaDTO reserva;
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Reservas WHERE borrado = 0");
			ps.setString(1,LocalDateTime.now().toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idReserva = rs.getString(1);
				String idUsuario = rs.getString(2);
				LocalDate fecha = rs.getDate(10).toLocalDate();
				LocalTime hora = rs.getTime(13).toLocalTime();
				int minutosReserva = rs.getInt(3);
				int idPista = rs.getInt(7);
				float precioPista = rs.getFloat(4);
				int descuento = rs.getInt(5);		
				int numeroNinos = rs.getInt(8);
				int numeroAdultos = rs.getInt(9);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
				String modalidad = rs.getString(6);
				
				reserva = new ReservaDTO(idUsuario, minutosReserva, idPista, precioPista, descuento, tipoReserva, modalidad, fecha, hora, 0, numeroNinos, numeroAdultos);
				reservas.add(reserva);
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
	
	public ArrayList<ReservaDTO> verReservasFecha(LocalDate Fecha) {
	
		ArrayList<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
		ReservaDTO reserva;
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Reservas WHERE fecha = ? and borrado = 0");
			ps.setString(1,Fecha.toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idReserva = rs.getString(1);
				String idUsuario = rs.getString(2);
				LocalDate fecha = rs.getDate(10).toLocalDate();
				LocalTime hora = rs.getTime(13).toLocalTime();
				int minutosReserva = rs.getInt(3);
				int idPista = rs.getInt(7);
				float precioPista = rs.getFloat(4);
				int descuento = rs.getInt(5);		
				int numeroNinos = rs.getInt(8);
				int numeroAdultos = rs.getInt(9);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
				String modalidad = rs.getString(6);	
				 
				reserva = new ReservaDTO(idUsuario, minutosReserva, idPista, precioPista, descuento, tipoReserva, modalidad, fecha, hora, 0, numeroNinos, numeroAdultos);
				reservas.add(reserva);
			}
		}
		catch (SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return reservas;
	}

	public ArrayList<ReservaDTO> verReservasUsuario(String IdUsuario) {


		ArrayList<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
		ReservaDTO reserva;
	
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Reservas WHERE idUsuario = ? and borrado = 0");
			ps.setString(1,LocalDate.now().toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idReserva = rs.getString(1);
				String idUsuario = rs.getString(2);
				LocalDate fecha = rs.getDate(10).toLocalDate();
				LocalTime hora = rs.getTime(13).toLocalTime();
				int minutosReserva = rs.getInt(3);
				int idPista = rs.getInt(7);
				float precioPista = rs.getFloat(4);
				int descuento = rs.getInt(5);		
				int numeroNinos = rs.getInt(8);
				int numeroAdultos = rs.getInt(9);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
				int borrado = rs.getInt(10);
				String modalidad = rs.getString(6);
				
				reserva = new ReservaDTO(idUsuario, minutosReserva, idPista, precioPista, descuento, tipoReserva, modalidad, fecha, hora, 0, numeroNinos, numeroAdultos);
				reservas.add(reserva);
			}
		} 
		catch (SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		return reservas;
	}
	
	public void modificarReserva(ReservaDTO r) {
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE Reservas SET idUsuario = ?, minutos= ? ,precio= ?, descuento= ?, modalidad= ?, idPista= ?, NumeroNinos= ?, NumeroAdultos= ?, fecha= ?, hora= ?, tipo= ? WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdUsuario());
			ps.setInt(2,r.getMinutosReserva());
			ps.setFloat(3, r.getPrecioPista());
			ps.setInt(4, r.getDescuento());
			ps.setString(5, r.getModalidad());
			ps.setInt(6, r.getIdPista());
			ps.setInt(7,r.getNumeroNinos());
			ps.setInt(8, r.getNumeroAdultos());
			ps.setDate(9, Date.valueOf(r.getFecha()));
			ps.setTime(10, Time.valueOf(r.getHora()));
			ps.setString(11, r.getTipo().toString());
			ps.setString(12, r.getIdReserva());
			ps.executeUpdate();			
		} 
		catch(SQLException e) {
			close();
			System.out.println(e);
		}
		close();
	}
}