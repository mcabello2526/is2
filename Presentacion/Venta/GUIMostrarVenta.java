/**
 *
 */
package Presentacion.Venta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Negocio.Venta.TVenta;
import Negocio.Venta.TVentaPelicula;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

class GUIMostrarVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
    private JTextArea  areaResultado;
 
    public GUIMostrarVenta() {
        super("Mostrar Venta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
 
        JPanel panelTop = new JPanel();
        panelTop.add(new JLabel("ID Venta:"));
        txtId = new JTextField(8);
        panelTop.add(txtId);
        JButton btnBuscar = new JButton("Buscar");
        panelTop.add(btnBuscar);
        panel.add(panelTop, BorderLayout.NORTH);
 
        areaResultado = new JTextArea(10, 40);
        areaResultado.setEditable(false);
        areaResultado.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        panel.add(new JScrollPane(areaResultado), BorderLayout.CENTER);
 
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar, BorderLayout.SOUTH);
 
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Controlador.getInstance().accion(Evento.MOSTRAR_VENTA, id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
 
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Evento.RES_MOSTRAR_VENTA_OK) {
            TVenta v = (TVenta) datos;
            StringBuilder sb = new StringBuilder();
            sb.append("ID Venta:      ").append(v.getId()).append("\n");
            sb.append("Fecha:         ").append(v.getFechaVenta()).append("\n");
            sb.append("Precio Total:  ").append(String.format("%.2f €", v.getPrecioTotal())).append("\n");
            sb.append("ID Cliente:    ").append(v.getIdCliente()).append("\n");
            sb.append("ID Trabajador: ").append(v.getIdTrabajador()).append("\n");
            if (v.getLineaCarrito() != null && !v.getLineaCarrito().isEmpty()) {
                sb.append("\n--- Líneas de Venta ---\n");
                for (TVentaPelicula lv : v.getLineaCarrito()) {
                    sb.append(String.format("  Película %d | %d cop. | %.2f €/u\n",
                        lv.getIdPelicula(), lv.getNumCopias(), lv.getPrecioPelicula()));
                }
            }
            areaResultado.setText(sb.toString());
        } else if (evento == Evento.RES_MOSTRAR_VENTA_KO) {
            JOptionPane.showMessageDialog(this, (String) datos, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}