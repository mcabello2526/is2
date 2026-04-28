/**
 *
 */
package Presentacion.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TNormal;
import Negocio.Cliente.TVip;
import Presentacion.Evento;
import Presentacion.Controlador.Controlador;
import Presentacion.IGUI;


public class GUIMostrarCliente extends JFrame implements IGUI, ActionListener {

	private JButton button;
	private JTextField id;

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MOSTRAR_CLIENTE_OK) {
			TCliente cliente = (TCliente) datos;
			String tipo;
			if (cliente.getEsVip()) {
				tipo = "Vip" + "\n" + "Descuento: " + ((TVip) cliente).getDescuento() + "%"; 
			}
			else {
				tipo = "Normal" + "\n" + "Copias: " + ((TNormal) cliente).getNumCopias(); 
			}
			String info = "ID: " + cliente.getId() + "\n" +
						  "Nombre: " + cliente.getNombre() + "\n" +
						  "Apellidos: " + cliente.getApellidos() + "\n" +
						  "Correo: " + cliente.getCorreo() + "\n" +
						  "Tipo: " + tipo;
						  
			JOptionPane.showMessageDialog(this, info, "Datos del Cliente", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			
		} else if (evento == Evento.RES_MOSTRAR_CLIENTE_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Error al mostrar", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			
			String textId = this.id.getText();
			
			if (textId != null && !textId.isEmpty()) {
				try {
					int idParseado = Integer.parseInt(textId);
					TCliente tCliente = new TCliente();
					tCliente.setId(idParseado);
					Controlador.getInstance().accion(Evento.MOSTRAR_CLIENTE, tCliente);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "El ID tiene que ser un número válido");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Por favor, introduce un ID");
			}
		}
	}
}