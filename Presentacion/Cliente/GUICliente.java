package Presentacion.Cliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador; 

public class GUICliente extends JFrame implements ActionListener, IGUI {

	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnMostrar;
	private JButton btnMostrarTodos;

	public GUICliente() {
		super("Menú de Gestión de Clientes");
		initGUI();
	}

	private void initGUI() {
		JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		this.btnCrear = new JButton("Crear Cliente");
		this.btnEliminar = new JButton("Eliminar Cliente");
		this.btnModificar = new JButton("Modificar Cliente");
		this.btnMostrar = new JButton("Mostrar Cliente");
		this.btnMostrarTodos = new JButton("Mostrar Todos los Clientes");

		this.btnCrear.addActionListener(this);
		this.btnEliminar.addActionListener(this);
		this.btnModificar.addActionListener(this);
		this.btnMostrar.addActionListener(this);
		this.btnMostrarTodos.addActionListener(this);

		panel.add(this.btnCrear);
		panel.add(this.btnEliminar);
		panel.add(this.btnModificar);
		panel.add(this.btnMostrar);
		panel.add(this.btnMostrarTodos);

		this.add(panel);
		this.pack(); 
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.btnCrear) {
			Controlador.getInstance().accion(Evento.GUI_CREAR_CLIENTE, null);
			
		} else if (e.getSource() == this.btnEliminar) {
			Controlador.getInstance().accion(Evento.GUI_ELIMINAR_CLIENTE, null);
			
		} else if (e.getSource() == this.btnModificar) {
			Controlador.getInstance().accion(Evento.GUI_MODIFICAR_CLIENTE, null);
			
		} else if (e.getSource() == this.btnMostrar) {
			Controlador.getInstance().accion(Evento.GUI_MOSTRAR_CLIENTE, null);
			
		} else if (e.getSource() == this.btnMostrarTodos) {
			Controlador.getInstance().accion(Evento.GUI_MOSTRAR_TODOS_LOS_CLIENTES, null);
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_CREAR_CLIENTE_KO ||
				evento == Evento.RES_ELIMINAR_CLIENTE_KO ||
				evento == Evento.RES_MODIFICAR_CLIENTE_KO ||
				evento == Evento.RES_MOSTRAR_CLIENTE_KO ||
				evento == Evento.RES_MOSTRAR_TODOS_LOS_CLIENTES_KO) {
				
				String error = (datos != null) ? (String) datos : "Error en el módulo Cliente.";
				
				javax.swing.JOptionPane.showMessageDialog(this, error, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
			}
	}
}