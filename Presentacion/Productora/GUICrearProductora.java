package Presentacion.Productora;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;
import Presentacion.Trabajador.GUITrabajador;

import static Presentacion.Controlador.Controlador.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



import javax.swing.border.LineBorder;

import Negocio.Productora.TProductora;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class GUICrearProductora extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblAnio;
	private JLabel lblNacionalidad;
	private JTextField txtNombre;
	private JTextField txtAnio;
	private JTextField txtNacionalidad;
	private JButton btnAnadir;
	private JButton btnVolver;
	
	public GUICrearProductora() {
		setTitle("Crear Productora");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		setBounds(100, 100, 700, 349);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getPanel());
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Crear Productora");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(148, 10, 357, 42);
		}
		return lblTitulo;
	}
	
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(169, 73, 320, 170);
			panel.setLayout(null);
			panel.add(getLblNombre());
			panel.add(getLblAnio());
			panel.add(getLblNacionalidad());
			panel.add(getTxtNombre());
			panel.add(getTxtAnio());
			panel.add(getTxtNacionalidad());
			panel.add(getBtnAnadir());
			panel.add(getBtnVolver());
		
		}
		return panel;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(33, 37, 119, 14);
		}
		return lblNombre;
	}
	
	private JLabel getLblAnio() {
		if (lblAnio == null) {
			lblAnio = new JLabel("Año de creación:");
			lblAnio.setBounds(33, 70, 119, 14);
		}
		return lblAnio; 
	}
	
	private JLabel getLblNacionalidad() {
		if (lblNacionalidad == null) {
			lblNacionalidad = new JLabel("Id de nacionalidad a la que pertenece");
			lblNacionalidad.setBounds(33, 70, 119, 14);
		}
		return lblNacionalidad;
	}
	
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(162, 34, 96, 20);
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}
	
	private JTextField getTxtAnio() {
		if (txtAnio == null) {
			txtAnio = new JTextField();
			txtAnio.setColumns(30);
			txtAnio.setBounds(162, 67, 96, 20);
		}
		return txtAnio;
	}
	
	private JTextField getTxtNacionalidad() {
		if (txtNacionalidad == null) {
			txtNacionalidad = new JTextField();
			txtNacionalidad.setColumns(30);
			txtNacionalidad.setBounds(162, 67, 96, 20);
		}
		return txtNacionalidad;
	}
	
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBackground(new Color(134, 231, 120));
			btnAnadir.setBounds(169, 117, 89, 23);
			btnAnadir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosValidos = validar();
                    if(datosValidos) {
                    	TProductora productora = null;
                    	String nombre = txtNombre.getText().trim();
                    	int anio = Integer.parseInt(txtAnio.getText().trim());    
                 
                    	int idNacionalidad = Integer.parseInt(txtNacionalidad.getText().trim()); 
                       	productora = new TProductora(0,nombre,anio, true, idNacionalidad);
                    	Controlador.getInstance().accion(Evento.CREAR_PRODUCTORA, productora);
          
                    }
                }
			});
		}
		return btnAnadir;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(new Color(255, 255, 255));
			btnVolver.setBackground(new Color(255, 0, 0));
			btnVolver.setBounds(33, 117, 89, 23);
			btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
		}
		return btnVolver;
	}
	
	
	private boolean validar() {
		
		String nombre = txtNombre.getText().trim();
		if(!nombre.matches("^[A-Za-z][A-Za-z\\s]{2,}$")) {
			JOptionPane.showMessageDialog(null, "El nombre debe tener al menos 3 caracteres y "
					+ "no empezar por un número o símbolo.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		String textoAnio = txtAnio.getText().trim();
		
		
		if (textoAnio.isEmpty()) {
			JOptionPane.showMessageDialog(null, "El año no puede estar vacío.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		try {
			int anio = Integer.parseInt(textoAnio);
			
			
			if (anio < 1895 || anio > 2024) { 
				JOptionPane.showMessageDialog(null, "El año debe ser un número válido (ej. entre 1895 y 2024).",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		} catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "El año debe ser un número entero, sin letras ni símbolos.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String textoNacionalidad = txtNacionalidad.getText().trim();
	    if (textoNacionalidad.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El ID de nacionalidad no puede estar vacío.",
	                "ERROR", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	    try {
	        int idNacionalidad = Integer.parseInt(textoNacionalidad);
	       
	        if (idNacionalidad <= 0) { 
	            JOptionPane.showMessageDialog(null, "El ID de nacionalidad debe ser un número mayor que 0.",
	                    "ERROR", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "El ID de nacionalidad debe ser un número entero, sin letras ni símbolos.",
	                "ERROR", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
		
		return true;
	}

	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Evento.RES_CREAR_PRODUCTORA_OK):{
    		JOptionPane.showMessageDialog(null, "La productora se ha dado de alta.",
					"ÉXITO", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}case(Evento.RES_CREAR_PRODUCTORA_KO):{
    		JOptionPane.showMessageDialog(null, (String) datos,
					"ERROR", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}
		}
		
	}
}
