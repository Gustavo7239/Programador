package vista;

import java.util.List;
import java.util.Scanner;

public class interfaces {
	 
    public static void verItemsApilados(List<String> items, int width, String lenguaje) {
    	for (int i = 0; i < width; i++) System.out.print("_"); System.out.println();
    	System.out.println("| " + lenguaje + getSpaces(width - 4 - lenguaje.length()) + " |");
    	for (int i = 0; i < width; i++) System.out.print("-"); System.out.println();
    	
        for (String item : items) {
            int spaces = width - 4 - item.length();
            System.out.println("| " + item + getSpaces(spaces) + " |");
        }
        
        for (int i = 0; i < width; i++) System.out.print("‾"); System.out.println();
    }
    
    private static String getSpaces(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }
    
    public static String seleccioneNomClase() {
		String Resultado=""; boolean continuar=false;
		Scanner teclado = new Scanner(System.in);
		while(!continuar) {
			System.out.println("Introduzca el nombre de su clase:");
			System.out.print("> ");
			Resultado=teclado.nextLine();
			if(Resultado!="") {
				continuar = true;
			}else System.out.println("Nombre de la clase no introducido");
		}
		return Resultado;
	}

    public static String seleccioneRutaProyecto() {
		String Resultado=""; boolean continuar=false;
		Scanner teclado = new Scanner(System.in);
		while(!continuar) {
			System.out.println("Introduzca la ruta del Proyecto:");
			System.out.print("> ");
			Resultado=teclado.nextLine();
			if(Resultado.toLowerCase().equals("[@desktop]")) {
				Resultado = "C:\\Users\\alumno2024\\Desktop\\";
			}else if(Resultado.toLowerCase().equals("[@pruebas]")) {
				Resultado = "C:\\Users\\alumno2024\\Desktop\\PruebasProgramador\\";
			}
			
			if(Resultado!="") {
				continuar = true;
			}else System.out.println("Nombre de la ruta no introducido");
		}
		System.out.println("Su ruta es: "+Resultado);
		return Resultado;
	}

	public static String seleccioneCarpetaProyecto() {
		String Resultado=""; boolean continuar=false;
		Scanner teclado = new Scanner(System.in);
		while(!continuar) {
			System.out.println("Introduzca el nombre del Proyecto:");
			System.out.print("> ");
			Resultado=teclado.nextLine();
			if(Resultado!="") {
				continuar = true;
			}else System.out.println("Nombre de fichero no introducido");
		}
		
		return Resultado;
	}
	
	public static String seleccioneLenguaje() {
		String leng = ""; boolean continuar=false;
		Scanner teclado = new Scanner(System.in);
		String Resultado = "";
		
		while(!continuar) {
			System.out.println("Introduzca el lenguaje de programacion:");
			System.out.print("> ");
			Resultado=teclado.nextLine();
			
			if(Resultado.toLowerCase().equals("java")) {
				leng = "java";
				continuar = true;
			}
			else if(Resultado.toLowerCase().equals("python")) {
				leng = "python";
				continuar = true;
			}
			else {
				System.out.println("[ERROR]: Lenguaje no reconocido.");
			}
		}
		
		return leng;
	}
    
}
