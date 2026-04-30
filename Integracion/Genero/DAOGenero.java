/**
 *
 */
package Integracion.Genero;

import java.util.List;

import Negocio.Genero.TGenero;


public interface DAOGenero {
	
	public int crear(TGenero tGenero);


	public List<TGenero> mostrarTodo();

	
	public TGenero mostrar(int id);

	
	public int modificar(TGenero tGenero);

	
	public int eliminar(int id);

	
	public List<TGenero> mostrarPorPelicula(int idPelicula);
}