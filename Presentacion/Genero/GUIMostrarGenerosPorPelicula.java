/**
 *
 */
package Presentacion.Genero;

import java.awt.event.ActionListener;
import java.util.List;

import Presentacion.IGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Presentacion.Evento;
import Negocio.Genero.TGenero;
import Presentacion.Controlador.Controlador;

public class GUIMostrarGenerosPorPelicula extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel panelBusqueda;
	private JPanel panelTabla;
	private JTextField txtIdPelicula;
	private JButton btnBuscar;
	private JButton btnVolver;
	private DefaultTableModel tableModel;

	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	public GUIMostrarGenerosPorPelicula() {
		setTitle("Mostrar géneros por película");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		contentPane.add(getLblTitulo());
		contentPane.add(getPanelBusqueda());
		contentPane.add(getPanelTabla());
		contentPane.add(getBtnVolver());

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Géneros por película");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(10, 10, 664, 42);
		}
		return lblTitulo;
	}

	private JPanel getPanelBusqueda() {
		if (panelBusqueda == null) {
			panelBusqueda = new JPanel();
			panelBusqueda.setBorder(new LineBorder(Color.BLACK, 2));
			panelBusqueda.setBackground(fondoGris);
			panelBusqueda.setBounds(50, 70, 580, 60);
			panelBusqueda.setLayout(null);

			JLabel lblId = new JLabel("ID Película:");
			lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblId.setBounds(30, 20, 100, 20);
			panelBusqueda.add(lblId);

			txtIdPelicula = new JTextField();
			txtIdPelicula.setBounds(120, 18, 100, 25);
			panelBusqueda.add(txtIdPelicula);

			btnBuscar = new JButton("Buscar");
			btnBuscar.setBackground(btnVerde);
			btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnBuscar.setBounds(430, 15, 120, 30);
			btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String idStr = txtIdPelicula.getText().trim();
					if (!idStr.isEmpty()) {
						try {
							int id = Integer.parseInt(idStr);
							Controlador.getInstance().accion(Evento.MOSTRAR_GENEROS_POR_PELICULA, id);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "El ID debe ser numérico.", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Introduce un ID de película.", "AVISO", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			panelBusqueda.add(btnBuscar);
		}
		return panelBusqueda;
	}

	private JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTabla.setBackground(fondoGris);
			panelTabla.setBounds(50, 150, 580, 330);
			panelTabla.setLayout(new BorderLayout());

			tableModel = new DefaultTableModel(new Object[] { "ID Género", "Nombre", "Descripción" }, 0);
			JTable table = new JTable(tableModel) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(80);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(350);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane scroll = new JScrollPane(table);
			panelTabla.add(scroll, BorderLayout.CENTER);
		}
		return panelTabla;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(290, 500, 100, 30);
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
		tableModel.setRowCount(0);
		switch (evento) {
		case Evento.RES_MOSTRAR_GENEROS_POR_PELICULA_OK:
			@SuppressWarnings("unchecked")
			List<TGenero> lista = (List<TGenero>) datos;
			if (lista.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No hay géneros para esta película.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			} else {
				for (TGenero g : lista) {
					tableModel.addRow(new Object[] { g.getId(), g.getNombre(), g.getDescripcion() });
				}
			}
			break;
		case Evento.RES_MOSTRAR_GENEROS_POR_PELICULA_KO:
			JOptionPane.showMessageDialog(this, "Error al consultar géneros: " + datos, "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}