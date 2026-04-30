/**
 *
 */
package Presentacion.Genero;

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
import Negocio.Genero.TGenero;
import Presentacion.Controlador.Controlador;


public class GUICrearGenero extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JButton btnAnadir;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	
	public GUICrearGenero() {
		setTitle("Añadir género");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 550, 400);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getPanel());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Añadir género");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(0, 20, 534, 40);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(75, 80, 380, 220);
			panel.setLayout(null);
			
			panel.add(getLblNombre());
			panel.add(getLblDescripcion());
			panel.add(getTxtNombre());
			panel.add(getTxtDescripcion());
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
	
	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripción:");
			lblDescripcion.setBounds(30, 70, 100, 20);
		}
		return lblDescripcion; 
	}
	
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(160, 27, 180, 25);
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}
	
	private JTextField getTxtDescripcion() {
		if (txtDescripcion == null) {
			txtDescripcion = new JTextField();
			txtDescripcion.setBounds(160, 67, 180, 25);
			txtDescripcion.setColumns(30);
		}
		return txtDescripcion;
	}
	
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBackground(btnVerde);
			btnAnadir.setBounds(210, 160, 110, 35);
			btnAnadir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosValidos = validar();
                    if(datosValidos) {
                    	String nombre = txtNombre.getText().trim();
                    	String descripcion = txtDescripcion.getText().trim();
                 
                    	TGenero tGenero = new TGenero(0, nombre, descripcion, true);
                    	Controlador.getInstance().accion(Evento.CREAR_GENERO, tGenero);
          
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
			btnVolver.setBounds(60, 160, 110, 35);
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
			JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (txtDescripcion.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.RES_CREAR_GENERO_OK:
			JOptionPane.showMessageDialog(this, "Género creado correctamente.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
			dispose(); 
			break;
		case Evento.RES_CREAR_GENERO_KO:
			JOptionPane.showMessageDialog(this,  "Error al crear el género: " + datos, "ERROR", JOptionPane.ERROR_MESSAGE);
			//dispose();
			break;
		}
	}
}