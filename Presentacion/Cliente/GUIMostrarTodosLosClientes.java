/**
 *
 */
package Presentacion.Cliente;

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

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TNormal;
import Negocio.Cliente.TVip;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


public class GUIMostrarTodosLosClientes extends JFrame implements IGUI {
	
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
	
	public GUIMostrarTodosLosClientes() {
		setTitle("Mostrar todos los clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		setBounds(100, 100, 800, 550);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());
		setContentPane(contentPane);
		contentPane.add(getBtnVolver());
		contentPane.add(getPanelTabla());
		
		Controlador.getInstance().accion(Evento.MOSTRAR_TODOS_LOS_CLIENTES, null);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel("Mostrar todos los clientes");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(10, 10, 764, 42);
		}
		return lblTitulo;
	}
	
	private JPanel getPanelTabla() {
		if(panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			panelTabla.setBackground(fondoGris);
			panelTabla.setBounds(50, 70, 684, 350);
			panelTabla.setLayout(new BorderLayout());
			
			tableModel = new DefaultTableModel(new Object[] {"ID", "Nombre", "Apellidos", "Correo", "Estado", "Tipo", "Extra"}, 0);
			JTable table = new JTable(tableModel) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(40);
			columnModel.getColumn(1).setPreferredWidth(120);
			columnModel.getColumn(2).setPreferredWidth(120);
			columnModel.getColumn(3).setPreferredWidth(180);
			
			table.getTableHeader().setReorderingAllowed(false);
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
			btnVolver.setBounds(340, 440, 100, 30);
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
		switch (evento) {
		case Evento.RES_MOSTRAR_TODOS_LOS_CLIENTES_OK:
			tableModel.setRowCount(0);
			@SuppressWarnings("unchecked")
			List<TCliente> listaClientes = (List<TCliente>) datos;
			
			if (listaClientes != null) {
				for (TCliente c : listaClientes) {
					String tipo = "Normal";
					String extra = "-";
					
					if (c instanceof TVip) {
						tipo = "VIP";
						extra = "Desc: " + ((TVip) c).getDescuento() + "%";
					} else if (c instanceof TNormal) {
						tipo = "Normal";
						extra = "Copias: " + ((TNormal) c).getNumCopias();
					}

					Object[] fila = {
						c.getId(),
						c.getNombre(),
						c.getApellidos(),
						c.getCorreo(),
						tipo,
						extra
					};
					tableModel.addRow(fila);
				}
			}
			break;

		case Evento.RES_MOSTRAR_TODOS_LOS_CLIENTES_KO:
			tableModel.setRowCount(0);
			String msg = (datos != null) ? datos.toString() : "Error al cargar los clientes.";
			JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
}