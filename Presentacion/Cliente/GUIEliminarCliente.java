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


public class GUIEliminarCliente extends JFrame implements IGUI, ActionListener {
	
	private javax.swing.JButton button;
	private javax.swing.JTextField idCliente;
	
	@Override
	public void actualizar(int evento, Object datos) {
		// Comprobamos si el resultado ha sido OK
		if (evento == Evento.RES_ELIMINAR_CLIENTE_OK) {
			
			javax.swing.JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
			this.dispose(); // Cerramos la ventana
			
		// Si ha fallado (KO)
		} else if (evento == Evento.RES_ELIMINAR_CLIENTE_KO) {
			String mensajeError = (String) datos;
			javax.swing.JOptionPane.showMessageDialog(this, mensajeError, "Error al intentar eliminar el cliente", JOptionPane.ERROR_MESSAGE);
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			
			String textId = this.idCliente.getText();
			
			if (textId != null && !textId.isEmpty()) {
				try {
					// Convertimos el texto a número entero
					int id = Integer.parseInt(textId);
					TCliente cliente = new TCliente();
					cliente.setId(id);
					
					// Llamamos al controlador con el evento y el cliente
					Controlador.getInstance().accion(Evento.ELIMINAR_CLIENTE, cliente);
					
				} catch (NumberFormatException ex) {
					// Si el usuario escribe letras en vez de un número, salta este error
					javax.swing.JOptionPane.showMessageDialog(this, "Por favor, introduce un ID numérico válido");
				}
			} else {
				javax.swing.JOptionPane.showMessageDialog(this, "Por favor, introduce un ID");
			}
		}
	}
}