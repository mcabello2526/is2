
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

public class GUICrearNacionalidad extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblContinente;
	private JTextField txtNombre;
	private JTextField txtContinente;
	
	private JButton btnAnadir;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	
	public GUICrearNacionalidad() {
		setTitle("Añadir nacionalidad");
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
			lblTitulo = new JLabel("Añadir nacionalidad");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(0, 20, 684, 40);
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
			panel.add(getLblContinente());
			panel.add(getTxtNombre());
			panel.add(getTxtContinente());
			
			panel.add(getBtnAnadir());
			panel.add(getBtnVolver());
		}
		return panel;
	}
	
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30, 50, 100, 20);
		}
		return lblNombre;
	}
	
	private JLabel getLblContinente() {
		if (lblContinente == null) {
			lblContinente = new JLabel("Continente:");
			lblContinente.setBounds(30, 100, 100, 20);
		}
		return lblContinente; 
	}
	
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(160, 47, 180, 25);
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}
	
	private JTextField getTxtContinente() {
		if (txtContinente == null) {
			txtContinente = new JTextField();
			txtContinente.setBounds(160, 97, 100, 25);
			txtContinente.setColumns(30);
		}
		return txtContinente;
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
                    	String continente = txtContinente.getText().trim();
                 
                    	TNacionalidad tNacionalidad = new TNacionalidad(0, nombre, continente, true);
                    	Controlador.getInstance().accion(Evento.CREAR_NACIONALIDAD, tNacionalidad);
          
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
			btnVolver.setBounds(85, 210, 100, 30);
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
		String continente = txtContinente.getText().trim();
		
		if(nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(continente.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El continente no puede estar vacío.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.RES_CREAR_NACIONALIDAD_OK:
			JOptionPane.showMessageDialog(this, "Nacionalidad creada correctamente.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
			dispose(); 
			break;
		case Evento.RES_CREAR_NACIONALIDAD_KO:
			JOptionPane.showMessageDialog(this,  "Error al crear la nacionalidad: " + datos, "ERROR", JOptionPane.ERROR_MESSAGE);
			//dispose();
			break;
		}
	}
}