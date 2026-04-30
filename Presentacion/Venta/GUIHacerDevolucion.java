
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

public class GUIHacerDevolucion extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
 
    public GUIHacerDevolucion() {
        super("Hacer Devolución");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
 
        panel.add(new JLabel("ID Venta a devolver:"));
        panel.add(txtId = new JTextField());
 
        JButton btnDevolver = new JButton("Hacer Devolución");
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnDevolver); panel.add(btnCancelar);
 
        btnDevolver.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Confirmas la devolución de la venta ID " + id + "?\n" +
                    "El stock de las películas será restaurado.",
                    "Confirmar devolución", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    TVenta tVenta = new TVenta();
                    tVenta.setId(id);
                    Controlador.getInstance().accion(Evento.HACER_DEVOLUCION, tVenta);
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
        if (evento == Evento.RES_HACER_DEVOLUCION_OK) {
            JOptionPane.showMessageDialog(this, "✅ Devolución realizada. Stock restaurado.");
            dispose();
        } else if (evento == Evento.RES_HACER_DEVOLUCION_KO) {
            JOptionPane.showMessageDialog(this, (String) datos, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}