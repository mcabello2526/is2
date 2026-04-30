package Presentacion.Venta;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.IGUI;

/**
 * Diálogo modal para eliminar (o reducir) una película del carrito de GUIAbrirVenta.
 *
 * OPERACIÓN DE GRUPO A – Solo modifica el Map en memoria de GUIAbrirVenta.
 * NO pasa por el Controlador. NO toca la Base de Datos.
 *
 * Comportamiento:
 *  - Si numCopias < copias en carrito → reduce la cantidad en el Map
 *  - Si numCopias >= copias en carrito → elimina la entrada del Map
 *
 * Al confirmar, llama directamente a guiAbrirVenta.eliminarDelCarrito(id, copias).
 */
public class GUIEliminarPeliculaDeCarrito extends JDialog implements IGUI {

    private static final long serialVersionUID = 1L;

    /** Referencia a GUIAbrirVenta para actualizar su Map del carrito */
    private final GUIAbrirVenta guiAbrirVenta;

    private JTextField txtIdPelicula;
    private JTextField txtNumCopias;

    // ─── Constructor ──────────────────────────────────────────────────────────

    public GUIEliminarPeliculaDeCarrito() {
        super();
        this.guiAbrirVenta = null;
    }
    
    public GUIEliminarPeliculaDeCarrito(GUIAbrirVenta guiAbrirVenta) {
        super(guiAbrirVenta, "Eliminar Película del Carrito", true); // modal
        this.guiAbrirVenta = guiAbrirVenta;
        initGUI();
    }

    // ─── Construcción de la interfaz ──────────────────────────────────────────

    private void initGUI() {
    	// ── Configuración General ──────────────────────────────────────────
        Color colorFondo = new Color(209, 239, 238);
        getContentPane().setBackground(colorFondo);
        // Usamos BoxLayout para apilar los paneles verticalmente
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // 1. Panel del Título (Centrado)
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(colorFondo);
        JLabel titulo = new JLabel("Eliminar Película del Carrito");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        titulo.setForeground(new Color(180, 50, 30));
        panelTitulo.add(titulo);

        // 2. Panel de Campos (GridLayout de 2x2 para alinear textos y cajas)
        // El panel base con FlowLayout evita que los campos se estiren por toda la pantalla
        JPanel panelCamposBase = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCamposBase.setBackground(colorFondo);
        
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 filas, 2 columnas, 10px separación
        panelCampos.setBackground(colorFondo);
        
        panelCampos.add(new JLabel("ID Película:"));
        txtIdPelicula = new JTextField(10);
        panelCampos.add(txtIdPelicula);
        
        panelCampos.add(new JLabel("Copias a quitar:"));
        txtNumCopias = new JTextField(10);
        panelCampos.add(txtNumCopias);
        
        panelCamposBase.add(panelCampos);

        // 3. Panel de la Nota (Centrado)
        JPanel panelNota = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNota.setBackground(colorFondo);
        // He añadido un 'text-align: center' al div del HTML para que las dos líneas queden centradas entre sí
        JLabel nota = new JLabel(
            "<html><div style='text-align: center;'><i>Si las copias resultantes llegan a 0,<br>la película se retira del carrito.</i></div></html>");
        nota.setFont(new Font("Tahoma", Font.ITALIC, 11));
        nota.setForeground(Color.GRAY);
        panelNota.add(nota);

        // 4. Panel de Botones (Centrado con 15px de separación entre ellos)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(colorFondo);

        JButton btnConfirmar = new JButton("✅ Confirmar Eliminación");
        btnConfirmar.setBackground(new Color(210, 100, 60));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setFocusPainted(false);

        JButton btnCancelar = new JButton("✖ Cancelar");
        btnCancelar.setBackground(new Color(150, 150, 150));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setFocusPainted(false);

        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);

        // ── Añadir Paneles a la Ventana ────────────────────────────────────
        add(Box.createVerticalStrut(10)); // Margen superior
        add(panelTitulo);
        add(panelCamposBase);
        add(panelNota);
        add(panelBotones);
        add(Box.createVerticalStrut(10)); // Margen inferior

        // ── Listeners ──────────────────────────────────────────────────────
        btnConfirmar.addActionListener(e -> accionEliminar());
        btnCancelar.addActionListener(e -> dispose());

        // ── Finalizar ──────────────────────────────────────────────────────
        getRootPane().setDefaultButton(btnConfirmar);
        pack();
        setResizable(false);
        setLocationRelativeTo(guiAbrirVenta);
        setVisible(true);
    }

    // ─── Acción confirmar ─────────────────────────────────────────────────────

    /**
     * Valida el formato de los campos y llama a guiAbrirVenta.eliminarDelCarrito()
     * para actualizar el Map en memoria.
     */
    private void accionEliminar() {
        String txtId     = txtIdPelicula.getText().trim();
        String txtCopias = txtNumCopias.getText().trim();

        if (txtId.isEmpty() || txtCopias.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, rellena todos los campos.",
                "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPelicula, numCopias;
        try {
            idPelicula = Integer.parseInt(txtId);
            numCopias  = Integer.parseInt(txtCopias);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "El ID y el número de copias deben ser números enteros.",
                "Formato inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idPelicula <= 0 || numCopias <= 0) {
            JOptionPane.showMessageDialog(this,
                "El ID de película y el número de copias deben ser mayores que 0.",
                "Valor inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── Actualizar el Map del carrito en GUIAbrirVenta ─────────────────
        guiAbrirVenta.eliminarDelCarrito(idPelicula, numCopias);
        dispose();
    }

    // ─── IGUI ─────────────────────────────────────────────────────────────────

    @Override
    public void actualizar(int evento, Object datos) {
        // Esta GUI solo modifica memoria; no recibe eventos del Controlador.
    }
}