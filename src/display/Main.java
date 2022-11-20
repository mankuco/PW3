package display;

import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	
       Scanner scanmain = new Scanner(System.in);
        
       int num=0;
  
       while(num!=4) {
			System.out.println("MENU PRINCIPAL");
			System.out.println("1. Ir al menu de usuarios");
			System.out.println("2. Ir al menu de pistas");
			System.out.println("3. Ir al menu de reservas");
			System.out.println("4. Salir");
	        String opcion = scanmain.nextLine();
	        
	        try{
				num = Integer.parseInt(opcion);
			}
			catch (NumberFormatException e) {
				System.out.print("Formato no valido");
			}
        	
            switch (num) {
                case 1:
                    ProgramaUsuarios.mainUsuarios();
                    break;
                case 2:
                    ProgramaPistas.mainPistas();
                    break;
                case 3:
                    ProgramaReservas.mainReservas();
                    break;
                case 4:
                	System.exit(num);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
       scanmain.close();
    }
 }
