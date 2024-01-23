package modelo.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramaJava extends Programa{

	private String ruta;
	private String nombreProyecto;
	//private String Tipo;
	private List<String> codigo;
	private String nombreClase;
	
	private String rutaSrc;
	private String rutaBin;
	
	public ProgramaJava(String nombreProyecto, String nombreClase, String ruta, List<String> codigo) {
		super();
		this.nombreProyecto = nombreProyecto;
		this.nombreClase=nombreClase;
		this.ruta = ruta;
		compruebaRuta();
		this.rutaSrc=ruta+"\\"+nombreProyecto+"\\src\\";
		this.rutaBin=ruta+"\\"+nombreProyecto+"\\bin\\";
		
		this.codigo=codigo;
		generaArchivo();
	}
	
	@Override
	public void compilar() {
		ProcessBuilder pb = new ProcessBuilder();
	    pb.redirectErrorStream(true);
	    pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
	    try {
	        /* Compilamos */
	        pb.directory(new File(rutaSrc));
	        pb.command("javac", nombreClase + ".java", "-d", rutaBin);
	        
	        // Esperamos 1 segundo para dar tiempo a generar el .class
	        pb.start();
	        Thread.sleep(1000);
	        
	        // Ejecutamos la clase
	        pb.command("java.exe", "-cp", rutaBin, nombreClase);
	        String resultado = pb.toString();
	        pb.start();
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void generaArchivo() {
		if (codigo == null || codigo.isEmpty()) {
            System.err.println("La lista de código está vacía.");
            return;
        }

        // Asegúrate de que la ruta termine con una barra diagonal (o diagonal invertida en Windows)
        if (!rutaSrc.endsWith(File.separator)) {
        	rutaSrc = rutaSrc + File.separator;
        }

        // Nombre del archivo .java (puedes personalizarlo)
        String nombreArchivo = nombreClase+".java";

        try {
            // Crea el archivo en la ruta especificada
        	System.out.println(rutaSrc);
            File archivo = new File(rutaSrc + nombreArchivo);
            FileWriter escritor = new FileWriter(archivo);

            // Escribe el contenido de la lista de código en el archivo
            for (String linea : codigo) {
                escritor.write(linea + "\n");
            }

            escritor.close();
            System.out.println("Archivo Java creado con éxito en " + rutaSrc + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al crear el archivo Java.");
        }
		
	}

	@Override
	public String getRutaSrc() {
		return ruta+"\\"+nombreProyecto+"\\src\\";
	}
	
	@Override
	public String getRutaBin() {
		return ruta+"\\"+nombreProyecto+"\\bin\\";
	}
	
	@Override
	public void compruebaRuta() {
		// Convierte la ruta en un objeto Path
        Path path = Paths.get(ruta, nombreProyecto);

        try {
            // Crea los directorios y los archivos intermedios si no existen
            Files.createDirectories(path);
            System.out.println("Directorio creado con éxito: " + path);

            // Crea las carpetas "bin" y "src" dentro de la ruta principal
            Path binPath = Paths.get(path.toString(), "bin");
            Path srcPath = Paths.get(path.toString(), "src");
            Files.createDirectories(binPath);
            Files.createDirectories(srcPath);
            System.out.println("Carpetas 'bin' y 'src' creadas dentro de la ruta.");
        } catch (IOException e) {
            System.err.println("Error al crear el directorio: " + e.getMessage());
        }
	}
	
	@Override
	public void verCodigo() {
		List<String> items = codigo;
		
        int maxWidth = 0;
        for (String item : items) {
            if (item.length() > maxWidth) {
                maxWidth = item.length();
            }
        }

        // Añadir márgenes a la ventana
        maxWidth += 4; // 2 caracteres de margen a cada lado

        int maxHeight = items.size() + 2; // 2 filas de margen arriba y abajo

        // Dibuja la ventana
        vista.interfaces.verItemsApilados(items, maxWidth, "JAVA");
	}

	@Override
	public void programar() {
		List<String> listaDeCodigo = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        listaDeCodigo.add("");

        System.out.println("¡Bienvenido al editor de código! Introduce tus líneas de código.");
        System.out.println("Escribe [@FIN] en una línea para terminar la programación.");
        System.out.println("Algunos comandos utiles: [@INITCLASS]  |  [@INITMAIN]  |  [@PRINT]  |  [@FIN]");
        
        while (true) {
            System.out.print("> ");
            String nuevaLinea = scanner.nextLine();

            if (nuevaLinea.toUpperCase().equals("[@FIN]")) {
                break;
            } 
            else if (nuevaLinea.toUpperCase().equals("[@INITCLASS]")) {
            	listaDeCodigo.add("public class "+nombreClase+" {");
            	for (String linea : listaDeCodigo) {
                    System.out.println(linea);
                }
            }
            else if (nuevaLinea.toUpperCase().equals("[@INITMAIN]")) {
            	listaDeCodigo.add("    public static void main(String[] args) {");
            	for (String linea : listaDeCodigo) {
                    System.out.println(linea);
                }
            }
            else if (nuevaLinea.toUpperCase().equals("[@PRINT]")) {
            	listaDeCodigo.add("System.out.println(");
            	for (String linea : listaDeCodigo) {
                    System.out.println(linea);
                }
            } else if(nuevaLinea.contains("@")){
            	System.out.println("[ERROR]: Comando no encotrado.");
            }
            else {
                listaDeCodigo.add(nuevaLinea);
                System.out.print("> ");
                for (String linea : listaDeCodigo) {
                    System.out.println(linea);
                }
            }
        }

        System.out.println("Programación finalizada.");
        codigo = listaDeCodigo;
        generaArchivo();
	}

}
