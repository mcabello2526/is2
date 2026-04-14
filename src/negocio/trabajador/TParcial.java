package negocio.trabajador;

public class TParcial extends TTrabajador {
	private String jornada; 
	private float horasExtras; 

	public TParcial(int id, String nom, float horas, boolean activo, String jornada, float horasExtra) {
		super(id, nom, horas, activo);

		this.jornada = jornada; 
		this.horasExtras = horasExtra; 
		
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
	
	public void setHorasExtras(float horas) {
		this.horasExtras = horas; 
	}

}
