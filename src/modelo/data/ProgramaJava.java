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

	public ProgramaJava(String nombreProyecto, String nombreClase, String ruta, List<String> codigo) {
		super(nombreProyecto, nombreClase, ruta, codigo);
		compruebaRuta();
		setRutaSrc(ObtenerRutaSrc());
		setRutaBin(ObtenerRutaBin());
		
		generaArchivo();
	}
	
	@Override
	public String ObtenerRutaSrc() {
		return getRuta()+"\\"+getNombreProyecto()+"\\src\\";
	}
	
	@Override
	public String ObtenerRutaBin() {
		return getRuta()+"\\"+getNombreProyecto()+"\\bin\\";
	}
	
	@Override
	public void compilar() {
		ProcessBuilder pb = new ProcessBuilder();
	    pb.redirectErrorStream(true);
	    pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
	    try {
	        /* Compilamos */
	        pb.directory(new File(getRutaSrc()));
	        pb.command("javac", getNombreClase() + ".java", "-d", getRutaBin());
	        
	        // Esperamos 1 segundo para dar tiempo a generar el .class
	        pb.start();
	        Thread.sleep(1000);
	        
	        // Ejecutamos la clase
	        pb.command("java.exe", "-cp", getRutaBin(), getNombreClase());
	        String resultado = pb.toString();
	        pb.start();
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void generaArchivo() {
		if (getCodigo() == null || getCodigo().isEmpty()) {
            System.err.println("La lista de código está vacía.");
            return;
        }

        // Asegúrate de que la ruta termine con una barra diagonal (o diagonal invertida en Windows)
        if (!getRutaSrc().endsWith(File.separator)) {
        	setRutaSrc(getRutaSrc() + File.separator);
        }

        // Nombre del archivo .java (puedes personalizarlo)
        String nombreArchivo = getNombreClase()+".java";

        try {
            // Crea el archivo en la ruta especificada
        	System.out.println(getRutaSrc());
            File archivo = new File(getRutaSrc() + nombreArchivo);
            FileWriter escritor = new FileWriter(archivo);

            // Escribe el contenido de la lista de código en el archivo
            for (String linea : getCodigo()) {
                escritor.write(linea + "\n");
            }

            escritor.close();
            System.out.println("Archivo Java creado con éxito en " + getRutaSrc() + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al crear el archivo Java.");
        }
		
	}

	@Override
	public String getRutaSrc() {
		return getRuta()+"\\"+getNombreProyecto()+"\\src\\";
	}
	
	@Override
	public String getRutaBin() {
		return getRuta()+"\\"+getNombreProyecto()+"\\bin\\";
	}
	
	@Override
	public void compruebaRuta() {
		// Convierte la ruta en un objeto Path
        Path path = Paths.get(getRuta(), getNombreProyecto());

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
		List<String> items = getCodigo();
		
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
            	listaDeCodigo.add("public class "+getNombreClase()+" {");
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
        setCodigo(listaDeCodigo);
        generaArchivo();
	}

}
