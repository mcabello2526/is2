package Presentacion.Genero;

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

public class GUIGenero extends JFrame implements ActionListener, IGUI {

	// Declaramos un botón por cada ventana
	private JButton btnCrear;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnMostrar;
	private JButton btnMostrarPorPelicula;
	private JButton btnMostrarTodos;

	public GUIGenero() {
		super("Menú de Gestión de Géneros");
		initGUI();
	}

	private void initGUI() {
	
		JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Inicializamos todos los botones
		this.btnCrear = new JButton("Crear Género");
		this.btnEliminar = new JButton("Eliminar Género");
		this.btnModificar = new JButton("Modificar Género");
		this.btnMostrar = new JButton("Mostrar Género");
		this.btnMostrarPorPelicula = new JButton("Mostrar Géneros por Película");
		this.btnMostrarTodos = new JButton("Mostrar Todos los Géneros");

		
		this.btnCrear.addActionListener(this);
		this.btnEliminar.addActionListener(this);
		this.btnModificar.addActionListener(this);
		this.btnMostrar.addActionListener(this);
		this.btnMostrarPorPelicula.addActionListener(this);
		this.btnMostrarTodos.addActionListener(this);

		// Añadimos los botones al panel
		panel.add(this.btnCrear);
		panel.add(this.btnEliminar);
		panel.add(this.btnModificar);
		panel.add(this.btnMostrar);
		panel.add(this.btnMostrarPorPelicula);
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
			ControladorImp.getInstance().accion(Evento.GUI_CREAR_GENERO, null);
			
		} else if (e.getSource() == this.btnEliminar) {
			ControladorImp.getInstance().accion(Evento.GUI_ELIMINAR_GENERO, null);
			
		} else if (e.getSource() == this.btnModificar) {
			ControladorImp.getInstance().accion(Evento.GUI_MODIFICAR_GENERO, null);
			
		} else if (e.getSource() == this.btnMostrar) {
			ControladorImp.getInstance().accion(Evento.GUI_MOSTRAR_GENERO, null);
			
		} else if (e.getSource() == this.btnMostrarPorPelicula) {
			ControladorImp.getInstance().accion(Evento.GUI_MOSTRAR_GENEROS_POR_PELICULA, null);
			
		} else if (e.getSource() == this.btnMostrarTodos) {
			ControladorImp.getInstance().accion(Evento.GUI_MOSTRAR_TODOS_LOS_GENEROS, null);
		}
	}

	@Override
	public void actualizar(int evento, Object datos) {
	
		if (evento < 0) {
			String error = (datos != null) ? (String) datos : "Error desconocido en el módulo Géneros.";
			JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}