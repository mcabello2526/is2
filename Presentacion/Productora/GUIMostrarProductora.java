
package Presentacion.Productora;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Productora.GUIProductora;
import Presentacion.Controlador.*;

import static Negocio.Productora.TProductora.*;
import static Presentacion.Controlador.Controlador.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Negocio.FactorySA.FactorySA;
import Negocio.Productora.SAProductoraImp;
import Negocio.Productora.TProductora;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author USUARIO
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIMostrarProductora extends JFrame implements IGUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnBuscar;
	private JPanel panelInfo;
	private JButton btnVolver;
	private JLabel lblNombre;
	private JLabel lblAnio;
	private JLabel lblNacionalidad;
	private JLabel lblNombreRes;
	private JLabel lblAnioRes;
	private JLabel lblNacionalidadRes;
	
	
	public GUIMostrarProductora() {
		setTitle("Mostrar Productora");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		setBounds(100, 100, 677, 437);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getBtnVolver());
		contentPane.add(getPanel());
		contentPane.add(getPanelInfo());
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar Productora");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(148, 10, 357, 42);
		}
		return lblTitulo;
	}
	

	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Evento.RES_MOSTRAR_PRODUCTORA_KO):{
			JOptionPane.showMessageDialog(null, (String) datos
					,"ERROR", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		}case(Evento.RES_MOSTRAR_PRODUCTORA_OK):{
			getPanelInfo().setVisible(true);
			TProductora t = (TProductora) datos;
			getLblNombreRes().setText(t.getNombre());
			getLblAnioRes().setText(String.valueOf(t.getAnioCreacion()));
			getLblNacionalidadRes().setText(String.valueOf(t.getIdNacionalidad()));
			
			break;
		}
		}
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(132, 73, 392, 75);
			panel.setLayout(null);
			panel.add(getLblId());
			panel.add(getTxtId());
			panel.add(getBtnBuscar());
		}
		return panel;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("Id de la marca:");
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
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBackground(new Color(176, 224, 230));
			btnBuscar.setBounds(275, 33, 89, 23);
			btnBuscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	boolean datosValidos = validar();
                    if(datosValidos) {
                    	Integer id = Integer.parseInt(txtId.getText().trim());
                    	Controlador.getInstance().accion(Evento.MOSTRAR_PRODUCTORA,id);
                    	dispose();
                    }
                }
            });
		}
		return btnBuscar;
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
	private JPanel getPanelInfo() {
		if (panelInfo == null) {
			panelInfo = new JPanel();
			panelInfo.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelInfo.setBackground(new Color(255, 255, 255));
			panelInfo.setBounds(132, 174, 392, 130);
			panelInfo.setLayout(null);
			panelInfo.add(getLblNombre());
			panelInfo.add(getLblAnio());
			panelInfo.add(getLblNombreRes());
			panelInfo.add(getLblAnioRes());
			panelInfo.add(getLblNacionalidadRes());
			panelInfo.add(getLblNacionalidad());
			
			panelInfo.setVisible(false);
		}
		return panelInfo;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(new Color(255, 255, 255));
			btnVolver.setBackground(new Color(255, 0, 0));
			btnVolver.setBounds(289, 339, 89, 23);
			btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
		}
		return btnVolver;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(30, 23, 179, 14);
		}
		return lblNombre;
	}
	
	private JLabel getLblAnio() {
		if (lblAnio == null) {
			lblAnio = new JLabel("Año Creacion: ");
			lblAnio.setBounds(33, 70, 119, 14);
		}
		return lblAnio;
	}
	
	private JLabel getLblNacionalidad() {
		if (lblNacionalidad == null) {
			lblNacionalidad = new JLabel("Id de nacionalidad a la que pertenece");
			lblNacionalidad.setBounds(33, 70, 119, 14);
		}
		return lblNacionalidad;
	}
	
	private JLabel getLblNombreRes() {
		if (lblNombreRes == null) {
			lblNombreRes = new JLabel("a");
			lblNombreRes.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNombreRes.setBounds(164, 67, 197, 14);
		}
		return lblNombreRes;
	}
	
	private JLabel getLblAnioRes() {
		if (lblAnioRes == null) {
			lblAnioRes = new JLabel("a");
			lblAnioRes.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnioRes.setBounds(164, 23, 197, 14);
		}
		return lblAnioRes;
	}
	
	private JLabel getLblNacionalidadRes() {
		if (lblNacionalidadRes == null) {
			lblNacionalidadRes = new JLabel("a");
			lblNacionalidadRes.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNacionalidadRes.setBounds(164, 23, 197, 14);
		}
		return lblNacionalidadRes;
	}
	
}
