/**
 *
 */
package Presentacion.Genero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Presentacion.IGUI;
import javax.swing.*;

import javax.swing.JButton;

import Presentacion.Evento;
import Negocio.Genero.TGenero;
import Presentacion.Controlador.Controlador;

public class GUIMostrarTodoslosGeneros extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panelTabla;
	private JButton btnVolver;
	private DefaultTableModel tableModel;
	
	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	
	public GUIMostrarTodoslosGeneros() {
		setTitle("Mostrar todos los géneros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 677, 543);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		contentPane.add(getLblTitulo());
		contentPane.add(getBtnVolver());
		contentPane.add(getPanelTabla());
		Controlador.getInstance().accion(Evento.MOSTRAR_TODOS_LOS_GENEROS, null);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar todos los géneros");
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
			
			tableModel = new DefaultTableModel(new Object[] {"ID", "Nombre", "Descripción"}, 0);
			
			JTable table = new JTable(tableModel) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(60);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(370);
			
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
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.RES_MOSTRAR_TODOS_LOS_GENEROS_OK:
			@SuppressWarnings("unchecked")
			List<TGenero> lista = (List<TGenero>) datos;
			tableModel.setRowCount(0);
			
			for(TGenero g : lista) {
				Object[] rowData = {
					g.getId(), 
					g.getNombre(), 
					g.getDescripcion()
				};
				tableModel.addRow(rowData);
			}
			break;
		case Evento.RES_MOSTRAR_TODOS_LOS_GENEROS_KO:
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(null, "No hay géneros registrados", "AVISO", JOptionPane.WARNING_MESSAGE);
			break;
		}
	}
}