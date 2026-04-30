/**
 *
 */
package Negocio.Productora;

import java.util.List;


public interface SAProductora {

	public TProductora mostrar(int id);


	public int modificar(TProductora tProductora);


	public int eliminar(int id);


	public List<TProductora> mostrarTodas();


	public List<TProductora> mostrarPorNacionalidad(int idNacionalidad);


	public int crear(TProductora tProductora);
}