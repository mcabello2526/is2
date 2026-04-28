
package Presentacion.Nacionalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Negocio.Nacionalidad.TNacionalidad;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.ControladorImp;


public class GUIModificarNacionalidad extends JFrame implements IGUI, ActionListener {
	
	private Object button;
	private javax.swing.JTextField nombre;

	@Override
	public void actualizar(int evento, java.lang.Object datos) {
		if (evento == Evento.RES_MODIFICAR_NACIONALIDAD_OK) {
			JOptionPane.showMessageDialog(this, "Nacionalidad modificado correctamente.");
			this.dispose(); 
		} else if (evento == Evento.RES_MODIFICAR_NACIONALIDAD_KO) {
			
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Error al modificar", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			
			String text = this.nombre.getText();
			
			if (text != null && !text.isEmpty()) {
				
				TNacionalidad tnacionalidad = new TNacionalidad();
				tnacionalidad.setNombre(text);
				
				ControladorImp.getInstance().accion(Evento.MODIFICAR_NACIONALIDAD,tnacionalidad);
			} else {
				JOptionPane.showMessageDialog(this, "por favor, introduce un nombre");
			}
		}
	}
}