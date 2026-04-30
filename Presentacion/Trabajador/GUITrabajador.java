package Presentacion.Trabajador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.*;

import javax.swing.JButton;

public class GUITrabajador extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnMostrar;
	private JButton btnMostrarTodos;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	//private Color fondoGris = new Color(255, 255, 255);
	private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	//private Color btnVerde = new Color(134, 231, 120);
	
	public GUITrabajador() {
		setTitle("Trabajador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(fondo);
		contentPane.setLayout(null);
		
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		
		contentPane.add(getBtnCrear());
		contentPane.add(getBtnEliminar());
		contentPane.add(getBtnModificar());
		contentPane.add(getBtnMostrar());
		contentPane.add(getBtnMostrarTodos());
		contentPane.add(getBtnVolver());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("TRABAJADORES");
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(10, 20, 464, 50);
		}
		return lblTitulo;
	}
	
	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Añadir Trabajador");
			btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCrear.setBackground(botones);
			btnCrear.setForeground(textoBlanco);
			btnCrear.setBounds(100, 80, 300, 35);
			btnCrear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controlador.getInstance().accion(Evento.GUI_CREAR_TRABAJADOR,null );
                }
            });
		}
		return btnCrear;
	}
	
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar Trabajador");
			btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnEliminar.setBackground(botones);
			btnEliminar.setForeground(textoBlanco);
			btnEliminar.setBounds(100, 120, 300, 35);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_ELIMINAR_TRABAJADOR,null );
				}
			});
		}
		return btnEliminar;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar Trabajador");
			btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnModificar.setBackground(botones);
			btnModificar.setForeground(textoBlanco);
			btnModificar.setBounds(100, 160, 300, 35);
			btnModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MODIFICAR_TRABAJADOR,null );
                }
            });
		}
		return btnModificar;
	}

	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("Mostrar Trabajador");
			btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrar.setBackground(botones);
			btnMostrar.setForeground(textoBlanco);
			btnMostrar.setBounds(100, 210, 300, 35);				btnMostrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_TRABAJADOR,null );
                }
            });
		}
		return btnMostrar;
	}
	private JButton getBtnMostrarTodos() {
		if (btnMostrarTodos == null) {
			btnMostrarTodos = new JButton("Mostrar todos los trabajadores");
			btnMostrarTodos.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrarTodos.setBackground(botones);
			btnMostrarTodos.setForeground(textoBlanco);
			btnMostrarTodos.setBounds(100, 250, 300, 35);			
			btnMostrarTodos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_TODOS_LOS_TRABAJADORES,null );
                }
            });
		}
		return btnMostrarTodos;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(185, 320, 130, 40);
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
