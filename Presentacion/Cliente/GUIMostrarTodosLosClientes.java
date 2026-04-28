/**
 *
 */
package Presentacion.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TNormal;
import Negocio.Cliente.TVip;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUIMostrarTodosLosClientes extends JFrame implements IGUI, ActionListener {
	
	private javax.swing.JButton button;
	private JTextArea textArea; // Área de texto para volcar todos los datos
	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MOSTRAR_TODOS_LOS_CLIENTES_OK) {
	        
	        List<TCliente> lista = (List<TCliente>) datos;
	        
	        if (lista == null || lista.isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(this, "No hay clientes registrados.");
	        } else {
	            String textoFinal = ""; 
	            
	            for (TCliente c : lista) {
	                String infoTipo;
	                if (c.getEsVip()) {
	                    infoTipo = "VIP (Descuento: " + ((TVip) c).getDescuento() + "%)"; 
	                } else {
	                    infoTipo = "Normal (Copias: " + ((TNormal) c).getNumCopias() + ")"; 
	                }
	                
	                textoFinal += "ID: " + c.getId() + " - " + 
	                              c.getNombre() + " " + c.getApellidos() + 
	                              " [" + c.getCorreo() + "] - " + infoTipo + "\n";
	            }
	            
	            javax.swing.JOptionPane.showMessageDialog(this, textoFinal, "Lista de Clientes", javax.swing.JOptionPane.INFORMATION_MESSAGE);
	            this.dispose(); 
	        }
	        
	    } else if (evento == Evento.RES_MOSTRAR_TODOS_LOS_CLIENTES_KO) {
	        String mensajeError = (String) datos;
	        javax.swing.JOptionPane.showMessageDialog(this, mensajeError, "Error al mostrar", javax.swing.JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			// No necesitamos pasar ningún dato para mostrar todos
			Controlador.getInstance().accion(Evento.MOSTRAR_TODOS_LOS_CLIENTES, null);
		}
	}
}