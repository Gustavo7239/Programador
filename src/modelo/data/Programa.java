package modelo.data;

import java.util.List;

public class Programa {	
	
	private String nombreProyecto;
	private String nombreClase;
	private String ruta;
	private List<String> codigo;
	
	private String rutaSrc;
	private String rutaBin;
	
	public Programa(String nombreProyecto, String nombreClase, String ruta, List<String> codigo) {
		super();
		this.nombreProyecto = nombreProyecto;
		this.nombreClase = nombreClase;
		this.ruta = ruta;
		this.codigo = codigo;
	}
	
	public void compilar() {};
	public void generaArchivo() {};
	public String getRutaSrc() {return "";};
	public String getRutaBin() {return "";};
	public void compruebaRuta() {};
	public void verCodigo() {};
	public void programar() {}
	public String ObtenerRutaSrc() {return "";};
	public String ObtenerRutaBin() {return "";};

	
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public List<String> getCodigo() {
		return codigo;
	}

	public void setCodigo(List<String> codigo) {
		this.codigo = codigo;
	}

	public void setRutaSrc(String rutaSrc) {
		this.rutaSrc = rutaSrc;
	}

	public void setRutaBin(String rutaBin) {
		this.rutaBin = rutaBin;
	}

	@Override
	public String toString() {
		return "Programa [nombreProyecto=" + nombreProyecto + ", nombreClase=" + nombreClase + ", ruta=" + ruta
				+ ", codigo=" + codigo + "]";
	};
	
	
}
