package Presentacion.Productora;

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

public class GUIProductora extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnModificar;
	private JButton btnLeer;
	private JButton btnLeerPorNacionalidad;
	private JButton btnListar;
	private JButton btnVolver;


	public GUIProductora() {
		setTitle("Productora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(209, 239, 238));
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());

		setContentPane(contentPane);
		contentPane.add(getBtnAlta());
		contentPane.add(getBtnBaja());
		contentPane.add(getBtnModificar());
		contentPane.add(getBtnLeer());
		contentPane.add(getBtnListar());
		contentPane.add(getBtnListarPorNacionalidad());
		contentPane.add(getBtnVolver());
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Productora");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
			lblTitulo.setBounds(184, 10, 195, 42);
		}
		return lblTitulo;
	}
	private JButton getBtnAlta() {
		if (btnAlta == null) {
			btnAlta = new JButton("Añadir Productora");
			btnAlta.setBounds(66, 122, 155, 29);
			btnAlta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controlador.getInstance().accion(Evento.GUI_PRODUCTORA,null );
                }
            });
		}
		return btnAlta;
	}
	private JButton getBtnBaja() {
		if (btnBaja == null) {
			btnBaja = new JButton("Eliminar productora");
			btnBaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_ELIMINAR_PRODUCTORA,null );
				}
			});
			btnBaja.setBounds(332, 122, 169, 29);
		}
		return btnBaja;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar Productora");
			btnModificar.setBounds(65, 167, 156, 29);
			btnModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MODIFICAR_PRODUCTORA,null );
                }
            });
		}
		return btnModificar;
	}
	private JButton getBtnLeer() {
		if (btnLeer == null) {
			btnLeer = new JButton("Mostrar Productora");
			btnLeer.setBounds(335, 166, 166, 29);
			btnLeer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_PRODUCTORA,null );
                }
            });
		}
		return btnLeer;
	}
	private JButton getBtnListar() {
		if (btnListar == null) {
			btnListar = new JButton("Mostrar Todas");
			btnListar.setBounds(214, 216, 129, 29);
			btnListar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_TODAS_LAS_PRODUCTORAS,null );
                }
            });
		}
		return btnListar;
	}
	
	private JButton getBtnListarPorNacionalidad() {
		if (btnLeerPorNacionalidad == null) {
			btnLeerPorNacionalidad = new JButton("Mostrar Todas Por Nacionalidad");
			btnLeerPorNacionalidad.setBounds(214, 216, 129, 29);
			btnLeerPorNacionalidad.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_PRODUCTORA_POR_NACIONALIDAD,null );
                }
            });
		}
		return btnListar;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnVolver.setForeground(new Color(255, 255, 255));
			btnVolver.setBackground(new Color(255, 0, 0));
			btnVolver.setBounds(215, 284, 129, 29);
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

