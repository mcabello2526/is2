package Negocio.Productora;

import java.util.ArrayList;
import java.util.List;

import Integracion.FactoryDAO.FactoryDAO;
import Negocio.Productora.TProductora;
import Negocio.Pelicula.TPelicula;

public class SAProductoraImp implements SAProductora {

	@Override
	public TProductora mostrar(int id) {
		TProductora t = FactoryDAO.getInstance().getDAOProductora().mostrar(id);
		return t;
	}
	
	//ERRORES:			
	//-1: no existe marca con ese id			
	//-2: hay una marca con el mismo nombre
	//-3: error realizando el update
	@Override
	public int modificar(TProductora tProductora) {
		TProductora tOriginal = FactoryDAO.getInstance().getDAOProductora().mostrar(tProductora.getId());
		if(tOriginal == null)
			return -1;
		List<TProductora> todos = FactoryDAO.getInstance().getDAOProductora().mostrarTodas();
		for(TProductora t : todos) {
			if(t.getNombre().equals(tProductora.getNombre()) &&  t.getId() != tProductora.getId()){
				return -2;
			}
		}
		int resultado = -3;
		tOriginal.setNombre(!tProductora.getNombre().equals("")?tProductora.getNombre():tOriginal.getNombre());
		tOriginal.setAnioCreacion(tProductora.getAnioCreacion() > 0 ? tProductora.getAnioCreacion() : tOriginal.getAnioCreacion());	
		tOriginal.setIdNacionalidad(tProductora.getIdNacionalidad() > 0 ? tProductora.getIdNacionalidad() : tOriginal.getIdNacionalidad());
		resultado = FactoryDAO.getInstance().getDAOProductora().modificar(tProductora);
		
		return resultado;
	}

	//ERRORES:
	//-1: error en bd
	//-2: no existe esa marca
	//-3: algun producto tiene esa marca
	@Override
	public int eliminar(int id) {
		TProductora t = FactoryDAO.getInstance().getDAOProductora().mostrar(id);
		if(t == null) {
			return -2;
		}
		List<TPelicula> lista = FactoryDAO.getInstance().getDAOPelicula().mostrarPeliculasPorProductora(id);
		if(lista==null || lista.size()>0)
			return -3;
		int res = FactoryDAO.getInstance().getDAOProductora().eliminar(id);
		return res;
	}

	@Override
	public List<TProductora> mostrarTodas() {
		List<TProductora> todos = FactoryDAO.getInstance().getDAOProductora().mostrarTodas();
		List<TProductora> activos = new ArrayList<TProductora>();
		for(TProductora t:todos)
			if(t.getActivo())
				activos.add(t);
		return activos;
	}

	@Override
	public List<TProductora> mostrarPorNacionalidad(int idNacionalidad) {
		List<TProductora> productoras = FactoryDAO.getInstance().getDAOProductora().mostrarPorNacionalidad(idNacionalidad);
		
		List<TProductora> activas = new ArrayList<TProductora>();
		
		if (productoras != null) {
			for (TProductora t : productoras) {
				if (t.getActivo()) {
					activas.add(t);
				}
			}
		}
		
		return activas;
	}

	//ERRORES:
	//-1: error con BD
	//-2: marca activa con mismo nombre
	@Override
	public int crear(TProductora tProductora) {
		List<TProductora> todos = FactoryDAO.getInstance().getDAOProductora().mostrarTodas();
		int id = 0;
		for(TProductora t : todos) {
			if(t.getNombre().equals(tProductora.getNombre())) {
				if(!t.getActivo()) {
					tProductora.setId(t.getId());
					id = FactoryDAO.getInstance().getDAOProductora().modificar(tProductora);
				}else {
					return -2;
				}
			}
		}
		id = FactoryDAO.getInstance().getDAOProductora().crear(tProductora);
		return id;
	}

}
