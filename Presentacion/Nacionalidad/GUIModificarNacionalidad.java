
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


public class GUIModificarNacionalidad extends JFrame implements IGUI {
	
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
	private JButton btnModificar;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIModificarNacionalidad() {
		setTitle("Modificar nacionalidad");
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
			lblTitulo = new JLabel("Modificar nacionalidad");
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
			panel.setBounds(150, 73, 400, 350);
			panel.setLayout(null);
			
			panel.add(getLblId());
			panel.add(getTxtId());
						
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			
			panel.add(getLblContinente());
			panel.add(getTxtContinente());
			
			panel.add(getBtnModificar());
			
			panel.add(getBtnVolver());
		}
		return panel;
	}
	
	private JLabel getLblId() {
		if(lblId == null) {
			lblId = new JLabel("ID nacionalidad: ");
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
			txtContinente.setBounds(160, 117, 210, 25);
		}
		return txtContinente;
	}

	private JButton getBtnModificar() {
		if(btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setBackground(btnVerde);
			btnModificar.setForeground(textoNegro);
			btnModificar.setBounds(220, 210, 120, 35);
			btnModificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(validar()) {
						TNacionalidad tNacionalidad = new TNacionalidad(
							Integer.parseInt(txtId.getText().trim()),
							txtNombre.getText().trim(),
							txtContinente.getText().trim(),
							true
						);
						Controlador.getInstance().accion(Evento.MODIFICAR_NACIONALIDAD, tNacionalidad);
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
			btnVolver.setBounds(60, 210, 120, 35);
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
			if (txtId.getText().trim().isEmpty() || Integer.parseInt(txtId.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(this,  "Resvise que todos los campos están completos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (txtNombre.getText().trim().isEmpty()) {
				throw new Exception();
			}
			if (txtContinente.getText().trim().isEmpty()) {
				throw new Exception();
			}
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this,  "Datos inválidos. Revisa los campos numéricos.");
			return false;
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.RES_MODIFICAR_NACIONALIDAD_OK:
			JOptionPane.showMessageDialog(this, "Nacionalidad actualizada correctamente.");
			dispose();
			break;
		case Evento.RES_MODIFICAR_NACIONALIDAD_KO:
			JOptionPane.showMessageDialog(this, "Error al modificar: " + datos);
			break;
		}
	}
}