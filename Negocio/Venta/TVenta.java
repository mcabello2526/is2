/**
 *
 */
package Negocio.Venta;

import java.util.List;

public class TVenta {
	
	private int id;
	private String fechaVenta;
	private float precioTotal;
	private boolean activo;
	private int idCliente;
	private int idTrabajador;
	List <TVentaPelicula> lineaCarrito; 
	
	
	public TVenta() {}
	
	public TVenta(int id, String fechaVenta, float precioTotal, boolean activo, int idCliente, int idTrabajador) {
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.precioTotal = precioTotal;
		this.activo = activo;
		this.idCliente = idCliente;
		this.idTrabajador = idTrabajador;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	
	public int getIdTrabajador() {
		return idTrabajador;
	}
	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}
	
	public List<TVentaPelicula> getLineaCarrito(){
		return this.lineaCarrito; 
	}
	public void setLineaCarrito(List<TVentaPelicula> carrito) {
		this.lineaCarrito = carrito; 
	}
		
}