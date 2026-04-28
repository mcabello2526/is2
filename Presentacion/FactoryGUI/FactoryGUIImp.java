package Presentacion.FactoryGUI;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Cliente.*;
import Presentacion.GUITiendaPelis.GUITiendaPelis;
import Presentacion.Genero.*;
import Presentacion.Nacionalidad.*;
import Presentacion.Pelicula.*;
import Presentacion.Productora.*;
import Presentacion.Trabajador.*;
import Presentacion.Venta.*;



public class FactoryGUIImp extends FactoryGUI {

	@Override
	public IGUI getGUI(int evento) {
		switch (evento) {
		//MENU
			case Evento.GUI_TIENDAPELIS:
				return (IGUI) new GUITiendaPelis();
		
		
		//CLIENTE
			case Evento.GUI_CREAR_CLIENTE:
				return (IGUI) new GUICrearCliente();
				
			case Evento.GUI_ELIMINAR_CLIENTE:
				return (IGUI) new GUIEliminarCliente();
				
			case Evento.GUI_MODIFICAR_CLIENTE:
				return (IGUI) new GUIModificarCliente();
				
			case Evento.GUI_MOSTRAR_CLIENTE:
				return (IGUI) new GUIMostrarCliente();
				
			case Evento.GUI_MOSTRAR_TODOS_LOS_CLIENTES:
				return (IGUI) new GUIMostrarTodosLosClientes();
				

			
			//GENERO
			case Evento.GUI_CREAR_GENERO:
				return (IGUI) new GUICrearGenero();
				
			case Evento.GUI_ELIMINAR_GENERO:
				return (IGUI) new GUIEliminarGenero();
				
			case Evento.GUI_MODIFICAR_GENERO:
				return (IGUI) new GUIModificarGenero();
				
			case Evento.GUI_MOSTRAR_GENERO:
				return (IGUI) new GUIMostrarGenero();
			
			case Evento.GUI_MOSTRAR_GENEROS_POR_PELICULA:
				return (IGUI) new GUIMostrarGenerosPorPelicula();
				
			case Evento.GUI_MOSTRAR_TODOS_LOS_GENEROS:
				return (IGUI) new GUIMostrarTodoslosGeneros();
	
				
			
			//NACIONALIDAD
			case Evento.GUI_CREAR_NACIONALIDAD:
				return (IGUI) new GUICrearNacionalidad();
				
			case Evento.GUI_ELIMINAR_NACIONALIDAD:
				return (IGUI) new GUIEliminarNacionalidad();
				
			case Evento.GUI_MODIFICAR_NACIONALIDAD:
				return (IGUI) new GUIModificarNacionalidad();
				
			case Evento.GUI_MOSTRAR_NACIONALIDAD:
				return (IGUI) new GUIMostrarNacionalidad();
				
			case Evento.GUI_MOSTRAR_TODAS_LAS_NACIONALIDADES:
				return (IGUI) new GUIMostrarTodasLasNacionalidades();
				
		
			
			//PELICULA
			case Evento.GUI_CREAR_PELICULA:
				return (IGUI) new GUICrearPelicula();	
				
			case Evento.GUI_DESVINCULAR_PELICULA_A_GENERO:
				return (IGUI) new GUIDesvincularPeliculaAGenero();	
				
			case Evento.GUI_ELIMINAR_PELICULA:
				return (IGUI) new GUIEliminarPelicula();	
				
			case Evento.GUI_MOSTRAR_PELICULA:
				return (IGUI) new GUIMostrarPelicula();	
				
			case Evento.GUI_MOSTRAR_PELICULA_POR_NOMBRE:
				return (IGUI) new GUIMostrarPeliculaPorNombre();	
				
			case Evento.GUI_MOSTRAR_PELICULAS_POR_GENERO:
				return (IGUI) new GUIMostrarPeliculasPorGenero();	
				
			case Evento.GUI_MOSTRAR_PELICULAS_POR_PRODUCTORA:
				return (IGUI) new GUIMostrarPeliculasPorProductora();	
				
			case Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS:
				return (IGUI) new GUIMostrarTodasLasPeliculas();	
				
			case Evento.GUI_MODIFICAR_PELICULA:
				return (IGUI) new GUIModificarPelicula();	
				
			case Evento.GUI_VINCULAR_PELICULA_A_GENERO:
				return (IGUI) new GUIVincularPeliculaAGenero();	
				
				
				
			//PRODUCTORA
			case Evento.GUI_CREAR_PRODUCTORA:
				return (IGUI) new GUICrearProductora();	
				
			case Evento.GUI_ELIMINAR_PRODUCTORA:
				return (IGUI) new GUIEliminarProductora();	
				
			case Evento.GUI_MODIFICAR_PRODUCTORA:
				return (IGUI) new GUIModificarProductora();	
				
			case Evento.GUI_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD:
				return (IGUI) new GUIMostrarProductoraPorNacionalidad();	
				
			case Evento.GUI_MOSTRAR_PRODUCTORA:
				return (IGUI) new GUIMostrarProductora();	
				
			case Evento.GUI_MOSTRAR_TODAS_LAS_PRODUCTORAS:
				return (IGUI) new GUIMostrarTodasLasProductoras();	
				
				
				
			//TRABAJADOR
			case Evento.GUI_CREAR_TRABAJADOR:
				return (IGUI) new GUICrearTrabajador();	
				
			case Evento.GUI_ELIMINAR_TRABAJADOR:
				return (IGUI) new GUIEliminarTrabajador();	
				
			case Evento.GUI_MODIFICAR_TRABAJADOR:
				return (IGUI) new GUIModificarTrabajador();	
				
			case Evento.GUI_MOSTRAR_TODOS_LOS_TRABAJADORES:
				return (IGUI) new GUIMostrarTodosLosTrabajadores();	
				
			case Evento.GUI_MOSTRAR_TRABAJADOR:
				return (IGUI) new GUIMostrarTrabajador();	
			
				
				
			//VENTA
			case Evento.GUI_ABRIR_VENTA:
				return (IGUI) new GUIAbrirVenta();	
				
			case Evento.GUI_ANIADIR_PELICULA_A_CARRITO:
				return (IGUI) new GUIAniadirPeliculaACarrito();	
				
			case Evento.GUI_CERRAR_VENTA:
				return (IGUI) new GUICerrarVenta();	
				
			case Evento.GUI_ELIMINAR_PELICULA_DE_CARRITO:
				return (IGUI) new GUIEliminarPeliculaDeCarrito();	
				
			case Evento.GUI_ELIMINAR_VENTA:
				return (IGUI) new GUIEliminarVenta();	
				
			case Evento.GUI_HACER_DEVOLUCION:
				return (IGUI) new GUIHacerDevolucion();	
				
			case Evento.GUI_MODIFICAR_VENTA:
				return (IGUI) new GUIModificarVenta();	
				
			case Evento.GUI_MOSTRAR_TODAS_LAS_VENTAS:
				return (IGUI) new GUIMostrarTodasLasVentas();	
				
			case Evento.GUI_MOSTRAR_VENTA:
				return (IGUI) new GUIMostrarVenta();	
				
			case Evento.GUI_MOSTRAR_VENTAS_POR_CLIENTE:
				return (IGUI) new GUIMostrarVentasPorCliente();	
				
			case Evento.GUI_MOSTRAR_VENTAS_POR_TRABAJADOR:
				return (IGUI) new GUIMostrarVentasPorTrabajador();	
				
				
			default:
				return null;
		}
	}
}
