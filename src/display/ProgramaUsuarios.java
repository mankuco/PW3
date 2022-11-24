package display;

import java.time.LocalDate;
import java.util.Scanner;

import business.Reserva.GestorReservas;
import business.Usuario.GestorUsuario;


	public class ProgramaUsuarios {

		public static void mainUsuarios() {
			
			GestorUsuario factoryGestor = new GestorUsuario();
			Scanner scan = new Scanner(System.in);

			int num = 0;
			while(num!=5) {
				
				System.out.println("BIENVENIDO AL PROGRAMA DE USUARIOS");
				System.out.println("1. Anadir usuario");
				System.out.println("2. Modificar usuario");
				System.out.println("3. Listar usuarios");
				System.out.println("4. Eliminar usuario");
				System.out.println("5. Salir");
				String opcion = scan.nextLine();
		        
		        try{
					num = Integer.parseInt(opcion);
				}
				catch (NumberFormatException e) {
					System.out.print("Formato no valido");
				}
		
			    switch (num) {
			        case 1:
			        	factoryGestor.altaUsuario();
			            break;
			        case 2:
			        	factoryGestor.modificarUsuario();
			            break;
			        case 3:
			        	factoryGestor.listarUsuarios();
			            break;
			        case 4:
	                    factoryGestor.eliminarUsuario();
	                    break;
			        case 5:
			            break;
			        default:
			            System.out.println("Opcion no valida");
			            break;
			    }
			}
		}
	}

