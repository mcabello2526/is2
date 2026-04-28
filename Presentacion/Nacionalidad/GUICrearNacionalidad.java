
package Presentacion.Nacionalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Negocio.Nacionalidad.TNacionalidad;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.ControladorImp;

public class GUICrearNacionalidad extends JFrame implements ActionListener, IGUI {

	private javax.swing.JButton button;
	
	private javax.swing.JTextField nombre;

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.button) {
			
			String text = this.nombre.getText();
			
			if (text != null && !text.isEmpty()) {
				
				TNacionalidad tnacionalidad = new TNacionalidad();
				tnacionalidad.setNombre(text);
				
				ControladorImp.getInstance().accion(Evento.CREAR_NACIONALIDAD,tnacionalidad);
			} else {
				JOptionPane.showMessageDialog(this, "por favor, introduce un nombre");
			}
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {

		if (evento == Evento.RES_CREAR_NACIONALIDAD_OK) {
			JOptionPane.showMessageDialog(this, "nacionalidad creado correctamente");
			this.dispose();
		} else if(evento == Evento.RES_CREAR_NACIONALIDAD_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Error al crear",JOptionPane.ERROR_MESSAGE);
		}
	
	}
}