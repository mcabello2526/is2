package Presentacion.GUITiendaPelis;

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

public class GUITiendaPelis extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnCliente;
	private JButton btnGenero;
	private JButton btnNacionalidad;
	private JButton btnPelicula;
	private JButton btnProductora;
	private JButton btnTrabajador;
	private JButton btnVenta;
	private JButton btnCerrar;
	
	private Color fondo = new Color(225, 245, 254);
	private Color botones = new Color(2, 87, 155);
	private Color texto = Color.WHITE;
	private Color btnRojo = new Color(211, 47, 47);
	//mejor BOLD a PLAIN -- para ver el texto gordo
	
	
	public GUITiendaPelis() {
		setTitle("TiendaPelis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(fondo);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		contentPane.add(getLblTitulo()); 
		contentPane.add(getBtnCliente());
		contentPane.add(getBtnGenero());
		contentPane.add(getBtnNacionalidad());
		contentPane.add(getBtnPelicula());
		contentPane.add(getBtnProductora());
		contentPane.add(getBtnTrabajador());
		contentPane.add(getBtnVenta());
		contentPane.add(getBtnCerrar());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLblTitulo() {
		if(lblTitulo == null) {
			lblTitulo = new JLabel("TiendaPelis");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 40));
			//lblTitulo.setBackground(new Color(1, 87, 155));
			lblTitulo.setBounds(100, 30, 400, 60);
		}
		return lblTitulo;
	}
	
	private JButton getBtnCliente() {
		if(btnCliente == null) {
			btnCliente = new JButton("CLIENTES");
			btnCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnCliente.setBackground(botones);
			btnCliente.setForeground(texto);
			btnCliente.setBounds(80, 130, 200, 45);
			btnCliente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_CLIENTE, null);;
					
				}
			});
		}
		return btnCliente;
	}
	
	private JButton getBtnGenero() {
		if(btnGenero == null) {
			btnGenero = new JButton("GÉNEROS");
			btnGenero.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnGenero.setBackground(botones);
			btnGenero.setForeground(texto);
			btnGenero.setBounds(80, 190, 200, 45);
			btnGenero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_GENERO,  null);
				}
			});
		}
		return btnGenero;
	}
	
	private JButton getBtnNacionalidad() {
		if(btnNacionalidad == null) {
			btnNacionalidad = new JButton("NACIONALIDADES");
			btnNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnNacionalidad.setBackground(botones);
			btnNacionalidad.setForeground(texto);
			btnNacionalidad.setBounds(310, 190, 200, 45);
			btnNacionalidad.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_NACIONALIDAD,  null);;
				}
			});
		}
		return btnNacionalidad;
	}

	private JButton getBtnPelicula() {
		if(btnPelicula == null) {
			btnPelicula = new JButton("PELÍCULAS");
			btnPelicula.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnPelicula.setBackground(botones);
			btnPelicula.setForeground(texto);
			btnPelicula.setBounds(310, 130, 200, 45);
			btnPelicula.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_PELICULA, null);;
				}
			});
		}
		return btnPelicula;
	}
	
	private JButton getBtnProductora() {
		if(btnProductora == null) {
			btnProductora = new JButton("PRODUCTORAS");
			btnProductora.setFont(new Font ("Tahoma", Font.BOLD, 15));
			btnProductora.setBackground(botones);
			btnProductora.setForeground(texto);
			btnProductora.setBounds(80, 250, 200, 45);
			btnProductora.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_PRODUCTORA,  null);				
				}
			});
		}
		return btnProductora;
	}
	
	private JButton getBtnTrabajador() {
		if(btnTrabajador == null) {
			btnTrabajador = new JButton("TRABAJADORES");
			btnTrabajador.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnTrabajador.setBackground(botones);
			btnTrabajador.setForeground(texto);
			btnTrabajador.setBounds(310, 250, 200, 45);
			btnTrabajador.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_TRABAJADOR,  null);
				}
			});
		}
		return btnTrabajador;
	}
	
	private JButton getBtnVenta() {
		if(btnVenta == null) {
			btnVenta = new JButton("VENTAS");
			btnVenta.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnVenta.setBackground(botones);
			btnVenta.setForeground(texto);
			btnVenta.setBounds(180, 310, 220, 45);
			btnVenta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_VENTA,  null);
				}
			});
		}
		return btnVenta;
	}
	
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 15));
			
			btnCerrar.setForeground(texto);
			btnCerrar.setBackground(btnRojo);
			
			btnCerrar.setBounds(400, 400, 160, 45);
			btnCerrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
		}
		return btnCerrar;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.GUI_TIENDAPELIS:
			this.setVisible(true);
			break;
		default:
			break;
		}	
	}
}
