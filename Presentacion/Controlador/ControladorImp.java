/**
 *
 */
package Presentacion.Controlador;

import java.util.List;

import Negocio.Cliente.TCliente;
import Negocio.FactorySA.FactorySA;
import Negocio.Genero.TGenero;
import Negocio.Nacionalidad.TNacionalidad;
import Negocio.Pelicula.TPelicula;
import Negocio.Productora.TProductora;
import Negocio.Trabajador.TTrabajador;
import Negocio.Venta.TVenta;
import Presentacion.Evento;
import Presentacion.FactoryGUI.FactoryGUI;

public class ControladorImp extends Controlador {

	@Override
	public void accion(int evento, Object datos) {
		switch (evento) {
		// -- GUI INICIAL -- //
		case Evento.GUI_TIENDAPELIS: {
			FactoryGUI.getInstance().getGUI(Evento.GUI_TIENDAPELIS);
			break;
		}
		
		
		

		// -- CLIENTE -- //
		case Evento.GUI_CLIENTE:
			FactoryGUI.getInstance().getGUI(Evento.GUI_CLIENTE);
			break;

		case Evento.GUI_CREAR_CLIENTE:
			FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_CLIENTE);
			break;

		case Evento.GUI_ELIMINAR_CLIENTE:
			FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_CLIENTE);
			break;

