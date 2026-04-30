/**
 *
 */
package Negocio.Pelicula;

import java.util.List;

public interface SAPelicula {
	
	public int crear(TPelicula tPelicula);
	public TPelicula mostrar(int id);
	public int modificar(TPelicula tPelicula);
	public int eliminar(int id);
	public List<TPelicula> mostrarTodo();
	public List<TPelicula> mostrarPeliculasPorGenero(int idGenero);
	public List<TPelicula> mostrarPeliculasPorProductora(int idProductora);
	public int vincularPeliculaAGenero(int idPelicula, int idGenero);
	public int desvincularPeliculaAGenero(int idPelicula, int idGenero);
	public TPelicula mostrarPorNombre(String nombre);
	
}