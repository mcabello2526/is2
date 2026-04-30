/**
 *
 */
package Integracion.Cliente;

import java.util.List;

import Negocio.Cliente.TCliente;

public interface DAOCliente  {
	
	public int crear(TCliente tCliente);
	public TCliente mostrar(int id);
	public int modificar(TCliente tCliente);
	public int eliminar(int id);
	public List<TCliente> mostrarTodo();
}