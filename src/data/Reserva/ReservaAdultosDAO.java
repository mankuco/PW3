package data.Reserva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import business.Reserva.ReservaAdultosDTO;
import business.Reserva.TipoReserva;

import java.sql.Types;

public class ReservaAdultosDAO extends ReservaDAO {
	
	/* 
	 * @Resumen Introduce en la base de datos la reserva de tipo Adultos
	 * @Param Reserva de tipo Adultos
	 */
	
	public void insertaReservaAdultos(ReservaAdultosDTO r) {
	
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
			ps.setNull(8, Types.NULL);
			ps.setInt(9, r.getNumeroAdultos());
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
	 * @Resumen Buscar la reserva de tipo adultos
	 * @Param Id de la reserva
	 * @Return Reserva tipo Adulto
	 */
	
	public ReservaAdultosDTO buscarReservaA(String idReserva) {
		ReservaAdultosDTO r= null;
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
			 int numeroAdultos = rs.getInt(9);
			 TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
			 String modalidad = rs.getString(6);	 
			
			r = new  ReservaAdultosDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
					modalidad, numeroAdultos,fechaYhora);
			}	
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		
		
		return r;
	}
	
	

	/* 
	 * @Resumen Modifica una reserva de tipo adultos
	 * @Param Reserva de tipo Adultos
	 */
	
public void modificaReservaAdultos(ReservaAdultosDTO r) {
		
		try{
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(getProps().getProperty("edita-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdUsuario());
			ps.setInt(2,r.getMinutosReserva());
			ps.setFloat(3, r.getPrecioPista());
			ps.setInt(4, r.getDescuento());
			ps.setString(5, r.getModalidad());
			ps.setInt(6, r.getIdPista());
			ps.setInt(7,Types.NULL);
			ps.setInt(8, r.getNumeroAdultos());
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

