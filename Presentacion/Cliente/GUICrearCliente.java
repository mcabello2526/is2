/**
 *
 */
package Presentacion.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Negocio.Cliente.TCliente;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUICrearCliente extends JFrame implements ActionListener, IGUI {
	
	private javax.swing.JButton button;
	private javax.swing.JTextField nombre;
	private javax.swing.JTextField apellidos;
	private javax.swing.JTextField correo;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			
			String textNombre = this.nombre.getText();
			String textApellidos = this.apellidos.getText();
			String textCorreo = this.correo.getText();
			
			if (textNombre != null && !textNombre.isEmpty() &&
				textApellidos != null && !textApellidos.isEmpty() &&
				textCorreo != null && !textCorreo.isEmpty()) {
				
				TCliente tCliente = new TCliente();
				tCliente.setNombre(textNombre);
				tCliente.setApellidos(textApellidos);
				tCliente.setCorreo(textCorreo);
				
				Controlador.getInstance().accion(Evento.CREAR_CLIENTE, tCliente);
				
			} else {
				javax.swing.JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos");
			}
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_CREAR_CLIENTE_OK) {
			
			javax.swing.JOptionPane.showMessageDialog(this, "Cliente creado correctamente");
			this.dispose();
			
		} else if (evento == Evento.RES_CREAR_CLIENTE_KO) {
			String mensajeError = (String) datos;
			javax.swing.JOptionPane.showMessageDialog(this, mensajeError, "Error al intentar crear el cliente", JOptionPane.ERROR_MESSAGE);
		}
	}
}