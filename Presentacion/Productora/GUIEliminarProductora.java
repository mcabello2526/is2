/**
 * 
 */
package Presentacion.Productora;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.border.LineBorder;
import javax.swing.JTextField;

import javax.swing.JButton;


public class GUIEliminarProductora extends JFrame implements IGUI {
	/**
	 * 
	 */
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
	
	public GUIEliminarProductora() {
		setTitle("Eliminar productora");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 700, 340);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getPanel());
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Eliminar Productora");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(0, 20, 700, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(fondoGris);
			panel.setBounds(169, 73, 320, 157);
			panel.setLayout(null);
			panel.add(getLblId());
			panel.add(getTxtId());
			panel.add(getBtnEliminar());
			panel.add(getBtnVolver());
		}
		return panel;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID de la productora:");
			lblId.setBounds(50, 60, 100, 20);
		}
		return lblId;
	}
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(150, 58, 150, 25);
			txtId.setColumns(30);
		}
		return txtId;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setForeground(textoNegro);
			btnEliminar.setBackground(btnVerde);
			btnEliminar.setBounds(210, 130, 100, 30);
			btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosValidos = validar();
                    if(datosValidos) {
                    	Integer id = Integer.parseInt(txtId.getText().trim());
                    	Controlador.getInstance().accion(Evento.ELIMINAR_PRODUCTORA, id);
                    	
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
			btnVolver.setBounds(44, 103, 89, 23);
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
		String id = txtId.getText().trim();
		int idInt=0;
		try {
			idInt = Integer.parseInt(id);
			if(idInt<0) {
				JOptionPane.showMessageDialog(null, "El ID debe ser un número igual o mayor a 0."
						,"ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El id debe ser un número entero.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case Evento.RES_ELIMINAR_PRODUCTORA_OK:
            JOptionPane.showMessageDialog(null, "La productora se ha dado de baja.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
            dispose(); 
            break;
        case Evento.RES_ELIMINAR_PRODUCTORA_KO:
            JOptionPane.showMessageDialog(null, (String) datos, "ERROR", JOptionPane.ERROR_MESSAGE);
            break;
		}
	}
}