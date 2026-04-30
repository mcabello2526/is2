/**
 *
 */
package Presentacion.Pelicula;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Negocio.Pelicula.TPelicula;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUICrearPelicula extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblStock;
	private JLabel lblPrecio;
	private JLabel lblIdProductora;
	private JTextField txtNombre;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtIdProductora;
	private JButton btnAnadir;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	
	public GUICrearPelicula() {
		setTitle("Añadir película");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 700, 450);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getPanel());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Añadir película");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(148, 10, 357, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(169, 73, 380, 280);
			panel.setLayout(null);
			
			panel.add(getLblNombre());
			panel.add(getLblStock());
			panel.add(getLblPrecio());
			panel.add(getLblIdProductora());
			panel.add(getTxtNombre());
			panel.add(getTxtStock());
			panel.add(getTxtPrecio());
			panel.add(getTxtIdProductora());
			panel.add(getBtnAnadir());
			panel.add(getBtnVolver());
	
		}
		return panel;
	}
	
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30, 30, 100, 20);
		}
		return lblNombre;
	}
	
	private JLabel getLblStock() {
		if (lblStock == null) {
			lblStock = new JLabel("Stock de película:");
			lblStock.setBounds(30, 70, 100, 20);
		}
		return lblStock; 
	}
	
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio película:");
			lblPrecio.setBounds(30, 110, 100, 20);
		}
		return lblPrecio; 
	}
	
	private JLabel getLblIdProductora() {
		if (lblIdProductora == null) {
			lblIdProductora = new JLabel("ID de su productora:");
			lblIdProductora.setBounds(30, 150, 130, 20);
		}
		return lblIdProductora; 
	}	
	
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(160, 27, 180, 25);
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}
	
	private JTextField getTxtStock() {
		if (txtStock == null) {
			txtStock = new JTextField();
			txtStock.setBounds(160, 67, 180, 25);
			txtStock.setColumns(30);
		}
		return txtStock;
	}
	
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setBounds(160, 107, 180, 25);
			txtPrecio.setColumns(30);
		}
		return txtPrecio;
	}
	
	private JTextField getTxtIdProductora() {
		if (txtIdProductora == null) {
			txtIdProductora = new JTextField();
			txtIdProductora.setBounds(160, 147, 180, 25);
			txtIdProductora.setColumns(30);
		}
		return txtIdProductora;
	}
		
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBackground(btnVerde);
			btnAnadir.setBounds(215, 210, 100, 30);
			btnAnadir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosValidos = validar();
                    if(datosValidos) {
                    	String nombre = txtNombre.getText().trim();
                    	float precio = Float.parseFloat(txtPrecio.getText().trim());
                    	int stock = Integer.parseInt(txtStock.getText().trim());
                    	int idProductora = Integer.parseInt(txtIdProductora.getText().trim());
                 
                    	TPelicula tPelicula = new TPelicula(0, nombre, stock, precio, idProductora, true);
                    	Controlador.getInstance().accion(Evento.CREAR_PELICULA, tPelicula);
          
                    }
                }
			});
		}
		return btnAnadir;
	}
	
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(65, 210, 100, 30);
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
		if(txtNombre.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,  "El nombre no puede estar vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			float precio = Float.parseFloat(txtPrecio.getText().trim());
			if(precio < 0) {
				JOptionPane.showMessageDialog(null, "El precio debe ser positivo", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El precio debe ser un número", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			int stock = Integer.parseInt(txtStock.getText().trim());
			if(stock < 0) {
				JOptionPane.showMessageDialog(null, "El stock debe ser un numero entero positivo", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null,  "El stock debe de ser positivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			if(Integer.parseInt(txtIdProductora.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "El ID de productora debe de ser mayor que 0", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El ID de productora debe ser un numero entero positivo sin letras ni símbolos.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.RES_CREAR_PELICULA_OK:
			JOptionPane.showMessageDialog(this, "Pelicula creada correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
			dispose(); 
			break;
		case Evento.RES_CREAR_PELICULA_KO:
			JOptionPane.showMessageDialog(this,  "Error al crear la película " + datos, "ERROR", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		}
	}
}