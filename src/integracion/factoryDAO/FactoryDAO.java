package integracion.factoryDAO;

import integracion.trabajador.DAOTrabajador;

public abstract class FactoryDAO {
	private static FactoryDAO instance; 
	
	public static FactoryDAO getInstance() {
		return instance;
		
	}
	
	public abstract DAOTrabajador getDAOTrabajador(); 
	
	// resto de getters de los modulos 
}
