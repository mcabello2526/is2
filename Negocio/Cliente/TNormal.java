/**
 *
 */
package Negocio.Cliente;


public class TNormal extends TCliente {

	private int numCopias;

	public TNormal() {
		super();
	}
	
	public TNormal(int id, String nombre, String apellidos, String correo, boolean activo, boolean esVip, int numCopias) {
		super(id, nombre, apellidos, correo, activo, esVip);
		this.numCopias = numCopias;
	}


	
	public int getNumCopias() {
		return numCopias;
	}

	
	public void setNumCopias(int numCopias) {
		this.numCopias = numCopias;
	}
}