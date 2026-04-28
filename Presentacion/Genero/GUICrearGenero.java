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


public class GUICrearGenero extends JFrame implements ActionListener, IGUI {
	
	private JButton button;
	
	private JTextField nombre;

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.button) {
			
			String text = this.nombre.getText();
			
			if (text != null && !text.isEmpty()) {
				
				TGenero tgenero = new TGenero();
				tgenero.setNombre(text);
				
				ControladorImp.getInstance().accion(Evento.CREAR_GENERO,tgenero);
			} else {
				JOptionPane.showMessageDialog(this, "por favor, introduce un nombre");
			}
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
		if (evento == Evento.RES_CREAR_GENERO_OK) {
			JOptionPane.showMessageDialog(this, "género creado correctamente");
			this.dispose();
		} else if(evento == Evento.RES_CREAR_GENERO_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Error al crear",JOptionPane.ERROR_MESSAGE);
		}
	}
}