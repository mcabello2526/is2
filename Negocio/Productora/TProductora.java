
package Negocio.Productora;


public class TProductora {
	
	private int id;
	private String nombre;
	private int anioCreacion;
	private boolean activo;
	private int idNacionalidad;
	
	
	public TProductora() {}
	
	public TProductora(int id, String nombre, int anioCreacion, boolean activo, int idNacionalidad) {
		this.id = id;
		this.nombre = nombre;
		this.anioCreacion = anioCreacion;
		this.activo = activo;
		this.idNacionalidad = idNacionalidad;
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

	
	public int getAnioCreacion() {
		return anioCreacion;
	}
	public void setAnioCreacion(int anioCreacion) {
		this.anioCreacion = anioCreacion;
	}

	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
}