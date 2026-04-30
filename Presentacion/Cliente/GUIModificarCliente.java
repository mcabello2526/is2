/**
 *
 */
package Presentacion.Cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TNormal;
import Negocio.Cliente.TVip;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUIModificarCliente extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId; 
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblCorreo;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCorreo;
	private JButton btnModificar;
	private JButton btnVolver;
	private JRadioButton rdbtnVip;
	private JRadioButton rdbtnNoVip;
	private ButtonGroup grupoVip;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	public GUIModificarCliente() {
		setTitle("Modificar cliente");
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
			lblTitulo = new JLabel("Modificar cliente");
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
			panel.setBounds(150, 70, 400, 360);
			panel.setLayout(null);
			
			panel.add(getLblId());
			panel.add(getTxtId());
						
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			
			panel.add(getLblApellidos());
			panel.add(getTxtApellidos());
			
			panel.add(getLblCorreo());
			panel.add(getTxtCorreo());
			
			panel.add(getRdbtnVip());
			panel.add(getRdbtnNoVip());
			
			grupoVip = new ButtonGroup();
			grupoVip.add(rdbtnVip);
			grupoVip.add(rdbtnNoVip);
			rdbtnNoVip.setSelected(true);
			
			panel.add(getBtnModificar());
			
			panel.add(getBtnVolver());
		}
		return panel;
	}
	
	private JLabel getLblId() {
		if(lblId == null) {
			lblId = new JLabel("ID del cliente: ");
			lblId.setBounds(30, 30, 110, 20);
		}
		return lblId;
	}
	
	private JTextField getTxtId() {
		if(txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(150, 27, 80, 25);
		}
		return txtId;
	}
	
	private JLabel getLblNombre() {
		if(lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30,70,100,20);
		}
		return lblNombre;
	}
	
	private JTextField getTxtNombre() {
		if(txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(150, 67, 210, 25);
		}
		return txtNombre;
	}
	
	private JLabel getLblApellidos() {
		if(lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(30, 110, 100, 20);
		}
		return lblApellidos;
	}

	private JTextField getTxtApellidos() {
		if(txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setBounds(150, 107, 210, 25);
		}
		return txtApellidos;
	}
	
	private JLabel getLblCorreo() {
		if(lblCorreo == null){
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(30, 150, 100, 20);
		}
		return lblCorreo;
	}
	
	private JTextField getTxtCorreo() {
		if(txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setBounds(150, 147, 210, 25);
		}
		return txtCorreo;
	}
	
	private JRadioButton getRdbtnVip() {
		if(rdbtnVip == null) {
			rdbtnVip = new JRadioButton("VIP");
			rdbtnVip.setBounds(150, 190, 80, 20);
			rdbtnVip.setBackground(fondoGris);
		}
		return rdbtnVip;
	}
	
	private JRadioButton getRdbtnNoVip() {
		if(rdbtnNoVip == null) {
			rdbtnNoVip = new JRadioButton("Normal");
			rdbtnNoVip.setBounds(240, 190, 80, 20);
			rdbtnNoVip.setBackground(fondoGris);
		}
		return rdbtnNoVip;
	}
	
	////TODO: aqui va lo de descuento?? y num de compras
	
	private JButton getBtnModificar() {
		if(btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setBackground(btnVerde);
			btnModificar.setForeground(textoNegro);
			btnModificar.setBounds(225, 280, 120, 35);
			btnModificar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(validar()) {
						TCliente tCliente = new TCliente(
							Integer.parseInt(txtId.getText().trim()),
							txtNombre.getText().trim(),
							txtApellidos.getText().trim(), 
							txtCorreo.getText().trim(),
							true,
							rdbtnVip.isSelected()
						);
						Controlador.getInstance().accion(Evento.MODIFICAR_CLIENTE, tCliente);
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
			btnVolver.setBounds(55, 280, 100, 35);
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
			if(txtId.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() ||
				txtApellidos.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this,  "Rellene todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);;
				return false;
			}
			int id = Integer.parseInt(txtId.getText().trim());
			if(id<=0) {
				throw new NumberFormatException();
			}
			if(!txtCorreo.getText().trim().contains("@") ||!txtCorreo.getText().trim().contains(".")) {
				JOptionPane.showMessageDialog(this, "Asegurese de que tiene el formato correcto (con @ y \".\")", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El ID debe ser un numero entero positivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case Evento.RES_MODIFICAR_CLIENTE_OK:
			JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.", "EXITO", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case Evento.RES_MODIFICAR_CLIENTE_KO:
			String mensajeError = (datos != null) ? datos.toString() : "Error desconocido al modificar.";
			JOptionPane.showMessageDialog(this, mensajeError, "Error al modificar", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}