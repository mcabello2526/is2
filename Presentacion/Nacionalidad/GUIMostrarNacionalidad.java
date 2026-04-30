
package Presentacion.Nacionalidad;

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

import Negocio.Nacionalidad.TNacionalidad;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUIMostrarNacionalidad extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId; 
	private JLabel lblNombre;
	private JLabel lblContinente;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtContinente;
	private JButton btnBuscar;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIMostrarNacionalidad() {
		setTitle("Mostrar nacionalidad");
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
		if(lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar nacionalidad");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(0, 20, 700, 40);		
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
			
			panel.add(getLblContinente());
			panel.add(getTxtContinente());
			
			panel.add(getBtnVolver());
			}
		return panel;
	}
	
	private JLabel getLblId() {
		if(lblId == null) {
			lblId = new JLabel("ID de nacionalidad a modificar:");
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
	
	private JLabel getLblContinente() {
		if(lblContinente == null) {
			lblContinente = new JLabel("Continente:");
			lblContinente.setBounds(30, 120, 100, 20);
		}
		return lblContinente;
	}
	
	private JTextField getTxtContinente() {
		if(txtContinente == null) {
			txtContinente = new JTextField();
			txtContinente.setBounds(140, 117, 210, 25);
			txtContinente.setEditable(false);
		}
		return txtContinente;
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
						Controlador.getInstance().accion(Evento.MOSTRAR_NACIONALIDAD, id);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
			btnVolver.setBounds(130, 220, 120, 35);
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
		case Evento.RES_MOSTRAR_NACIONALIDAD_OK:
			TNacionalidad t = (TNacionalidad) datos;
			txtNombre.setText(t.getNombre());
			txtContinente.setText(String.valueOf(t.getContinente()));
			if(!t.getActivo()) {
				txtNombre.setText(t.getNombre() + "(inactivo)");
			}
			break;
		case Evento.RES_MOSTRAR_NACIONALIDAD_KO:
			txtNombre.setText("");
			txtContinente.setText("");
			JOptionPane.showMessageDialog(this,  "No se encontro ninguna nacionalidad con ese ID.", "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}