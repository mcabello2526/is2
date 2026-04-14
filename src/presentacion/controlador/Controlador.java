package presentacion.controlador;

public abstract class Controlador {
	
	private static Controlador instance; 
	
	public Controlador () {
		if (instance == null) {
			instance = new ControladorImp(); 

		}
	}
	
	public static Controlador getInstance() {
		return instance; 
	}
	
	public abstract void accion(int eventos, Object datos); 

}
