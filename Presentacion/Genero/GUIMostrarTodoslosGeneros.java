/**
 *
 */
package Presentacion.Genero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Presentacion.IGUI;
import Presentacion.IGUI;
import static Presentacion.Controlador.Controlador.*;
import static Negocio.Genero.TGenero.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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

public class GUIMostrarTodoslosGeneros extends JFrame implements ActionListener, IGUI {
	
	private JButton button;
	
	private JTextField nombre;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			
		ControladorImp.getInstance().accion(Evento.MOSTRAR_TODOS_LOS_GENEROS, null);
			
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
		if (evento == Evento.RES_MOSTRAR_TODOS_LOS_GENEROS_OK && datos != null) {
			
			try {
				List<TGenero> listag = (List<TGenero>) datos;
				
				if (listag.isEmpty()) {
					JOptionPane.showMessageDialog(this, "No hay ningún género registrado en el sistema.");
				}
				else{
					
				StringBuilder mensajeInfo = new StringBuilder("Géneros asociados a la película: \n\n");
				
			
				for (TGenero genero : listag) {
						mensajeInfo.append("- ID: ").append(genero.getId())
				           .append(" | Nombre: ").append(genero.getNombre())
				           .append("\n");
					}
				JOptionPane.showMessageDialog(this, mensajeInfo.toString(), "Todos los Géneros", JOptionPane.INFORMATION_MESSAGE);
			}
		
			}catch(ClassCastException ex) {
				JOptionPane.showMessageDialog(this, "Error interno: El formato de los datos devueltos no es correcto.");
			}
			}
			else if (evento == Evento.RES_MOSTRAR_TODOS_LOS_GENEROS_KO) {
			
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Aviso", JOptionPane.WARNING_MESSAGE);
		
	}
	}
}