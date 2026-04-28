/**
 *
 */
package Negocio.Cliente;


public class TVip extends TCliente {
	
	private int descuento;
	
	public TVip() {
		super();
	}
	
	public TVip(int id, String nombre, String apellidos, String correo, boolean activo, boolean esVip, int descuento) {
		super(id, nombre, apellidos, correo, activo, esVip);
		this.descuento = descuento;
	}
	
	
	public int getDescuento() {
		return descuento;
	}	
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
}