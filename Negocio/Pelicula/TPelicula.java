/**
 *
 */
package Negocio.Pelicula;

public class TPelicula {
	
	private int id;
	private String nombre;
	private int stock;
	private float precio;
	private boolean activo;
	
	private int idProductora;
	
	public TPelicula() {}
	
	public TPelicula(int id, String nombre, int stock, float precio, int idProductora, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
		this.activo = activo;
		this.idProductora = idProductora;
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

	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	public int getIdProductora() {
		return idProductora;
	}
	public void setIdProductora(int idProductora) {
		this.idProductora = idProductora;
	}
}