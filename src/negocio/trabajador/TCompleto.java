package negocio.trabajador;

public class TCompleto extends TTrabajador {
	private int anioIncorporacion; 
	private float bonificacionAnual; 

	public TCompleto(int id, String nom, float horas, boolean activo, int anio, int bonificacion) {
		super(id, nom, horas, activo);
		this.anioIncorporacion = anio;
		this.bonificacionAnual= bonificacion; 
		
	}
	
	public int getAnioIncorporacion() {
		return anioIncorporacion; 
	}
	
	public float getBonificacionAnual() {
		return bonificacionAnual; 
	}
	
	public void setAnioIncorporacion(int anio) {
		this.anioIncorporacion = anio; 
	}
	
	public void setBonificacionAnual(float bono) {
		this.bonificacionAnual = bono; 
	}

}
