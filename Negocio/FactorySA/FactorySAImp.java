/**
 *
 */
package Negocio.FactorySA;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.SAClienteImp;
import Negocio.Genero.SAGenero;
import Negocio.Genero.SAGeneroImp;
import Negocio.Nacionalidad.SANacionalidad;
import Negocio.Nacionalidad.SANacionalidadImp;
import Negocio.Pelicula.SAPelicula;
import Negocio.Pelicula.SAPeliculaImp;
import Negocio.Productora.SAProductora;
import Negocio.Productora.SAProductoraImp;
import Negocio.Trabajador.SATrabajador;
import Negocio.Trabajador.SATrabajadorImp;
import Negocio.Venta.SAVenta;
import Negocio.Venta.SAVentaImp;


public class FactorySAImp extends FactorySA {

	@Override
	public  SACliente getSACliente() {
		return new SAClienteImp();
	}

	@Override
	public  SAGenero getSAGenero() {
		return new SAGeneroImp();
	}

	@Override
	public  SANacionalidad getSANacionalidad() {
		return new SANacionalidadImp();
	}

	@Override
	public  SAPelicula getSAPelicula() {
		return new SAPeliculaImp();
	}

	@Override
	public  SAProductora getSAProductora() {
		return new SAProductoraImp();
	}

	@Override
	public  SATrabajador getSATrabajador() {
		return new SATrabajadorImp();
	}

	@Override
	public  SAVenta getSAVenta() {
		return new SAVentaImp();
	}
}