package business.Pista_Kart;

import java.util.ArrayList;

import data.Pista_Kart.KartDAO;
import data.Pista_Kart.PistaDAO;

public class GestorPistas {
	
	public GestorPistas(){ }

	/* 
	 * @Resumen Devuelve la pista, si esta registrada en la base de datos, si no lo esta devuelve null
	 * @param nombrePista = String, es el nombre de la pista que buscamos
	 * @return pista = Pista, es la pista que ha encontrado (si no la ha encontrado entonces devolvera null)
	 */
	public PistaDTO existePista(String nombrePista) {
		PistaDAO consultar = new PistaDAO();
		PistaDTO pista = consultar.existepista(nombrePista);
		return pista;
	}
	/* 
	 * @Resumen Devuelve el kart, si esta registrado en la base de datos., si no lo esta devuelve null
	 * @param idKart = Kart, es el identificador del kart que buscamos
	 * @return kart = Kart, es el kart que ha encontrado (si no lo ha encontrado entonces devolvera null)
	 */
	public KartDTO existeKart(int idKart) {
		KartDAO consultar = new KartDAO();
		KartDTO kart= consultar.existekart(idKart);
		return kart;
	}
	
	/* 
	 * @Resumen Llama a la funcion void guardarpista(Pista pista)
	 * @param Entran como parametros todos los datos necesarios para crear una pista
	 */
	public void crearPista(String nombre, boolean tipoEstado, Dificultades dificultad, int maxKarts) {
		PistaDTO pista = new PistaDTO(nombre, tipoEstado, dificultad, maxKarts);
		PistaDAO crear = new PistaDAO();
		crear.guardarPista(pista);
	}
	/* 
	 * @Resumen Llama a la funcion void guardarKart(Kart kart)
	 * @param Entran como parametros todos los datos necesarios para crear un kart
	 */
	public void crearKart(int idKart, boolean tipoKart, Estados estado) {
		KartDTO kart = new KartDTO(idKart,tipoKart, estado);
		KartDAO crear = new KartDAO();
		crear.guardarKart(kart);
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
	public void eliminarkart(KartDTO kart, PistaDTO pista) {
		ArrayList<KartDTO> array = new ArrayList<KartDTO>();
		for(int i = 0; i < pista.getListaKarts().size(); i++) {
			if(pista.getListaKarts().get(i).getIdKart() != kart.getIdKart()) {
				array.add(pista.getListaKarts().get(i));
			}
		}
		pista.setListaKarts(array);
		pista.setnkartsasociados(pista.getnkartsasociados() - 1);
		PistaDAO cambiar = new PistaDAO();
		cambiar.cambiarnkartsasociados(pista);
	}
	
	/* 
	 * @Resumen Lista todas las pistas que no estén en mantenimiento
	 */
	public ArrayList<PistaDTO> listarPistasmantenimiento() {
		PistaDAO listar= new PistaDAO();
		ArrayList<PistaDTO> pistas = listar.listarmantenimiento();
		return pistas;
	}
	
	/* 
	 * @Resumen LLama a la funcion ArrayList<Pista> listardisponibles(int numKart, Dificultades dificultad)
	 * @param numKart, es el numero de karts para los que buscamos una pista disponible
	 * @param dificultad, el la dificultas (infantil, adultos, familiar) para la que buscamos la pista
	 * @return disponibles, devuelve un vector con todas las pistas que cumplen los requisitos
	 */
	public ArrayList<PistaDTO> pistasDisponibles (int numKart, Dificultades dificultad){
		PistaDAO listar= new PistaDAO();
		ArrayList<PistaDTO> pistas = listar.listardisponibles(numKart, dificultad);
		return pistas;
	}
}
	
