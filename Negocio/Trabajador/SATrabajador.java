/**
 *
 */
package Negocio.Trabajador;

import java.util.List;


public interface SATrabajador {

	public int crear(TTrabajador tTrabajador);

	
	public List<TTrabajador> mostrarTodos();


	public TTrabajador mostrar(int id);


	public int modificar(TTrabajador tTrabajador);


	public int eliminar(int id);
}