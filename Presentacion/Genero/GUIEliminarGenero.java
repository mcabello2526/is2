/**
 *
 */
package Presentacion.Genero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Presentacion.Evento;
import Presentacion.IGUI;
import Negocio.Genero.TGenero;
import Presentacion.Controlador.ControladorImp;

public class GUIEliminarGenero extends JFrame implements ActionListener, IGUI {

	private JButton button;

	private JTextField nombre;

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.button) {

			String text = this.nombre.getText();

			if (text != null && !text.isEmpty()) {
				try {

					int idgenero = Integer.parseInt(text);

					TGenero tgenero = new TGenero();
					tgenero.setId(idgenero);

					ControladorImp.getInstance().accion(Evento.ELIMINAR_GENERO, tgenero);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "El ID tiene que ser un número válido");
				}
			} else {
				JOptionPane.showMessageDialog(this, "El campo no puede estar vacío.");
			}
		}
	}

	@Override
	public void actualizar(int evento, java.lang.Object datos) {
		if (evento == Evento.RES_ELIMINAR_GENERO_OK) {
			JOptionPane.showMessageDialog(this, "género eliminado correctamente");
			this.dispose();
		} else if(evento == Evento.RES_ELIMINAR_GENERO_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Error al eliminar", JOptionPane.ERROR_MESSAGE);
		}
	}
}