/**
 *
 */
package Negocio.Venta;

public class TVentaPelicula {
	

	private int idVenta;  // Este atributo lo tiene en null hasta hacer cerrar() 
	private int idPelicula;

	private float precioPelicula; 
	private int numCopias;
	private boolean activo;
	//private String nombrePeli;  // para buscar por nombre no se si es necesario
	
	public TVentaPelicula() {}
	
	public TVentaPelicula (int idVenta, int idPelicula, boolean activo, float precioPelicula, int numCopias) {
		this.idVenta = idVenta;
		this.idPelicula = idPelicula;

		this.precioPelicula = precioPelicula;
		this.numCopias = numCopias;
		this.activo = activo;
	}


	public int getIdVenta() {	// Esto se usará solo al hacer mostrar()
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	
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