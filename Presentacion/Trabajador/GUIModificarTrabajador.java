
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


public class GUIModificarTrabajador extends JFrame implements IGUI, ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblHorasTotales;
	private JLabel lblTipo;
	private JTextField txtNombre;
	private JTextField txtHorasTotales;
	private JRadioButton rdbtnCompleta;
	private JRadioButton rdbtnParcial;
	private JLabel lblAnioIncorporacion;
	private JLabel lblBonificacionAnual;
	private JLabel lblJornada;
	private JLabel lblHorasExtras;
	private JTextField txtAnioIncorporacion;
	private JTextField txtBonificacionAnual; 
	private JTextField txtHorasExtras;
	private JTextField txtJornada; 
	private JButton btnModificar;
	private JButton btnVolver;
	private JLabel lblId;
	private JTextField txtId;
	
	public GUIModificarTrabajador() {
		setTitle("Modificar Trabajador");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		setBounds(100, 100, 700, 600);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getPanel());
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Modificar Trabajador");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(122, 10, 418, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(169, 63, 320, 463);
			panel.setLayout(null);
			panel.add(getLblNombre());
			panel.add(getLblHorasTotales());
			panel.add(getLblTipo());
			panel.add(getTxtNombre());
			panel.add(getTxtHorasTotales());

			panel.add(getTxtId());
			panel.add(getRdbtnCompleta());
			panel.add(getRdbtnParcial());
			panel.add(getLblAnioIncorporacion());
			panel.add(getLblBonificacionAnual());
			panel.add(getLblJornada());
			panel.add(getLblHorasExtras());
			panel.add(getTxtAno());
			panel.add(getTxtBonificacion());
			panel.add(getTxtJornada());
			panel.add(getTxtHorasExtras());
			panel.add(getBtnModificar());
			panel.add(getBtnVolver());
			ButtonGroup grupoJornada = new ButtonGroup();
			grupoJornada.add(getRdbtnCompleta());
			grupoJornada.add(getRdbtnParcial());
			panel.add(getLblId());
		}
		return panel;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(33, 37, 119, 14);
		}
		return lblNombre;
	}
	private JLabel getLblHorasTotales() {
		if (lblHorasTotales == null) {
			lblHorasTotales = new JLabel("Horas Totales:");
			lblHorasTotales.setBounds(33, 363, 119, 14);
		}
		return lblHorasTotales;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(33, 182, 49, 14);
		}
		return lblTipo;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(162, 34, 96, 20);
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}
	
	private JTextField getTxtHorasTotales() {
		if (txtHorasTotales == null) {
			txtHorasTotales = new JTextField();
			//txtHorasTotales.setEnabled(false);
			txtHorasTotales.setColumns(20);
			txtHorasTotales.setBounds(190, 360, 96, 20);
		}
		return txtHorasTotales;
	}

	private JRadioButton getRdbtnCompleta() {
		if (rdbtnCompleta == null) {
			rdbtnCompleta = new JRadioButton("Jornada Completa");
			rdbtnCompleta.setBounds(102, 178, 156, 23);
			rdbtnCompleta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getTxtAno().setEnabled(true);
					getTxtBonificacion().setEnabled(true);
					getTxtJornada().setEnabled(false);
					getTxtHorasExtras().setEnabled(false);
					getTxtJornada().setText("");
					getTxtHorasExtras().setText("");
				}
			});
		}
		return rdbtnCompleta;
	}
	private JRadioButton getRdbtnParcial() {
		if (rdbtnParcial == null) {
			rdbtnParcial = new JRadioButton("Tiempo Parcial");
			rdbtnParcial.setBounds(102, 215, 156, 23);
			rdbtnParcial.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getTxtAno().setEnabled(false);
					getTxtBonificacion().setEnabled(false);
					getTxtJornada().setEnabled(true);
					getTxtHorasExtras().setEnabled(true);
					getTxtAno().setText("");
					getTxtBonificacion().setText("");
				}
			});
		}
		return rdbtnParcial;
	}
	private JLabel getLblAnioIncorporacion() {
		if (lblAnioIncorporacion == null) {
			lblAnioIncorporacion = new JLabel("Año de Incorporación:");
			lblAnioIncorporacion.setBounds(33, 256, 147, 14);
		}
		return lblAnioIncorporacion;
	}
	private JLabel getLblBonificacionAnual() {
		if (lblBonificacionAnual == null) {
			lblBonificacionAnual = new JLabel("Bonificación Anual:");
			lblBonificacionAnual.setBounds(33, 291, 119, 14);
		}
		return lblBonificacionAnual;
	}
	private JLabel getLblJornada() {
		if (lblJornada == null) {
			lblJornada = new JLabel("Jornada:");
			lblJornada.setBounds(33, 326, 119, 14);
		}
		return lblJornada;
	}
	private JLabel getLblHorasExtras() {
		if (lblHorasExtras == null) {
			lblHorasExtras = new JLabel("Horas Extra:");
			lblHorasExtras.setBounds(33, 363, 119, 14);
		}
		return lblHorasExtras;
	}
	private JTextField getTxtAno() {
		if (txtAnioIncorporacion == null) {
			txtAnioIncorporacion = new JTextField();
			txtAnioIncorporacion.setEnabled(false);
			txtAnioIncorporacion.setBounds(190, 253, 96, 20);
			txtAnioIncorporacion.setColumns(20);
		}
		return txtAnioIncorporacion;
	}
	private JTextField getTxtBonificacion() {
		if (txtBonificacionAnual == null) {
			txtBonificacionAnual = new JTextField();
			txtBonificacionAnual.setEnabled(false);
			txtBonificacionAnual.setColumns(20);
			txtBonificacionAnual.setBounds(190, 288, 96, 20);
		}
		return txtBonificacionAnual;
	}
	private JTextField getTxtJornada() {

		if (txtJornada == null) {
			txtJornada = new JTextField();
			txtJornada.setEnabled(false);
			txtJornada.setBounds(190, 323, 96, 20);
			txtJornada.setColumns(20);
		}
		return txtJornada;
	}
	private JTextField getTxtHorasExtras() {
		if (txtHorasExtras == null) {
			txtHorasExtras = new JTextField();
			txtHorasExtras .setEnabled(false);
			txtHorasExtras .setColumns(20);
			txtHorasExtras .setBounds(190, 360, 96, 20);
		}
		return txtHorasExtras;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setBackground(new Color(134, 231, 120));
			btnModificar.setBounds(197, 406, 89, 23);
			btnModificar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosValidos = validar();
                    if(datosValidos) {
                    	TTrabajador tTrabajador = null;
                    	int id = Integer.parseInt(txtId.getText().trim());
                    	String nombre = txtNombre.getText().trim();
                    	float horasTotales = Float.parseFloat(txtHorasTotales.getText().trim()); 
                    	if(getRdbtnCompleta().isSelected()) {
                    		int anio = !txtAnioIncorporacion.getText().trim().equals("")?Integer.parseInt(txtAnioIncorporacion.getText()
                    				.trim()):0;
                    		float bonificacion = !txtBonificacionAnual.getText().trim().equals("")?
                    				Float.parseFloat(txtBonificacionAnual.getText().trim()):0;
                    		tTrabajador = new TCompleto(id,nombre,horasTotales,true,true,
                    				anio,bonificacion);
                    	}else if(getRdbtnParcial().isSelected()) {
                    		String jornada = txtJornada.getText().trim();
                    		Integer horas = !txtHorasExtras.getText().trim().equals("")?
                    				Integer.parseInt(txtHorasExtras.getText().trim()):0;
                    		tTrabajador = new TParcial(id,nombre,horasTotales,true,false,jornada,horas);
                    	}
                    	Controlador.getInstance().accion(Evento.MODIFICAR_TRABAJADOR,tTrabajador);
                    	
                    }
                }
            });
		}
		return btnModificar;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(new Color(255, 255, 255));
			btnVolver.setBackground(new Color(255, 0, 0));
			btnVolver.setBounds(33, 406, 89, 23);
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
		if(id.equals("")) {
			JOptionPane.showMessageDialog(null, "Introduz un id.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			int idInt=0;
			try {
				idInt = Integer.parseInt(id);
				if(idInt<0) {
					JOptionPane.showMessageDialog(null, "El id debe ser un número positivo.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "El id debe ser un número entero.",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		String nombre = txtNombre.getText().trim();
		if(!nombre.equals("")&& !nombre.matches("^[A-Za-z][A-Za-z\\s]{2,}$")) {
			JOptionPane.showMessageDialog(null, "El nombre debe tener al menos 3 caracteres y "
					+ "no empezar por un número o símbolo.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String horasStr = txtHorasTotales.getText().trim();
		try {
		   
		    float horasTotales = Float.parseFloat(horasStr);
		    
		   
		    if (horasTotales < 0 || horasTotales > 744) { 
		        JOptionPane.showMessageDialog(null, "Las horas totales deben ser un valor positivo y coherente.",
		                "ERROR", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido para las horas (ej: 40 o 40.5).",
		            "ERROR", JOptionPane.ERROR_MESSAGE);
		    return false;
		}
		


		if(getRdbtnCompleta().isSelected()) {
			String ano = txtAnioIncorporacion.getText().trim();
			if(!ano.equals("")) {
				int anoInt = 0;
				try {
					anoInt = Integer.parseInt(ano);
					if(anoInt<2020) {
						JOptionPane.showMessageDialog(null, "El año de incorporación debe de ser mayor o igual"
								+ " a 2020.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "El año debe ser un número entero.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			
			
			String bonificacion = txtBonificacionAnual.getText().trim();
			if(!bonificacion.equals("")) {
				float bonificacionFloat=0;
				try {
					bonificacionFloat = Float.parseFloat(bonificacion);
					if(bonificacionFloat<0) {
						JOptionPane.showMessageDialog(null, "La bonificación debe ser un número positivo.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "La bonificación anual debe de ser un número. Puede tener decimales.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}else if(getRdbtnParcial().isSelected()) {
			String jornada = txtJornada.getText().trim();
			if(!jornada.equals("")&& !jornada.matches("^[A-Za-z][A-Za-z\\s]{2,}$")) {
				JOptionPane.showMessageDialog(null, "La jornada debe tener al menos 3 caracteres y "
						+ "no empezar por un número o símbolo.", "ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			String horas = txtHorasExtras.getText().trim();
			if(!horas.equals("")) {
				int horasInt=0;
				try {
					horasInt = Integer.parseInt(horas);
					if(horasInt<=0 || horasInt>40) {
						JOptionPane.showMessageDialog(null, "Las horas semanales deben comprenderse entre 1 y 40.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Las horas deben ser un número entero.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Debes seleccionar entre Jornada Completa o Tiempo Parcial.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("Id:");
			lblId.setBounds(33, 11, 119, 14);
		}
		return lblId;
	}
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setColumns(30);
			txtId.setBounds(162, 8, 96, 20);
		}
		return txtId;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case(Evento.RES_MODIFICAR_TRABAJADOR_OK):{
			JOptionPane.showMessageDialog(null, "El trabajador se ha modificado.",
					"ÉXITO", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}case(Evento.RES_MODIFICAR_TRABAJADOR_KO):{
			JOptionPane.showMessageDialog(null, (String) datos,
					"ERROR", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}