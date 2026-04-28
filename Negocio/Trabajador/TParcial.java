
package Negocio.Trabajador;


public class TParcial extends TTrabajador {
	
	private String jornada;
	private float horasExtras;
	
	
	public TParcial() {
		super();
	}
	
	public TParcial(int id, String nombre, float horasTotales, boolean activo, boolean esTiempoCompleto, String jornada, float horasExtras) {
		super(id, nombre, horasTotales, activo, esTiempoCompleto);
		this.jornada = jornada;
		this.horasExtras = horasExtras;
	}

	
	public String getJornada() {
		return jornada;
	}

	
	public float getHorasExtra() {
		return horasExtras;
	}

	
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	
	public void setHorasExtra(float horasExtras) {
		this.horasExtras = horasExtras;
	}
}