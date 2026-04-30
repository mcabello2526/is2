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

public class GUIMostrarPeliculaPorNombre extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblStock;
	private JLabel lblPrecio;
	private JLabel lblIdProductora;
	private JLabel lblBusqueda;
	private JTextField txtNombre;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtIdProductora;
	private JTextField txtBusqueda;
	private JButton btnBuscar;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIMostrarPeliculaPorNombre() {
		setTitle("Mostrar película por nombre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 750, 500);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());
		setContentPane(contentPane);
		contentPane.add(getPanel());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar por nombre");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(0, 20, 750, 50);		
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if(panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(100, 75, 550, 300);
			panel.setLayout(null);
			
			panel.add(getLblBusqueda());
			panel.add(getTxtBusqueda());
			
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
	
	private JLabel getLblBusqueda() {
		if(lblBusqueda == null) {
			lblBusqueda = new JLabel("Nombre a buscar:");
			lblBusqueda.setBounds(30, 35, 140, 20);
		}
		return lblBusqueda;
	}
	
	private JTextField getTxtBusqueda() {
		if(txtBusqueda == null) {
			txtBusqueda = new JTextField();
			txtBusqueda.setBounds(175, 32, 220, 25);
		}
		return txtBusqueda;
	}
	
	private JButton getBtnBuscar() {
		if(btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.setForeground(textoNegro);
			btnBuscar.setBackground(btnVerde);
			btnBuscar.setBounds(410, 32, 100, 25);
			btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = txtBusqueda.getText().trim();
					if(!nombre.isEmpty()) {
						Controlador.getInstance().accion(Evento.MOSTRAR_PELICULA_POR_NOMBRE, nombre);
					}else {
						JOptionPane.showMessageDialog(null,  "Escribe el nombre a buscar");
					}
				}
			});
		}
		return btnBuscar;
	}
	
	private JLabel getLblNombre() {
		if(lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30, 100, 100, 20);
		}
		return lblNombre;
	}
	
	private JTextField getTxtNombre() {
		if(txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(140, 97, 250, 25);
			txtNombre.setEditable(false);
		}
		return txtNombre;
	}
	
	private JLabel getLblStock() {
		if(lblStock == null) {
			lblStock = new JLabel("Stock:");
			lblStock.setBounds(30, 140, 100, 20);
		}
		return lblStock;
	}
	
	private JTextField getTxtStock() {
		if(txtStock == null) {
			txtStock = new JTextField();
			txtStock.setBounds(140, 137, 100, 25);
			txtStock.setEditable(false);
		}
		return txtStock;
	}
	
	private JLabel getLblPrecio() {
		if(lblPrecio == null) {
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setBounds(30, 180, 100, 20);
		}
		return lblPrecio;
	}
	
	private JTextField getTxtPrecio() {
		if(txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setBounds(140, 177, 100, 25);
			txtPrecio.setEditable(false);
		}
		return txtPrecio;
	}
	
	private JLabel getLblIdProductora() {
		if(lblIdProductora == null) {
			lblIdProductora = new JLabel("ID Productora:");
			lblIdProductora.setBounds(30, 220, 120, 20);
		}
		return lblIdProductora;
	}
	
	private JTextField getTxtIdProductora() {
		if(txtIdProductora == null) {
			txtIdProductora = new JTextField();
			txtIdProductora.setBounds(140, 217, 100, 25);
			txtIdProductora.setEditable(false);
		}
		return txtIdProductora;
	}
	
	private JButton getBtnVolver() {
		if(btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(225, 260, 100, 30);
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
		case Evento.RES_MOSTRAR_PELICULA_POR_NOMBRE_OK:
			TPelicula t = (TPelicula) datos;
			txtNombre.setText(t.getNombre());
			txtStock.setText(String.valueOf(t.getStock()));
			txtPrecio.setText(String.valueOf(t.getPrecio()));
			txtIdProductora.setText(String.valueOf(t.getIdProductora()));
			break;
		case Evento.RES_MOSTRAR_PELICULA_POR_NOMBRE_KO:
			JOptionPane.showMessageDialog(this,  "No se encontro la película con ese nombre ", "ERROR", JOptionPane.ERROR_MESSAGE);
			txtNombre.setText("");
			txtStock.setText("");
			txtPrecio.setText("");
			txtIdProductora.setText("");
			break;
		}
	}
}