
package Presentacion.Nacionalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Negocio.Nacionalidad.TNacionalidad;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.ControladorImp;


public class GUIMostrarNacionalidad extends JFrame implements ActionListener, IGUI {

	private javax.swing.JButton button;
	private javax.swing.JTextField nombre;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {

			String text = this.nombre.getText();

			if (text != null && !text.isEmpty()) {
				try {

					int idnacionalidad = Integer.parseInt(text);

					TNacionalidad tnacionalidad  = new TNacionalidad();
					tnacionalidad .setId(idnacionalidad);

					ControladorImp.getInstance().accion(Evento.MOSTRAR_NACIONALIDAD, tnacionalidad);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "El ID tiene que ser un número válido");
				}
			} else {
				JOptionPane.showMessageDialog(this, "El campo no puede estar vacío.");
			}
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MOSTRAR_NACIONALIDAD_OK && datos != null) {
			
			TNacionalidad nacionalidad = (TNacionalidad) datos;
			String mostrar = "Datos de la nacionalidad encontrada: \n\n" + "ID: " + nacionalidad.getId() 
			+ "Nombre: " + nacionalidad.getNombre();
			
			JOptionPane.showMessageDialog(this, mostrar, "Detalles de la Nacionalidad", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (evento == Evento.RES_MOSTRAR_NACIONALIDAD_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}