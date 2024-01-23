package modelo.data;

import java.util.List;

public abstract class Programa {
	public abstract void compilar();
	public abstract void generaArchivo();
	public abstract String getRutaSrc();
	public abstract String getRutaBin();
	public abstract void compruebaRuta();
	public abstract void verCodigo();
	public abstract void programar();
}
