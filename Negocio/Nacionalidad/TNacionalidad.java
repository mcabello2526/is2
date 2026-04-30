/**
 *
 */
package Negocio.Nacionalidad;


public class TNacionalidad {
	
	private int id;
	private String nombre;
	private String continente;
	private boolean activo;

	public TNacionalidad() {}
	
	public TNacionalidad(int id, String nombre, String continente, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.continente = continente;
		this.activo = activo;
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

	public String getContinente() {
		return continente;
	}
	public void setContinente(String continente) {
		this.continente = continente;
	}

	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}