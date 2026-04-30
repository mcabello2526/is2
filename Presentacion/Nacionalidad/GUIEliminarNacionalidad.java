
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

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

public class GUIEliminarNacionalidad extends JFrame implements IGUI {

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
	
	public GUIEliminarNacionalidad() {
		setTitle("Eliminar nacionalidad");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 550, 340);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());
		
		setContentPane(contentPane);
		contentPane.add(getPanel());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel ("Eliminar nacionalidad");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(10, 20, 514, 40);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if(panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(115, 80, 320, 180);
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
			lblId = new JLabel ("ID nacionalidad: ");
			lblId.setBounds(30, 30, 100, 20);
		}
		return lblId;
	}
	
	private JTextField getTxtId() {
		if(txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(140, 27, 130, 25);
		}
		return txtId;
	}
	
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setForeground(textoNegro);
			btnEliminar.setBackground(btnVerde);
			btnEliminar.setBounds(175, 80, 100, 30);
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (validar()) {
						int id = Integer.parseInt(txtId.getText().trim());
						Controlador.getInstance().accion(Evento.ELIMINAR_NACIONALIDAD, id);
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
			btnVolver.setBounds(45, 80, 100, 30);
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
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El ID no puede estar vacío.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int id = Integer.parseInt(texto);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número entero positivo.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El ID debe ser un numero entero positivo sin simbolos", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.RES_ELIMINAR_NACIONALIDAD_OK:
			JOptionPane.showMessageDialog(this, "Nacionalidad eliminada correctamente.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
			dispose(); 
			break;
		case Evento.RES_ELIMINAR_NACIONALIDAD_KO:
			String mensaje = "Error al eliminar la nacionalidad.";
			if(datos != null) {
				mensaje += "Debido a: " + datos;
			}else{
				mensaje += "E ID no existe.";
			}
			JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}