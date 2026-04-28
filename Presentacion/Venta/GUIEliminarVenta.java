/**
 *
 */
package Presentacion.Venta;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;


class GUIEliminarVenta extends JFrame implements IGUI {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
 
    public GUIEliminarVenta() {
        super("Eliminar Venta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
 
        panel.add(new JLabel("ID Venta a eliminar:"));
        panel.add(txtId = new JTextField());
 
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnEliminar); panel.add(btnCancelar);
 
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Confirmas eliminar la venta con ID " + id + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Controlador.getInstance().accion(Evento.ELIMINAR_VENTA, id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnCancelar.addActionListener(e -> dispose());
 
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Evento.RES_ELIMINAR_VENTA_OK) {
            JOptionPane.showMessageDialog(this, "Venta eliminada correctamente.");
            dispose();
        } else if (evento == Evento.RES_ELIMINAR_VENTA_KO) {
            JOptionPane.showMessageDialog(this, (String) datos, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}