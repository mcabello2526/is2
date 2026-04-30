/**
 *
 */
package Negocio.Pelicula;

import java.util.ArrayList;
import java.util.List;

import Integracion.FactoryDAO.FactoryDAO;
import Integracion.Pelicula.DAOPelicula;
import Integracion.Pelicula.DAOPeliculaGenero;
import Integracion.Venta.DAOVentaPelicula;


public class SAPeliculaImp implements SAPelicula {
	
	@Override
	public int crear(TPelicula tPelicula) {
		if(tPelicula != null &&
				tPelicula.getNombre() != null &&
				!tPelicula.getNombre().isEmpty() &&
				tPelicula.getPrecio()>=0 &&
				tPelicula.getStock()>= 0) {
			
			DAOPelicula daoPelicula = FactoryDAO.getInstance().getDAOPelicula(); //obtener DAO
			TPelicula t = daoPelicula.mostrarPorNombre(tPelicula.getNombre()); //leer por nombre para comprobar ssi existe
			
			if(t == null) {
				return daoPelicula.crear(tPelicula); //si no existe, se crea
			}else {
				if(!t.getActivo()) { //si existe pero esta inactiva --> activar
					tPelicula.setId(t.getId());
					tPelicula.setActivo(true);
					return daoPelicula.modificar(tPelicula);
				}else { // ya existe y ya esta activa
					return -2;
				}
			}
		}
		return -1;
		
	}

	@Override
	public TPelicula mostrar(int id) {
		TPelicula tPelicula = null;
		if(id>0) {
			DAOPelicula daoPelicula = FactoryDAO.getInstance().getDAOPelicula();
			tPelicula = daoPelicula.mostrar(id);
			if(tPelicula != null &&
					!tPelicula.getActivo()){
				return null;
			}
		}
		return tPelicula;
	}

	@Override
	public int modificar(TPelicula tPelicula) {
		if(tPelicula == null) { //final menor
			return -1;
		}
		
		DAOPelicula daoPelicula = FactoryDAO.getInstance().getDAOPelicula();
		TPelicula t = daoPelicula.mostrar(tPelicula.getId()); //comprobar is la pelicula con dicho id existe

		
		if(t != null) {
			//comprobar la validez sintáctica
			if(tPelicula.getNombre() != null && !tPelicula.getNombre().isEmpty() && tPelicula.getPrecio() >= 0 && tPelicula.getStock() >= 0) {
				
				//comprobar si se modifica la productora
				if(tPelicula.getIdProductora() != t.getIdProductora()) {
					//comprobar si la productora con ese id existe
					if(FactoryDAO.getInstance().getDAOProductora().mostrar(tPelicula.getIdProductora()) != null) {
						return daoPelicula.modificar(tPelicula);
					}else {
						return -4; //error por ID productora inexistente
					}
				}
				else {
					return daoPelicula.modificar(tPelicula);
				}
			}else {
				return -3; //error por datos sintacticos invalidos
			}
		} else {
			return -2; //error por id de pelicula inexistene
		}
	}

	@Override
	public int eliminar(int id) {
		if(id <= 0) {
			return -1;
		}else {
			DAOPelicula daoPelicula = FactoryDAO.getInstance().getDAOPelicula();
			TPelicula t = daoPelicula.mostrar(id);
			
			if(t != null) {
				DAOVentaPelicula daoVP = FactoryDAO.getInstance().getDAOVentaPelicula();
				
				if(daoVP.estaEnVentaAbierta(id)) {
					return -3;
				}else {
					return daoPelicula.eliminar(id);
				}
			}else {
				return -2;
			}
		}
	}

	@Override
	public List<TPelicula> mostrarTodo() {
		DAOPelicula daoPelicula = FactoryDAO.getInstance().getDAOPelicula();
		List<TPelicula> lista = daoPelicula.mostrarTodo();
		List<TPelicula> lista2 = new ArrayList<TPelicula>();
		
		for(TPelicula t: lista) {
			if(t.getActivo()) {
				lista2.add(t);
			}
		}
		
		return lista2;
	}

	@Override
	public List<TPelicula> mostrarPeliculasPorGenero(int idGenero) {
		List<TPelicula> listaBuena = new ArrayList<TPelicula>();
		
		if(idGenero > 0) {
			if(FactoryDAO.getInstance().getDAOGenero().mostrar(idGenero) != null) {
				List<TPelicula> lista = FactoryDAO.getInstance().getDAOPelicula().mostrarPeliculasPorGenero(idGenero);
				
				if(!lista.isEmpty()) {
					for(TPelicula t: lista) {
						if(t.getActivo()) {
							listaBuena.add(t);
						}
					}
				}
			}else {
				return null;
			}
		}
		return listaBuena;
	}

	@Override
	public List<TPelicula> mostrarPeliculasPorProductora(int idProductora) {
		List<TPelicula> listaBuena = new ArrayList<TPelicula>();
		
		if(idProductora > 0) {
			//comprobar si la prodd existe
			if(FactoryDAO.getInstance().getDAOProductora().mostrar(idProductora) != null) {
				List<TPelicula> lista = FactoryDAO.getInstance().getDAOPelicula().mostrarPeliculasPorProductora(idProductora);
				
				if(!lista.isEmpty()) { //comprobar que existen pelis
					for(TPelicula t : lista) {
						if(t.getActivo()) {
							listaBuena.add(t);
						}
					}
				}
			}else {
				return null; //productora no existente
			}
		}
		return listaBuena;
	}

	@Override
	public int vincularPeliculaAGenero(int idPelicula, int idGenero) {
		if (idPelicula <= 0 || idGenero <= 0) {
			return -1;
		}else {
			if(FactoryDAO.getInstance().getDAOPelicula().mostrar(idPelicula) != null) {
				if(FactoryDAO.getInstance().getDAOGenero().mostrar(idGenero)!= null) {
					DAOPeliculaGenero daoPG = FactoryDAO.getInstance().getDAOPeliculaGenero();
					
					if(daoPG.yaVinculado(idPelicula, idGenero)) {
						return -4; //ya vinculados
					}else {
						return daoPG.vincularPeliculaAGenero(idPelicula, idGenero); ///vincular
					}
				}else {
					return -3; //no existe el genero
				}
			}else {
				return -2; //no existe la peli
			}
		}
	}

	@Override
	public int desvincularPeliculaAGenero(int idPelicula, int idGenero) {
		if(idPelicula <= 0 || idGenero <= 0) {
			return -1;
		}else {
			if(FactoryDAO.getInstance().getDAOPelicula().mostrar(idPelicula)!= null) {
				if(FactoryDAO.getInstance().getDAOGenero().mostrar(idGenero) != null) {
					DAOPeliculaGenero daoPG = FactoryDAO.getInstance().getDAOPeliculaGenero();
					
					if(daoPG.yaVinculado(idPelicula,idGenero)) {
						return daoPG.desvincularPeliculaAGenero(idPelicula, idGenero); //si estan vinculados --> los desvinculamos
					
					}else {
						return -4; //no estan vinculados
					}
				}else {
					return -3; //no existe el genero
				}
			}else {
				return -2; //no existe la peli
			}
		}
	}
	
	
	@Override
	public TPelicula mostrarPorNombre(String nombre) {
		
		if(nombre!= null && !nombre.isEmpty()) {
			DAOPelicula daoPelicula = FactoryDAO.getInstance().getDAOPelicula();
			TPelicula t = daoPelicula.mostrarPorNombre(nombre);
			if(t != null && !t.getActivo()){
				return t;
			}
		}
		return null;
	}
}