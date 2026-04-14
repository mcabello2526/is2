package integracion.trabajador;

import java.util.List;

import negocio.trabajador.TTrabajador;

public interface DAOTrabajador {
	public int crear(TTrabajador tTrabajador); 
	public int eliminar (int id); 
	public TTrabajador mostrar(int id); 
	public List<TTrabajador> mostrarTodos(); 
	public int modificar (TTrabajador tTrabajador); 
	
}
