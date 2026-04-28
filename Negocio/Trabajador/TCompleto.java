/**
 *
 */
package Negocio.Trabajador;


public class TCompleto extends TTrabajador {
	
	private int anioIncorporacion;
	private float bonificacionAnual;
	
	
	public TCompleto() {
		super();
	}
	
	public TCompleto(int id, String nombre, float horasTotales, boolean activo, boolean esTiempoCompleto, int anioIncorporacion, float bonificacionAnual) {
		super(id, nombre, horasTotales, activo, esTiempoCompleto);
		this.anioIncorporacion = anioIncorporacion;
		this.bonificacionAnual = bonificacionAnual;
	}

	
	public int getAnio() {
		return anioIncorporacion;
	}

	
	public float getBonificacion() {
		return bonificacionAnual;
	}

	
	public void setAnio(int anioIncorporacion) {
		this.anioIncorporacion = anioIncorporacion;
	}

	
	public void setBonificacion(float bonificacionAnual) {
		this.bonificacionAnual = bonificacionAnual;
	}


}