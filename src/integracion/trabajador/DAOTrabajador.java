package integracion.trabajador;

public interface DAOTrabajador {
	public int crear(TTrabajador tTrabajador); 
	public int eliminar (int id); 
	public TTrabajador mostrar(int id); 
	public List<TTrabajador> mostrarTodos(); 
	public int modificar (TTrabajador tTrabajador); 
	
}
