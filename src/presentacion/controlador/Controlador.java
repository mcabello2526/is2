package presentacion.controlador;

public abstract class Controlador {
	
	private static Controlador instance; 
	
	public static Controlador getInstance() {
		return instance; 
	}
	
	public abstract void accion(); 

}
