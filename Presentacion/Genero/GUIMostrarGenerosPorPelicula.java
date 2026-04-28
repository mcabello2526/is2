/**
 *
 */
package Presentacion.Genero;

import javax.swing.JFrame;


import java.awt.event.ActionListener;
import java.util.List;

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

public class GUIMostrarGenerosPorPelicula extends JFrame implements ActionListener, IGUI {
	
	private JButton button ;

	private JTextField nombre;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {

			String text = this.nombre.getText();

			if (text != null && !text.isEmpty()) {
				try {

					int idpelicula = Integer.parseInt(text);


					ControladorImp.getInstance().accion(Evento.MOSTRAR_GENEROS_POR_PELICULA, idpelicula);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "El ID tiene que ser un número válido");
				}
			} else {
				JOptionPane.showMessageDialog(this, "El campo no puede estar vacío.");
			}
		}
	}

	public void actualizar(int evento, Object datos) {
		 
		if (evento == Evento.RES_MOSTRAR_GENEROS_POR_PELICULA_OK && datos != null) {
			
			try {
			List<TGenero> listag = (List<TGenero>) datos;
			
			if (listag.isEmpty()) {
				
				StringBuilder mensajeInfo = new StringBuilder("Géneros asociados a la película: \n\n");
				
				for (TGenero genero : listag) {
					mensajeInfo.append("- ID: ").append(genero.getId())
			           .append(" | Nombre: ").append(genero.getNombre())
			           .append("\n");
				}
				
				JOptionPane.showMessageDialog(this, mensajeInfo.toString(), "Resultados", JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}	catch(ClassCastException ex){
			
			JOptionPane.showMessageDialog(this, "Error interno con los datos.");
		}
			
		}
	
		else if (evento == Evento.RES_MOSTRAR_GENEROS_POR_PELICULA_KO) {
	
			String mensajeError = (String) datos;
			JOptionPane.showMessageDialog(this, mensajeError, "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	
}
}