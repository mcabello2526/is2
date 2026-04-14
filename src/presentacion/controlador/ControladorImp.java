package presentacion.controlador;

import negocio.factorySA.FactorySA;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.TTrabajador;
import presentacion.GUITiendaDePelis.*; 


public class ControladorImp extends Controlador{
	


	@Override
	public void accion(int evento, Object datos ) {
		// TODO Auto-generated method stub
		switch (evento) {
		case Evento.CREAR_TRABAJADOR: 
		{
			TTrabajador tTrabajador = (TTrabajador) datos; 
			SATrabajador saTrabajador = FactorySA.getInstance().getSATrabajador();    // cuando se ha creado este sa?
			int res = saTrabajador.crear(tTrabajador); 
			if (res > 0) {
				// aqui tine que haber una referencia de GUIRespuestaCrearTrabajador pero nosotros no tenemos nada de eso
				IGUI guiRespuestaTrabajador = new GUIMostrarTrabajador();
				guiRespuestaTrabajador.actualizar(Evento.RES_CREAR_TRABAJADOR_OK, res); 
			}
			else {
				guiRespuestaTrabajador.actualizar(Evento.RES_CREAR_TRABAJADOR_KO, res); 
			}
		}
			break; 
		case Evento.ELIMINAR_TRABAJADOR: 
			break; 
		case Evento.MOSTRAR_TRABAJADOR: 
			break; 
		case Evento.MOSTRAR_TODOS_TRABAJADORES: 
			break; 
		case Evento.MODIFICAR_TRABAJADOR: 
			break; 
		
		}
	
		
	}
}
