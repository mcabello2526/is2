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


public class GUIMostrarPelicula extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId; 
	private JLabel lblNombre;
	private JLabel lblStock;
	private JLabel lblPrecio;
	private JLabel lblIdProductora;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtIdProductora;
	private JButton btnBuscar;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIMostrarPelicula() {
		setTitle("Mostrar película");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 700, 500);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());
		setContentPane(contentPane);
		contentPane.add(getPanel());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar película");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(0, 11, 700, 42);		
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if(panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(150, 70, 400, 320);
			panel.setLayout(null);
			
			panel.add(getLblId());
			panel.add(getTxtId());
			
			panel.add(getBtnBuscar());
						
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			
			panel.add(getLblStock());
			panel.add(getTxtStock());
			
			panel.add(getLblPrecio());
			panel.add(getTxtPrecio());
			
			panel.add(getLblIdProductora());
			panel.add(getTxtIdProductora());
			
			panel.add(getBtnVolver());
			}
		return panel;
	}
	
	private JLabel getLblId() {
		if(lblId == null) {
			lblId = new JLabel("ID de pelicula a mostrar:");
			lblId.setBounds(30, 30, 180, 20);
		}
		return lblId;
	}
	
	private JTextField getTxtId() {
		if(txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(210, 27, 60, 25);
		}
		return txtId;
	}
	
	private JLabel getLblNombre() {
		if(lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30, 80, 100, 20);
		}
		return lblNombre;
	}
	
	private JTextField getTxtNombre() {
		if(txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(140, 77, 210, 25);
			txtNombre.setEditable(false);
		}
		return txtNombre;
	}
	
	private JLabel getLblStock() {
		if(lblStock == null) {
			lblStock = new JLabel("Stock:");
			lblStock.setBounds(30, 120, 100, 20);
		}
		return lblStock;
	}
	
	private JTextField getTxtStock() {
		if(txtStock == null) {
			txtStock = new JTextField();
			txtStock.setBounds(140, 117, 210, 25);
			txtStock.setEditable(false);
		}
		return txtStock;
	}
	
	private JLabel getLblPrecio() {
		if(lblPrecio == null) {
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setBounds(30, 160, 100, 20);
		}
		return lblPrecio;
	}
	
	private JTextField getTxtPrecio() {
		if(txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setBounds(140, 157, 210, 25);
			txtPrecio.setEditable(false);
		}
		return txtPrecio;
	}
	
	private JLabel getLblIdProductora() {
		if(lblIdProductora == null) {
			lblIdProductora = new JLabel("ID Productora:");
			lblIdProductora.setBounds(30, 200, 120, 20);
		}
		return lblIdProductora;
	}
	
	private JTextField getTxtIdProductora() {
		if(txtIdProductora == null) {
			txtIdProductora = new JTextField();
			txtIdProductora.setBounds(140, 197, 210, 25);
			txtIdProductora.setEditable(false);
		}
		return txtIdProductora;
	}
	
	private JButton getBtnBuscar() {
		if(btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.setForeground(textoNegro);
			btnBuscar.setBackground(btnVerde);
			btnBuscar.setBounds(280, 27, 90, 25);
			btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int id = Integer.parseInt(txtId.getText().trim());
						Controlador.getInstance().accion(Evento.MOSTRAR_PELICULA, id);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Introduce un ID numérico válido.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});	
		}
		return btnBuscar;
	}
	
	private JButton getBtnVolver() {
		if(btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(150, 260, 100, 30);
			btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    }
			});	
		}
		return btnVolver;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.RES_MOSTRAR_PELICULA_OK:
			TPelicula t = (TPelicula) datos;
			txtNombre.setText(t.getNombre());
			txtStock.setText(String.valueOf(t.getStock()));
			txtPrecio.setText(String.valueOf(t.getPrecio()));
			txtIdProductora.setText(String.valueOf(t.getIdProductora()));
			break;
		case Evento.RES_MOSTRAR_PELICULA_KO:
			JOptionPane.showMessageDialog(this,  "No se encontro la película con ese ID", "ERROR", JOptionPane.ERROR_MESSAGE);
			txtNombre.setText("");
			txtStock.setText("");
			txtPrecio.setText("");
			txtIdProductora.setText("");
			break;
		}
	}
}