package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import business.ReservaInfantilDTO;
import business.TipoReserva;

import java.sql.Types;

public class ReservaInfatilDAO extends ReservaDAO {
	
	/* 
	 * @Resumen Introduce en la base de datos la reserva de tipo Infantil
	 * @Param Reserva de tipo infantil
	 */
	
	public void insertaReservaInfantil(ReservaInfantilDTO r) {
	
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(getProps().getProperty("inserta-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdReserva());
			ps.setString(2,r.getIdUsuario());
			ps.setInt(3,r.getMinutosReserva());
			ps.setFloat(4, r.getPrecioPista());
			ps.setInt(5, r.getDescuento());
			ps.setString(6, r.getModalidad());
			ps.setInt(7, r.getIdPista());
			ps.setInt(8, r.getNumeroNinos());
			ps.setNull(9, Types.NULL);
			ps.setDate(10, Date.valueOf(r.getFechaYhora()));
			ps.setString(11, r.getTipo().toString());
			ps.executeUpdate();
				
			
		} catch(SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		
	}
	
	
	
	/* 
	 * @Resumen Buscar la reserva de tipo infantil
	 * @Param Id de la reserva
	 * @Return Reserva tipo infantil
	 */
	
	public ReservaInfantilDTO buscarReservaI(String idReserva) {
		ReservaInfantilDTO r= null;
		try {
			Connection connection = getConnection();
			PreparedStatement ps = con.prepareStatement(getProps().getProperty("buscar-reserva"));
			ps.setString(1, idReserva);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			 String idUsuario = rs.getString(2);
			 LocalDate fechaYhora =(rs.getDate(10)).toLocalDate();
			 int minutosReserva = rs.getInt(3);
			 int idPista = rs.getInt(7);
			 float precioPista = rs.getFloat(4);
			 int descuento = rs.getInt(5);		
			 int numeroNinos = rs.getInt(8);
			 TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
			 String modalidad = rs.getString(6);	 
			
			r = new  ReservaInfantilDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
					modalidad, numeroNinos,fechaYhora);
			}	
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		
		
		return r;
	}
	
	/* 
	 * @Resumen Modifica una reserva de tipo infantil
	 * @Param Reserva de tipo infantil
	 */
	
	
	public void modificaReservaInfantil(ReservaInfantilDTO r) {
		
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(getProps().getProperty("edita-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdUsuario());
			ps.setInt(2,r.getMinutosReserva());
			ps.setFloat(3, r.getPrecioPista());
			ps.setInt(4, r.getDescuento());
			ps.setString(5, r.getModalidad());
			ps.setInt(6, r.getIdPista());
			ps.setInt(7,r.getNumeroNinos());
			ps.setInt(8, Types.NULL);
			ps.setDate(9, Date.valueOf(r.getFechaYhora()));
			ps.setString(10, r.getTipo().toString());
			ps.setString(10, r.getTipo().toString());
			ps.setString(11, r.getIdReserva());
			ps.executeUpdate();
				
			
		} catch(SQLException e) {
			close();
			System.out.println(e);
		}
		close();
		
	}
	
	
	
}

