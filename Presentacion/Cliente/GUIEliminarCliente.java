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

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUIEliminarCliente extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnEliminar;
	private JButton btnVolver;

	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIEliminarCliente() {
		setTitle("Eliminar cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 700, 340);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());
		
		setContentPane(contentPane);
		contentPane.add(getPanel());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel ("Eliminar cliente");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(0, 11, 700, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if(panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(169, 73, 350, 160);
			panel.setLayout(null);
			
			panel.add(getLblId());
			panel.add(getTxtId());
			
			panel.add(getBtnEliminar());
			panel.add(getBtnVolver());
		}
		return panel;
	}
	
	private JLabel getLblId() {
		if(lblId == null) {
			lblId = new JLabel ("ID del cliente: ");
			lblId.setBounds(30, 35, 110, 20);
		}
		return lblId;
	}
	
	private JTextField getTxtId() {
		if(txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(150, 32, 160, 25);
		}
		return txtId;
	}
	
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setForeground(textoNegro);
			btnEliminar.setBackground(btnVerde);
			btnEliminar.setBounds(190, 90, 100, 30);
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (validar()) {
						int id = Integer.parseInt(txtId.getText().trim());
						Controlador.getInstance().accion(Evento.ELIMINAR_CLIENTE, id);
					}
				}
			});
		}
		return btnEliminar;
	}
	
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(50, 90, 100, 30);
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
			String texto = txtId.getText().trim();
			if(texto.isEmpty()) {
				JOptionPane.showMessageDialog(this,  "El ID no puede estar vacío.", "ERROR", JOptionPane.ERROR);
				return false;
			}
			int id = Integer.parseInt(texto);
			if(id<= 0) {
				JOptionPane.showMessageDialog(this,  "El ID debe ser un número entero positivo.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El ID debe ser un número entero (sin letras ni símbolos).", "ERROR", JOptionPane.ERROR_MESSAGE); 
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.RES_ELIMINAR_CLIENTE_OK:
			int id = (int) datos;
			JOptionPane.showMessageDialog(this,  "Cliente con ID " + id + " eliminado correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			break;
		case Evento.RES_ELIMINAR_CLIENTE_KO:
			String mensaje = "No se puedo eliminar el cliente. ";
			if(datos != null) {
				mensaje += datos;
			}
			JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

}