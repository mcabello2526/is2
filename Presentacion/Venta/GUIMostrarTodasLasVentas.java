
package Presentacion.Venta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Negocio.Venta.TVenta;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

class GUIMostrarTodasLasVentas extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private javax.swing.table.DefaultTableModel modelo;
 
    public GUIMostrarTodasLasVentas() {
        super("Todas las Ventas");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] cols = {"ID", "Fecha", "Total €", "ID Cliente", "ID Trabajador"};
        modelo = new javax.swing.table.DefaultTableModel(cols, 0) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(modelo);
        JButton btnCargar = new JButton("Cargar");
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        btnCargar.addActionListener(e -> Controlador.getInstance().accion(Evento.MOSTRAR_TODAS_LAS_VENTAS, null));
 
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel top = new JPanel();
        top.add(btnCargar);
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnCerrar, BorderLayout.SOUTH);
        setContentPane(panel);
        pack();
        setSize(620, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Evento.RES_MOSTRAR_TODAS_LAS_VENTAS_OK) {
            modelo.setRowCount(0);
            java.util.List<TVenta> lista = (java.util.List<TVenta>) datos;
            for (TVenta v : lista) {
                modelo.addRow(new Object[]{
                    v.getId(), v.getFechaVenta(),
                    String.format("%.2f", v.getPrecioTotal()),
                    v.getIdCliente(), v.getIdTrabajador()
                });
            }
        } else if (evento == Evento.RES_MOSTRAR_TODAS_LAS_VENTAS_KO) {
            JOptionPane.showMessageDialog(this, (String) datos, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}