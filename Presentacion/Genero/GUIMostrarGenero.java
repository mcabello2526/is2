/**
 *
 */
package Presentacion.Genero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Presentacion.IGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Presentacion.Evento;
import Presentacion.IGUI;
import Negocio.Genero.TGenero;
import Presentacion.Controlador.ControladorImp;
public class GUIMostrarGenero extends JFrame implements ActionListener, IGUI {
	
	
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

					ControladorImp.getInstance().accion(Evento.MOSTRAR_GENERO, tgenero);
					
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
		
		if (evento == Evento.RES_MOSTRAR_GENERO_OK && datos != null) {
			
			TGenero genero = (TGenero) datos;
			String mostrar = "Datos del género encontrado: \n\n" + "ID: " + genero.getId() 
			+ "Nombre: " + genero.getNombre();
			
			JOptionPane.showMessageDialog(this, mostrar, "Detalles del Género", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (evento == Evento.RES_MOSTRAR_GENERO_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
}