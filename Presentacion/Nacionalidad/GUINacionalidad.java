package Presentacion.Nacionalidad;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Presentacion.Evento; 
import Presentacion.Controlador.ControladorImp;
import Presentacion.IGUI;

public class GUINacionalidad extends JFrame implements ActionListener, IGUI {

	// Declaramos un botón por cada ventana
	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnMostrar;
	private JButton btnMostrarTodos;

	public GUINacionalidad() {
		super("Menú de Gestión de Nacionalidades");
		initGUI();
	}

	private void initGUI() {
	
		JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Inicializamos todos los botones
		this.btnCrear = new JButton("Crear Nacionalidad");
		this.btnEliminar = new JButton("Eliminar Nacionalidad");
		this.btnModificar = new JButton("Modificar Nacionalidad");
		this.btnMostrar = new JButton("Mostrar Nacionalidad");
		this.btnMostrarTodos = new JButton("Mostrar Todos las Nacionalidades");

		
		this.btnCrear.addActionListener(this);
		this.btnEliminar.addActionListener(this);
		this.btnModificar.addActionListener(this);
		this.btnMostrar.addActionListener(this);
		this.btnMostrarTodos.addActionListener(this);

		// Añadimos los botones al panel
		panel.add(this.btnCrear);
		panel.add(this.btnEliminar);
		panel.add(this.btnModificar);
		panel.add(this.btnMostrar);
		panel.add(this.btnMostrarTodos);

		// Añadimos el panel a la ventana y la configuramos
		this.add(panel);
		this.pack(); // Ajusta el tamaño de la ventana automáticamente al contenido
		this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al darle a la X
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Dependiendo de qué botón se haya pulsado, mandamos el evento correspondiente
		
		
		if (e.getSource() == this.btnCrear) {
			ControladorImp.getInstance().accion(Evento.GUI_CREAR_NACIONALIDAD, null);
			
		} else if (e.getSource() == this.btnEliminar) {
			ControladorImp.getInstance().accion(Evento.GUI_ELIMINAR_NACIONALIDAD, null);
			
		} else if (e.getSource() == this.btnModificar) {
			ControladorImp.getInstance().accion(Evento.GUI_MODIFICAR_NACIONALIDAD, null);
			
		} else if (e.getSource() == this.btnMostrar) {
			ControladorImp.getInstance().accion(Evento.GUI_MOSTRAR_NACIONALIDAD, null);
			
		} else if (e.getSource() == this.btnMostrarTodos) {
			ControladorImp.getInstance().accion(Evento.GUI_MOSTRAR_TODAS_LAS_NACIONALIDADES, null);
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
	
		if (evento == Evento.RES_CREAR_NACIONALIDAD_KO ||
		        evento == Evento.RES_ELIMINAR_NACIONALIDAD_KO ||
		        evento == Evento.RES_MODIFICAR_NACIONALIDAD_KO ||
		        evento == Evento.RES_MOSTRAR_NACIONALIDAD_KO ||
		        evento == Evento.RES_MOSTRAR_TODAS_LAS_NACIONALIDADES_KO) {
			
			String error = (datos != null) ? (String) datos : "Error desconocido en el módulo Nacionalidades.";
			JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
