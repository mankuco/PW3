package data;

import java.util.ArrayList;

import business.Estados;
import business.KartDTO;
import business.PistaDTO;

import java.sql.*;



public class KartDAO extends DAO {

	/* 
	 * @Resumen Guarda en la base de datos un nuevo kart
	 */
	public void guardarKart(KartDTO kart) {
		try {
			
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("inserta-kart"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1,kart.getIdKart());
			if(kart.getTipoKart()==true) {
				ps.setInt(2, 1);
			}
			else {
				ps.setInt(2, 0);
			}
			if(kart.getEstado() == Estados.DISPONIBLE) {
				ps.setString(3,"DISPONIBLE");
			}
			else if(kart.getEstado() == Estados.MANTENIMIENTO) {
				ps.setString(3,"MANTENIMIENTO");
			}
			else {
				ps.setString(3,"RESERVADO");
			}
			ps.executeUpdate();
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* 
	 * @Resumen Devuelve el kart si existe, si no existe un null
	 * @return kart = Kart
	 * @return null
	 */
	public KartDTO existekart(int idKart) {
		KartDTO kart = null;
		try {
			
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("existe-kart"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idKart);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("tipoKart");
				boolean tipoKart = true;
				if(i == 0) {
					tipoKart = false;
				}
				String est = rs.getString("estado");
				Estados estado = Estados.RESERVADO;
				if(est.equals("ADULTOS")) {
					estado = Estados.MANTENIMIENTO;
				}
				if(est.equals("DISPONIBLE")) {
					estado = Estados.DISPONIBLE;
				}
				kart = new KartDTO(idKart, tipoKart, estado);
				kart.setnombrePista(rs.getString("nombrePista"));
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
		return kart;
	}
	

	/* 
	 * @Resumen Devuelve la lista con todos los karts que tiene asociados
	 * @return listakart = ArrayList<Kart>
	 */
	public ArrayList<KartDTO> listarkart(String nombrePista){
		ArrayList<KartDTO> listakart = new ArrayList<KartDTO>();
		try {
		
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("ver-kart-pista"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, nombrePista);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("tipoKart");
				boolean tipoKart = true;
				if(i == 0) {
					tipoKart = false;
				}
				String est = rs.getString("estado");
				Estados estado = Estados.RESERVADO;
				if(est.equals("ADULTOS")) {
					estado = Estados.MANTENIMIENTO;
				}
				if(est.equals("DISPONIBLE")) {
					estado = Estados.DISPONIBLE;
				}
				int idKart = rs.getInt("idKart");
				KartDTO kart = new KartDTO(idKart, tipoKart, estado);
				kart.setnombrePista(nombrePista);
				listakart.add(kart);
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
		return listakart;
	}
	
	/* 
	 * @Resumen Cambia el valor de la variable nombrePista en el kart
	 * @param kart = Kart, el kart que se quiere asociaciar
	 * @param pista = Pista, la pista a la que se le quiere asociar un kart
	 */
	public void cambiarnombrePista(KartDTO kart, PistaDTO pista) {
		try {
		
			Connection connection =getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("cambiar-nombre-pista"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,pista.getNombrePista());
			ps.setInt(2,kart.getIdKart());
			ps.executeUpdate();
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}