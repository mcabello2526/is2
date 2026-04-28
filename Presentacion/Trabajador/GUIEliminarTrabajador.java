package Presentacion.Trabajador; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;
import Presentacion.Trabajador.GUITrabajador;

import static Negocio.Trabajador.TTrabajador.*;
import static Presentacion.Controlador.Controlador.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Negocio.FactorySA.FactorySA;
import Negocio.Trabajador.SATrabajadorImp;
import Negocio.Trabajador.TCompleto;
import Negocio.Trabajador.TParcial;
import Negocio.Trabajador.TTrabajador;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import Presentacion.IGUI;


public class GUIEliminarTrabajador extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnEliminar;
	private JButton btnVolver;
	
	public GUIEliminarTrabajador() {
		setTitle("Eliminar Trabajador");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		setBounds(100, 100, 700, 340);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getPanel());
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Eliminar Trabajador");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(122, 11, 429, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(new Color(255, 255, 255));
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
			lblId = new JLabel("Id del trabajador:");
			lblId.setBounds(33, 37, 119, 14);
		}
		return lblId;
	}
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(162, 34, 96, 20);
			txtId.setColumns(30);
		}
		return txtId;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(new Color(134, 231, 120));
			btnEliminar.setBounds(184, 103, 89, 23);
			btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosValidos = validar();
                    if(datosValidos) {
                    	Integer id = Integer.parseInt(txtId.getText().trim());
                    	Controlador.getInstance().accion(Evento.ELIMINAR_TRABAJADOR, id);
                    	
                    }
                }
            });
		}
		return btnEliminar;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(new Color(255, 255, 255));
			btnVolver.setBackground(new Color(255, 0, 0));
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
		case(Evento.RES_ELIMINAR_TRABAJADOR_OK):{
			JOptionPane.showMessageDialog(null, "El trabajador se ha dado de baja.",
					"ÉXITO", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}case(Evento.RES_ELIMINAR_TRABAJADOR_KO):{
			JOptionPane.showMessageDialog(null, (String) datos,
					"ERROR", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}
		}
		
	}

}