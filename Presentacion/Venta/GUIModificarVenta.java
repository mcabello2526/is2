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

import Negocio.Venta.TVenta;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

public class GUIModificarVenta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JTextField txtId, txtIdCliente, txtIdTrabajador;
 
    public GUIModificarVenta() {
        super("Modificar Venta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
 
        panel.add(new JLabel("ID Venta:")); panel.add(txtId = new JTextField());
        panel.add(new JLabel("Nuevo ID Cliente:")); panel.add(txtIdCliente = new JTextField());
        panel.add(new JLabel("Nuevo ID Trabajador:")); panel.add(txtIdTrabajador = new JTextField());
 
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnGuardar); panel.add(btnCancelar);
 
        btnGuardar.addActionListener(e -> {
            try {
                TVenta v = new TVenta();
                v.setId(Integer.parseInt(txtId.getText().trim()));
                v.setIdCliente(Integer.parseInt(txtIdCliente.getText().trim()));
                v.setIdTrabajador(Integer.parseInt(txtIdTrabajador.getText().trim()));
                v.setActivo(true);
                Controlador.getInstance().accion(Evento.MODIFICAR_VENTA, v);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
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
        if (evento == Evento.RES_MODIFICAR_VENTA_OK) {
            JOptionPane.showMessageDialog(this, "Venta modificada correctamente.");
            dispose();
        } else if (evento == Evento.RES_MODIFICAR_VENTA_KO) {
            JOptionPane.showMessageDialog(this, (String) datos, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}