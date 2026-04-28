
package Presentacion.Nacionalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Negocio.Nacionalidad.TNacionalidad;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.ControladorImp;

public class GUIMostrarTodasLasNacionalidades extends JFrame implements ActionListener, IGUI {

	private javax.swing.JButton button;
	private javax.swing.JTextField nombre;

	public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.button) {

        ControladorImp.getInstance().accion(Evento.MOSTRAR_TODAS_LAS_NACIONALIDADES, null);
        }	
	}

	@Override
	public void actualizar(int evento, Object datos) {

		   if (evento == Evento.RES_MOSTRAR_TODAS_LAS_NACIONALIDADES_OK && datos != null) {

	            try {
	                List<TNacionalidad> listag = (List<TNacionalidad>) datos;

	                if (listag.isEmpty()) {
	                    JOptionPane.showMessageDialog(this, "No hay ninguna nacionalidad registrada en el sistema.");
	                }
	                else{

	                StringBuilder mensajeInfo = new StringBuilder(" ");
//AYUDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

	                for (TNacionalidad nacionalidad : listag) {
	                       mensajeInfo.append("- ID: ").append(nacionalidad.getId())
	                           .append(" | Nombre: ").append(nacionalidad.getNombre())
	                           .append("\n");
	                    }
	                JOptionPane.showMessageDialog(this, mensajeInfo.toString(), "Todos las Nacionalidades", JOptionPane.INFORMATION_MESSAGE);
	            }

	            }catch(ClassCastException ex) {
	                JOptionPane.showMessageDialog(this, "Error interno: El formato de los datos devueltos no es correcto.");
	            }
	            }
	            else if (evento == Evento.RES_MOSTRAR_TODAS_LAS_NACIONALIDADES_KO) {

	            String mensajeError = (String) datos;
	            JOptionPane.showMessageDialog(this, mensajeError, "Aviso", JOptionPane.WARNING_MESSAGE);

	    }
	    }	
		
	
}