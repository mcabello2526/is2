package Presentacion.Venta;

import java.awt.Color;
import java.awt.Component;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton cancelar; 
	private JButton abrirVenta; 
	private JButton eliminarVenta; 
	private JButton mostrarVenta; 
	private JButton mostrarTodas; 
	private JButton ventasCliente; 
	private JButton ventasTrabajador; 
	private JButton hacerDevolucion;
	
	private JPanel contentPane; 
	
	private JLabel labelTitulo; 
	
	public GUIVenta() {
		setTitle("Ventas"); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100,100,661,368); 
		contentPane = new JPanel(); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(209, 239, 238));
        contentPane.setLayout(null);
        contentPane.add(getLblTitulo()); 
        
        setContentPane(contentPane); 
        contentPane.add(getBtnAbrir());
        contentPane.add(getBtnEliminar());
        contentPane.add(getBtnMostrar());
        contentPane.add(getBtnMostrarTodos());
        contentPane.add(getBtnCerrar());
        contentPane.add(getBtnVentaTrabajador());
        contentPane.add(getBtnVentaCliente());
        contentPane.add(getBtnHacerDevolucion());


        this.setVisible(true);
		
	}

	private JButton getBtnHacerDevolucion() {
		if (hacerDevolucion == null) {
			hacerDevolucion = new JButton("Hacer Devolución");
			hacerDevolucion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_HACER_DEVOLUCION, null);
				}
			});
			hacerDevolucion.setBounds(35, 110, 166, 29);
			
		}
		return hacerDevolucion;
	}

	private JButton getBtnVentaCliente() {
		if (ventasCliente== null) {
			ventasCliente = new JButton("Ver por cliente");
			ventasCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_MOSTRAR_VENTAS_POR_CLIENTE, null);
				}
			});
			ventasCliente.setBounds(47, 150, 166, 29);
		}
		return ventasCliente;
	}

	private JButton getBtnVentaTrabajador() {
		if (ventasTrabajador == null) {
			ventasTrabajador = new JButton("Ver por trabajador");
			ventasTrabajador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_MOSTRAR_VENTAS_POR_TRABAJADOR, null);
				}
			});
			ventasTrabajador.setBounds(47, 150, 166, 29);
		}
		return ventasTrabajador;
	}

	private JButton getBtnCerrar() {
        if (cancelar == null) {
        	cancelar = new JButton("Cancelar");
        	cancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
        	cancelar.setForeground(Color.WHITE);
        	cancelar.setBackground(Color.RED);
        	cancelar.setBounds(215, 284, 129, 29);
        	cancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	dispose();
                }
            });
        }
        return cancelar;
	}

	private Component getBtnMostrarTodos() {
        if (mostrarTodas == null) {
        	mostrarTodas = new JButton("Ver todas");
        	mostrarTodas.setBounds(453, 110, 129, 29);
        	mostrarTodas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_VENTA, null);
                }
            });
        }
        return mostrarTodas;
	}

	private JButton getBtnMostrar() {
        if (mostrarVenta == null) {
        	mostrarVenta = new JButton("Ver Venta");
        	mostrarVenta.setBounds(433, 70, 166, 29);
        	mostrarVenta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Controlador.getInstance().accion(Evento.GUI_MOSTRAR_VENTA, null);
                }
            });
        }
        return mostrarVenta;
	}

	private JButton getBtnEliminar() {
		if (eliminarVenta== null) {
			eliminarVenta = new JButton("Eliminar Venta");
			eliminarVenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controlador.getInstance().accion(Evento.GUI_ELIMINAR_VENTA, null);
				}
			});
			eliminarVenta.setBounds(35, 110, 166, 29);
			
		}
		return eliminarVenta;
	}

	private JButton getBtnAbrir() {
	    if (abrirVenta == null) {
	    	abrirVenta = new JButton("Abrir Venta");
	    	abrirVenta.setBounds(25, 70, 155, 29);
	    	abrirVenta.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	            	  Controlador.getInstance().accion(Evento.GUI_ABRIR_VENTA, null);
	              }
	            });
	        }
		return abrirVenta;
	}

	private JLabel getLblTitulo() {
		if (labelTitulo == null) {
			labelTitulo = new JLabel("Ventas"); 
			labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitulo.setFont(new Font ("Rockwell", Font.BOLD, 35));
			labelTitulo.setBounds(184, 10, 195, 42);
		}

		return labelTitulo;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}
}
