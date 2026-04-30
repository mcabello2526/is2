/**
 *
 */
package Negocio.Cliente;

import java.util.ArrayList;
import java.util.List;

import Integracion.Cliente.DAOCliente;
import Integracion.FactoryDAO.FactoryDAO;



public class SAClienteImp implements SACliente {
	@Override
	public int crear(TCliente tCliente) {
		if (tCliente != null && 
				tCliente.getNombre() != null && !tCliente.getNombre().isEmpty() &&
				tCliente.getApellidos() != null && !tCliente.getApellidos().isEmpty() &&
				tCliente.getCorreo() != null && !tCliente.getCorreo().isEmpty()) { 
			
			DAOCliente daoCliente = FactoryDAO.getInstance().getDAOCliente();
			TCliente t = daoCliente.mostrar(tCliente.getId());
			
			if (t == null) {
				return daoCliente.crear(tCliente);
			}
			else {
				if(!t.getActivo()) { 
					tCliente.setId(t.getId());
					tCliente.setActivo(true);
					return daoCliente.modificar(tCliente);
				}else { 
					return -2;
				}
			}
		}
		else return -1;
	}

	@Override
	public TCliente mostrar(int id) {
		TCliente tCliente = null;
		if (id > 0) {
			DAOCliente daoCliente = FactoryDAO.getInstance().getDAOCliente();
			tCliente = daoCliente.mostrar(id);
			if (tCliente != null && !tCliente.getActivo()) {
				return null;
			}
		}
		return tCliente;
	}

	@Override
	public int modificar(TCliente tCliente) {
		if (tCliente == null) {
			return -1;
		}
		
		DAOCliente daoCliente = FactoryDAO.getInstance().getDAOCliente();
		TCliente t = daoCliente.mostrar(tCliente.getId()); 
		
		if (t != null) {
			if (tCliente.getNombre() != null && !tCliente.getNombre().isEmpty() &&
				tCliente.getApellidos() != null && !tCliente.getApellidos().isEmpty() &&
				tCliente.getCorreo() != null && !tCliente.getCorreo().isEmpty()) {
				
				return daoCliente.modificar(tCliente);
			} else {
				return -3; // Error por datos sintácticos inválidos
			}
		} else {
			return -2; // Error por id de cliente inexistente
		}
	}

	@Override
	public int eliminar(int id) {
		if (id <= 0) {
			return -1; // Error por ID inválido
		} else {
			DAOCliente daoCliente = FactoryDAO.getInstance().getDAOCliente();
			TCliente t = daoCliente.mostrar(id);
			
			if (t != null) {
				
				return daoCliente.eliminar(id); 
			} else {
				return -2; // Error: el cliente no existe
			}
		}
	}

	@Override
	public List<TCliente> mostrarTodos() {
		DAOCliente daoCliente = FactoryDAO.getInstance().getDAOCliente();
		List<TCliente> lista = daoCliente.mostrarTodo();
		List<TCliente> listaActivos = new ArrayList<TCliente>();
		
		if (lista != null) {
			for (TCliente t : lista) {
				if (t.getActivo()) {
					listaActivos.add(t);
				}
			}
		}
		
		return listaActivos;
	}
}