
package Presentacion.Nacionalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Negocio.Nacionalidad.TNacionalidad;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.ControladorImp;

public class GUIEliminarNacionalidad extends JFrame implements IGUI, ActionListener {

	private javax.swing.JButton button;
	private javax.swing.JTextField nombre;

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_ELIMINAR_NACIONALIDAD_OK) {
			JOptionPane.showMessageDialog(this, "nacionalidad eliminado correctamente");
			this.dispose();
		} else if(evento == Evento.RES_ELIMINAR_NACIONALIDAD_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Error al eliminar", JOptionPane.ERROR_MESSAGE);
			}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {

			String text = this.nombre.getText();

			if (text != null && !text.isEmpty()) {
				try {

					int idnacionalidad = Integer.parseInt(text);

					TNacionalidad tnacionalidad = new TNacionalidad();
					tnacionalidad.setId(idnacionalidad);

					ControladorImp.getInstance().accion(Evento.ELIMINAR_NACIONALIDAD, tnacionalidad);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "El ID tiene que ser un número válido");
				}
			} else {
				JOptionPane.showMessageDialog(this, "El campo no puede estar vacío.");
			}
		}
	}
}