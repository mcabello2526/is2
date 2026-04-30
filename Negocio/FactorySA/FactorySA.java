/**
 *
 */
package Negocio.FactorySA;

import Negocio.Cliente.SACliente;
import Negocio.Genero.SAGenero;
import Negocio.Nacionalidad.SANacionalidad;
import Negocio.Pelicula.SAPelicula;
import Negocio.Productora.SAProductora;
import Negocio.Trabajador.SATrabajador;
import Negocio.Venta.SAVenta;


public abstract class FactorySA {
	
	private static FactorySA instance;

	
	public static FactorySA getInstance() {
		if(instance == null) {
			instance = new FactorySAImp();
		}
		
		return instance;
	}

	
	public abstract SACliente getSACliente();

	
	public abstract SAGenero getSAGenero();

	
	public abstract SANacionalidad getSANacionalidad();

	
	public abstract SAPelicula getSAPelicula();

	
	public abstract SAProductora getSAProductora();

	
	public abstract SATrabajador getSATrabajador();

	
	public abstract SAVenta getSAVenta();
	
}