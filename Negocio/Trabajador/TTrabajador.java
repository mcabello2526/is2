/**
 *
 */
package Negocio.Trabajador;


public class TTrabajador {
	
	private int id;
	private String nombre;
	private float horasTotales;
	private boolean activo;
	private boolean esTiempoCompleto;
	
	public TTrabajador() {}
	
	public TTrabajador(int id, String nombre, float horasTotales, boolean activo, boolean esTiempoCompleto) {
		this.id = id;
		this.nombre = nombre;
		this.horasTotales = horasTotales;
		this.activo = activo;
		this.esTiempoCompleto = esTiempoCompleto;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public float getHorasTotales() {
		return horasTotales;
	}
	public void setHorasTotales(float horasTotales) {
		this.horasTotales = horasTotales;
	}

	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	public boolean getEsTiempoCompleto() {
		return esTiempoCompleto;
	}
	public void setEsTiempoCompleto(boolean esTiempoCompleto) {
		this.esTiempoCompleto = esTiempoCompleto;
	}
}