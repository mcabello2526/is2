package Presentacion.Venta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

public class GUIVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnAbrirVenta;
	private JButton btnEliminarVenta;
	private JButton btnMostrarVenta;
	private JButton btnMostrarTodas;
	private JButton btnVentasCliente;
	private JButton btnVentasTrabajador;
	private JButton btnHacerDevolucion;
	private JButton btnVolver;

	private Color fondo = new Color(225, 245, 254);
	private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);

	public GUIVenta() {
		setTitle("Ventas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 550); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		contentPane.setLayout(null);

		contentPane.add(getLblTitulo());
		setContentPane(contentPane);

		contentPane.add(getBtnAbrirVenta());
		contentPane.add(getBtnEliminarVenta());
		contentPane.add(getBtnHacerDevolucion());
		contentPane.add(getBtnMostrarVenta());
		contentPane.add(getBtnMostrarTodas());
		contentPane.add(getBtnVentasCliente());
		contentPane.add(getBtnVentasTrabajador());
		contentPane.add(getBtnVolver());

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("VENTAS");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(10, 20, 464, 50);
		}
		return lblTitulo;
	}

	private JButton getBtnAbrirVenta() {
		if (btnAbrirVenta == null) {
			btnAbrirVenta = new JButton("Abrir Venta");
			btnAbrirVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAbrirVenta.setBackground(botones);
			btnAbrirVenta.setForeground(textoBlanco);
			btnAbrirVenta.setBounds(100, 80, 300, 35);
			btnAbrirVenta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_ABRIR_VENTA, null);
				}
			});
		}
		return btnAbrirVenta;
	}

	private JButton getBtnEliminarVenta() {
		if (btnEliminarVenta == null) {
			btnEliminarVenta = new JButton("Eliminar Venta");
			btnEliminarVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnEliminarVenta.setBackground(botones);
			btnEliminarVenta.setForeground(textoBlanco);
			btnEliminarVenta.setBounds(100, 120, 300, 35);
			btnEliminarVenta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_ELIMINAR_VENTA, null);
				}
			});
		}
		return btnEliminarVenta;
	}

	private JButton getBtnHacerDevolucion() {
		if (btnHacerDevolucion == null) {
			btnHacerDevolucion = new JButton("Hacer Devolución");
			btnHacerDevolucion.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnHacerDevolucion.setBackground(botones);
			btnHacerDevolucion.setForeground(textoBlanco);
			btnHacerDevolucion.setBounds(100, 160, 300, 35);
			btnHacerDevolucion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_HACER_DEVOLUCION, null);
				}
			});
		}
		return btnHacerDevolucion;
	}

	private JButton getBtnMostrarVenta() {
		if (btnMostrarVenta == null) {
			btnMostrarVenta = new JButton("Mostrar Venta");
			btnMostrarVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrarVenta.setBackground(botones);
			btnMostrarVenta.setForeground(textoBlanco);
			btnMostrarVenta.setBounds(100, 210, 300, 35);
			btnMostrarVenta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_MOSTRAR_VENTA, null);
				}
			});
		}
		return btnMostrarVenta;
	}

	private JButton getBtnMostrarTodas() {
		if (btnMostrarTodas == null) {
			btnMostrarTodas = new JButton("Mostrar todas las ventas");
			btnMostrarTodas.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrarTodas.setBackground(botones);
			btnMostrarTodas.setForeground(textoBlanco);
			btnMostrarTodas.setBounds(100, 250, 300, 35);
			btnMostrarTodas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_MOSTRAR_TODAS_LAS_VENTAS, null);
				}
			});
		}
		return btnMostrarTodas;
	}

	private JButton getBtnVentasCliente() {
		if (btnVentasCliente == null) {
			btnVentasCliente = new JButton("Ver ventas por cliente");
			btnVentasCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnVentasCliente.setBackground(botones);
			btnVentasCliente.setForeground(textoBlanco);
			btnVentasCliente.setBounds(100, 290, 300, 35);
			btnVentasCliente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_MOSTRAR_VENTAS_POR_CLIENTE, null);
				}
			});
		}
		return btnVentasCliente;
	}

	private JButton getBtnVentasTrabajador() {
		if (btnVentasTrabajador == null) {
			btnVentasTrabajador = new JButton("Ver ventas por trabajador");
			btnVentasTrabajador.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnVentasTrabajador.setBackground(botones);
			btnVentasTrabajador.setForeground(textoBlanco);
			btnVentasTrabajador.setBounds(100, 330, 300, 35);
			btnVentasTrabajador.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_MOSTRAR_VENTAS_POR_TRABAJADOR, null);
				}
			});
		}
		return btnVentasTrabajador;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(185, 420, 130, 40);
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
	}
}