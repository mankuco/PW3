package data.Reserva;

import java.sql.*;
import java.time.*;

import business.Reserva.*;

public class ReservaInfatilDAO extends ReservaDAO {
	
	/* 
	 * @Resumen Introduce en la base de datos la reserva de tipo Adultos
	 * @Param Reserva de tipo Adultos
	 */
	public void insertaReservaInfantil(ReservaInfantilDTO r) {
	
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT into Reservas (id,idUsuario,minutos,precio,modalidad,idPista,NumeroNinos,NumeroAdultos,fecha,hora,tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdReserva());
			ps.setString(2,r.getIdUsuario());
			ps.setInt(3,r.getMinutosReserva());
			ps.setFloat(4, r.getPrecioPista());
			ps.setString(5, r.getModalidad());
			ps.setString(6, r.getIdPista());
			ps.setNull(7, r.getNumeroNinos());
			ps.setNull(8, 0 );
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
		
	}
		
	/* 
	 * @Resumen Buscar la reserva
	 * @Param Id de la reserva
	 * @Return Reserva
	 */
	public ReservaInfantilDTO buscarReservaA(String idReserva) {
		ReservaInfantilDTO r= null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("Select id,idUsuario,minutos,precio,modalidad,idPista,NumeroNinos,NumeroAdultos,fecha,hora,tipo from Reservas WHERE id=? and borrado = 0");
			ps.setString(1, idReserva);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String idUsuario = rs.getString(2);
				LocalDate fecha =(rs.getDate(9)).toLocalDate();
				LocalTime hora =(rs.getTime(10)).toLocalTime();
				int minutosReserva = rs.getInt(3);
				String idPista = rs.getString(6);
				float precioPista = rs.getFloat(4);
				int numeroNinos = rs.getInt(7);
				TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
				String modalidad = rs.getString(5);	 
			
				r = new ReservaInfantilDTO(idReserva, idUsuario, minutosReserva, idPista, precioPista, tipoReserva, modalidad , fecha, hora, 0, numeroNinos);
			}	
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return r;
		
	}

	/* 
	 * @Resumen Modifica una reserva
	 * @Param Reserva
	 */
	
	public void modificaReservaInfantil(ReservaInfantilDTO r) {
		
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE Reservas SET idUsuario = ?, minutos= ? ,precio= ?, modalidad= ?, idPista= ?, NumeroNinos= ?, NumeroAdultos= ?, fecha= ?, hora=?, tipo= ? WHERE id = ? and borrado = 0", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(11,r.getIdReserva());
			ps.setString(1,r.getIdUsuario());
			ps.setInt(2,r.getMinutosReserva());
			ps.setFloat(3, r.getPrecioPista());
			ps.setString(4, r.getModalidad());
			ps.setString(5, r.getIdPista());
			ps.setNull(6, r.getNumeroNinos());
			ps.setNull(7, 0 );
			ps.setDate(8, Date.valueOf(r.getFecha()));
			ps.setTime(9, Time.valueOf(r.getHora()));
			ps.setString(10, r.getTipo().toString());
			ps.executeUpdate();			
		}
		catch(SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		
	}
	
}

