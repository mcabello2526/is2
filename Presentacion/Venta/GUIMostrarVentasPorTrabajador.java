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
import javax.swing.JTable;
import javax.swing.JTextField;

import Negocio.Venta.TVenta;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

public class GUIMostrarVentasPorTrabajador extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdTrabajador;
    private javax.swing.table.DefaultTableModel modelo;
 
    public GUIMostrarVentasPorTrabajador() {
        super("Ventas por Trabajador");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] cols = {"ID Venta", "Fecha", "Total €", "ID Cliente"};
        modelo = new javax.swing.table.DefaultTableModel(cols, 0) {
 
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(modelo);
 
        JPanel top = new JPanel();
        top.add(new JLabel("ID Trabajador:"));
        txtIdTrabajador = new JTextField(8);
        top.add(txtIdTrabajador);
        JButton btnBuscar = new JButton("Buscar");
        top.add(btnBuscar);
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdTrabajador.getText().trim());
                Controlador.getInstance().accion(Evento.MOSTRAR_VENTAS_POR_TRABAJADOR, id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
 
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
 
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(top, BorderLayout.NORTH);
        panel.add(new javax.swing.JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnCerrar, BorderLayout.SOUTH);
        setContentPane(panel);
        pack();
        setSize(540, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Evento.RES_MOSTRAR_VENTAS_POR_TRABAJADOR_OK) {
            modelo.setRowCount(0);
            @SuppressWarnings("unchecked")
			java.util.List<TVenta> lista = (java.util.List<TVenta>) datos;
            for (TVenta v : lista) {
                modelo.addRow(new Object[]{
                    v.getId(), v.getFechaVenta(),
                    String.format("%.2f", v.getPrecioTotal()), v.getIdCliente()
                });
            }
        } else if (evento == Evento.RES_MOSTRAR_VENTAS_POR_TRABAJADOR_KO) {
            JOptionPane.showMessageDialog(this, (String) datos, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}