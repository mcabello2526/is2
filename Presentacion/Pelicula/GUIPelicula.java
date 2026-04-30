package Presentacion.Pelicula;

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

public class GUIPelicula extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnMostrar;
	private JButton btnMostrarPorNombre;
	private JButton btnMostrarPorGenero;
	private JButton btnMostrarPorProductora;
	private JButton btnMostrarTodas;
	private JButton btnVincularAGenero;
	private JButton btnDesvincularAGenero;
	private JButton btnVolver;
	
	private Color fondo = new Color(225, 245, 254);
	//private Color fondoGris = new Color(255, 255, 255);
	private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	//private Color btnVerde = new Color(134, 231, 120);

 	public GUIPelicula() {
		setTitle("Películas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 650);
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
		contentPane.add(getBtnMostrarPorNombre());
		contentPane.add(getBtnMostrarPorGenero());
		contentPane.add(getBtnMostrarPorProductora());
		contentPane.add(getBtnMostrarTodas());
		contentPane.add(getBtnVincularAGenero());
		contentPane.add(getBtnDesvincularAGenero());
		contentPane.add(getBtnVolver());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("PELÍCULAS");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setForeground(textoNegro);
			lblTitulo.setBounds(10, 20, 464, 50);
		}
		return lblTitulo;
	}
	
	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Añadir película");
			btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCrear.setBackground(botones);
			btnCrear.setForeground(textoBlanco);
			btnCrear.setBounds(100, 80, 300, 35);
			btnCrear.addActionListener(new ActionListener() {
               @Override
                public void actionPerformed(ActionEvent e) {
                    Controlador.getInstance().accion(Evento.GUI_CREAR_PELICULA,null );
                }
            });
		}
		return btnCrear;
	}
	
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar película");
			btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnEliminar.setBackground(botones);
			btnEliminar.setForeground(textoBlanco);
			btnEliminar.setBounds(100, 120, 300, 35);
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_ELIMINAR_PELICULA,null );
				}
			});
		}
		return btnEliminar;
	}
	
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar película");
			btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnModificar.setBackground(botones);
			btnModificar.setForeground(textoBlanco);
			btnModificar.setBounds(100, 160, 300, 35);
			btnModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MODIFICAR_PELICULA,null );
                }
            });
		}
		return btnModificar;
	}
	
	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("Mostrar película (por ID)");
			btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrar.setBackground(botones);
			btnMostrar.setForeground(textoBlanco);
			btnMostrar.setBounds(100, 210, 300, 35);
			btnMostrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_PELICULA,null );
                }
            });
		}
		return btnMostrar;
	}
	
	private JButton getBtnMostrarPorNombre() {
		if (btnMostrarPorNombre == null) {
			btnMostrarPorNombre = new JButton("Mostrar película por nombre");
			btnMostrarPorNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrarPorNombre.setBackground(botones);
			btnMostrarPorNombre.setForeground(textoBlanco);
			btnMostrarPorNombre.setBounds(100, 250, 300, 35);
			btnMostrarPorNombre.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_PELICULA_POR_NOMBRE,null );
                }
            });
		}
		return btnMostrarPorNombre;
	}
	
	private JButton getBtnMostrarPorGenero() {
		if (btnMostrarPorGenero == null) {
			btnMostrarPorGenero = new JButton("Mostrar película por género");
			btnMostrarPorGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrarPorGenero.setBackground(botones);
			btnMostrarPorGenero.setForeground(textoBlanco);
			btnMostrarPorGenero.setBounds(100, 290, 300, 35);
			btnMostrarPorGenero.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_PELICULAS_POR_GENERO,null );
                }
            });
		}
		return btnMostrarPorGenero;
	}
	
	private JButton getBtnMostrarPorProductora() {
		if (btnMostrarPorProductora == null) {
			btnMostrarPorProductora = new JButton("Mostrar películas por productora");
			btnMostrarPorProductora.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrarPorProductora.setBackground(botones);
			btnMostrarPorProductora.setForeground(textoBlanco);
			btnMostrarPorProductora.setBounds(100, 330, 300, 35);
			btnMostrarPorProductora.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_PELICULAS_POR_PRODUCTORA,null );
                }
            });
		}
		return btnMostrarPorProductora;
	}
	
	private JButton getBtnMostrarTodas() {
		if (btnMostrarTodas == null) {
			btnMostrarTodas = new JButton("Mostrar todas las películas");
			btnMostrarTodas.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnMostrarTodas.setBackground(botones);
			btnMostrarTodas.setForeground(textoBlanco);
			btnMostrarTodas.setBounds(100, 370, 300, 35);
			btnMostrarTodas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_TODAS_LAS_PELICULAS,null );
                }
            });
		}
		return btnMostrarTodas;
	}
	
	private JButton getBtnVincularAGenero() {
		if (btnVincularAGenero == null) {
			btnVincularAGenero = new JButton("Vincular película a género");
			btnVincularAGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnVincularAGenero.setBackground(botones);
			btnVincularAGenero.setForeground(textoBlanco);
			btnVincularAGenero.setBounds(100, 420, 300, 35);
			btnVincularAGenero.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_VINCULAR_PELICULA_A_GENERO,null );
                }
            });
		}
		return btnVincularAGenero;
	}
	
	private JButton getBtnDesvincularAGenero() {
		if (btnDesvincularAGenero == null) {
			btnDesvincularAGenero = new JButton("Desvincular película de género");
			btnDesvincularAGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnDesvincularAGenero.setBackground(botones);
			btnDesvincularAGenero.setForeground(textoBlanco);
			btnDesvincularAGenero.setBounds(100, 460, 300, 35);
			btnDesvincularAGenero.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_DESVINCULAR_PELICULA_A_GENERO,null );
                }
            });
		}
		return btnDesvincularAGenero;
	}
	
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnVolver.setForeground(textoBlanco);
			btnVolver.setBackground(btnRojo);
			btnVolver.setBounds(185, 530, 130, 40);
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