
package Presentacion.Trabajador;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Trabajador.GUITrabajador;
import Presentacion.Controlador.*;

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


public class GUIMostrarTrabajador extends JFrame implements IGUI {

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
	private JLabel lblHorasTotales;
	private JLabel lblTipo;
	private JLabel lblAux1;
	private JLabel lblAux2;
	private JLabel lblNombreRes;
	private JLabel lblHorasTotalesRes;
	private JLabel lblTipoRes;
	private JLabel lblAux1Res;
	private JLabel lblAux2Res;
	
	
	public GUIMostrarTrabajador() {
		setTitle("Mostrar Trabajador");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		setBounds(100, 100, 677, 543);
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
			lblTitulo = new JLabel("Mostrar Trabajador");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(148, 10, 357, 42);
		}
		return lblTitulo;
	}
	

	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Evento.RES_MOSTRAR_TRABAJADOR_KO):{
			JOptionPane.showMessageDialog(null, (String) datos
					,"ERROR", JOptionPane.ERROR_MESSAGE);
			dispose();
			break;
		}case(Evento.RES_MOSTRAR_TRABAJADOR_OK):{
			getPanelInfo().setVisible(true);
			TTrabajador t = (TTrabajador) datos;
			getLblNombreRes().setText(t.getNombre());
			getLblHorasTotalesRes().setText(String.format("%.2f", t.getHorasTotales()));
			String tipo = "";
			if(t.getEsTiempoCompleto()) {
				tipo="Jornada Completa";
				TCompleto com = (TCompleto) t;
				getLblAux1().setText("Año de Incorporación: ");
				getLblAux1Res().setText(com.getAnio() + "");
				getLblAux2().setText("Bonificación Anual: ");
				getLblAux2Res().setText(com.getBonificacion()+"");
			}else {
				tipo="Tiempo Parcial";
				TParcial p = (TParcial) t;
				getLblAux1().setText("Jornada: ");
				getLblAux1Res().setText(p.getJornada());
				getLblAux2().setText("Horas extra: ");
				getLblAux2Res().setText(String.format("%.2f", p.getHorasExtra()));
			
			}
			getLblTipoRes().setText(tipo);
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
                    	Controlador.getInstance().accion(Evento.MOSTRAR_TRABAJADOR,id);
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
			panelInfo.setBounds(132, 174, 392, 226);
			panelInfo.setLayout(null);
			panelInfo.add(getLblNombre());
			panelInfo.add(getLblHorasTotales());
			panelInfo.add(getLblTipo());
			panelInfo.add(getLblAux1());
			panelInfo.add(getLblAux2());
			panelInfo.add(getLblNombreRes());
			panelInfo.add(getLblHorasTotalesRes());
			panelInfo.add(getLblTipoRes());
			panelInfo.add(getLblAux1Res());
			panelInfo.add(getLblAux2Res());
			panelInfo.setVisible(false);
		}
		return panelInfo;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(new Color(255, 255, 255));
			btnVolver.setBackground(new Color(255, 0, 0));
			btnVolver.setBounds(288, 443, 89, 23);
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
	private JLabel getLblHorasTotales() {
		if (lblHorasTotales == null) {
			lblHorasTotales = new JLabel("Horas Totales:");
			lblHorasTotales.setBounds(30, 56, 179, 14);
		}
		return lblHorasTotales;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo de trabajador:");
			lblTipo.setBounds(30, 139, 215, 14);
		}
		return lblTipo;
	}
	private JLabel getLblAux1() {
		if (lblAux1 == null) {
			lblAux1 = new JLabel("a");
			lblAux1.setBounds(30, 164, 265, 14);
		}
		return lblAux1;
	}
	private JLabel getLblAux2() {
		if (lblAux2 == null) {
			lblAux2 = new JLabel("a");
			lblAux2.setBounds(30, 189, 265, 14);
		}
		return lblAux2;
	}
	private JLabel getLblNombreRes() {
		if (lblNombreRes == null) {
			lblNombreRes = new JLabel("a");
			lblNombreRes.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNombreRes.setBounds(164, 23, 197, 14);
		}
		return lblNombreRes;
	}
	private JLabel getLblHorasTotalesRes() {
		if (lblHorasTotalesRes == null) {
			lblHorasTotalesRes = new JLabel("a");
			lblHorasTotalesRes.setHorizontalAlignment(SwingConstants.TRAILING);
			lblHorasTotalesRes.setBounds(164, 56, 197, 14);
		}
		return lblHorasTotalesRes;
	}

	private JLabel getLblTipoRes() {
		if (lblTipoRes == null) {
			lblTipoRes = new JLabel("a");
			lblTipoRes.setHorizontalAlignment(SwingConstants.TRAILING);
			lblTipoRes.setBounds(164, 139, 197, 14);
		}
		return lblTipoRes;
	}
	private JLabel getLblAux1Res() {
		if (lblAux1Res == null) {
			lblAux1Res = new JLabel("a");
			lblAux1Res.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAux1Res.setBounds(164, 164, 197, 14);
		}
		return lblAux1Res;
	}
	private JLabel getLblAux2Res() {
		if (lblAux2Res == null) {
			lblAux2Res = new JLabel("a");
			lblAux2Res.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAux2Res.setBounds(164, 189, 197, 14);
		}
		return lblAux2Res;
	}
}