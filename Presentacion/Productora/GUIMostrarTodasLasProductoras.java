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
import Presentacion.Productora.GUIProductora;
import Presentacion.Controlador.*;

import static Negocio.Productora.TProductora.*;
import static Presentacion.Controlador.Controlador.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Negocio.FactorySA.FactorySA;
import Negocio.Productora.SAProductoraImp;
import Negocio.Productora.TProductora ;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;


public class GUIMostrarTodasLasProductoras extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panel;
	private JButton btnVolver;
	private DefaultTableModel tableModel;
	
	
	public GUIMostrarTodasLasProductoras() {
		setTitle("Mostrar todas las productoras");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		setBounds(100, 100, 677, 543);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getBtnVolver());
		contentPane.add(getPanel());
		panel.setBounds(50, 80, 580, 330); 
		panel.setLayout(new BorderLayout());
		tableModel = new DefaultTableModel(new Object[] { "Id", "Nombre","Anio Creación"},
				0);
		
		JTable table = new JTable(tableModel) {


			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

		};
		

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel columnModel = table.getColumnModel();

		columnModel.getColumn(0).setPreferredWidth(50);// ID
		columnModel.getColumn(1).setPreferredWidth(100);// NOMBRE
		columnModel.getColumn(2).setPreferredWidth(100);// ANIO CREACION

		table.setPreferredScrollableViewportSize(new Dimension(700, 400));
		
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar Productoras");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(148, 10, 357, 42);
		}
		return lblTitulo;
	}
	

	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Evento.RES_MOSTRAR_TODAS_LAS_PRODUCTORAS_OK):{
			List<TProductora> lista = (List<TProductora>)datos;
			tableModel.setRowCount(0);

			for (TProductora t : lista) {
					
				Object[] rowData = { t.getId(), t.getNombre(), t.getAnioCreacion()};
				tableModel.addRow(rowData);	
			}
			
			break;
		}case(Evento.RES_MOSTRAR_TODAS_LAS_PRODUCTORAS_KO):{
			JOptionPane.showMessageDialog(null, (String) datos, "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
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
		}
		return panel;
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
	
}