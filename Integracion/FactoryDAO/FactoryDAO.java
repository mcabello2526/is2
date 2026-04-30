/**
 *
 */
package Integracion.FactoryDAO;

import Integracion.Cliente.DAOCliente;
import Integracion.Genero.DAOGenero;
import Integracion.Nacionalidad.DAONacionalidad;
import Integracion.Pelicula.DAOPelicula;
import Integracion.Pelicula.DAOPeliculaGenero;
import Integracion.Productora.DAOProductora;
import Integracion.Trabajador.DAOTrabajador;
import Integracion.Venta.DAOVenta;
import Integracion.Venta.DAOVentaPelicula;

public abstract class FactoryDAO {
	
	private static FactoryDAOImp instance;

	
	public static FactoryDAO getInstance() {
		if(instance == null) {
			instance = new FactoryDAOImp();
		}
		return instance;
	}

	
	public abstract DAOVentaPelicula getDAOVentaPelicula();

	
	public abstract DAOPeliculaGenero getDAOPeliculaGenero();

	
	public abstract DAOPelicula getDAOPelicula();

	
	public abstract DAOTrabajador getDAOTrabajador();

	
	public abstract DAOVenta getDAOVenta();

	
	public abstract DAONacionalidad getDAONacionalidad();

	
	public abstract DAOGenero getDAOGenero();

	
	public abstract DAOCliente getDAOCliente();

	
	public abstract DAOProductora getDAOProductora();
}