/**
 *
 */
package Negocio.Nacionalidad;

import java.util.ArrayList;
import java.util.List;


import Integracion.FactoryDAO.FactoryDAO;
import Integracion.Nacionalidad.DAONacionalidad;
import Integracion.Pelicula.DAOPelicula;
import Integracion.Venta.DAOVentaPelicula;
import Negocio.Pelicula.TPelicula;


public class SANacionalidadImp implements SANacionalidad {
	
	@Override
	public int crear(TNacionalidad tNacionalidad) {

		if(tNacionalidad != null &&
				tNacionalidad.getNombre() != null &&
				tNacionalidad.getNombre().isEmpty()) {
			
			DAONacionalidad daoNacionalidad = FactoryDAO.getInstance().getDAONacionalidad(); 
			TNacionalidad t = daoNacionalidad.mostrarPorNombre(tNacionalidad.getNombre()); 
			if(t == null) {
				return daoNacionalidad.crear(tNacionalidad); 
			}else {
				if(!t.getActivo()) { 
					tNacionalidad.setId(t.getId());
					tNacionalidad.setActivo(true);
					return daoNacionalidad.modificar(tNacionalidad);
				}else { // 
					return -2;
				}
			}
		}
		return -1;
		
	}

	@Override
	public TNacionalidad mostrar(int id) {
		

		TNacionalidad tNacionalidad = null;
		if(id>0) {
			DAONacionalidad daoNacionalidad = FactoryDAO.getInstance().getDAONacionalidad();
			tNacionalidad = daoNacionalidad.mostrar(id);
			if(tNacionalidad != null &&
					!tNacionalidad.getActivo()){
				return null;
			}
		}
		return tNacionalidad;
		
		
		
	}

	@Override
	public int modificar(TNacionalidad tNacionalidad) {
	
		if(tNacionalidad == null) {
			return -1;
		}
		DAONacionalidad daoNacionalidad = FactoryDAO.getInstance().getDAONacionalidad();
		TNacionalidad t = daoNacionalidad.mostrar(tNacionalidad.getId()); 
		
		if(t != null) { 
			if(tNacionalidad.getNombre() != null && 
					!tNacionalidad.getNombre().isEmpty()) {
	
					return daoNacionalidad.modificar(tNacionalidad);
				
			}else {
				return -3; //error por datos sintacticois invalidos
			}
		}else {
			return -2; //error por id de pelicula inexistene
		}	
		
	}

	@Override
	public int eliminar(int id) {
		if(id <= 0) {
			return -1;
		}else {
			DAONacionalidad daoNacionalidad = FactoryDAO.getInstance().getDAONacionalidad();
			TNacionalidad t = daoNacionalidad.mostrar(id);
			
			if(t != null) {
				
					return daoNacionalidad.eliminar(id);
				
			}else {
				return -2;
			}
		}	
	}

	@Override
	public List<TNacionalidad> mostrarTodo() {

		DAONacionalidad daoNacionalidad = FactoryDAO.getInstance().getDAONacionalidad();
		List<TNacionalidad> lista = daoNacionalidad.mostrarTodo();
		List<TNacionalidad> lista2 = new ArrayList<TNacionalidad>();
		
		for(TNacionalidad t: lista) {
			if(t.getActivo()) {
				lista2.add(t);
			}
		}
		
		return lista2;
		
		
	}
}