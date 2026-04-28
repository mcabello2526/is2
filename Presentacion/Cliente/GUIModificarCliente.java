/**
 *
 */
package Presentacion.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TNormal;
import Negocio.Cliente.TVip;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUIModificarCliente extends JFrame implements IGUI, ActionListener {
	
	private JButton button;
	private JTextField id;
	private JTextField nombre;
	private JTextField apellidos;
	private JTextField correo;
	private JComboBox<String> tipo;
	private JTextField datoExtra; // Servirá para el descuento (VIP) o numCopias (Normal)
	

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MODIFICAR_CLIENTE_OK) {
			JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.");
			this.dispose();
		} else if (evento == Evento.RES_MODIFICAR_CLIENTE_KO) {
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Error al modificar", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			
			String textId = this.id.getText();
			String textNombre = this.nombre.getText();
			String textApellidos = this.apellidos.getText();
			String textCorreo = this.correo.getText();
			String textTipo = (String) this.tipo.getSelectedItem();
			String textDatoExtra = this.datoExtra.getText();
			
			if (textId != null && !textId.isEmpty() &&
				textNombre != null && !textNombre.isEmpty() &&
				textApellidos != null && !textApellidos.isEmpty() &&
				textCorreo != null && !textCorreo.isEmpty() &&
				textDatoExtra != null && !textDatoExtra.isEmpty()) {
				
				try {
					int idParseado = Integer.parseInt(textId);
					int extraParseado = Integer.parseInt(textDatoExtra);
					
					TCliente tcliente;
					
					// Instanciamos la clase hija correspondiente
					if ("VIP".equals(textTipo)) {
						tcliente = new TVip();
						((TVip) tcliente).setDescuento(extraParseado);
					} else {
						tcliente = new TNormal();
						((TNormal) tcliente).setNumCopias(extraParseado);
					}
					
					// Asignamos los campos comunes
					tcliente.setId(idParseado);
					tcliente.setNombre(textNombre);
					tcliente.setApellidos(textApellidos);
					tcliente.setCorreo(textCorreo);
					
					Controlador.getInstance().accion(Evento.MODIFICAR_CLIENTE, tcliente);
					
				} catch (NumberFormatException ex) {
					// Ahora avisamos de que tanto el ID como el dato extra deben ser números
					JOptionPane.showMessageDialog(this, "El ID y las copias/descuento tienen que ser números válidos.");
				}
				
			} else {
				JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos");
			}
		}
	} 
}