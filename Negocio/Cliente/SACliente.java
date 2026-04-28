/**
 *
 */
package Negocio.Cliente;

import java.util.List;


public interface SACliente {
	
	public int crear(TCliente tCliente);
	public TCliente mostrar(int id);
	public int modificar(TCliente tCliente);
	public int eliminar(int id);
	public List<TCliente> mostrarTodos();
	
}