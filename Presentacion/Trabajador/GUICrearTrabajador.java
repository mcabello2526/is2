package Presentacion.Trabajador;

import java.awt.Color;
import java.awt.Component;
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

import Negocio.Trabajador.TCompleto;
import Negocio.Trabajador.TParcial;
import Negocio.Trabajador.TTrabajador;
import Presentacion.Evento;
import Presentacion.Controlador.Controlador;
import Presentacion.IGUI;

public class GUICrearTrabajador extends JFrame implements ActionListener, IGUI {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblHorasTotales;
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
	private JTextField txtJornada;
	private JTextField txtHorasExtras;
	private JButton btnAnadir;
	private JButton btnVolver; 
	
	public GUICrearTrabajador() {
		setTitle("Alta Trabajador");
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

	private Component getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(169, 73, 320, 453);
			panel.setLayout(null);
			panel.add(getLblNombre());
			panel.add(getLblHorasTotales());
			panel.add(getLblTitulo());
			panel.add(getTxtNombre());
			panel.add(getTxtHorasTotales());
			panel.add(getRdbtnCompleta());
			panel.add(getRdbtnParcial());
			panel.add(getLblAnioIncorporacion());
			panel.add(getLblBonificacionAnual());
			panel.add(getLblJornada());
			panel.add(getLblHorasExtras());
			panel.add(getTxtAnioincorporacion());
			panel.add(getTxtBonificacionAnual());
			panel.add(getTxtJornada());
			panel.add(getTxtHorasExtras());
			panel.add(getBtnAnadir());
			panel.add(getBtnVolver());
			ButtonGroup grupoJornada = new ButtonGroup();
			grupoJornada.add(getRdbtnCompleta());
			grupoJornada.add(getRdbtnParcial());
		}
		return panel;

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

	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBackground(new Color(134, 231, 120));
			btnAnadir.setBounds(197, 406, 89, 23);
			btnAnadir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean datosValidos = validar();
                    if(datosValidos) {
                    	TTrabajador tTrabajador = null;
                    	String nombre = txtNombre.getText().trim();
                    	float horasTotales = Float.parseFloat(txtHorasTotales.getText().trim()); 
                    	if(getRdbtnCompleta().isSelected()) {
                    		Integer anio = Integer.parseInt(txtAnioIncorporacion.getText().trim());
                    		float bonificacion = Float.parseFloat(txtBonificacionAnual.getText().trim());
                    		tTrabajador = new TCompleto(0,nombre,horasTotales,true,true,
                    				anio,bonificacion);
                    	}else if(getRdbtnParcial().isSelected()) {
                    		String jornada = txtJornada.getText().trim();
                    		Integer horas = Integer.parseInt(txtHorasExtras.getText().trim());
                    		tTrabajador = new TParcial(0,nombre,horasTotales,true,false,jornada,horas);
                    	}
                    	Controlador.getInstance().accion(Evento.CREAR_TRABAJADOR,tTrabajador);
                    	
                    }
                }


            });
		}
		return btnAnadir;
	}

	private JTextField getTxtHorasExtras() {
		if (txtHorasExtras == null) {
			txtHorasExtras = new JTextField();
			txtHorasExtras.setEnabled(false);
			txtHorasExtras.setColumns(20);
			txtHorasExtras.setBounds(190, 360, 96, 20);
		}
		return txtHorasExtras;
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

	private JTextField getTxtBonificacionAnual() {
		if (txtBonificacionAnual == null) {
			txtBonificacionAnual = new JTextField();
			txtBonificacionAnual.setEnabled(false);
			txtBonificacionAnual.setColumns(20);
			txtBonificacionAnual.setBounds(190, 288, 96, 20);
		}
		return txtBonificacionAnual;
	}

	private JTextField getTxtAnioincorporacion() {
		if (txtAnioIncorporacion == null) {
			txtAnioIncorporacion = new JTextField();
			txtAnioIncorporacion.setEnabled(false);
			txtAnioIncorporacion.setBounds(190, 253, 96, 20);
			txtAnioIncorporacion.setColumns(20);
		}
		return txtAnioIncorporacion;
	}

	private JLabel getLblHorasExtras() {
		if (lblHorasExtras == null) {
			lblHorasExtras = new JLabel("Horas Extra:");
			lblHorasExtras.setBounds(33, 363, 119, 14);
		}
		return lblHorasExtras;
	}

	private JLabel getLblJornada() {
		if (lblJornada == null) {
			lblJornada = new JLabel("Jornada:");
			lblJornada.setBounds(33, 326, 119, 14);
		}
		return lblJornada;
	}

	private JLabel getLblBonificacionAnual() {
		if (lblBonificacionAnual== null) {
			lblBonificacionAnual = new JLabel("Bonificación Anual:");
			lblBonificacionAnual.setBounds(33, 291, 119, 14);
		}
		return lblBonificacionAnual;
	}

	private JLabel getLblAnioIncorporacion() {
		if (lblAnioIncorporacion == null) {
			lblAnioIncorporacion = new JLabel("Año de Incorporación:");
			lblAnioIncorporacion.setBounds(33, 256, 147, 14);
		}
		return lblAnioIncorporacion;
	}

	private JRadioButton getRdbtnParcial() {
		if (rdbtnParcial == null) {
			rdbtnParcial = new JRadioButton("Tiempo Parcial");
			rdbtnParcial.setBounds(102, 215, 156, 23);
			rdbtnParcial.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getTxtAnioincorporacion().setEnabled(false);
					getTxtBonificacionAnual().setEnabled(false);
					getTxtJornada().setEnabled(true);
					getTxtHorasExtras().setEnabled(true);
					getTxtJornada().setText("");
					getTxtHorasExtras().setText("");
				}
			});
		}
		return rdbtnParcial;
	}

	private JRadioButton getRdbtnCompleta() {
		if (rdbtnCompleta == null) {
			rdbtnCompleta = new JRadioButton("Jornada Completa");
			rdbtnCompleta.setBounds(102, 178, 156, 23);
			rdbtnCompleta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getTxtAnioincorporacion().setEnabled(true);
					getTxtBonificacionAnual().setEnabled(true);
					getTxtJornada().setEnabled(false);
					getTxtHorasExtras().setEnabled(false);
					getTxtJornada().setText("");
					getTxtHorasExtras().setText("");

					

				}
			});
		}
		return rdbtnCompleta;
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

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(162, 34, 96, 20);
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}

	private JLabel getLblHorasTotales() {
		if (lblHorasTotales == null) {
			lblHorasTotales = new JLabel("Horas Totales:");
			lblHorasTotales.setBounds(33, 363, 119, 14);
		}
		return lblHorasTotales;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(33, 363, 119, 14);
		}
		return lblHorasTotales;
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Añadir Trabajador");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(148, 10, 357, 42);
		}
		return lblTitulo;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Evento.RES_CREAR_TRABAJADOR_OK):{
    		JOptionPane.showMessageDialog(null, "El trabajador se ha dado de alta.",
					"ÉXITO", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}case(Evento.RES_CREAR_TRABAJADOR_KO):{
    		JOptionPane.showMessageDialog(null, (String) datos,
					"ERROR", JOptionPane.INFORMATION_MESSAGE);
    		dispose();
    		break;
		}
		}

	}
	private boolean validar() {
		String nombre = txtNombre.getText().trim();
		if(!nombre.matches("^[A-Za-z][A-Za-z\\s]{2,}$")) {
			JOptionPane.showMessageDialog(null, "El nombre debe tener al menos 3 caracteres y "
					+ "no empezar por un número o símbolo.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		/*
		String horasTotalesStr = txtHorasTotales.getText().trim();
		Float horasTotales = Float.parseFloat(horasTotalesStr);
		if (horasTotales < 0 || horasTotales > 240) {
	        JOptionPane.showMessageDialog(null, "Las horas totales deben ser un valor positivo y coherente.",
	                "ERROR", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    */
		
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
			
			String bonificacion = txtBonificacionAnual.getText().trim();
			float bonificacionFloat=0;
			try {
				bonificacionFloat = Float.parseFloat(bonificacion);
				if(bonificacionFloat < 0) {
					JOptionPane.showMessageDialog(null, "La bonificación debe ser un número positivo.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "La bonificación anual debe de ser un número. Puede tener decimales.",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}else if (getRdbtnParcial().isSelected()) {
			String jornada = txtJornada.getText().trim();
			if(!jornada.matches("^[A-Za-z][A-Za-z\\s]{2,}$")) {
				JOptionPane.showMessageDialog(null, "La jornada debe tener al menos 3 caracteres y "
						+ "no empezar por un número o símbolo.", "ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			String horas = txtHorasExtras.getText().trim();
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
			
		}else {
			JOptionPane.showMessageDialog(null, "Debes seleccionar entre Jornada Completa o Tiempo Parcial.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) { // y esto? 
		// TODO Auto-generated method stub
		
	}


}
