package Presentacion.Venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Venta.TVentaPelicula;
import Negocio.Venta.TVenta;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║  GUIAbrirVenta  –  Gestión del carrito de compra en memoria         ║
 * ╠══════════════════════════════════════════════════════════════════════╣
 * ║                                                                      ║
 * ║  ARQUITECTURA DEL CARRITO (GRUPO A – SIN TOCAR LA BD)               ║
 * ║  ─────────────────────────────────────────────────────────────────  ║
 * ║  • El carrito se almacena en: Map<Integer, TLineaVenta>              ║
 * ║    Clave:  ID de la película                                         ║
 * ║    Valor:  TLineaVenta (idPelicula + numCopias)                      ║
 * ║                                                                      ║
 * ║  • Añadir/Eliminar película modifica SOLO este Map en memoria.       ║
 * ║    No se valida si la película existe ni si hay stock.               ║
 * ║                                                                      ║
 * ║  CERRAR VENTA (GRUPO B – TOCA LA BD)                                 ║
 * ║  ─────────────────────────────────────────────────────────────────  ║
 * ║  Al pulsar "Cerrar Venta" se:                                         ║
 * ║    1. Construye un TVenta con idCliente e idTrabajador del formulario ║
 * ║    2. Convierte el Map a List<TVentaPelicula> y lo adjunta al TVenta  ║
 * ║    3. Envía el evento CERRAR_VENTA al Controlador                    ║
 * ║    4. Toda validación (cliente, trabajador, stock) ocurre en SAVenta  ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 */
