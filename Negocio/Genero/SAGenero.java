/**
 *
 */
package Negocio.Genero;

import java.util.List;


public interface SAGenero {
	
	public int crear(TGenero tGenero);

	
	public TGenero mostrar(int id);

	
	public int modificar(TGenero tGenero);

	
	public List<TGenero> mostrarTodo();

	
	public int eliminar(int id);


	public List<TGenero> mostrarPorPelicula(int idPelicula);
}