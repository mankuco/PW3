package business.Pista_Kart;

import java.util.ArrayList;
import java.util.Properties;

import data.Pista_Kart.KartDAO;
import data.Pista_Kart.PistaDAO;

public class GestorPistas {
	
	public GestorPistas(){ }

	/* 
	 * @Resumen Devuelve la pista, si esta registrada en la base de datos, si no lo esta devuelve null
	 * @param nombrePista = String, es el nombre de la pista que buscamos
	 * @return pista = Pista, es la pista que ha encontrado (si no la ha encontrado entonces devolvera null)
	 */
	public PistaDTO existePista(String nombrePista, Properties prop, String jdbc, String dbuser, String dbpass) {
		PistaDAO consultar = new PistaDAO(prop, jdbc, dbuser, dbpass);
		PistaDTO pista = consultar.existepista(nombrePista);
		return pista;
	}
	/* 
	 * @Resumen Devuelve el kart, si esta registrado en la base de datos., si no lo esta devuelve null
	 * @param idKart = Kart, es el identificador del kart que buscamos
	 * @return kart = Kart, es el kart que ha encontrado (si no lo ha encontrado entonces devolvera null)
	 */
	public KartDTO existeKart(int idKart, Properties prop, String jdbc, String dbuser, String dbpass) {
		KartDAO consultar = new KartDAO(prop, jdbc, dbuser, dbpass);
		KartDTO kart= consultar.existekart(idKart);
		return kart;
	}
	
	/* 
	 * @Resumen Llama a la funcion void guardarpista(Pista pista)
	 * @param Entran como parametros todos los datos necesarios para crear una pista
	 */
	public void crearPista(String nombre, boolean tipoEstado, Dificultades dificultad, int maxKarts, Properties prop, String jdbc, String dbuser, String dbpass) {
		if(existePista(nombre,prop,jdbc,dbuser,dbpass) == null) {
			PistaDTO pista = new PistaDTO(nombre, tipoEstado, dificultad, maxKarts);
			PistaDAO crear = new PistaDAO(prop, jdbc, dbuser, dbpass);
			crear.guardarPista(pista);
		}
	}
	/* 
	 * @Resumen Llama a la funcion void guardarKart(Kart kart)
	 * @param Entran como parametros todos los datos necesarios para crear un kart
	 */
	public int crearKart(boolean tipoKart, Estados estado, Properties prop, String jdbc, String dbuser, String dbpass) {
		int idKart = numerorandom();
		while(existeKart(idKart,prop,jdbc,dbuser,dbpass) != null) {
			idKart = numerorandom();
		}
		KartDTO kart = new KartDTO(idKart,tipoKart, estado);
		KartDAO crear = new KartDAO(prop, jdbc, dbuser, dbpass);
		crear.guardarKart(kart);
		return idKart;
	}

	/* 
	 * @Resumen Devuelve true si el kart esta asignado a una pista
	 * @param kart = Kart
	 */
	public boolean tienepista(KartDTO kart) {
		return (kart.getnombrePista() != null);
	}
	
	/* 
	 * @Resumen Elimina de la lista de karts asignados a una pista el kart que se va a asignar a otra pista distinta y se actualiza el numero de karts asignados
	 * @param kart = Kart, el kart que hay que eliminar
	 * @param pista = Pista
	 */
	public void eliminarkart(PistaDTO pista, Properties prop, String jdbc, String dbuser, String dbpass) {
		pista.setnkartsasociados(pista.getnkartsasociados() - 1);
		PistaDAO cambiar = new PistaDAO(prop, jdbc, dbuser, dbpass);
		cambiar.cambiarnkartsasociados(pista);
	}
	
	/* 
	 * @Resumen LLama a la funcion ArrayList<Pista> listardisponibles(int numKart, Dificultades dificultad)
	 * @param numKart, es el numero de karts para los que buscamos una pista disponible
	 * @param dificultad, el la dificultas (infantil, adultos, familiar) para la que buscamos la pista
	 * @return disponibles, devuelve un vector con todas las pistas que cumplen los requisitos
	 */
	public ArrayList<PistaDTO> pistasDisponibles (int numKart, Dificultades dificultad, Properties prop, String jdbc, String dbuser, String dbpass){
		PistaDAO listar= new PistaDAO(prop, jdbc, dbuser, dbpass);
		ArrayList<PistaDTO> pistas = listar.listardisponibles(numKart, dificultad);
		return pistas;
	}
	
	/* 
	 * @Resumen Asocia un kart a una pista
	 * @param kart = Kart
	 * @param pista = Pista 
	 */
	public void asociarKartPista(KartDTO kart, PistaDTO pista, Properties prop, String jdbc, String dbuser, String dbpass) {
		if(pista.getMaxKarts() != pista.getnkartsasociados()) {
			if(kart.getEstado() != Estados.RESERVADO){
				kart.setnombrePista(pista.getNombrePista());
				kart.setEstado(Estados.RESERVADO);
				KartDAO modificar = new KartDAO(prop, jdbc, dbuser, dbpass);
				modificar.cambiarnombrePista(kart,pista);
				pista.setnkartsasociados(pista.getnkartsasociados() + 1);
				PistaDAO cambiar = new PistaDAO(prop, jdbc, dbuser, dbpass);
				cambiar.cambiarnkartsasociados(pista);
			}
		}
	}
	
	/*
	 * @Resumen Devuelve una lista con todos los karts asociados a una pista
	 * @param pista = String
	 * @return lista = ArrayLista<KartDTO>
	 */
	public ArrayList<KartDTO> getListaKarts(String pista, Properties prop, String jdbc, String dbuser, String dbpass){
		KartDAO listar= new KartDAO(prop, jdbc, dbuser, dbpass);
		ArrayList<KartDTO> lista = listar.listarkart(pista);
		return lista;
	}
	
	/*
	 * @Resumen devuelve un numero aleatorio
	 * @return int
	 */
	public int numerorandom() {
		return (int)(Math.random()*99999+1);
	}
}
	