public class GUIAbrirVenta extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;

    // ─── Estado del carrito (Grupo A – solo en memoria) ──────────────────────
    /**
     * Estructura central del carrito.
     * Clave   = ID de la película (Integer)
     * Valor   = TLineaVenta con idPelicula y numCopias
     *
     * Se usa HashMap para acceso O(1) al actualizar/eliminar entradas.
     */
    private final Map<Integer, TVentaPelicula> carrito = new HashMap<>();

    // ─── Componentes de la vista ──────────────────────────────────────────────
    private JTextField txtIdCliente;
    private JTextField txtIdTrabajador;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;

    // ─── Constructor ──────────────────────────────────────────────────────────

    public GUIAbrirVenta() {
        super("Abrir Venta – Gestión del Carrito");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGUI();
    }

    // ─── Construcción de la interfaz ──────────────────────────────────────────

    private void initGUI() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(209, 239, 238));

        // ── Panel NORTE: datos de cabecera ─────────────────────────────────
        JPanel panelCabecera = new JPanel(new GridBagLayout());
        panelCabecera.setBackground(new Color(180, 220, 230));
        panelCabecera.setBorder(BorderFactory.createTitledBorder("Datos de la Venta"));
        panelCabecera.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 6));

        panelCabecera.add(new JLabel("ID Cliente:"));
        txtIdCliente = new JTextField(8);
        panelCabecera.add(txtIdCliente);

        panelCabecera.add(new JLabel("ID Trabajador:"));
        txtIdTrabajador = new JTextField(8);
        panelCabecera.add(txtIdTrabajador);

        add(panelCabecera, BorderLayout.NORTH);

        // ── Panel CENTRO: tabla del carrito ────────────────────────────────
        String[] columnas = {"ID Película", "Nº Copias", "Precio Película"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
  
			private static final long serialVersionUID = 1L;

			@Override public boolean isCellEditable(int row, int col) { return false; }
        };
        
        JTable tablaCarrito = new JTable(modeloTabla);
        tablaCarrito.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tablaCarrito.setRowHeight(22);
        tablaCarrito.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tablaCarrito);
        scroll.setBorder(BorderFactory.createTitledBorder("Carrito de la Venta"));
        scroll.setPreferredSize(new Dimension(500, 200));
        add(scroll, BorderLayout.CENTER);

        // ── Panel SUR: botones de acción ───────────────────────────────────
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setBackground(new Color(209, 239, 238));

        // Total
        lblTotal = new JLabel("Artículos en carrito: 0 líneas", JLabel.CENTER);
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelSur.add(lblTotal, BorderLayout.NORTH);

        // Botones de carrito (Grupo A – solo memoria)
        JPanel panelBotonesCarrito = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        panelBotonesCarrito.setBackground(new Color(209, 239, 238));

        JButton btnAnadir   = crearBoton("➕ Añadir Película",   new Color(60, 160, 80));
        JButton btnEliminar = crearBoton("➖ Eliminar Película",  new Color(210, 100, 60));
        JButton btnLimpiar  = crearBoton("🗑 Vaciar Carrito",     new Color(150, 150, 150));

        panelBotonesCarrito.add(btnAnadir);
        panelBotonesCarrito.add(btnEliminar);
        panelBotonesCarrito.add(btnLimpiar);
        panelSur.add(panelBotonesCarrito, BorderLayout.CENTER);

        // Botones de cierre/cancelación (Grupo B – pasan al Controlador)
        JPanel panelBotonesVenta = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        panelBotonesVenta.setBackground(new Color(209, 239, 238));

        JButton btnCerrarVenta = crearBoton("✅ Cerrar Venta", new Color(30, 100, 200));
        btnCerrarVenta.setFont(new Font("Tahoma", Font.BOLD, 13));
        JButton btnCancelar = crearBoton("✖ Cancelar", Color.RED);

        panelBotonesVenta.add(btnCerrarVenta);
        panelBotonesVenta.add(btnCancelar);
        panelSur.add(panelBotonesVenta, BorderLayout.SOUTH);

        add(panelSur, BorderLayout.SOUTH);

        // ── Listeners ──────────────────────────────────────────────────────

        // GRUPO A: abre GUIAniadirPeliculaACarrito pasando 'this' como referencia
        btnAnadir.addActionListener(e -> new GUIAniadirPeliculaACarrito(this));

        // GRUPO A: abre GUIEliminarPeliculaDeCarrito pasando 'this' como referencia
        btnEliminar.addActionListener(e -> new GUIEliminarPeliculaDeCarrito(this));

        // GRUPO A: vacía el Map y refresca la vista
        btnLimpiar.addActionListener(e -> {
            carrito.clear();
            refrescarTabla();
        });

        // GRUPO B: transforma el Map en TVenta y lo envía al Controlador
        btnCerrarVenta.addActionListener(e -> accionCerrarVenta());

        btnCancelar.addActionListener(e -> dispose());

        // ── Fin ────────────────────────────────────────────────────────────
        pack();
        setMinimumSize(new Dimension(560, 430));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ─── Operaciones del Carrito (GRUPO A – solo memoria) ─────────────────────

    /**
     * AÑADIR AL CARRITO.
     *
     * Si la película ya estaba en el carrito, acumula las copias.
     * No se valida nada (ni existencia, ni stock). La validación ocurre en
     * SAVenta.cerrar() al momento de Cerrar Venta.
     *
     * Llamado por GUIAniadirPeliculaACarrito tras confirmar el formulario.
     *
     * @param idPelicula ID de la película (clave del Map)
     * @param numCopias  Número de copias a añadir
     */
    public void aniadirAlCarrito(int idPelicula, int numCopias, float precioPelicula) {
        if (carrito.containsKey(idPelicula)) {
            // Acumular copias si la película ya estaba
            TVentaPelicula lineaExistente = carrito.get(idPelicula);
            lineaExistente.setNumCopias(lineaExistente.getNumCopias() + numCopias);
        } else {
            // Nueva entrada en el Map
            carrito.put(idPelicula, new TVentaPelicula(idPelicula, true, precioPelicula,  numCopias));
        }
        refrescarTabla();
    }

    /**
     * ELIMINAR DEL CARRITO.
     *
     * Resta copias a una entrada del Map. Si el resultado es <= 0, elimina
     * la entrada completa del Map.
     *
     * Llamado por GUIEliminarPeliculaDeCarrito tras confirmar el formulario.
     *
     * @param idPelicula ID de la película a reducir/eliminar
     * @param numCopias  Número de copias a quitar
     */
    public void eliminarDelCarrito(int idPelicula, int numCopias) {
        if (!carrito.containsKey(idPelicula)) {
            JOptionPane.showMessageDialog(this,
                "La película con ID " + idPelicula + " no está en el carrito.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TVentaPelicula linea = carrito.get(idPelicula);
        int nuevasCopias = linea.getNumCopias() - numCopias;

        if (nuevasCopias <= 0) {
            // Eliminar la entrada del Map completamente
            carrito.remove(idPelicula);
        } else {
            linea.setNumCopias(nuevasCopias);
        }
        refrescarTabla();
    }

    // ─── Acción Cerrar Venta (GRUPO B – pasa al Controlador) ─────────────────

    /**
     * CERRAR VENTA.
     *
     * Transforma el Map<Integer, TLineaVenta> del carrito en un TVenta con
     * su lista de TVentaPelicula, y lo envía al Controlador para que invoque
     * SAVenta.cerrar().
     *
     * ┌─────────────────────────────────────────────────────────────────┐
     * │  Conversión del carrito (Map → List<TVentaPelicula>)            │
     * │  ─────────────────────────────────────────────────────────────  │
     * │  Para cada entrada del Map:                                     │
     * │    - idPelicula  → TVentaPelicula.idPelicula                   │
     * │    - numCopias   → TVentaPelicula.numCopias                    │
     * │    - idVenta     = 0  (lo asigna la BD al insertar)            │
     * │    - precio      = 0f (lo enriquece SAVenta consultando la BD) │
     * └─────────────────────────────────────────────────────────────────┘
     */
    private void accionCerrarVenta() {
        // Validación mínima en presentación: campos no vacíos
        String txtCliente    = txtIdCliente.getText().trim();
        String txtTrabajador = txtIdTrabajador.getText().trim();

        if (txtCliente.isEmpty() || txtTrabajador.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, introduce el ID del cliente y del trabajador.",
                "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idCliente, idTrabajador;
        try {
            idCliente    = Integer.parseInt(txtCliente);
            idTrabajador = Integer.parseInt(txtTrabajador);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Los IDs de cliente y trabajador deben ser números enteros.",
                "Formato inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El carrito está vacío. Añade al menos una película antes de cerrar la venta.",
                "Carrito vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ── Construir el TVenta para enviar al Controlador ─────────────────
        TVenta tVenta = new TVenta();
        tVenta.setIdCliente(idCliente);
        tVenta.setIdTrabajador(idTrabajador);

        // Convertir Map<Integer, TLineaVenta>  →  List<TVentaPelicula>
        List<TVentaPelicula> lineas = new ArrayList<>();
        for (TVentaPelicula lineaCarrito : carrito.values()) {
            TVentaPelicula vp = new TVentaPelicula();
            vp.setIdPelicula(lineaCarrito.getIdPelicula());
            vp.setNumCopias(lineaCarrito.getNumCopias());
            //vp.setIdVenta(0);          // La BD asignará el ID real
            vp.setPrecioPelicula(0f);  // SAVenta enriquecerá el precio
            lineas.add(vp);
        }
        tVenta.setLineaCarrito(lineas);

        // Enviar al Controlador (Grupo B: toca BD)
        Controlador.getInstance().accion(Evento.CERRAR_VENTA, tVenta);
    }

    // ─── Refresco visual de la tabla ──────────────────────────────────────────

    /**
     * Repinta la JTable con el contenido actual del Map.
     * Llamado cada vez que el carrito se modifica.
     */
    private void refrescarTabla() {
        modeloTabla.setRowCount(0); // Limpiar filas actuales

        for (TVentaPelicula linea : carrito.values()) {
            modeloTabla.addRow(new Object[]{
                linea.getIdPelicula(),
                linea.getNumCopias()
            });
        }

        lblTotal.setText("Artículos en carrito: " + carrito.size() + " línea(s)");
    }

    // ─── IGUI ─────────────────────────────────────────────────────────────────

    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Evento.RES_CERRAR_VENTA_OK) {
            int idVenta = (Integer) datos;
            JOptionPane.showMessageDialog(this,
                "✅ Venta cerrada con éxito.\nID de venta asignado: " + idVenta,
                "Venta completada", JOptionPane.INFORMATION_MESSAGE);
            carrito.clear();
            refrescarTabla();
            txtIdCliente.setText("");
            txtIdTrabajador.setText("");

        } else if (evento == Evento.RES_CERRAR_VENTA_KO) {
            String msg = (datos != null) ? (String) datos : "Error al cerrar la venta.";
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Utilidad ─────────────────────────────────────────────────────────────

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        if (color != null) {
            btn.setBackground(color);
            btn.setForeground(Color.WHITE);
            btn.setOpaque(true);
        }
        return btn;
    }
}