package data.Reserva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import business.Reserva;
import business.Pista_Kart.Estados;
import business.Pista_Kart.KartDTO;
import business.Reserva.ReservaAdultosDTO;
import business.Reserva.ReservaFamiliarDTO;
import business.Reserva.ReservaInfantilDTO;
import business.Reserva.TipoReserva;
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
	
	public String insertaReserva(Reserva r) {
		String id =r.getIdReserva();
		
		try{
			PreparedStatement ps = con.prepareStatement(getProps().getProperty("inserta-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,r.getIdReserva());
			ps.setString(2,r.getIdUsuario());
			ps.setInt(3,r.getMinutosReserva());
			ps.setFloat(4, r.getPrecioPista());
			ps.setInt(5, r.getDescuento());
			ps.setString(6, r.getModalidad());
			ps.setInt(7, r.getIdPista());
			ps.setNull(8, Types.NULL);
			ps.setNull(9, Types.NULL );
			ps.setDate(10, Date.valueOf(r.getFechaYhora()));
			ps.setString(11, r.getTipo().toString());
			ps.executeUpdate();
			
		} catch(SQLException e) {
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
			PreparedStatement ps = con.prepareStatement(getProps().getProperty("elimina-reserva"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,id);

			ps.executeUpdate();
				
			
		} catch(SQLException e) {
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
		PreparedStatement ps = con.prepareStatement(getProps().getProperty("ver-reservas"));
		ps.setString(1,LocalDate.now().toString());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			 String idReserva = rs.getString(1);
			 String idUsuario = rs.getString(2);
			 LocalDate fechaYhora =(rs.getDate(10)).toLocalDate();
			 int minutosReserva = rs.getInt(3);
			 int idPista = rs.getInt(7);
			 float precioPista = rs.getFloat(4);
			 int descuento = rs.getInt(5);		
			 int numeroNinos = rs.getInt(8);
			 int numeroAdultos = rs.getInt(9);
			 TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
			 String modalidad = rs.getString(6);	
			 
		if(tipoReserva == TipoReserva.FAMILIAR) {
			 rf = new  ReservaFamiliarDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
					modalidad, numeroNinos, numeroAdultos,fechaYhora);
			 reservas.add(rf);	 
		}
			
			
			
			if(tipoReserva == TipoReserva.INFANTIL) {
				 ri = new  ReservaInfantilDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
						modalidad, numeroNinos,fechaYhora);
					reservas.add(ri);	 
			}
				
			
				
		if(tipoReserva == TipoReserva.ADULTOS) {
			 ra = new  ReservaAdultosDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
					modalidad, numeroAdultos,fechaYhora);
			 reservas.add(ra);	 
		}
			
			
				
			
			}
		
		
		
	} catch (SQLException e) {
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
		PreparedStatement ps = con.prepareStatement(getProps().getProperty("ver-reservas-fecha"));
		ps.setString(1,Fecha.toString());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			 String idReserva = rs.getString(1);
			 String idUsuario = rs.getString(2);
			 LocalDate fechaYhora =(rs.getDate(10)).toLocalDate();
			 int minutosReserva = rs.getInt(3);
			 int idPista = rs.getInt(7);
			 float precioPista = rs.getFloat(4);
			 int descuento = rs.getInt(5);		
			 int numeroNinos = rs.getInt(8);
			 int numeroAdultos = rs.getInt(9);
			 TipoReserva tipoReserva = TipoReserva.valueOf(rs.getString(11));
			 String modalidad = rs.getString(6);	
			 
		if(tipoReserva == TipoReserva.FAMILIAR) {
			 rf = new  ReservaFamiliarDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
					modalidad, numeroNinos, numeroAdultos,fechaYhora);
			 reservas.add(rf);	 
		}
			
			
			
			if(tipoReserva == TipoReserva.INFANTIL) {
				 ri = new  ReservaInfantilDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
						modalidad, numeroNinos,fechaYhora);
					reservas.add(ri);	 
			}
				
			
				
		if(tipoReserva == TipoReserva.ADULTOS) {
			 ra = new  ReservaAdultosDTO(idReserva,idUsuario, minutosReserva,precioPista, descuento,
					modalidad, numeroAdultos,fechaYhora);
			 reservas.add(ra);	 
		}
			
			
				
			
			}
		
		
		
	} catch (SQLException e) {
		close();
		System.out.println(e);
	}
	close();
	return reservas;
	
}

}
	

