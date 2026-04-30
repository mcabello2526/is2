
package Negocio.Nacionalidad;

import java.util.List;


public interface SANacionalidad {

	public int crear(TNacionalidad tNacionalidad);
	public TNacionalidad mostrar(int id);
	public int modificar(TNacionalidad tNacionalidad);
	public int eliminar(int id);
	public List<TNacionalidad> mostrarTodo();
}