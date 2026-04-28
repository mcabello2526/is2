package Presentacion.Venta;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
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
 * Diálogo modal para añadir una película al carrito de GUIAbrirVenta.
 *
 * OPERACIÓN DE GRUPO A – Solo modifica el Map en memoria de GUIAbrirVenta.
 * NO pasa por el Controlador. NO toca la Base de Datos.
 * NO valida si la película existe ni si hay stock suficiente
 * (esa validación ocurre en SAVenta.cerrar()).
 *
 * Al confirmar, llama directamente a guiAbrirVenta.aniadirAlCarrito(id, copias).
 */
public class GUIAniadirPeliculaACarrito extends JDialog implements IGUI {

    private static final long serialVersionUID = 1L;

    /** Referencia a GUIAbrirVenta para actualizar su Map del carrito */
    private final GUIAbrirVenta guiAbrirVenta;

    private JTextField txtIdPelicula;
    private JTextField txtNumCopias;

    // ─── Constructor ──────────────────────────────────────────────────────────

    /**
     * @param guiAbrirVenta Ventana padre que contiene el carrito en memoria
     */
    public GUIAniadirPeliculaACarrito(GUIAbrirVenta guiAbrirVenta) {
        super(guiAbrirVenta, "Añadir Película al Carrito", true); 
        this.guiAbrirVenta = guiAbrirVenta;
        initGUI();
    }

    // ─── Construcción de la interfaz ──────────────────────────────────────────

    private void initGUI() {
    	// ── Configuración General ──────────────────────────────────────────
        Color colorFondo = new Color(209, 239, 238);
        getContentPane().setBackground(colorFondo);
        // BoxLayout alinea los paneles uno debajo del otro
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 

        // 1. Panel del Título (Centrado)
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(colorFondo);
        JLabel titulo = new JLabel("Añadir Película al Carrito");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        titulo.setForeground(new Color(30, 80, 100));
        panelTitulo.add(titulo);

        // 2. Panel de Campos (GridLayout de 2 filas x 2 columnas para que quede alineado)
        // El FlowLayout exterior evita que el GridLayout se estire por toda la pantalla
        JPanel panelCamposBase = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCamposBase.setBackground(colorFondo);
        
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 10, 10)); // 10px de separación
        panelCampos.setBackground(colorFondo);
        
        panelCampos.add(new JLabel("ID Película:"));
        txtIdPelicula = new JTextField(10);
        panelCampos.add(txtIdPelicula);
        
        panelCampos.add(new JLabel("Nº Copias:"));
        txtNumCopias = new JTextField(10);
        panelCampos.add(txtNumCopias);
        
        panelCamposBase.add(panelCampos);

        // 3. Panel de la Nota (Centrado)
        JPanel panelNota = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER));
        panelNota.setBackground(colorFondo);
        JLabel nota = new JLabel("<html><i>⚠ No se valida stock hasta cerrar la venta.</i></html>");
        nota.setFont(new Font("Tahoma", Font.ITALIC, 11));
        nota.setForeground(Color.GRAY);
        panelNota.add(nota);

        // 4. Panel de Botones (Centrado con 15px de separación entre ellos)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(colorFondo);

        JButton btnConfirmar = new JButton("✅ Añadir al Carrito");
        btnConfirmar.setBackground(new Color(60, 160, 80));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setFocusPainted(false);

        JButton btnCancelar = new JButton("✖ Cancelar");
        btnCancelar.setBackground(Color.RED);
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
        btnConfirmar.addActionListener(e -> accionAnadir());
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
     * Valida los campos del formulario (solo formato, no coherencia con BD)
     * y llama a guiAbrirVenta.aniadirAlCarrito() para actualizar el Map.
     */
    private void accionAnadir() {
        String txtId     = txtIdPelicula.getText().trim();
        String txtCopias = txtNumCopias.getText().trim();

        if (txtId.isEmpty() || txtCopias.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, rellena todos los campos.",
                "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPelicula, numCopias;
        float precioPelicula; 
        try {
            idPelicula = Integer.parseInt(txtId);
            numCopias  = Integer.parseInt(txtCopias);
            precioPelicula  = Integer.parseInt(txtCopias);
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
        guiAbrirVenta.aniadirAlCarrito(idPelicula, numCopias, precioPelicula);

        JOptionPane.showMessageDialog(this,
            "Película " + idPelicula + " (" + numCopias + " copia(s)) añadida al carrito.",
            "Añadida", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    // ─── IGUI ─────────────────────────────────────────────────────────────────

    @Override
    public void actualizar(int evento, Object datos) {
        // Esta GUI solo modifica memoria; no recibe eventos del Controlador.
    }
}