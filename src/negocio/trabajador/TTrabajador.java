package negocio.trabajador;

public class TTrabajador {
	private int id;
	private String nombre; 
	private float horasTotales; 
	private boolean activo; 
	
	public TTrabajador(int id, String nom, float horas, boolean activo) {
		this.id = id ; 
		this.nombre = nom; 
		this.horasTotales = horas; 
		this.activo = activo; // o lo podemos poner siempre a true y cambiarlo cuando sea y ya 
	}
	
	public int getID() {
		return id ; 
	}
	
	public String getNombre() {
		return nombre; 
	}
	
	public float getHorasTotales() {
		return horasTotales; 
	}
	
	public boolean estaActivo() {
		return activo; 
	}
	
	public void setId (int id) {
		this.id = id; 
	}
	
	public void setNombre (String nom) {
		this.nombre = nom; 
	}
	
	public void setHorasTotales (float horas) {
		this.horasTotales = horas; 
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo; 
	}

}
