package Negocio.Trabajador;

import java.util.ArrayList;
import java.util.List;

import Negocio.Trabajador.TCompleto;
import Negocio.Trabajador.TParcial;
import Negocio.Trabajador.TTrabajador;
import Negocio.Venta.TVenta;
import Integracion.FactoryDAO.FactoryDAO;
import Integracion.Trabajador.DAOTrabajador;

public class SATrabajadorImp implements SATrabajador {

	// -1: error al añadir el trabajador a BD 
	// -2: error por trabajador ya existente en BD
	@Override
	public int crear(TTrabajador tTrabajador) {
	    List<TTrabajador> trabajadores = FactoryDAO.getInstance().getDAOTrabajador().mostrarTodo();
	    int id = -1; 
	    boolean encontrado = false;

	    for (TTrabajador t : trabajadores) {
	        if (t.getNombre().equals(tTrabajador.getNombre())) {
	            encontrado = true;
	            if (t.getActivo()) {
	                id = -2; 
	            } else {
	                tTrabajador.setId(t.getId());
	                id = FactoryDAO.getInstance().getDAOTrabajador().modificar(tTrabajador);
	            }
	            break; 
	        }
	    }

	    
	    if (!encontrado) {
	        id = FactoryDAO.getInstance().getDAOTrabajador().crear(tTrabajador);
	    }

	    return id;
	}
	
	
	// ERROR
	// null: no hay trabajador con ese id
	
	@Override
    public TTrabajador mostrar(int id) {
		TTrabajador t = FactoryDAO.getInstance().getDAOTrabajador().mostrar(id);
		return t;
    }

	// esto es solo para los que estan activos pero en nuestro proyecto tenemos mostrar todos 
	// ERROR 
	// null: no hay trabajadores (lista vacia, size = 0) 
    @Override
    public List<TTrabajador> mostrarTodos() {
		List<TTrabajador> todos = FactoryDAO.getInstance().getDAOTrabajador().mostrarTodo();
		List<TTrabajador> activos = new ArrayList<TTrabajador>();
		for(TTrabajador t:todos)
			if(t.getActivo())
				activos.add(t);
		return activos;
    }
    
    //ERRORES
    // -1: no existe trabajador con ese ID 
    // -2: hay trabajador con el mismo ID 
    // -3: no se puede hacer el modificar

    @Override
    public int modificar(TTrabajador tTrabajador) {
   
        TTrabajador tOriginal = FactoryDAO.getInstance().getDAOTrabajador().mostrar(tTrabajador.getId());
        if (tOriginal == null) {
            return -1;
        }

        List<TTrabajador> todos = FactoryDAO.getInstance().getDAOTrabajador().mostrarTodo();
        for (TTrabajador t : todos) {
            if (t.getNombre().equals(tTrabajador.getNombre()) && t.getId() != tTrabajador.getId()) {
                return -2;
            }
        }

        tOriginal.setNombre(!tTrabajador.getNombre().equals("") ? tTrabajador.getNombre() : tOriginal.getNombre());
        tOriginal.setHorasTotales(tTrabajador.getHorasTotales() != 0 ? tTrabajador.getHorasTotales() : tOriginal.getHorasTotales());
        tOriginal.setEsTiempoCompleto(tTrabajador.getEsTiempoCompleto());

        int resultado = -3;

        if (tTrabajador instanceof TCompleto) {
            TCompleto tNuevoCompleto = (TCompleto) tTrabajador; 
            
            if (tOriginal instanceof TCompleto) {
                TCompleto tOriginalCompleto = (TCompleto) tOriginal;
                tOriginalCompleto.setAnio(tNuevoCompleto.getAnio() != 0 ? tNuevoCompleto.getAnio() : tOriginalCompleto.getAnio());
                tOriginalCompleto.setBonificacion(tNuevoCompleto.getBonificacion() != 0 ? tNuevoCompleto.getBonificacion() : tOriginalCompleto.getBonificacion());
                
                resultado = FactoryDAO.getInstance().getDAOTrabajador().modificar(tOriginalCompleto);
                
            } else {
            	tNuevoCompleto.setId(tOriginal.getId());
                tNuevoCompleto.setNombre(tOriginal.getNombre());
                tNuevoCompleto.setHorasTotales(tOriginal.getHorasTotales());
                
                resultado = FactoryDAO.getInstance().getDAOTrabajador().modificar(tNuevoCompleto);
            }

        } else if (tTrabajador instanceof TParcial) {
            TParcial tNuevoParcial = (TParcial) tTrabajador;
            
            if (tOriginal instanceof TParcial) {

                TParcial tOriginalParcial = (TParcial) tOriginal;
                tOriginalParcial.setJornada(!tNuevoParcial.getJornada().equals("") ? tNuevoParcial.getJornada() : tOriginalParcial.getJornada());
                tOriginalParcial.setHorasExtra(tNuevoParcial.getHorasExtra() != 0 ? tNuevoParcial.getHorasExtra() : tOriginalParcial.getHorasExtra());
                
                resultado = FactoryDAO.getInstance().getDAOTrabajador().modificar(tOriginalParcial);
            } else {
            	tNuevoParcial.setId(tOriginal.getId());
                tNuevoParcial.setNombre(tOriginal.getNombre());
                tNuevoParcial.setHorasTotales(tOriginal.getHorasTotales());
                
                resultado = FactoryDAO.getInstance().getDAOTrabajador().modificar(tNuevoParcial);
            }
        }

        return resultado;
    }
    
    // ERRORES: 
    // -1: error dando de baja al trabajador en la bd 
    // -2: el trabajador no esta en la bd 
    // -3: el trabajador esta vinculado a al menos 1 venta

    @Override
	public int eliminar(int id) {
		TTrabajador t = FactoryDAO.getInstance().getDAOTrabajador().mostrar(id);
		int eliminado = 0; 
		if (t == null) {
			return -2;   // no encontrado 
		}
		List<TVenta> ventas = FactoryDAO.getInstance().getDAOVenta().mostrarPorTrabajador(id);   
		
		if (ventas.size() > 0) {
			return -3; 
		}
		
		eliminado = FactoryDAO.getInstance().getDAOTrabajador().eliminar(id) ;
		return eliminado; 

	}
    
 
}
