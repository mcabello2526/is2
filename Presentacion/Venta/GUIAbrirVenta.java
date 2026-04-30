package Presentacion.Venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import Negocio.Venta.TVenta;
import Negocio.Venta.TVentaPelicula;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

public class GUIAbrirVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	
	private final Map<Integer, TVentaPelicula> carrito = new HashMap<>();
	private JPanel contentPane;
	private JTextField txtIdCliente, txtIdTrabajador;
	private DefaultTableModel tableModel;
	private JLabel lblTitulo, lblTotal;
	private JButton btnAnadir, btnEliminar, btnVaciar, btnFinalizar, btnVolver;

	private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	private Color botonesAzules = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

	public GUIAbrirVenta() {
		setTitle("Abrir Venta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblTitulo = new JLabel("Abrir venta");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
		lblTitulo.setForeground(textoNegro);
		lblTitulo.setBounds(10, 10, 664, 50);
		contentPane.add(lblTitulo);

		JLabel lblIdC = new JLabel("ID Cliente:");
		lblIdC.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdC.setBounds(50, 75, 80, 25);
		contentPane.add(lblIdC);

		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(135, 75, 100, 25);
		contentPane.add(txtIdCliente);

		JLabel lblIdT = new JLabel("ID Trabajador:");
		lblIdT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdT.setBounds(270, 75, 100, 25);
		contentPane.add(lblIdT);

		txtIdTrabajador = new JTextField();
		txtIdTrabajador.setBounds(375, 75, 100, 25);
		contentPane.add(txtIdTrabajador);

		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new LineBorder(Color.BLACK, 2));
		panelTabla.setBackground(fondoGris);
		panelTabla.setBounds(50, 120, 580, 250);
		panelTabla.setLayout(new BorderLayout());

		tableModel = new DefaultTableModel(new Object[] { "ID Película", "Cantidad" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { return false; }
		};
		JTable tabla = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(tabla);
		panelTabla.add(scroll, BorderLayout.CENTER);
		contentPane.add(panelTabla);

		lblTotal = new JLabel("Líneas en carrito: 0");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(50, 380, 250, 20);
		contentPane.add(lblTotal);

		btnAnadir = crearBotonStyle("Añadir Película", 50, 420, 180, 35, botonesAzules);
		btnAnadir.addActionListener(e -> new GUIAniadirPeliculaACarrito(this));
		contentPane.add(btnAnadir);

		btnEliminar = crearBotonStyle("Quitar Película", 250, 420, 180, 35, botonesAzules);
		btnEliminar.addActionListener(e -> new GUIEliminarPeliculaDeCarrito(this));
		contentPane.add(btnEliminar);

		btnVaciar = crearBotonStyle("Vaciar Carrito", 450, 420, 180, 35, botonesAzules);
		btnVaciar.addActionListener(e -> {
			carrito.clear();
			refrescarTabla();
		});
		contentPane.add(btnVaciar);

		btnFinalizar = crearBotonStyle("FINALIZAR VENTA", 150, 480, 200, 40, btnVerde);
		btnFinalizar.addActionListener(e -> accionCerrarVenta());
		contentPane.add(btnFinalizar);

		btnVolver = crearBotonStyle("VOLVER", 370, 480, 150, 40, btnRojo);
		btnVolver.addActionListener(e -> dispose());
		contentPane.add(btnVolver);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JButton crearBotonStyle(String texto, int x, int y, int w, int h, Color bg) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn.setForeground(textoBlanco);
		btn.setBackground(bg);
		btn.setBounds(x, y, w, h);
		btn.setFocusPainted(false);
		btn.setBorder(new LineBorder(Color.DARK_GRAY));
		return btn;
	}

	public void aniadirAlCarrito(int idPelicula, int numCopias) {
		if (carrito.containsKey(idPelicula)) {
			TVentaPelicula linea = carrito.get(idPelicula);
			linea.setNumCopias(linea.getNumCopias() + numCopias);
		} else {
			carrito.put(idPelicula, new TVentaPelicula(idPelicula, numCopias));
		}
		refrescarTabla();
	}

	public void eliminarDelCarrito(int idPelicula, int numCopias) {
		if (carrito.containsKey(idPelicula)) {
			TVentaPelicula linea = carrito.get(idPelicula);
			int result = linea.getNumCopias() - numCopias;
			if (result <= 0) carrito.remove(idPelicula);
			else linea.setNumCopias(result);
			refrescarTabla();
		}
	}

	private void refrescarTabla() {
		tableModel.setRowCount(0);
		for (TVentaPelicula vp : carrito.values()) {
			tableModel.addRow(new Object[] { vp.getIdPelicula(), vp.getNumCopias() });
		}
		lblTotal.setText("Líneas en carrito: " + carrito.size());
	}

	private void accionCerrarVenta() {
		try {
			int idC = Integer.parseInt(txtIdCliente.getText().trim());
			int idT = Integer.parseInt(txtIdTrabajador.getText().trim());

			if (carrito.isEmpty()) {
				JOptionPane.showMessageDialog(this, "El carrito está vacío.");
				return;
			}

			TVenta venta = new TVenta();
			venta.setIdCliente(idC);
			venta.setIdTrabajador(idT);
			venta.setLineaCarrito(new ArrayList<>(carrito.values()));

			Controlador.getInstance().accion(Evento.CERRAR_VENTA, venta);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Los IDs deben ser numéricos.");
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_CERRAR_VENTA_OK) {
			JOptionPane.showMessageDialog(this, "Venta realizada. ID: " + datos);
			dispose();
		} else if (evento == Evento.RES_CERRAR_VENTA_KO) {
			JOptionPane.showMessageDialog(this, "Error: " + datos, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}