package business.Pista_Kart;

public class KartDTO {
	
	private int idKart;
	private boolean tipoKart;  //True= Adulto  False= Infantil
	private Estados estado;	
	private String nombrePista;
	
	//CONSTRUCTOR PARAMETRIZADO
	public KartDTO(int idKart, boolean tipoKart, Estados estado) {
		super();
		this.idKart = idKart;
		this.tipoKart = tipoKart;
		this.estado = estado;
		this.nombrePista = null;
	}
	//CONSTRUCTOR
	public KartDTO() {
		super();
	}
	
	/* 
	 * @Resumen Devuelve la identificación del kart
	 * @return idKart = int que identifica al kart
	 */
	public int getIdKart() {
		return idKart;
	}
	/* 
	 * @Resumen Cambia la identificación del kart
	 * @param idKart = int que identifica al kart
	 */
	public void setIdKart(int idKart) {
		this.idKart = idKart;
	}
	/* 
	 * @Resumen Devuelve el tipo de kart
	 * @return tipokart = bool // true=adulto, false=infantil
	 */ 
	public boolean getTipoKart() {
		return tipoKart;
	}
	/* 
	 * @Resumen Cambia el tipo de kart
	 * @return tipokart = bool // true=adulto, false=infantil
	 */
	public void setTipoKart(boolean tipoKart) {
		this.tipoKart = tipoKart;
	}
	/* 
	 * @Resumen Devuelve el estado del kart
	 * @return estado = Estados // DISPONIBLE, RESERVADO o MANTENIMIENTO 
	 */
	public Estados getEstado() {
		return estado;
	}
	/* 
	 * @Resumen Cambia el estado del kart
	 * @param estado = Estados // DISPONIBLE, RESERVADO o MANTENIMIENTO 
	 */
	public void setEstado(Estados estado) {
		this.estado = estado;
	}
	/* 
	 * @Resumen Cambia el nombre de la pista a la que esta asociado el kart
	 * @return nombrePista = String
	 */
	public String getnombrePista(){
		return nombrePista;
	}
	/* 
	 * @Resumen Devuelve el nombre de la pista a la que esta asociado el kart
	 * @param nombrePista = String
	 */
	public void setnombrePista(String nombrePista){
		this.nombrePista=nombrePista;
	}
	
	/* 
	 * @Resumen Devuelve una cadena con la informacion del kart
	 * @return string
	 */
	@Override
	public String toString() {
		return "KART [IDKart=" + idKart + ", TipoKart=" + tipoKart + ", Estado=" + estado + "]";
	}	
}