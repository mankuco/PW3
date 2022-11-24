package business.Pista_Kart;

public class PistaDTO {
	
	private String nombrePista;
	private boolean tipoEstado; //true si esta disponible a reserva
	private Dificultades dificultad;
	private int maxKarts;
	private int nkartsasociados;
	
	//CONSTRUCTOR PARAMETRIZADO
	public PistaDTO(String nombrePista, boolean tipoEstado, Dificultades dificultad, int maxKarts) {
		super();
		this.nombrePista = nombrePista;
		this.tipoEstado = tipoEstado;
		this.dificultad = dificultad;
		this.maxKarts = maxKarts;
		this.nkartsasociados=0;
	}
	//CONSTRUCTOR
	public PistaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	/* 
	 * @Resumen Devuelve el nombre de la pista
	 * @return nombrePista = String
	 */
	public String getNombrePista() {
		return nombrePista;
	}
	/* 
	 * @Resumen Cambia el nombre de la pista
	 * @param nombrePista = String
	 */
	public void setNombrePista(String nombrePista) {
		this.nombrePista = nombrePista;
	}
	/* 
	 * @Resumen Devuelve el estado de la pista
	 * @return tipoEstado = bool // true=disponible false=no disponible
	 */
	public boolean getTipoEstado() {
		return tipoEstado;
	}
	/* 
	 * @Resumen Cambia el estado de la pista
	 * @param tipoEstado = bool // true=disponible false=no disponible
	 */
	public void setTipoEstado(boolean tipoEstado) {
		this.tipoEstado = tipoEstado;
	}
	/* 
	 * @Resumen Devuelve la dificultad de la pista
	 * @return dificultad = Dificultades // INFANTIL, FAMILIAR, ADULTOS 
	 */
	public Dificultades getDificultad() {
		return dificultad;
	}
	/* 
	 * @Resumen Cambia la dificultad de la pista
	 * @param dificultad = Dificultades // INFANTIL, FAMILIAR, ADULTOS 
	 */
	public void setDificultad(Dificultades dificultad) {
		this.dificultad = dificultad;
	}
	/* 
	 * @Resumen Devuelve el numero maximo de karts que puede tener asignados una pista
	 * @return maxKarts = int 
	 */
	public int getMaxKarts() {
		return maxKarts;
	}
	/* 
	 * @Resumen Cambia el numero maximo de karts que puede tener asignados una pista
	 * @param maxKarts = int 
	 */
	public void setMaxKarts(int maxKarts) {
		this.maxKarts = maxKarts;
	}
	/* 
	 * @Resumen Devuelve el numero de karts asignados una pista
	 * @return nkartsasociados = int 
	 */
	public int getnkartsasociados() {
		return nkartsasociados;
	}
	/* 
	 * @Resumen Cambia el numero de karts asignados una pista
	 * @param nkartsasociados = int 
	 */
	public void setnkartsasociados(int nkartsasociados) {
		this.nkartsasociados = nkartsasociados;
	}
	
}
