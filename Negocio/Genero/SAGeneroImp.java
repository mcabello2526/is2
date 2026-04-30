/**
 *
 */
package Negocio.Genero;

import java.util.ArrayList;
import java.util.List;

import Integracion.FactoryDAO.FactoryDAO;
import Integracion.Genero.DAOGenero;



public class SAGeneroImp implements SAGenero {
	
	
	@Override
	public int crear(TGenero tGenero) {
		if (tGenero != null && tGenero.getNombre() != null && 
				  !tGenero.getNombre().isEmpty() && tGenero.getDescripcion() != null 
				&& !tGenero.getDescripcion().isEmpty()) {
			DAOGenero daogenero = FactoryDAO.getInstance().getDAOGenero();//devuelve el DAO
			TGenero tg = daogenero.mostrar(tGenero.getId()); //leer segun id para ver si existe
			
			
			if (tg == null) {
				return daogenero.crear(tGenero); // si no existe creamos
			}else {
				if (!tg.getActivo()) { // si existe y no está activo modificamos su actividad
					tGenero.setId(tg.getId());
					tGenero.setActivo(true);
					return daogenero.modificar(tGenero);
				}else {
					return -2;
				}
			}
			
		}
		return -1;
	
	}

	@Override
	public TGenero mostrar(int id) {
		TGenero tGenero = null;
		if (id > 0) {
			DAOGenero daogenero = FactoryDAO.getInstance().getDAOGenero();
			tGenero = daogenero.mostrar(id);
			if (tGenero != null && !tGenero.getActivo()) {
				return null;
			}
		}
		return tGenero;
	}

	@Override
	public int modificar(TGenero tGenero) {
		if (tGenero == null) return -1;
		
		DAOGenero daogenero = FactoryDAO.getInstance().getDAOGenero();//devuelve el DAO
		TGenero tg = daogenero.mostrar(tGenero.getId()); //leer segun id para ver si existe
		
		if (tg != null) {
			//comprobar la validez
			if (tGenero != null && tGenero.getNombre() != null  && 
					!tGenero.getNombre().isEmpty() && tGenero.getDescripcion() != null 
					&& !tGenero.getDescripcion().isEmpty()) {
				return daogenero.modificar(tGenero);
			}
			else {
				return -3; //datos sintacticos invalidos
			}
		}
		else {
			return -2; //id invalido
		}
	}

	@Override
	public List<TGenero> mostrarTodo() {
		DAOGenero daogenero = FactoryDAO.getInstance().getDAOGenero();
		List<TGenero> list = daogenero.mostrarTodo();
		List<TGenero> listb = new ArrayList<TGenero>();
		
		for (TGenero tg : list) {
			if (tg.getActivo()) {
				listb.add(tg);
			}
		}
		return listb;
	}

	@Override
	public int eliminar(int id) {
		if (id <= 0) {
			return -1;
		} else {
			DAOGenero daogenero = FactoryDAO.getInstance().getDAOGenero();//devuelve el DAO
			TGenero tg = daogenero.mostrar(id);
			
			if (tg != null) {
				return daogenero.eliminar(id);
			}
			else {
				return -2;
			}
		}
		
	}

	@Override
	public List<TGenero> mostrarPorPelicula(int idPelicula) {
		List<TGenero> generosActivos = new ArrayList<TGenero>();
		
		if (idPelicula > 0) {
			
			if(FactoryDAO.getInstance().getDAOPelicula().mostrar(idPelicula) != null) {
			List<TGenero> lista = FactoryDAO.getInstance().getDAOGenero().mostrarPorPelicula(idPelicula);
			
			if (!lista.isEmpty()) {
				for (TGenero tg : lista) {
					if (tg.getActivo()) {
						generosActivos.add(tg);
					}
				}
			}
			
			}else {
				return null;
			}
			
		}
		return generosActivos;
	}
}