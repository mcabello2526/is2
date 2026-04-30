/**
 *
 */
package Integracion.FactoryDAO;

import Integracion.Cliente.DAOCliente;
import Integracion.Cliente.DAOClienteImp;
import Integracion.Genero.DAOGenero;
import Integracion.Genero.DAOGeneroImp;
import Integracion.Nacionalidad.DAONacionalidad;
import Integracion.Nacionalidad.DAONacionalidadImp;
import Integracion.Pelicula.DAOPeliculaImp;
import Integracion.Pelicula.DAOPelicula;
import Integracion.Pelicula.DAOPeliculaGenero;
import Integracion.Pelicula.DAOPeliculaGeneroImp;
import Integracion.Productora.DAOProductora;
import Integracion.Productora.DAOProductoraImp;
import Integracion.Trabajador.DAOTrabajador;
import Integracion.Trabajador.DAOTrabajadorImp;
import Integracion.Venta.DAOVenta;
import Integracion.Venta.DAOVentaImp;
import Integracion.Venta.DAOVentaPelicula;
import Integracion.Venta.DAOVentaPeliculaImp;

public class FactoryDAOImp extends FactoryDAO{
	@Override
	public DAOVentaPelicula getDAOVentaPelicula() {
		return new DAOVentaPeliculaImp();
	}

	@Override
	public DAOPeliculaGenero getDAOPeliculaGenero() {
		return new DAOPeliculaGeneroImp();
	}

	@Override
	public DAOPelicula getDAOPelicula() {
		return new DAOPeliculaImp();
	}

	@Override
	public DAOTrabajador getDAOTrabajador() {
		return new DAOTrabajadorImp();
	}

	@Override
	public DAOVenta getDAOVenta() {
		return new DAOVentaImp();
	}

	@Override
	public DAONacionalidad getDAONacionalidad() {
		return new DAONacionalidadImp();
	}

	@Override
	public DAOGenero getDAOGenero() {
		return new DAOGeneroImp();
	}

	@Override
	public DAOCliente getDAOCliente() {
		return new DAOClienteImp();
	}

	@Override
	public DAOProductora getDAOProductora() {
		return new DAOProductoraImp();
	}
}