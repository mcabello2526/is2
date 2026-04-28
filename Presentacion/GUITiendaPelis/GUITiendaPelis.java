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
	private JLabel labelTitulo;
	private JButton buttonCliente;
	private JButton buttonGenero;
	private JButton buttonNacionalidad;
	private JButton buttonPelicula;
	private JButton buttonProductora;
	private JButton buttonTrabajador;
	private JButton buttonVenta;
	
	
	public GUITiendaPelis() {
		setTitle("TiendaPelis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 520);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 150, 238)); //////
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); ///////
		contentPane.setLayout(null);
		setContentPane(contentPane);
		//contentPane.setLayout(null);;
		contentPane.add(getLabelTitulo()); 
		contentPane.add(getButtonCliente());
		contentPane.add(getButtonGenero());
		contentPane.add(getButtonNacionalidad());
		contentPane.add(getButtonPelicula());
		contentPane.add(getButtonProductora());
		contentPane.add(getButtonTrabajador());
		contentPane.add(getButtonVenta());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JLabel getLabelTitulo() {
		if(labelTitulo == null) {
			labelTitulo = new JLabel("TiendaPelis");
			labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 40));
			labelTitulo.setBackground(Color.WHITE); //new Color(255,255,255)
			labelTitulo.setBounds(100, 30, 400, 60);
		}
		return labelTitulo;
	}
	
	private JButton getButtonCliente() {
		if(buttonCliente == null) {
			buttonCliente = new JButton("CLIENTES");
			buttonCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
			buttonCliente.setBackground(Color.WHITE); //(new Color(255, 255, 255));
			buttonCliente.setBounds(80, 130, 200, 45);
			buttonCliente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_CLIENTE, null);;
					
				}
			});
		}
		return buttonCliente;
	}
	
	private JButton getButtonGenero() {
		if(buttonGenero == null) {
			buttonGenero = new JButton("GÉNEROS");
			buttonGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
			buttonGenero.setBackground(Color.WHITE);//(new Color(255, 255, 255));
			buttonGenero.setBounds(80, 190, 200, 45);
			buttonGenero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_GENERO,  null);
				}
			});
		}
		return buttonGenero;
	}
	
	private JButton getButtonNacionalidad() {
		if(buttonNacionalidad == null) {
			buttonNacionalidad = new JButton("NACIONALIDADES");
			buttonNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
			buttonNacionalidad.setBackground(Color.WHITE); //(new Color(255, 255, 255));
			buttonNacionalidad.setBounds(310, 190, 200, 45);
			buttonNacionalidad.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_NACIONALIDAD,  null);;
				}
			});
		}
		return buttonNacionalidad;
	}

	private JButton getButtonPelicula() {
		if(buttonPelicula == null) {
			buttonPelicula = new JButton("PELÍCULAS");
			buttonPelicula.setFont(new Font("Tahoma", Font.PLAIN, 14));
			buttonPelicula.setBackground(Color.WHITE); //(new Color(255, 255, 255));
			buttonPelicula.setBounds(310, 130, 200, 45);
			buttonPelicula.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_PELICULA, null);;
				}
			});
		}
		return buttonPelicula;
	}
	
	private JButton getButtonProductora() {
		if(buttonProductora == null) {
			buttonProductora = new JButton("PRODUCTORAS");
			buttonProductora.setFont(new Font ("Tahoma", Font.PLAIN, 14));
			buttonProductora.setBackground(Color.WHITE); //(new Color(255, 255, 255));
			buttonProductora.setBounds(80, 250, 200, 45);
			buttonProductora.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_PRODUCTORA,  null);				
				}
			});
		}
		return buttonProductora;
	}
	
	private JButton getButtonTrabajador() {
		if(buttonTrabajador == null) {
			buttonTrabajador = new JButton("TRABAJADORES");
			buttonTrabajador.setFont(new Font("Tahoma", Font.PLAIN, 15));
			buttonTrabajador.setBackground(Color.WHITE); //(new Color(255, 255, 255));
			buttonTrabajador.setBounds(310, 250, 200, 45);
			buttonTrabajador.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_TRABAJADOR,  null);
				}
			});
		}
		return buttonTrabajador;
	}
	
	private JButton getButtonVenta() {
		if(buttonVenta == null) {
			buttonVenta = new JButton("VENTAS");
			buttonVenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			buttonVenta.setBackground(Color.WHITE);//(new Color(255, 255, 200));
			buttonVenta.setBounds(150, 340, 300, 60);
			buttonVenta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_VENTA,  null);
				}
			});
		}
		return buttonVenta;
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
