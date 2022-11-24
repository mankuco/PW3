package display;

import java.util.ArrayList;
import java.util.Scanner;

import business.Pista_Kart.Dificultades;
import business.Pista_Kart.Estados;
import business.Pista_Kart.GestorPistas;
import business.Pista_Kart.KartDTO;
import business.Pista_Kart.PistaDTO;

public class ProgramaPistas {

	public static void mainPistas() {
		
		GestorPistas factoryGestor = new GestorPistas();
		Scanner scanpistas = new Scanner(System.in);
		
		int num = 0;
		while(num != 6) {
			
			System.out.println("BIENVENIDO AL PROGRAMA DE PISTAS");
			System.out.println("1. Crear Pista");
			System.out.println("2. Crear Kart");
			System.out.println("3. Asociar kart a pista disponible");
			System.out.println("4. Mostrar pistas que estan en manteniento");
			System.out.println("5. Mostrar pistas libres para un numero de karts");
			System.out.println("6. Salir");
			String aux = scanpistas.nextLine();
			try{
				num = Integer.parseInt(aux);
				switch(num) {
					//Crear pista
					case 1:
						System.out.println("Introduce el nombre de la pista");
						String nombre = scanpistas.nextLine();
						if (factoryGestor.existePista(nombre) != null) {
							System.out.println("La pista ya existe");
						}
						else {
							boolean tipoEstado = true;
							int dif = 0, maxKarts = 0;
							String opcion;
							while((dif != 1) && (dif != 2) && (dif != 3)) {
								System.out.println("Introduce la dificultad");
								System.out.println("1. Familiar");
								System.out.println("2. Adultos");
								System.out.println("3. Infantil");
						        opcion = scanpistas.nextLine();
						        try {
						        	dif = Integer.parseInt(opcion);
						        }
						        catch (NumberFormatException e) {
									System.out.println("Formato no valido");
								}
							}
							Dificultades dificultad = Dificultades.FAMILIAR;
							if(dif == 2) {
								dificultad = Dificultades.ADULTOS;
							}
							else if(dif == 3) {
								dificultad = Dificultades.INFANTIL;
							}
							dif = 0;
							while(dif == 0) {
								System.out.println("Introduce el numero maximo de karts");
								opcion = scanpistas.nextLine();
								try {
									maxKarts = Integer.parseInt(opcion);
									dif = 1;
						        }
						        catch (NumberFormatException e) {
									System.out.println("Formato no valido");
									dif = 0;
								}
							}
							factoryGestor.crearPista(nombre, tipoEstado, dificultad, maxKarts);
							System.out.println("Pista creada");
						}
						break;
					//Crear Kart
					case 2:
						int dif = 0, idKart = 0;
						String opcion;
						while(dif == 0) {
							System.out.println("Introduce el id del kart");
							opcion = scanpistas.nextLine();
							try {
								idKart = Integer.parseInt(opcion);
								dif = 1;
					        }
					        catch (NumberFormatException e) {
								System.out.println("Formato no valido");
								dif = 0;
							}
						}
						if (factoryGestor.existeKart(idKart) != null) {
							System.out.println("El kart ya existe");
						}
						else {
							Estados estado = Estados.DISPONIBLE;
							dif = 0;
							while((dif != 1) && (dif != 2) && (dif != 3)) {
								System.out.println("Introduce el estado del kart");
								System.out.println("1. Disponible");
								System.out.println("2. Reservado");
								System.out.println("3. Mantenimiento");
								opcion = scanpistas.nextLine();
								try {
									dif = Integer.parseInt(opcion);
						        }
						        catch (NumberFormatException e) {
									System.out.println("Formato no valido");
								}
							}
							if(dif == 2) {
								estado=Estados.RESERVADO;
							}
							else if(dif == 3) {
								estado=Estados.MANTENIMIENTO;
							}
							boolean tipoKart = false;
							dif = 0;
							while((dif != 1) && (dif != 2)) {
								System.out.println("Introduce el tipo de kart");
								System.out.println("1. Adulto");
								System.out.println("2. Infantil");
								opcion = scanpistas.nextLine();
								try {
									dif = Integer.parseInt(opcion);
						        }
						        catch (NumberFormatException e) {
									System.out.println("Formato no valido");
								}
							}
							if(dif == 1) {
								tipoKart=true;
							}
							factoryGestor.crearKart(idKart,tipoKart, estado);
							System.out.println("Kart creado");
						}
						break;
					//Asociar kart a pista
					case 3:
						System.out.println("Introduce el nombre de la pista");
						String opcion1 = scanpistas.nextLine();
						PistaDTO pista;
						if ((pista = factoryGestor.existePista(opcion1)) != null) {
							int a = 0, id = 0;
							while (a == 0) {
								System.out.println("Introduce el id del kart");
								opcion1 = scanpistas.nextLine();
								try {
									id = Integer.parseInt(opcion1);
									a = 1;
						        }
						        catch (NumberFormatException e) {
									System.out.println("Formato no valido");
									a = 0;
								}
							}
							KartDTO kart;
							if ((kart = factoryGestor.existeKart(id)) != null) {
								if (pista.getListaKarts().size() < pista.getMaxKarts()) {
									if(pista.getDificultad()== Dificultades.ADULTOS && kart.getTipoKart() == true){
										if (factoryGestor.tienepista(kart)) {
											PistaDTO pista1 = factoryGestor.existePista(kart.getnombrePista());
											factoryGestor.eliminarkart(kart,pista1);
										}
										pista.asociarKartPista(kart, pista);
										System.out.println("Kart asociado");
									}
									else if(pista.getDificultad()== Dificultades.INFANTIL && kart.getTipoKart() == false){
										if (factoryGestor.tienepista(kart)) {
											PistaDTO pista1 = factoryGestor.existePista(kart.getnombrePista());
											factoryGestor.eliminarkart(kart,pista1);
										}
										pista.asociarKartPista(kart, pista);
										System.out.println("Kart asociado");
									}
									else if(pista.getDificultad()== Dificultades.FAMILIAR){
										if (factoryGestor.tienepista(kart)) {
											PistaDTO pista1 = factoryGestor.existePista(kart.getnombrePista());
											factoryGestor.eliminarkart(kart,pista1);
										}
										pista.asociarKartPista(kart, pista);
										System.out.println("Kart asociado");
									}
									else {
										System.out.println("Kart no valido para esta pista");
									}
								}
								else {
									System.out.println("La pista esta llena");
								}
							}
							else {
								System.out.println("El kart no existe");
							}
						}
						else {
							System.out.println("La pista no existe");
						}
						break;
					//Mostrar pistas que estan en mantenimiento
					case 4:
						System.out.println("Pistas en mantenimiento:");
						ArrayList<PistaDTO> pistas = factoryGestor.listarPistasmantenimiento();
						for (PistaDTO a : pistas) {
							System.out.println(a.toString());
						}
						break;
					//Mostrar pistas libres para un numero de karts
					case 5:
						int numkarts = 0, a = 0;
						String opcion2;
						while(a == 0) {
							System.out.println("Introduce el numero de karts");
							opcion2 = scanpistas.nextLine();
							try{
								numkarts = Integer.parseInt(opcion2);
								a = 1;
							}
							catch (NumberFormatException e) {
								System.out.println("Formato no valido");
								a = 0;
							}
						}
						Dificultades dificultad = Dificultades.FAMILIAR;
						a = 0;
						while((a != 1) && (a != 2) && (a != 3)){
							System.out.println("Quiere que la pista sea:");
							System.out.println("1. Familiar");
							System.out.println("2. Adulto");
							System.out.println("3. Infantil");
							opcion2 = scanpistas.nextLine();
							try{
								a = Integer.parseInt(opcion2);
							}
							catch (NumberFormatException e) {
								System.out.println("Formato no valido");
							}
						}
						if(a == 2) {
							dificultad=Dificultades.ADULTOS;
						}
						else if(a == 3) {
							dificultad=Dificultades.INFANTIL;
						}
						ArrayList<PistaDTO> disponibles = factoryGestor.pistasDisponibles(numkarts, dificultad);
						a = 0;
						for (PistaDTO b : disponibles) {
							System.out.println(b.toString());
							a++;
						}
						if(a == 0) {
							System.out.println("No existe ninguna pista que cumpla los requisitos");
						}
						break;
					case 6:
						break;
					default:
						System.out.println("Opcion no valida");
				}	
			}
			catch (NumberFormatException e) {
				System.out.println("Formato no valido");
			}
		}
		
	}
}