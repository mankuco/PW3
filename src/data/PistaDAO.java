package data;

import java.util.ArrayList;

import business.Dificultades;
import business.PistaDTO;

import java.sql.*;




public class PistaDAO extends DAO {

	/* 
	 * @Resumen Guarda en la base de datos una nueva pista
	 */
	public void guardarPista(PistaDTO pista) {
		try {
		
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(getProps().getProperty("inserta-pista"));
			ps.setString(1,pista.getNombrePista());
			if(pista.getTipoEstado()==true) {
				ps.setInt(2, 1);
			}
			else {
				ps.setInt(2, 0);
			}
			if(pista.getDificultad() == Dificultades.FAMILIAR) {
				ps.setString(3,"FAMILIAR");
			}
			else if (pista.getDificultad() == Dificultades.ADULTOS) {
				ps.setString(3,"ADULTOS");
			}
			else {
				ps.setString(3,"INFANTIL");
			}
			ps.setInt(4,pista.getMaxKarts());
			ps.executeUpdate();
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* 
	 * @Resumen Devuelve una pista si existe, si no existe un null
	 * @return pista = Pista
	 * @return null
	 */
	public PistaDTO existepista(String nombrePista) {

		PistaDTO pista = null;
		try {
		
			Connection connection =getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("existe-pista"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, nombrePista);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("tipoEstado");
				boolean tipoEstado = true;
				if(i == 0) {
					tipoEstado = false;
				}
				String dif = rs.getString("dificultad");
				Dificultades dificultad = Dificultades.FAMILIAR;
				if(dif.equals("ADULTOS")) {
					dificultad = Dificultades.ADULTOS;
				}
				if(dif.equals("INFANTIL")) {
					dificultad = Dificultades.INFANTIL;
				}
				int maxKarts = rs.getInt("maxKarts");
				pista = new PistaDTO(nombrePista, tipoEstado, dificultad, maxKarts);
				int nkartsasociados = rs.getInt("nkartsasociados");
				pista.setnkartsasociados(nkartsasociados);
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
		return pista;
	}

	/* 
	 * @Resumen Cambiar el valor de karts que tiene asociados una pista
	 * @param pista = Pista
	 */
	public void cambiarnkartsasociados(PistaDTO pista) {
		try {
			Connection connection =getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("cambiar-kart-asociado"), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1,pista.getnkartsasociados());
			ps.setString(2,pista.getNombrePista());
			ps.executeUpdate();
			close();
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/* 
	 * @Resumen Devuelve la lista con todas las pistas que estan en mantenimiento
	 * @return listamantenimiento = ArrayList<Pista>
	 */
	public ArrayList<PistaDTO> listarmantenimiento() {
		ArrayList<PistaDTO> listamantenimiento = new ArrayList<PistaDTO>();
		try {
			Connection connection =getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("lista-mantenimiento"), PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nombrePista = rs.getString("nombrePista");
				boolean tipoEstado = false;
				String dif = rs.getString("dificultad");
				Dificultades dificultad = Dificultades.FAMILIAR;
				if(dif.equals("ADULTOS")) {
					dificultad = Dificultades.ADULTOS;
				}
				if(dif.equals("INFANTIL")) {
					dificultad = Dificultades.INFANTIL;
				}
				int maxKarts = rs.getInt("maxKarts");
				listamantenimiento.add(new PistaDTO(nombrePista, tipoEstado, dificultad, maxKarts));
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
		return listamantenimiento;
	}
	
	/* 
	 * @Resumen Busca las pistas que estan disponibles para cierto numero de karts
	 * @param numKart, es el numero de karts para los que buscamos una pista disponible
	 * @param dificultad, el la dificultas (infantil, adultos, familiar) para la que buscamos la pista
	 * @return disponibles, devuelve un vector con todas las pistas que cumplen los requisitos
	 */
	public ArrayList<PistaDTO> listardisponibles(int numKart, Dificultades dificultad){
		ArrayList<PistaDTO> listadisponibles = new ArrayList<PistaDTO>();
		try {
			Connection connection = getConnection();
			PreparedStatement ps=connection.prepareStatement(getProps().getProperty("lista-disponibles"), PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			String dif = "FAMILIAR";
			if(dificultad == Dificultades.ADULTOS) {
				dif = "ADULTOS";
			}
			else if(dificultad == Dificultades.INFANTIL) {
				dif = "INFANTIL";
			}
			while (rs.next()) {
				int maxKarts = rs.getInt("maxKarts");
				String dificul = rs.getString("dificultad");
				int nkartsasociados = rs.getInt("nkartsasociados");
				if((nkartsasociados >= numKart) && (dif.equals(dificul))) {
					String nombrePista = rs.getString("nombrePista");
					PistaDTO pista = new PistaDTO(nombrePista, true, dificultad, maxKarts);
					pista.setnkartsasociados(nkartsasociados);
					listadisponibles.add(pista);
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
		return listadisponibles;
	}
}
