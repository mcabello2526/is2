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
import javax.swing.JTextField;
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
public class GUIMostrarPeliculasPorProductora extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panelTabla;
	private JPanel panelBuscar;
	private JTextField txtIdProductora;
	private JButton btnBuscar;
	private JButton btnVolver;
	private DefaultTableModel tableModel;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);
	
	public GUIMostrarPeliculasPorProductora() {
		setTitle("Mostrar películas por productora");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 677, 543);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());
		setContentPane(contentPane);
		contentPane.add(getBtnVolver());
		contentPane.add(getPanelBuscar());
		contentPane.add(getPanelTabla());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar películas por productora");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(10, 10, 643, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanelBuscar() {
		if(panelBuscar == null) {
			panelBuscar = new JPanel();
			panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelBuscar.setBackground(fondoGris);
			panelBuscar.setBounds(50, 60, 580, 70);
			panelBuscar.setLayout(null);
			
			JLabel lblId = new JLabel("ID productora:");
			lblId.setBounds(30, 25, 110, 20);
			panelBuscar.add(lblId);
			
			txtIdProductora = new JTextField();
			txtIdProductora.setBounds(140, 23, 120, 25);
			panelBuscar.add(txtIdProductora);
			
			btnBuscar = new JButton("Mostrar");
			btnBuscar.setForeground(textoNegro);
			btnBuscar.setBackground(btnVerde);
			btnBuscar.setBounds(280, 23, 100, 25);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (validar()) {
						int id = Integer.parseInt(txtIdProductora.getText().trim());
						Controlador.getInstance().accion(Evento.GUI_MOSTRAR_PELICULAS_POR_PRODUCTORA,  id);
					}
				}
			});
			panelBuscar.add(btnBuscar);
		}
		return panelBuscar;
	}
	
	private JPanel getPanelTabla() {
		if(panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTabla.setBackground(fondoGris);
			panelTabla.setBounds(50, 150, 580, 270);
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
	
	private boolean validar() {
		String id = txtIdProductora.getText().trim();
		try {
			int idInt = Integer.parseInt(id);
			if(idInt < 0) {
				JOptionPane.showMessageDialog(null, "El ID debe ser un número igual o mayor a 0.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.RES_MOSTRAR_PELICULAS_POR_PRODUCTORA_OK:
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
		case Evento.RES_MOSTRAR_PELICULAS_POR_PRODUCTORA_KO:
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(null,  "No se han encontrados películas para esta productora", "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}