		case Evento.GUI_MODIFICAR_CLIENTE:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_CLIENTE);
			break;

		case Evento.GUI_MOSTRAR_CLIENTE:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_CLIENTE);
			break;

		case Evento.GUI_MOSTRAR_TODOS_LOS_CLIENTES:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_CLIENTES);
			break;

		case Evento.CREAR_CLIENTE: {
			TCliente tCliente = (TCliente) datos;
			int id = FactorySA.getInstance().getSACliente().crear(tCliente);

			if (id == 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_CLIENTE).actualizar(Evento.RES_CREAR_CLIENTE_OK, null);
			else if (id == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_CLIENTE).actualizar(Evento.RES_CREAR_CLIENTE_KO,
						"Ha habido un problema dando de alta al cliente.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_CLIENTE).actualizar(Evento.RES_CREAR_CLIENTE_KO,
						"Ya existe un cliente activo con ese correo.");
			break;
		}
		case Evento.ELIMINAR_CLIENTE: {
			int id = (Integer) datos;
			int res = FactorySA.getInstance().getSACliente().eliminar(id);

			if (res >= 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_CLIENTE).actualizar(Evento.RES_ELIMINAR_CLIENTE_OK,
						null);
			else if (res == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_CLIENTE).actualizar(Evento.RES_ELIMINAR_CLIENTE_KO,
						"Error dando de baja al cliente.");
			else if (res == -2)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_CLIENTE).actualizar(Evento.RES_ELIMINAR_CLIENTE_KO,
						"No se ha encontrado un cliente con ese ID.");
			else if (res == -3)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_CLIENTE).actualizar(Evento.RES_ELIMINAR_CLIENTE_KO,
						"El cliente está vinculado a alguna venta."); // TODO: revisar este caso
			break;
		}
		case Evento.MODIFICAR_CLIENTE: {
			TCliente tCliente = (TCliente) datos;
			int id = FactorySA.getInstance().getSACliente().modificar(tCliente);

			if (id > 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_CLIENTE)
						.actualizar(Evento.RES_MODIFICAR_CLIENTE_OK, null);
			else if (id == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_CLIENTE)
						.actualizar(Evento.RES_MODIFICAR_CLIENTE_KO, "No se ha encontrado un cliente con ese ID.");
			else if (id == -2)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_CLIENTE)
						.actualizar(Evento.RES_MODIFICAR_CLIENTE_KO, "Ya existe un cliente activo con ese correo.");
			else if (id == -3)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_CLIENTE).actualizar(
						Evento.RES_MODIFICAR_CLIENTE_KO, "Ha habido un problema actualizando los datos del cliente.");
			break;
		}
		case Evento.MOSTRAR_CLIENTE: {
			int id = (Integer) datos;
			TCliente tCliente = FactorySA.getInstance().getSACliente().mostrar(id);

			if (tCliente == null) // TODO: no debería de devolver un entero siempre? 0 (bien), otro (mal)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_CLIENTE).actualizar(Evento.RES_MOSTRAR_CLIENTE_KO,
						"No se ha encontrado un cliente con ese ID.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_CLIENTE).actualizar(Evento.RES_MOSTRAR_CLIENTE_OK,
						tCliente);
			break;
		}
		case Evento.MOSTRAR_TODOS_LOS_CLIENTES: {
			List<TCliente> lista = FactorySA.getInstance().getSACliente().mostrarTodos();

			if (lista.isEmpty())
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_CLIENTES).actualizar(
						Evento.RES_MOSTRAR_TODOS_LOS_CLIENTES_KO, "No existen clientes registrados en el sistema.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_CLIENTES)
						.actualizar(Evento.RES_MOSTRAR_TODOS_LOS_CLIENTES_OK, lista);
			break;
		}

		// -- GÉNERO -- //
		case Evento.GUI_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_GENERO);
			break;

		case Evento.GUI_CREAR_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_GENERO);
			break;

		case Evento.GUI_ELIMINAR_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_GENERO);
			break;

		case Evento.GUI_MODIFICAR_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_GENERO);
			break;

		case Evento.GUI_MOSTRAR_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_GENERO);
			break;

		case Evento.GUI_MOSTRAR_GENEROS_POR_PELICULA:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_GENEROS_POR_PELICULA);
			break;

		case Evento.GUI_MOSTRAR_TODOS_LOS_GENEROS:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_GENEROS);
			break;

		case Evento.CREAR_GENERO: {
			TGenero tGenero = (TGenero) datos;
			int id = FactorySA.getInstance().getSAGenero().crear(tGenero);

			if (id == 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_GENERO).actualizar(Evento.RES_CREAR_GENERO_OK, null);
			else if (id == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_GENERO).actualizar(Evento.RES_CREAR_GENERO_KO,
						"Ha habido un problema dando de alta el género.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_GENERO).actualizar(Evento.RES_CREAR_GENERO_KO,
						"Ya existe un género activo con ese nombre.");
			break;
		}
		case Evento.ELIMINAR_GENERO: {
			int id = (Integer) datos;
			int res = FactorySA.getInstance().getSAGenero().eliminar(id);

			if (res >= 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_GENERO).actualizar(Evento.RES_ELIMINAR_GENERO_OK,
						null);
			else if (res == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_GENERO).actualizar(Evento.RES_ELIMINAR_GENERO_KO,
						"Error dando de baja el género.");
			else if (res == -2)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_GENERO).actualizar(Evento.RES_ELIMINAR_GENERO_KO,
						"No se ha encontrado un género con ese ID.");
			else if (res == -3)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_GENERO).actualizar(Evento.RES_ELIMINAR_GENERO_KO,
						"El género está vinculado a alguna pelicula."); // TODO: revisar este caso
			break;
		}
		case Evento.MODIFICAR_GENERO: {
			TGenero tGenero = (TGenero) datos;
			int id = FactorySA.getInstance().getSAGenero().modificar(tGenero);

			if (id > 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_GENERO).actualizar(Evento.RES_MODIFICAR_GENERO_OK,
						null);
			else if (id == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_GENERO).actualizar(Evento.RES_MODIFICAR_GENERO_KO,
						"No se ha encontrado un género con ese ID.");
			else if (id == -2)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_GENERO).actualizar(Evento.RES_MODIFICAR_GENERO_KO,
						"Ya existe un género activo con ese nombre.");
			else if (id == -3)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_GENERO).actualizar(Evento.RES_MODIFICAR_GENERO_KO,
						"Ha habido un problema actualizando los datos del género.");
			break;
		}
		case Evento.MOSTRAR_GENERO: {
			int id = (Integer) datos;
			TGenero tGenero = FactorySA.getInstance().getSAGenero().mostrar(id);

			if (tGenero == null) // TODO: no debería de devolver un entero siempre? 0 (bien), otro (mal)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_GENERO).actualizar(Evento.RES_MOSTRAR_GENERO_KO,
						"No se ha encontrado un género con ese ID.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_GENERO).actualizar(Evento.RES_MOSTRAR_GENERO_OK,
						tGenero);
			break;
		}
		case Evento.MOSTRAR_GENEROS_POR_PELICULA: {
			int id = (Integer) datos;
			List<TGenero> lista = FactorySA.getInstance().getSAGenero().mostrarPorPelicula(id);

			if (lista.isEmpty())
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_GENEROS_POR_PELICULA).actualizar(
						Evento.RES_MOSTRAR_GENEROS_POR_PELICULA_KO, "No existen generos asociados a esa película.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_GENEROS_POR_PELICULA)
						.actualizar(Evento.RES_MOSTRAR_GENEROS_POR_PELICULA_OK, lista);
			break;
		}
		case Evento.MOSTRAR_TODOS_LOS_GENEROS: {
			List<TGenero> lista = FactorySA.getInstance().getSAGenero().mostrarTodo();

			if (lista.isEmpty())
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_GENEROS).actualizar(
						Evento.RES_MOSTRAR_TODOS_LOS_GENEROS_KO, "No existen géneros registrados en el sistema.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_GENEROS)
						.actualizar(Evento.RES_MOSTRAR_TODOS_LOS_GENEROS_OK, lista);
			break;
		}

		// -- NACIONALIDAD -- //
		case Evento.GUI_NACIONALIDAD:
			FactoryGUI.getInstance().getGUI(Evento.GUI_NACIONALIDAD);
			break;

		case Evento.GUI_CREAR_NACIONALIDAD:
			FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_NACIONALIDAD);
			break;

		case Evento.GUI_ELIMINAR_NACIONALIDAD:
			FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_NACIONALIDAD);
			break;

		case Evento.GUI_MODIFICAR_NACIONALIDAD:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_NACIONALIDAD);
			break;

		case Evento.GUI_MOSTRAR_NACIONALIDAD:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_NACIONALIDAD);
			break;

		case Evento.GUI_MOSTRAR_TODAS_LAS_NACIONALIDADES:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_NACIONALIDADES);
			break;

		case Evento.CREAR_NACIONALIDAD: {
			TNacionalidad tNacionalidad = (TNacionalidad) datos;
			int id = FactorySA.getInstance().getSANacionalidad().crear(tNacionalidad);

			if (id == 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_NACIONALIDAD)
						.actualizar(Evento.RES_CREAR_NACIONALIDAD_OK, null);
			else if (id == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_NACIONALIDAD).actualizar(
						Evento.RES_CREAR_NACIONALIDAD_KO, "Ha habido un problema dando de alta la nacionalidad.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_NACIONALIDAD).actualizar(
						Evento.RES_CREAR_NACIONALIDAD_KO, "Ya existe una nacionalidad activa con ese nombre.");
			break;
		}
		case Evento.ELIMINAR_NACIONALIDAD: {
			int id = (Integer) datos;
			int res = FactorySA.getInstance().getSANacionalidad().eliminar(id);

			if (res >= 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_NACIONALIDAD)
						.actualizar(Evento.RES_ELIMINAR_NACIONALIDAD_OK, null);
			else if (res == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_NACIONALIDAD)
						.actualizar(Evento.RES_ELIMINAR_NACIONALIDAD_KO, "Error dando de baja la nacionalidad.");
			else if (res == -2)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_NACIONALIDAD).actualizar(
						Evento.RES_ELIMINAR_NACIONALIDAD_KO, "No se ha encontrado una nacionalidad con ese ID.");
			else if (res == -3)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_NACIONALIDAD)
						.actualizar(Evento.RES_ELIMINAR_NACIONALIDAD_KO, "El género está vinculado a alguna pelicula."); // TODO:
																															// revisar
																															// este
																															// caso,
																															// que
																															// pasa
																															// con
																															// una
																															// productora
																															// si
																															// elimino
																															// su
																															// nacionalidad
			break;
		}
		case Evento.MODIFICAR_NACIONALIDAD: {
			TNacionalidad tNacionalidad = (TNacionalidad) datos;
			int id = FactorySA.getInstance().getSANacionalidad().modificar(tNacionalidad);

			if (id > 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_NACIONALIDAD)
						.actualizar(Evento.RES_MODIFICAR_NACIONALIDAD_OK, null);
			else if (id == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_NACIONALIDAD).actualizar(
						Evento.RES_MODIFICAR_NACIONALIDAD_KO, "No se ha encontrado una nacionalidad con ese ID.");
			else if (id == -2)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_NACIONALIDAD).actualizar(
						Evento.RES_MODIFICAR_NACIONALIDAD_KO, "Ya existe una nacionalidad activa con ese nombre.");
			else if (id == -3)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_NACIONALIDAD).actualizar(
						Evento.RES_MODIFICAR_NACIONALIDAD_KO,
						"Ha habido un problema actualizando los datos de la nacionalidad.");
			break;
		}
		case Evento.MOSTRAR_NACIONALIDAD: {
			int id = (Integer) datos;
			TNacionalidad tNacionalidad = FactorySA.getInstance().getSANacionalidad().mostrar(id);

			if (tNacionalidad == null) // TODO: no debería de devolver un entero siempre? 0 (bien), otro (mal)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_NACIONALIDAD)
						.actualizar(Evento.RES_MOSTRAR_NACIONALIDAD_KO, "No se ha encontrado un género con ese ID.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_NACIONALIDAD)
						.actualizar(Evento.RES_MOSTRAR_NACIONALIDAD_OK, tNacionalidad);
			break;
		}
		case Evento.MOSTRAR_TODAS_LAS_NACIONALIDADES: {
			List<TNacionalidad> lista = FactorySA.getInstance().getSANacionalidad().mostrarTodo();

			if (lista.isEmpty())
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_NACIONALIDADES).actualizar(
						Evento.RES_MOSTRAR_TODAS_LAS_NACIONALIDADES_KO,
						"No existen nacionalidades registradas en el sistema.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_NACIONALIDADES)
						.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_NACIONALIDADES_OK, lista);
			break;
		}

		// -- PELICULA -- //
		case Evento.GUI_PELICULA:
			FactoryGUI.getInstance().getGUI(Evento.GUI_PELICULA);
			break;

		case Evento.GUI_CREAR_PELICULA:
			FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA);
			break;

		case Evento.GUI_ELIMINAR_PELICULA:
			FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA);
			break;

		case Evento.GUI_MODIFICAR_PELICULA:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PELICULA);
			break;

		case Evento.GUI_MOSTRAR_PELICULA:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULA);
			break;

		case Evento.GUI_MOSTRAR_PELICULA_POR_NOMBRE:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULA_POR_NOMBRE);
			break;

		case Evento.GUI_MOSTRAR_PELICULAS_POR_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULAS_POR_GENERO);
			break;

		case Evento.GUI_MOSTRAR_PELICULAS_POR_PRODUCTORA:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULAS_POR_PRODUCTORA);
			break;

		case Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS:
			FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS);
			break;

		case Evento.GUI_VINCULAR_PELICULA_A_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_VINCULAR_PELICULA_A_GENERO);
			break;

		case Evento.GUI_DESVINCULAR_PELICULA_A_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_DESVINCULAR_PELICULA_A_GENERO);
			break;

		case Evento.CREAR_PELICULA: {
			TPelicula tPelicula = (TPelicula) datos;
			int id = FactorySA.getInstance().getSAPelicula().crear(tPelicula);

			if (id == 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA).actualizar(Evento.RES_CREAR_PELICULA_OK,
						null);
			else if (id == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA).actualizar(Evento.RES_CREAR_PELICULA_KO,
						"Ha habido un problema dando de alta la película.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA).actualizar(Evento.RES_CREAR_PELICULA_KO,
						"Ya existe una nacionalidad activa con ese nombre.");
			break;
		}
		case Evento.ELIMINAR_PELICULA: {
			int id = (Integer) datos;
			int res = FactorySA.getInstance().getSAPelicula().eliminar(id);

			if (res >= 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA)
						.actualizar(Evento.RES_ELIMINAR_PELICULA_OK, null);
			else if (res == -1)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA)
						.actualizar(Evento.RES_ELIMINAR_PELICULA_KO, "Error dando de baja la película.");
			else if (res == -2)
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA)
						.actualizar(Evento.RES_ELIMINAR_PELICULA_KO, "No se ha encontrado una película con ese ID.");
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA)
						.actualizar(Evento.RES_ELIMINAR_PELICULA_KO, "La película tiene dependencias activas.");
			break;
		}
		
		}
	}
}