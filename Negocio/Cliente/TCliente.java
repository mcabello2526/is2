/**
 *
 */
package Negocio.Cliente;


public class TCliente {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String correo;
	private boolean activo;
	private boolean esVip;
	
	public TCliente() {}
	
	public TCliente(int id, String nombre, String apellidos, String correo, boolean activo, boolean esVip) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.activo = activo;
		this.esVip = esVip;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id= id;	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	public boolean getEsVip() {
		return esVip;
	}
	public void setEsVip(boolean esVip) {
		this.esVip = esVip;
	}
}