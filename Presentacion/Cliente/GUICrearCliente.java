/**
 *
 */
package Presentacion.Cliente;

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

import Negocio.Cliente.TCliente;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUICrearCliente extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblCorreo;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCorreo;

	private JButton btnAnadir;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	public GUICrearCliente() {
		setTitle("Añadir cliente");
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
			lblTitulo = new JLabel("Añadir cliente");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(0, 10, 700, 50);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(160, 70, 380, 280);
			panel.setLayout(null);
			
			panel.add(getLblNombre());
			panel.add(getLblApellidos());
			panel.add(getLblCorreo());
			panel.add(getTxtNombre());
			panel.add(getTxtApellidos());
			panel.add(getTxtCorreo());
			
			panel.add(getBtnAnadir());
			panel.add(getBtnVolver());
	
		}
		return panel;
	}
	
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30, 35, 100, 20);
		}
		return lblNombre;
	}
	
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(30, 85, 100, 20);
		}
		return lblApellidos; 
	}
	
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo cliente:");
			lblCorreo.setBounds(30, 125, 120, 20);
		}
		return lblCorreo; 
	}
	
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(160, 32, 180, 25);
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}
	
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setBounds(160, 82, 180, 25);
			txtApellidos.setColumns(30);
		}
		return txtApellidos;
	}
	
	private JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setBounds(160, 132, 180, 25);
			txtCorreo.setColumns(30);
		}
		return txtCorreo;
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
                    	String apellidos = txtApellidos.getText().trim();
                    	String correo = txtCorreo.getText().trim();
                    	
                		TCliente tCliente = new TCliente(0, nombre, apellidos, correo, true, false);
                    	Controlador.getInstance().accion(Evento.CREAR_CLIENTE, tCliente);
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
		String nombre = txtNombre.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		String correo = txtCorreo.getText().trim();
		
		if(nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty()) {
			JOptionPane.showMessageDialog(this,  "Rellene todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!correo.contains("@") || !correo.contains(".")) {
			JOptionPane.showMessageDialog(this, "Asegurese de que tiene el formato correcto (con @ y \".\")", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_CREAR_CLIENTE_OK) {
			int id = (int) datos;
			javax.swing.JOptionPane.showMessageDialog(this, "Cliente creado correctamente, con ID:" + id);
			this.dispose();
			
		} else if (evento == Evento.RES_CREAR_CLIENTE_KO) {
			String mensajeError = (String) datos;
			javax.swing.JOptionPane.showMessageDialog(this, mensajeError, "Error al intentar crear el cliente.", JOptionPane.ERROR_MESSAGE);
		}
	}
}