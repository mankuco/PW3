package data.Reserva;

import java.sql.*;
import java.util.ArrayList;

import business.Pista_Kart.Estados;
import business.Pista_Kart.KartDTO;
import business.Reserva.*;
import data.DAO;

public class BonoDAO extends DAO {

	public void guardar(BonoReservaDTO bono) {
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("inserta-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,bono.getIdBono());
			ps.setString(2,bono.getIdReserva1());
			if(bono.getIdReserva2() != null) {
				ps.setString(3,bono.getIdReserva2());
				if(bono.getIdReserva3() != null) {
					ps.setString(4,bono.getIdReserva3());
					if(bono.getIdReserva4() != null) {
						ps.setString(5,bono.getIdReserva4());
						if(bono.getIdReserva5() != null) {
							ps.setString(6,bono.getIdReserva5());
						}
					}
				}
			}
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
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("edita-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(6,bono.getIdBono());
			ps.setString(1,bono.getIdReserva1());
			if(bono.getIdReserva2() != null) {
				ps.setString(2,bono.getIdReserva2());
				if(bono.getIdReserva3() != null) {
					ps.setString(3,bono.getIdReserva3());
					if(bono.getIdReserva4() != null) {
						ps.setString(4,bono.getIdReserva4());
						if(bono.getIdReserva5() != null) {
							ps.setString(5,bono.getIdReserva5());
						}
					}
				}
			}
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
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("lista-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BonoReservaDTO bono = new BonoReservaDTO(rs.getString("id"),rs.getString("r1"),rs.getString("r2"),rs.getString("r3"),rs.getString("r4"),rs.getString("r5"));
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
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("listareservas-bono"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,bono.getIdBono());
			ResultSet rs = ps.executeQuery();
			ReservaDAO resDAO = new ReservaDAO();
			while (rs.next()) {
				Reserva r = resDAO.getreserva(rs.getString("r1"));
				reservas.add(r);
				r = resDAO.getreserva(rs.getString("r2"));
				if(r != null) {
					reservas.add(r);
					r = resDAO.getreserva(rs.getString("r3"));
					if(r != null) {
						reservas.add(r);
						r = resDAO.getreserva(rs.getString("r4"));
						if(r != null) {
							reservas.add(r);
							r = resDAO.getreserva(rs.getString("r5"));
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
	
}
