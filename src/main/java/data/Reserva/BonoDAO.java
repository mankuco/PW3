package data.Reserva;

import java.sql.*;

import java.util.ArrayList;
import java.util.Properties;
import java.time.*;

import business.Reserva.*;
import data.DAO;

public class BonoDAO extends DAO {

	/**
	 * Constructor 
	 */
	public BonoDAO(Properties prop, String jdbc, String dbuser, String dbpass) {
		super(prop, jdbc, dbuser, dbpass);
	}
	
	public void insertar(BonoReservaDTO bono) {
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(prop.getProperty("inserta-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,bono.getIdBono());
			String tipo = "INFANTIL";
			if(bono.getTipo() == TipoReserva.ADULTOS) {
				tipo = "ADULTOS";
			}
			if(bono.getTipo() == TipoReserva.FAMILIAR) {
				tipo = "FAMILIAR";
			}
			ps.setString(2,tipo);
			ps.setString(3, bono.getEmail());
			ps.executeUpdate();
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void editar(BonoReservaDTO bono) {
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(prop.getProperty("edita-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(7,bono.getIdBono());
			if(bono.getIdReserva1() != null) {
				ps.setString(1,bono.getIdReserva1());
			}
			else{
				ps.setString(1,null);
			}
			if(bono.getIdReserva2() != null) {
				ps.setString(2,bono.getIdReserva2());
				if(bono.getIdReserva3() != null) {
					ps.setString(3,bono.getIdReserva3());
					if(bono.getIdReserva4() != null) {
						ps.setString(4,bono.getIdReserva4());
						if(bono.getIdReserva5() != null) {
							ps.setString(5,bono.getIdReserva5());
						}
						else {
							ps.setString(5,null);
						}
					}
					else {
						ps.setString(4,null);
					}
				}
				else {
					ps.setString(3,null);
				}
			}
			else {
				ps.setString(2,null);
			}
			ps.setDate(6, Date.valueOf(bono.getFecha()));
			ps.executeUpdate();
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public ArrayList<BonoReservaDTO> listarBonos() {
		ArrayList<BonoReservaDTO> reservas = new ArrayList<BonoReservaDTO>();
		TipoReserva tipo = TipoReserva.ADULTOS;
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(prop.getProperty("lista-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString(8) == "INFANTIL") {
					tipo = TipoReserva.INFANTIL;
				}
				else if(rs.getString(8) == "FAMILIAR") {
					tipo = TipoReserva.FAMILIAR;
				}
				LocalDate date = null;
				if (rs.getDate(7) != null) {
					date = rs.getDate(7).toLocalDate();
				}
				BonoReservaDTO bono = new BonoReservaDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),tipo,date,rs.getString(9));
				reservas.add(bono);
			}
			if (ps != null){ 
				ps.close(); 
			}
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	public ArrayList<Reserva> listarReservaBono(BonoReservaDTO bono) {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(prop.getProperty("listareservas-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,bono.getIdBono());
			ResultSet rs = ps.executeQuery();
			ReservaDAO resDAO = new ReservaDAO(prop, jdbc, dbuser, dbpass);
			while (rs.next()) {
				Reserva r = resDAO.getreserva(rs.getString(1));
				reservas.add(r);
				r = resDAO.getreserva(rs.getString(2));
				if(r != null) {
					reservas.add(r);
					r = resDAO.getreserva(rs.getString(3));
					if(r != null) {
						reservas.add(r);
						r = resDAO.getreserva(rs.getString(4));
						if(r != null) {
							reservas.add(r);
							r = resDAO.getreserva(rs.getString(5));
							if(r != null) {
								reservas.add(r);
							}
						}
					}
				}
			}
			if (ps != null){ 
				ps.close(); 
			}
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	public ArrayList<BonoReservaDTO> listarBonosUsuario(String email) {
		ArrayList<BonoReservaDTO> bonos = new ArrayList<BonoReservaDTO>();
		TipoReserva tipo = TipoReserva.ADULTOS;
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(prop.getProperty("listabono-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString(8) == "INFANTIL") {
					tipo = TipoReserva.INFANTIL;
				}
				else if(rs.getString(8) == "FAMILIAR") {
					tipo = TipoReserva.FAMILIAR;
				}
				LocalDate date = null;
				if (rs.getDate(7) != null) {
					date = rs.getDate(7).toLocalDate();
				}
				BonoReservaDTO bono = new BonoReservaDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),tipo,date,rs.getString(9));
				bonos.add(bono);
			}
			if (ps != null){ 
				ps.close(); 
			}
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return bonos;
	}
	
	public BonoReservaDTO existeBono(String id) {
		BonoReservaDTO bono = null;
		TipoReserva tipo = TipoReserva.ADULTOS;
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(prop.getProperty("busca-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString(8) == "INFANTIL") {
					tipo = TipoReserva.INFANTIL;
				}
				else if(rs.getString(8) == "FAMILIAR") {
					tipo = TipoReserva.FAMILIAR;
				}
				LocalDate date = null;
				if (rs.getDate(7) != null) {
					date = rs.getDate(7).toLocalDate();
				}
				bono = new BonoReservaDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),tipo,date,rs.getString(9));
			}
			if (ps != null){ 
				ps.close(); 
			}
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return bono;
	}
	
	public BonoReservaDTO bonoActivo(String email) {
		ArrayList<BonoReservaDTO> bonos = new ArrayList<BonoReservaDTO>();
		TipoReserva tipo = TipoReserva.ADULTOS;
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(prop.getProperty("listabono-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getString(8) == "INFANTIL") {
					tipo = TipoReserva.INFANTIL;
				}
				else if(rs.getString(8) == "FAMILIAR") {
					tipo = TipoReserva.FAMILIAR;
				}
				LocalDate date = null;
				if (rs.getDate(7) != null) {
					date = rs.getDate(7).toLocalDate();
				}
				BonoReservaDTO bono = new BonoReservaDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),tipo,date,rs.getString(9));
				bonos.add(bono);
			}
			if (ps != null){ 
				ps.close(); 
			}
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		if(bonos != null) {
			for(BonoReservaDTO a : bonos) {
				if((a.getIdReserva5() == null) && ((a.getFecha()).isAfter(LocalDate.now()))){
					return a;
				}
			}
		}
		return null;
	}
}
