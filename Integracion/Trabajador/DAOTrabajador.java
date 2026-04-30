
package Integracion.Trabajador;

import java.util.List;

import Negocio.Trabajador.TTrabajador;


public interface DAOTrabajador {

	public int crear(TTrabajador tTrabajador);


	public int eliminar(int id);


	public TTrabajador mostrar(int id);

	
	public List<TTrabajador> mostrarTodo();

	
	public int modificar(TTrabajador tTrabajador);
}