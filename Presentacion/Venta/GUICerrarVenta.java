/**
 *
 */
package Presentacion.Venta;

import javax.swing.JFrame;
import Presentacion.IGUI;


public class GUICerrarVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	public GUICerrarVenta() {
        super("Cerrar Venta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // El cierre de venta se gestiona desde GUIAbrirVenta.
        // Esta clase se mantiene como stub para compatibilidad con FactoryGUI.
    }
    @Override
    public void actualizar(int evento, Object datos) {}
}