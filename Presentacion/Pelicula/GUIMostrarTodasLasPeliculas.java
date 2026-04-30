/**
 *
 */
package Presentacion.Pelicula;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Negocio.Pelicula.TPelicula;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIMostrarTodasLasPeliculas extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panelTabla;

	private JButton btnVolver;
	private DefaultTableModel tableModel;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	//private Color btnVerde = new Color(134, 231, 120);
	
	public GUIMostrarTodasLasPeliculas() {
		setTitle("Mostrar todas las películas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 677, 543);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());
		setContentPane(contentPane);
		contentPane.add(getBtnVolver());
		contentPane.add(getPanelTabla());
		//Controlador.getInstance().accion(Evento.MOSTRAR_TODAS_LAS_PELICULAS, null);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar todas las películas");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(10, 10, 643, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanelTabla() {
		if(panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTabla.setBackground(fondoGris);
			panelTabla.setBounds(50, 70, 580, 350);
			panelTabla.setLayout(new BorderLayout());
			
			tableModel = new DefaultTableModel(new Object[] {"ID", "Nombre", "Stock", "Precio", "ID Productora"}, 0);
			JTable table = new JTable(tableModel) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(50);
			columnModel.getColumn(1).setPreferredWidth(200);
			columnModel.getColumn(2).setPreferredWidth(70);
			columnModel.getColumn(3).setPreferredWidth(70);
			columnModel.getColumn(4).setPreferredWidth(80);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane scroll = new JScrollPane(table);
			panelTabla.add(scroll, BorderLayout.CENTER);
		}
		return panelTabla;
	}
	
	private JButton getBtnVolver() {
		if(btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(288, 440, 100, 30);
			btnVolver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
		}
		return btnVolver;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.RES_MOSTRAR_TODAS_LAS_PELICULAS_OK:
			List<TPelicula> lista = (List<TPelicula>) datos;
			tableModel.setRowCount(0);
			
			for(TPelicula p : lista) {
				Object[] rowData = {
				p.getId(), p.getNombre(),
				p.getStock(), p.getPrecio(), p.getIdProductora()
				};
				tableModel.addRow(rowData);
			}
			break;
		case Evento.RES_MOSTRAR_TODAS_LAS_PELICULAS_KO:
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(null,  "No hay peliculas registradas", "AVISO", JOptionPane.WARNING_MESSAGE);
			break;
		}
	}
}