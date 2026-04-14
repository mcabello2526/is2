package integracion.factoryDAO;

import integracion.trabajador.DAOTrabajador;
import integracion.trabajador.DAOTrabajadorImp;

public class FactoryDAOImp extends FactoryDAO {

	@Override
	public DAOTrabajador getDAOTrabajador() {
		return new DAOTrabajadorImp();
	}

}
