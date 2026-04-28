/**
 *
 */
package Negocio.Venta;

import java.util.List;


public interface SAVenta {

	public int abrir(TVenta tVenta);

	public TVenta mostrar(int id);

	public int modificar(TVenta TVenta);

	public int eliminar(int id);

	public int hacerDevolucion(TVenta tVenta);

	public List<TVenta> mostrarTodas();

	public int cerrar(TVenta tVenta);

	public List<TVenta> mostrarPorCiente(int idCliente);

	public List<TVenta> mostrarPorTrabajador(int idTrabajador);

	public int aniadirPeliculaACarrito(TVentaPelicula tVentaPelicula);

	public int eliminarPeliculaDeCarrito(int idVenta, int idPelicula);
}