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
import Negocio.Cliente.TNormal;
import Negocio.Cliente.TVip;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUIMostrarCliente extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnBuscar;
	private JButton btnVolver;

	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	
	public GUIMostrarCliente() {
		setTitle("Mostrar cliente");
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
			lblTitulo = new JLabel("Mostrar cliente");
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
			panel.setBounds(169, 73, 350, 160);
			panel.setLayout(null);
			
			panel.add(getLblId());
			panel.add(getTxtId());
			
			panel.add(getBtnBuscar());
			
			panel.add(getBtnVolver());
			}
		return panel;
	}
	
	private JLabel getLblId() {
		if(lblId == null) {
			lblId = new JLabel("ID del cliente:");
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
	
	private JButton getBtnBuscar() {
		if(btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.setForeground(textoNegro);
			btnBuscar.setBackground(btnVerde);
			btnBuscar.setBounds(190, 90, 110, 30);
			btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String texto = txtId.getText().trim();
					if(!texto.isEmpty()) {
						try {
							int id = Integer.parseInt(texto);
							Controlador.getInstance().accion(Evento.MOSTRAR_CLIENTE, id);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null,  "Introduce un ID.");
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
			btnVolver.setBounds(50, 90, 110, 30);
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
		case Evento.RES_MOSTRAR_CLIENTE_OK:
			TCliente cliente = (TCliente) datos;
			String tipoInfo = "";
			if (cliente instanceof TVip) {
				tipoInfo = "\nTipo: VIP\nDescuento: " + ((TVip) cliente).getDescuento() + "%";
			} else if (cliente instanceof TNormal) {
				tipoInfo = "\nTipo: Normal\nCopias: " + ((TNormal) cliente).getNumCopias();
			} else {
				tipoInfo = "\nTipo: " + (cliente.getEsVip() ? "VIP" : "Normal");
			}
			
			String estado = cliente.getActivo() ? "Activo" : "Inactivo";
			String info = "ID: " + cliente.getId() + "\n" +
						  "Nombre: " + cliente.getNombre() + "\n" +
						  "Apellidos: " + cliente.getApellidos() + "\n" +
						  "Correo: " + cliente.getCorreo() + "\n" +
						  "Estado: " + estado + 
						  tipoInfo;
						  
			JOptionPane.showMessageDialog(this, info, "Datos del Cliente", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case Evento.RES_MOSTRAR_CLIENTE_KO:
			String msg = (datos != null) ? datos.toString() : "El cliente no existe.";
			JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}