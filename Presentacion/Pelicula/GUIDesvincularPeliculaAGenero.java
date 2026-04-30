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

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIDesvincularPeliculaAGenero extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panelForm;
	private JLabel lblIdPelicula;
	private JLabel lblIdGenero;
	private JTextField txtIdPelicula;
	private JTextField txtIdGenero;
	private JButton btnDesvincular;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIDesvincularPeliculaAGenero() {
		setTitle("Desvincular película a género");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		contentPane.add(getLblTitulo());
		contentPane.add(getPanelForm());
		
		panelForm.add(getLblIdPelicula());
		panelForm.add(getTxtIdPelicula());
		
		panelForm.add(getLblIdGenero());
		panelForm.add(getTxtIdGenero());
		
		panelForm.add(getBtnDesvincular());
		panelForm.add(getBtnVolver());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Desvincular película a género");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(10, 20, 610, 50);
		}
		return lblTitulo;
	}
	
	private JPanel getPanelForm() {
		if(panelForm == null) {
			panelForm = new JPanel();
			panelForm.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelForm.setBackground(fondoGris);
			panelForm.setBounds(135, 80, 380, 320);
			panelForm.setLayout(null);
		}
		return panelForm;
	}
	
	private JLabel getLblIdPelicula() {
		if (lblIdPelicula == null) {
			lblIdPelicula = new JLabel("ID de la película:");
			lblIdPelicula.setBounds(30, 50, 120, 20);
		} 
		return lblIdPelicula;
	}
	
	private JTextField getTxtIdPelicula() {
		if(txtIdPelicula == null) {
			txtIdPelicula = new JTextField();
			txtIdPelicula.setBounds(160, 48, 150, 25);
		}
		return txtIdPelicula;
	}
	
	private JLabel getLblIdGenero() {
		if (lblIdGenero == null) {
			lblIdGenero = new JLabel("ID del género:");
			lblIdGenero.setBounds(30, 100, 120, 20);
		} 
		return lblIdGenero;
	}
	
	private JTextField getTxtIdGenero() {
		if(txtIdGenero == null) {
			txtIdGenero = new JTextField();
			txtIdGenero.setBounds(160, 98, 150, 25);
		}
		return txtIdGenero;
	}
	
	private JButton getBtnDesvincular() {
		if(btnDesvincular == null) {
			btnDesvincular = new JButton("Desvincular");
			btnDesvincular.setForeground(textoNegro);
			btnDesvincular.setBackground(btnVerde);
			btnDesvincular.setBounds(210, 230, 110, 35);
			btnDesvincular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validar()) {
						int[] datosVincular = {
								Integer.parseInt(txtIdPelicula.getText().trim()),
								Integer.parseInt(txtIdGenero.getText().trim())
						};
						Controlador.getInstance().accion(Evento.DESVINCULAR_PELICULA_A_GENERO, datosVincular);
					}
				}
			});
		}
		return btnDesvincular;
	}
	
	private JButton getBtnVolver() {
		if(btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(60, 230, 110, 35);
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
		try {
			if(Integer.parseInt(txtIdPelicula.getText().trim()) < 0 ||
				Integer.parseInt(txtIdGenero.getText().trim())<0) {
				JOptionPane.showMessageDialog(this, "Los IDs deben ser número enteros.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;			}
			return true;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Los IDs deben ser número enteros.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case Evento.RES_DESVINCULAR_PELICULA_A_GENERO_OK:
			JOptionPane.showMessageDialog(this,  "Desvinculación realizada correctamente.");
			dispose();
			break;
		case Evento.RES_DESVINCULAR_PELICULA_A_GENERO_KO:
			JOptionPane.showMessageDialog(this, "Error al desvincular, asegurese de que ambos IDs existen", "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}