/**
 *
 */
package Negocio.Venta;

import java.util.List;

public class TVentaPelicula {
	
	//claves primarias de las entidades 
	//private int idVenta;  // como va a tener el carrito el id de la venta si no se ha procesado todavia 
	private int idPelicula;
	
	private String nombrePeli;  // para buscar por nombre no se si es necesario 
	
	private boolean activo;
	
	// atributos de la relacion 
	private float precioPelicula; 
	private int numCopias;
	
	
	public TVentaPelicula() {}
	
	public TVentaPelicula (int idPelicula, boolean activo, float precioPelicula, int numCopias) {
		//this.idVenta = idVenta;
		this.idPelicula = idPelicula;
		
		this.activo = activo;
		
		this.precioPelicula = precioPelicula;
		this.numCopias = numCopias;
	}

/*
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
*/
	
	public int getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo (boolean activo) {
		this.activo = activo;
	}
	
	
	public float getPrecioPelicula() {
		return precioPelicula;
	}
	public void setPrecioPelicula(float precioPelicula) {
		this.precioPelicula = precioPelicula;
	}
	
	
	public int getNumCopias() {
		return numCopias;
	}
	public void setNumCopias(int numCopias) {
		this.numCopias = numCopias;
	}
	
	@Override
	public String toString() {
		return "Carrito [ID de la pelicula = " + idPelicula + ", número de copias = " + numCopias + "]"; 
	}

}