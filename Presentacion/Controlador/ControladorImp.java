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
import Presentacion.IGUI;
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
						.actualizar(Evento.RES_ELIMINAR_NACIONALIDAD_KO, "El género está vinculado a alguna pelicula."); 																					// nacionalidad
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

			IGUI gui = FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_NACIONALIDADES);

		    if (lista == null || lista.isEmpty()) {
		        gui.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_NACIONALIDADES_KO, 
		                      "No existen nacionalidades registradas.");
		    } else {
		        gui.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_NACIONALIDADES_OK, lista);
		    }
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

		case Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS: {
		    // 1. Pedimos los datos al SA
		    List<TPelicula> lista = FactorySA.getInstance().getSAPelicula().mostrarTodo();
		    
		    // 2. Pedimos la ventana a la Factory (que ahora se creará sin bucles)
		    IGUI gui = FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS);
		    
		    // 3. Le pasamos los datos directamente
		    if (lista != null && !lista.isEmpty()) {
		        gui.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_PELICULAS_OK, lista);
		    } else {
		        gui.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_PELICULAS_KO, "No hay películas");
		    }
		    break;
		}
		case Evento.GUI_VINCULAR_PELICULA_A_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_VINCULAR_PELICULA_A_GENERO);
			break;

		case Evento.GUI_DESVINCULAR_PELICULA_A_GENERO:
			FactoryGUI.getInstance().getGUI(Evento.GUI_DESVINCULAR_PELICULA_A_GENERO);
			break;

		case Evento.CREAR_PELICULA: {
			TPelicula tPelicula = (TPelicula) datos;
		    int res = FactorySA.getInstance().getSAPelicula().crear(tPelicula);

		    if (res > 0) // El DAO suele devolver el ID generado (>0)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA)
		            .actualizar(Evento.RES_CREAR_PELICULA_OK, res);
		    else if (res == -1)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA)
		            .actualizar(Evento.RES_CREAR_PELICULA_KO, "Datos sintácticos erróneos (Nombre vacío, precio o stock negativo).");
		    else if (res == -2)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA)
		            .actualizar(Evento.RES_CREAR_PELICULA_KO, "La película ya existe y está activa.");
		    else
		        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PELICULA)
		            .actualizar(Evento.RES_CREAR_PELICULA_KO, "Error desconocido al crear la película.");
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
		            .actualizar(Evento.RES_ELIMINAR_PELICULA_KO, "ID no válido.");
		    else if (res == -2)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA)
		            .actualizar(Evento.RES_ELIMINAR_PELICULA_KO, "No se ha encontrado una película con ese ID.");
		    else if (res == -3)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA)
		            .actualizar(Evento.RES_ELIMINAR_PELICULA_KO, "No se puede eliminar: La película está en una venta abierta.");
		    break;
		}
		case Evento.MODIFICAR_PELICULA:{
			TPelicula tPelicula = (TPelicula) datos;
		    int res = FactorySA.getInstance().getSAPelicula().modificar(tPelicula);

		    if (res >= 0)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PELICULA)
		            .actualizar(Evento.RES_MODIFICAR_PELICULA_OK, null);
		    else if (res == -1)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PELICULA)
		            .actualizar(Evento.RES_MODIFICAR_PELICULA_KO, "Error: Transfer nulo.");
		    else if (res == -2)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PELICULA)
		            .actualizar(Evento.RES_MODIFICAR_PELICULA_KO, "La película no existe.");
		    else if (res == -3)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PELICULA)
		            .actualizar(Evento.RES_MODIFICAR_PELICULA_KO, "Datos sintácticos inválidos.");
		    else if (res == -4)
		        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PELICULA)
		            .actualizar(Evento.RES_MODIFICAR_PELICULA_KO, "La Productora especificada no existe.");
		    break;	
		}
		case Evento.MOSTRAR_PELICULA:{
			int id = (Integer) datos;
			TPelicula p = FactorySA.getInstance().getSAPelicula().mostrar(id);

			if (p != null)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULA)
						.actualizar(Evento.RES_MOSTRAR_PELICULA_OK, p);
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULA)
						.actualizar(Evento.RES_MOSTRAR_PELICULA_KO, "No existe la película o está inactiva.");
			break;
		}
		case Evento.MOSTRAR_PELICULA_POR_NOMBRE:{
			String nombre = (String) datos;
			TPelicula p = FactorySA.getInstance().getSAPelicula().mostrarPorNombre(nombre);

			if (p != null)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULA_POR_NOMBRE)
						.actualizar(Evento.RES_MOSTRAR_PELICULA_POR_NOMBRE_OK, p);
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULA_POR_NOMBRE)
						.actualizar(Evento.RES_MOSTRAR_PELICULA_POR_NOMBRE_KO, "No se encontró la película.");
			break;
		}
		case Evento.MOSTRAR_PELICULAS_POR_GENERO:{
			int idGenero = (Integer) datos;
			List<TPelicula> lista = FactorySA.getInstance().getSAPelicula().mostrarPeliculasPorGenero(idGenero);

			if (lista != null) // Si el SA devuelve null es porque el género no existe
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULAS_POR_GENERO)
						.actualizar(Evento.RES_MOSTRAR_PELICULAS_POR_GENERO_OK, lista);
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULAS_POR_GENERO)
						.actualizar(Evento.RES_MOSTRAR_PELICULAS_POR_GENERO_KO, "El género especificado no existe.");
			break;
		}
		case Evento.MOSTRAR_PELICULAS_POR_PRODUCTORA:{
			int idProd = (Integer) datos;
			List<TPelicula> lista = FactorySA.getInstance().getSAPelicula().mostrarPeliculasPorProductora(idProd);

			if (lista != null)
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULAS_POR_PRODUCTORA)
						.actualizar(Evento.RES_MOSTRAR_PELICULAS_POR_PRODUCTORA_OK, lista);
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PELICULAS_POR_PRODUCTORA)
						.actualizar(Evento.RES_MOSTRAR_PELICULAS_POR_PRODUCTORA_KO, "La productora no existe.");
			break;
		}
		case Evento.MOSTRAR_TODAS_LAS_PELICULAS:{
			List<TPelicula> lista = FactorySA.getInstance().getSAPelicula().mostrarTodo();
			
			if (lista != null && !lista.isEmpty())
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS)
						.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_PELICULAS_OK, lista);
			else
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS)
						.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_PELICULAS_KO, "No hay películas para mostrar.");
			break;
		}
		case Evento.VINCULAR_PELICULA_A_GENERO:{
			int[] info = (int[]) datos;
			int res = FactorySA.getInstance().getSAPelicula().vincularPeliculaAGenero(info[0], info[1]);

			if (res >= 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_VINCULAR_PELICULA_A_GENERO)
						.actualizar(Evento.RES_VINCULAR_PELICULA_A_GENERO_OK, null);
			else {
				String msg = (res == -2) ? "Pelicula no existe" : (res == -3) ? "Género no existe" : "Ya están vinculados";
				FactoryGUI.getInstance().getGUI(Evento.GUI_VINCULAR_PELICULA_A_GENERO)
						.actualizar(Evento.RES_VINCULAR_PELICULA_A_GENERO_KO, msg);
			}
			break;
		}
		case Evento.DESVINCULAR_PELICULA_A_GENERO:{
			int[] info = (int[]) datos;
			int res = FactorySA.getInstance().getSAPelicula().desvincularPeliculaAGenero(info[0], info[1]);

			if (res >= 0)
				FactoryGUI.getInstance().getGUI(Evento.GUI_DESVINCULAR_PELICULA_A_GENERO)
						.actualizar(Evento.RES_DESVINCULAR_PELICULA_A_GENERO_OK, null);
			else {
				String msg = (res == -4) ? "No estaban vinculados" : "Error en los IDs proporcionados";
				FactoryGUI.getInstance().getGUI(Evento.GUI_DESVINCULAR_PELICULA_A_GENERO)
						.actualizar(Evento.RES_DESVINCULAR_PELICULA_A_GENERO_KO, msg);
			}
			break;
		}
		
		
		// -- PRODUCTORA -- //
			case Evento.GUI_PRODUCTORA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_PRODUCTORA);
				break;
			case Evento.GUI_CREAR_PRODUCTORA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PRODUCTORA);
				break;
			case Evento.GUI_ELIMINAR_PRODUCTORA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PRODUCTORA);
				break;
			case Evento.GUI_MODIFICAR_PRODUCTORA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PRODUCTORA);
				break;
			case Evento.GUI_MOSTRAR_PRODUCTORA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PRODUCTORA);
				break;
			case Evento.GUI_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD);
				break;
			case Evento.GUI_MOSTRAR_TODAS_LAS_PRODUCTORAS:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_PRODUCTORAS);
				break;
			case Evento.CREAR_PRODUCTORA:{
				TProductora tProductora = (TProductora) datos;
			    int res = FactorySA.getInstance().getSAProductora().crear(tProductora);

			    if (res > 0) // Éxito (tanto si es nuevo ID como si es reactivación vía modificar)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PRODUCTORA)
			            .actualizar(Evento.RES_CREAR_PRODUCTORA_OK, res);
			    else if (res == -1)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PRODUCTORA)
			            .actualizar(Evento.RES_CREAR_PRODUCTORA_KO, "Error de acceso a la base de datos.");
			    else if (res == -2)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PRODUCTORA)
			            .actualizar(Evento.RES_CREAR_PRODUCTORA_KO, "Ya existe una productora activa con ese nombre.");
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_PRODUCTORA)
			            .actualizar(Evento.RES_CREAR_PRODUCTORA_KO, "Error desconocido al intentar crear la productora.");
			    break;
			}
			case Evento.ELIMINAR_PRODUCTORA:{
				int id = (Integer) datos;
			    int res = FactorySA.getInstance().getSAProductora().eliminar(id);

			    if (res >= 0)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PRODUCTORA)
			            .actualizar(Evento.RES_ELIMINAR_PRODUCTORA_OK, null);
			    else if (res == -2)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PRODUCTORA)
			            .actualizar(Evento.RES_ELIMINAR_PRODUCTORA_KO, "No existe ninguna productora con el ID especificado.");
			    else if (res == -3)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PRODUCTORA)
			            .actualizar(Evento.RES_ELIMINAR_PRODUCTORA_KO, "No se puede eliminar: Hay películas vinculadas a esta productora.");
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PRODUCTORA)
			            .actualizar(Evento.RES_ELIMINAR_PRODUCTORA_KO, "Error interno al intentar eliminar.");
			    break;
			}
			case Evento.MODIFICAR_PRODUCTORA:{
				TProductora tProductora = (TProductora) datos;
			    int res = FactorySA.getInstance().getSAProductora().modificar(tProductora);

			    if (res >= 0)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PRODUCTORA)
			            .actualizar(Evento.RES_MODIFICAR_PRODUCTORA_OK, null);
			    else if (res == -1)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PRODUCTORA)
			            .actualizar(Evento.RES_MODIFICAR_PRODUCTORA_KO, "La productora que intentas modificar no existe.");
			    else if (res == -2)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PRODUCTORA)
			            .actualizar(Evento.RES_MODIFICAR_PRODUCTORA_KO, "El nuevo nombre ya pertenece a otra productora existente.");
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_PRODUCTORA)
			            .actualizar(Evento.RES_MODIFICAR_PRODUCTORA_KO, "Error al realizar la actualización en la base de datos.");
			    break;
			}
			case Evento.MOSTRAR_PRODUCTORA:{
				int id = (Integer) datos;
			    TProductora t = FactorySA.getInstance().getSAProductora().mostrar(id);

			    if (t != null)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PRODUCTORA)
			            .actualizar(Evento.RES_MOSTRAR_PRODUCTORA_OK, t);
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PRODUCTORA)
			            .actualizar(Evento.RES_MOSTRAR_PRODUCTORA_KO, "Productora no encontrada.");
			    break;
			}
			case Evento.MOSTRAR_PRODUCTORA_POR_NACIONALIDAD:{
				int idNac = (Integer) datos;
			    List<TProductora> lista = FactorySA.getInstance().getSAProductora().mostrarPorNacionalidad(idNac);

			    if (lista != null && !lista.isEmpty())
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD)
			            .actualizar(Evento.RES_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD_OK, lista);
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD)
			            .actualizar(Evento.RES_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD_KO, "No se encontraron productoras activas para esa nacionalidad.");
			    break;
			}
			case Evento.MOSTRAR_TODAS_LAS_PRODUCTORAS:{
				List<TProductora> lista = FactorySA.getInstance().getSAProductora().mostrarTodas();

			    if (lista != null && !lista.isEmpty())
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_PRODUCTORAS)
			            .actualizar(Evento.RES_MOSTRAR_TODAS_LAS_PRODUCTORAS_OK, lista);
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_PRODUCTORAS)
			            .actualizar(Evento.RES_MOSTRAR_TODAS_LAS_PRODUCTORAS_KO, "No hay productoras activas en el sistema.");
			    break;
			}
				
				
				
		// -- TRABAJADOR -- //
			case Evento.GUI_TRABAJADOR:
				FactoryGUI.getInstance().getGUI(Evento.GUI_TRABAJADOR);
				break;
			case Evento.GUI_CREAR_TRABAJADOR:
				FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_TRABAJADOR);
				break;
			case Evento.GUI_ELIMINAR_TRABAJADOR:
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_TRABAJADOR);
				break;
			case Evento.GUI_MODIFICAR_TRABAJADOR:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_TRABAJADOR);
				break;
			case Evento.GUI_MOSTRAR_TRABAJADOR:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TRABAJADOR);
				break;
			case Evento.GUI_MOSTRAR_TODOS_LOS_TRABAJADORES:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_TRABAJADORES);
				break;
			case Evento.CREAR_TRABAJADOR:{
				TTrabajador tTrabajador = (TTrabajador) datos;
			    int res = FactorySA.getInstance().getSATrabajador().crear(tTrabajador);

			    if (res > 0) // Éxito: devuelve el ID generado
			        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_TRABAJADOR)
			            .actualizar(Evento.RES_CREAR_TRABAJADOR_OK, res);
			    else if (res == -1)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_TRABAJADOR)
			            .actualizar(Evento.RES_CREAR_TRABAJADOR_KO, "Error al añadir el trabajador a la base de datos.");
			    else if (res == -2)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_CREAR_TRABAJADOR)
			            .actualizar(Evento.RES_CREAR_TRABAJADOR_KO, "Ya existe un trabajador activo con ese nombre.");
			    break;
			}
			case Evento.ELIMINAR_TRABAJADOR:{
				int id = (Integer) datos;
			    int res = FactorySA.getInstance().getSATrabajador().eliminar(id);

			    if (res >= 0)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_TRABAJADOR)
			            .actualizar(Evento.RES_ELIMINAR_TRABAJADOR_OK, null);
			    else if (res == -2)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_TRABAJADOR)
			            .actualizar(Evento.RES_ELIMINAR_TRABAJADOR_KO, "El trabajador no existe en la base de datos.");
			    else if (res == -3)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_TRABAJADOR)
			            .actualizar(Evento.RES_ELIMINAR_TRABAJADOR_KO, "No se puede eliminar: El trabajador tiene ventas vinculadas.");
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_TRABAJADOR)
			            .actualizar(Evento.RES_ELIMINAR_TRABAJADOR_KO, "Error al procesar la baja.");
			    break;
			}
			case Evento.MODIFICAR_TRABAJADOR:{
				TTrabajador tTrabajador = (TTrabajador) datos;
			    int res = FactorySA.getInstance().getSATrabajador().modificar(tTrabajador);

			    if (res >= 0)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_TRABAJADOR)
			            .actualizar(Evento.RES_MODIFICAR_TRABAJADOR_OK, null);
			    else if (res == -1)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_TRABAJADOR)
			            .actualizar(Evento.RES_MODIFICAR_TRABAJADOR_KO, "No existe un trabajador con ese ID.");
			    else if (res == -2)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_TRABAJADOR)
			            .actualizar(Evento.RES_MODIFICAR_TRABAJADOR_KO, "Ya existe otro trabajador con ese nombre.");
			    else if (res == -3)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_TRABAJADOR)
			            .actualizar(Evento.RES_MODIFICAR_TRABAJADOR_KO, "Error técnico al realizar la modificación.");
			    break;
			}
			case Evento.MOSTRAR_TRABAJADOR:{
				int id = (Integer) datos;
			    TTrabajador t = FactorySA.getInstance().getSATrabajador().mostrar(id);

			    if (t != null)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TRABAJADOR)
			            .actualizar(Evento.RES_MOSTRAR_TRABAJADOR_OK, t);
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TRABAJADOR)
			            .actualizar(Evento.RES_MOSTRAR_TRABAJADOR_KO, "Trabajador no encontrado.");
			    break;
			}
			case Evento.MOSTRAR_TODOS_LOS_TRABAJADORES:{
				List<TTrabajador> lista = FactorySA.getInstance().getSATrabajador().mostrarTodos();

			    if (lista != null && !lista.isEmpty())
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_TRABAJADORES)
			            .actualizar(Evento.RES_MOSTRAR_TODOS_LOS_TRABAJADORES_OK, lista);
			    else
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODOS_LOS_TRABAJADORES)
			            .actualizar(Evento.RES_MOSTRAR_TODOS_LOS_TRABAJADORES_KO, "No hay trabajadores activos.");
			    break;
			}
				
				
				
		// -- VENTA -- //
			case Evento.GUI_VENTA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_VENTA);
				break;
			case Evento.GUI_ABRIR_VENTA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_ABRIR_VENTA);
				break;
			case Evento.GUI_CERRAR_VENTA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_CERRAR_VENTA);
				break;
			case Evento.GUI_ELIMINAR_VENTA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_VENTA);
				break;
			case Evento.GUI_MODIFICAR_VENTA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_VENTA);
				break;
			case Evento.GUI_MOSTRAR_VENTA:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTA);
				break;
			case Evento.GUI_MOSTRAR_TODAS_LAS_VENTAS:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_VENTAS);
				break;
			case Evento.GUI_MOSTRAR_VENTAS_POR_CLIENTE:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTAS_POR_CLIENTE);
				break;
			case Evento.GUI_MOSTRAR_VENTAS_POR_TRABAJADOR:
				FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTAS_POR_TRABAJADOR);
				break;
			case Evento.GUI_ANIADIR_PELICULA_A_CARRITO:
				FactoryGUI.getInstance().getGUI(Evento.GUI_ANIADIR_PELICULA_A_CARRITO);
				break;
			case Evento.GUI_ELIMINAR_PELICULA_DE_CARRITO:
				FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_PELICULA_DE_CARRITO);
				break;
			case Evento.GUI_HACER_DEVOLUCION:
				FactoryGUI.getInstance().getGUI(Evento.GUI_HACER_DEVOLUCION);
				break;
			case Evento.ABRIR_VENTA:{
				FactoryGUI.getInstance().getGUI(Evento.GUI_ABRIR_VENTA).actualizar(Evento.RES_ABRIR_VENTA_OK, null);
				break;
			}
			case Evento.CERRAR_VENTA:{
				TVenta tVenta = (TVenta) datos;
				int res = FactorySA.getInstance().getSAVenta().cerrar(tVenta);

				if (res > 0)
					FactoryGUI.getInstance().getGUI(Evento.GUI_ABRIR_VENTA)
						.actualizar(Evento.RES_CERRAR_VENTA_OK, res);
				else {
					String msg;
					switch (res) {
						case -1: msg = "Error: Parámetros de venta inválidos."; break;
						case -2: msg = "Error: El carrito está vacío."; break;
						case -3: msg = "Error: El Cliente no existe o está inactivo."; break;
						case -4: msg = "Error: El Trabajador no existe o está inactivo."; break;
						case -5: msg = "Error: Alguna película del carrito no existe o no está activa."; break;
						case -6: msg = "Error: Stock insuficiente para completar la venta."; break;
						case -7: msg = "Error crítico: Fallo al guardar en la Base de Datos."; break;
						default: msg = "Error desconocido al cerrar la venta."; break;
					}
					FactoryGUI.getInstance().getGUI(Evento.GUI_ABRIR_VENTA)
						.actualizar(Evento.RES_CERRAR_VENTA_KO, msg);
				}
				break;
			}
			case Evento.ELIMINAR_VENTA:{
				int id = (Integer) datos;
				int res = FactorySA.getInstance().getSAVenta().eliminar(id);

				if (res >= 0)
					FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_VENTA)
						.actualizar(Evento.RES_ELIMINAR_VENTA_OK, null);
				else
					FactoryGUI.getInstance().getGUI(Evento.GUI_ELIMINAR_VENTA)
						.actualizar(Evento.RES_ELIMINAR_VENTA_KO, "Error al eliminar la venta (ID inexistente).");
				break;
			}
			case Evento.MODIFICAR_VENTA:{
				TVenta tVenta = (TVenta) datos;
			    int res = FactorySA.getInstance().getSAVenta().modificar(tVenta);

			    if (res >= 0)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_VENTA)
			            .actualizar(Evento.RES_MODIFICAR_VENTA_OK, null);
			    else {
			        String msg = (res == -2) ? "La venta no existe o no está activa." : "Error al modificar parámetros.";
			        FactoryGUI.getInstance().getGUI(Evento.GUI_MODIFICAR_VENTA)
			            .actualizar(Evento.RES_MODIFICAR_VENTA_KO, msg);
			    }
			    break;
			}
			case Evento.MOSTRAR_VENTA:{
				int id = (Integer) datos;
				TVenta v = FactorySA.getInstance().getSAVenta().mostrar(id);

				if (v != null)
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTA)
						.actualizar(Evento.RES_MOSTRAR_VENTA_OK, v);
				else
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTA)
						.actualizar(Evento.RES_MOSTRAR_VENTA_KO, "Venta no encontrada o inactiva.");
				break;
			}
			case Evento.MOSTRAR_TODAS_LAS_VENTAS:{
				List<TVenta> lista = FactorySA.getInstance().getSAVenta().mostrarTodas();

				if (lista != null && !lista.isEmpty())
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_VENTAS)
						.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_VENTAS_OK, lista);
				else
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_TODAS_LAS_VENTAS)
						.actualizar(Evento.RES_MOSTRAR_TODAS_LAS_VENTAS_KO, "No hay ventas registradas.");
				break;
			}
			case Evento.MOSTRAR_VENTAS_POR_CLIENTE:{
				int idCliente = (Integer) datos;
				List<TVenta> lista = FactorySA.getInstance().getSAVenta().mostrarPorCiente(idCliente);

				if (lista != null && !lista.isEmpty())
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTAS_POR_CLIENTE)
						.actualizar(Evento.RES_MOSTRAR_VENTAS_POR_CLIENTE_OK, lista);
				else
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTAS_POR_CLIENTE)
						.actualizar(Evento.RES_MOSTRAR_VENTAS_POR_CLIENTE_KO, "No se encontraron ventas para este cliente.");
				break;
			}
			case Evento.MOSTRAR_VENTAS_POR_TRABAJADOR:{
				int idTrabajador = (Integer) datos;
				List<TVenta> lista = FactorySA.getInstance().getSAVenta().mostrarPorTrabajador(idTrabajador);

				if (lista != null && !lista.isEmpty())
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTAS_POR_TRABAJADOR)
						.actualizar(Evento.RES_MOSTRAR_VENTAS_POR_TRABAJADOR_OK, lista);
				else
					FactoryGUI.getInstance().getGUI(Evento.GUI_MOSTRAR_VENTAS_POR_TRABAJADOR)
						.actualizar(Evento.RES_MOSTRAR_VENTAS_POR_TRABAJADOR_KO, "No se encontraron ventas para este trabajador.");
				break;
			}
			case Evento.ANIADIR_PELICULA_A_CARRITO:{
				FactoryGUI.getInstance().getGUI(Evento.GUI_ABRIR_VENTA).actualizar(Evento.RES_ANIADIR_PELICULA_A_CARRITO_OK, datos);
				break;
			}
			case Evento.ELIMINAR_PELICULA_DE_CARRITO:{
				FactoryGUI.getInstance().getGUI(Evento.GUI_ABRIR_VENTA).actualizar(Evento.RES_ELIMINAR_PELICULA_DE_CARRITO_OK, datos);
		    break;
			}
			case Evento.HACER_DEVOLUCION:{
				TVenta tVenta = (TVenta) datos;
			    int res = FactorySA.getInstance().getSAVenta().hacerDevolucion(tVenta);

			    if (res >= 0)
			        FactoryGUI.getInstance().getGUI(Evento.GUI_HACER_DEVOLUCION)
			            .actualizar(Evento.RES_HACER_DEVOLUCION_OK, null);
			    else {
			        String msg = (res == -2) ? "Venta no encontrada o ya inactiva." : "Error al realizar la devolución.";
			        FactoryGUI.getInstance().getGUI(Evento.GUI_HACER_DEVOLUCION)
			            .actualizar(Evento.RES_HACER_DEVOLUCION_KO, msg);
				}
				break;
			}
		}		
	}
}
		
