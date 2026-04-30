
package Presentacion.Productora; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Nacionalidad.GUINacionalidad;
import Presentacion.Controlador.*;

import static Negocio.Nacionalidad.TNacionalidad.*;
import static Presentacion.Controlador.Controlador.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Negocio.FactorySA.FactorySA;
import Negocio.Nacionalidad.SANacionalidadImp;
import Negocio.Nacionalidad.TNacionalidad;
import Negocio.Productora.TProductora;
import Negocio.Cliente.TCliente;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author USUARIO
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIMostrarProductoraPorNacionalidad extends JFrame implements IGUI {
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panelTabla;
	private JPanel panelBuscar;
	private JTextField txtIdProductora;
	private JButton btnBuscar;
	private JButton btnVolver;
	private DefaultTableModel tableModel;
	
	
	public GUIMostrarProductoraPorNacionalidad() {
		setTitle("Mostrar Productoras Por Nacionalidad");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		setBounds(100, 100, 677, 543);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getBtnVolver());
		contentPane.add(getPanelBuscar());
		contentPane.add(getPanelTabla());
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar Productoras Por Nacionalidad");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(10, 10, 643, 42);
		}
		return lblTitulo;
	}
	

	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case(Evento.RES_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD_OK):{
			List<TProductora> lista = (List<TProductora>)datos;
			tableModel.setRowCount(0);

			for (TProductora t : lista) {
					Object[] rowData = { t.getId(), t.getNombre(), t.getAnioCreacion(), t.getIdNacionalidad()};
					tableModel.addRow(rowData);
			}
			break;
		}case(Evento.RES_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD_KO):{
			JOptionPane.showMessageDialog(null, (String) datos, "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
			
	}
		
	}
	private JPanel getPanelBuscar() {
		if (panelBuscar == null) {
			panelBuscar = new JPanel();
			panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelBuscar.setBackground(new Color(255, 255, 255));
			panelBuscar.setBounds(50, 60, 580, 70);
			panelBuscar.setLayout(null);
			
			JLabel lblId = new JLabel("ID Nacionalidad:");
			lblId.setBounds(30, 25, 100, 20);
			panelBuscar.add(lblId);
			
			txtIdProductora = new JTextField();
			txtIdProductora.setBounds(140, 23, 120, 25);
			panelBuscar.add(txtIdProductora);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(280, 23, 100, 25);
			btnBuscar.setBackground(new Color(176, 224, 230));
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (validar()) {
						int id = Integer.parseInt(txtIdProductora.getText().trim());
						Controlador.getInstance().accion(Evento.MOSTRAR_PRODUCTORA_POR_NACIONALIDAD, id);
						dispose();
					}
				}
			});
			panelBuscar.add(btnBuscar);
		}
		
		
		
		return panelBuscar;
	}
	
	private JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTabla.setBackground(new Color(255, 255, 255));
			panelTabla.setBounds(50, 150, 580, 270);
			panelTabla.setLayout(new BorderLayout());
			
			tableModel = new DefaultTableModel(new Object[]{"Id", "Nombre", "Año Creacion", "Id Nacionalidad Perteneciente"}, 0);
			JTable table = new JTable(tableModel) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(50);
			columnModel.getColumn(1).setPreferredWidth(100);
			columnModel.getColumn(2).setPreferredWidth(100);
			columnModel.getColumn(3).setPreferredWidth(100);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane scroll = new JScrollPane(table);
			panelTabla.add(scroll, BorderLayout.CENTER);
		}
		return panelTabla;
	}
	
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(new Color(255, 255, 255));
			btnVolver.setBackground(new Color(255, 0, 0));
			btnVolver.setBounds(288, 440, 89, 25);
			btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // mirar si hay que quitar este dispose para que al cerrar la venta no se dejen de ver los resultados? 
                }
            });
		}
		return btnVolver;
	}
	
	private boolean validar() {
		String id = txtIdProductora.getText().trim();
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
	
}