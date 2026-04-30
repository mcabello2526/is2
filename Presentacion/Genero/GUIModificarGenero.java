/**
 *
 */
package Presentacion.Genero;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Presentacion.IGUI;


import javax.swing.JButton;

import Presentacion.Evento;
import Negocio.Genero.TGenero;
import Presentacion.Controlador.Controlador;

public class GUIModificarGenero extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId; 
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JButton btnModificar;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIModificarGenero() {
		setTitle("Modificar género");
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
			lblTitulo = new JLabel("Modificar género");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(122, 11, 429, 42);		
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if(panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(169, 73, 400, 350);
			panel.setLayout(null);
			
			panel.add(getLblId());
			panel.add(getTxtId());
						
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			
			panel.add(getLblDescripcion());
			panel.add(getTxtDescripcion());
			
			panel.add(getBtnModificar());
			
			panel.add(getBtnVolver());
		}
		return panel;
	}
	
	private JLabel getLblId() {
		if(lblId == null) {
			lblId = new JLabel("ID género: ");
			lblId.setBounds(30, 30, 100, 20);
		}
		return lblId;
	}
	
	private JTextField getTxtId() {
		if(txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(160, 27, 100, 25);
		}
		return txtId;
	}
	
	private JLabel getLblNombre() {
		if(lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30,80,100,20);
		}
		return lblNombre;
	}
	
	private JTextField getTxtNombre() {
		if(txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(160, 77, 210, 25);
		}
		return txtNombre;
	}
	
	private JLabel getLblDescripcion() {
		if(lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripción:");
			lblDescripcion.setBounds(30, 120, 100, 20);
		}
		return lblDescripcion;
	}

	private JTextField getTxtDescripcion() {
		if(txtDescripcion == null) {
			txtDescripcion = new JTextField();
			txtDescripcion.setBounds(160, 117, 210, 25);
		}
		return txtDescripcion;
	}

	private JButton getBtnModificar() {
		if(btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setBackground(btnVerde);
			btnModificar.setForeground(textoNegro);
			btnModificar.setBounds(225, 260, 120, 30);
			btnModificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(validar()) {
						TGenero tGenero = new TGenero(
							Integer.parseInt(txtId.getText().trim()),
							txtNombre.getText().trim(),
							txtDescripcion.getText().trim(),
							true
						);
						Controlador.getInstance().accion(Evento.MODIFICAR_GENERO, tGenero);
					}
				}
            });
		}
		return btnModificar;
	}
	
	private JButton getBtnVolver() {
		if(btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(75, 260, 100, 30);
			btnVolver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
		}
		return btnVolver;
	}
	
	public boolean validar() {
		try {
			if (txtId.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede estar el ID vacío.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (Integer.parseInt(txtId.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(this, "El ID tiene que ser un entero positivo.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (txtNombre.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede estar el nombre vacío.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (txtDescripcion.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede estar la descripción vacía.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.RES_MODIFICAR_GENERO_OK:
			JOptionPane.showMessageDialog(this, "Género actualizada correctamente.");
			dispose();
			break;
		case Evento.RES_MODIFICAR_GENERO_KO:
			JOptionPane.showMessageDialog(this, "Error al modificar: " + datos);
			break;
		}
	}
}