/**
 *
 */
package Presentacion.Venta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Presentacion.IGUI;


class GUICerrarVenta extends JFrame implements IGUI {

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