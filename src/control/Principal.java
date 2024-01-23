package control;

import java.util.ArrayList;
import java.util.List;

import modelo.data.Programa;
import modelo.data.ProgramaJava;
import modelo.data.ProgramaPython;

//Este proyecto es una prueba de concepto

public class Principal {
	public static String carpetaProyecto; 
	public static String rutaProyecto;
	public static String nombreClase;
	public static List<String> codigo;

	public static void main(String[] args) {
		pruebaEstructurada();
		pruebaEstructurada2();
	}

	private static void pruebaEstructurada() {
		carpetaProyecto = vista.interfaces.seleccioneCarpetaProyecto();
		rutaProyecto = vista.interfaces.seleccioneRutaProyecto();
		nombreClase = vista.interfaces.seleccioneNomClase();
	    
		Programa p1 = null;
		Programa p2 = null;
		
		switch (vista.interfaces.seleccioneLenguaje()) {
			case "java" -> p1 = new ProgramaJava(carpetaProyecto,nombreClase,rutaProyecto,codigo);
			case "python" -> p1 = new ProgramaPython(carpetaProyecto,nombreClase,rutaProyecto,codigo);
		}
	    
	    p1.programar();
		p1.compilar();
		p1.verCodigo();
	}
	private static void pruebaEstructurada2() {
		nombreClase = vista.interfaces.seleccioneNomClase();
	    
		Programa p1 = null;
		
		switch (vista.interfaces.seleccioneLenguaje()) {
			case "java" -> p1 = new ProgramaJava(carpetaProyecto,nombreClase,rutaProyecto,codigo);
			case "python" -> p1 = new ProgramaPython(carpetaProyecto,nombreClase,rutaProyecto,codigo);
		}
	    
	    p1.programar();
		p1.compilar();
		p1.verCodigo();
	}
}
