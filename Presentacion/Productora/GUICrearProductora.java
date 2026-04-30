package Presentacion.Productora;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;

import Negocio.Productora.TProductora;

import javax.swing.JTextField;
import javax.swing.JButton;

public class GUICrearProductora extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblTitulo;
    private JPanel panel;
    private JLabel lblNombre, lblAnio, lblNacionalidad;
    private JTextField txtNombre, txtAnio, txtNacionalidad;
    private JButton btnAnadir, btnVolver;
    
    private Color fondo = new Color(225, 245, 254);
	private Color fondoGris = new Color(255, 255, 255);
	//private Color botones = new Color(2, 120, 200);
	private Color textoBlanco = Color.WHITE;
	private Color textoNegro = Color.BLACK;
	private Color btnRojo = new Color(211, 47, 47);
	private Color btnVerde = new Color(134, 231, 120);

    public GUICrearProductora() {
        setTitle("Crear productora");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 450);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(209, 239, 238));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        contentPane.add(getLblTitulo());
        contentPane.add(getPanel());
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JLabel getLblTitulo() {
        if (lblTitulo == null) {
            lblTitulo = new JLabel("Crear productora");
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Rockwell", Font.BOLD, 35));
            lblTitulo.setBounds(0, 20, 700, 42);
        }
        return lblTitulo;
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
            panel.setBackground(Color.WHITE);
            panel.setBounds(150, 80, 400, 250);
            panel.setLayout(null);
            
            panel.add(getLblNombre());
            panel.add(getTxtNombre());
            
            panel.add(getLblAnio());
            panel.add(getTxtAnio());
            
            panel.add(getLblNacionalidad());
            panel.add(getTxtNacionalidad());
            
            panel.add(getBtnAnadir());
            panel.add(getBtnVolver());
        }
        return panel;
    }

    private JLabel getLblNombre() {
        if (lblNombre == null) {
            lblNombre = new JLabel("Nombre:");
            lblNombre.setBounds(30, 30, 120, 20);
        }
        return lblNombre;
    }

    private JLabel getLblAnio() {
        if (lblAnio == null) {
            lblAnio = new JLabel("Año de creación:");
            lblAnio.setBounds(30, 70, 120, 20);
        }
        return lblAnio;
    }

    private JLabel getLblNacionalidad() {
        if (lblNacionalidad == null) {
            lblNacionalidad = new JLabel("ID Nacionalidad:");
            lblNacionalidad.setBounds(30, 110, 120, 20); 
        }
        return lblNacionalidad;
    }

    private JTextField getTxtNombre() {
        if (txtNombre == null) {
            txtNombre = new JTextField();
            txtNombre.setBounds(180, 28, 180, 25);
        }
        return txtNombre;
    }

    private JTextField getTxtAnio() {
        if (txtAnio == null) {
            txtAnio = new JTextField();
            txtAnio.setBounds(180, 68, 180, 25);
        }
        return txtAnio;
    }

    private JTextField getTxtNacionalidad() {
        if (txtNacionalidad == null) {
            txtNacionalidad = new JTextField();
            txtNacionalidad.setBounds(180, 108, 180, 25);
        }
        return txtNacionalidad;
    }

    private JButton getBtnAnadir() {
        if (btnAnadir == null) {
            btnAnadir = new JButton("Añadir");
            btnAnadir.setBackground(new Color(134, 231, 120));
            btnAnadir.setBounds(210, 180, 100, 30);
            btnAnadir.addActionListener(e -> {
                if (validar()) {
                    TProductora productora = new TProductora(
                        0,
                        txtNombre.getText().trim(),
                        Integer.parseInt(txtAnio.getText().trim()),
                        true,
                        Integer.parseInt(txtNacionalidad.getText().trim())
                    );
                    Controlador.getInstance().accion(Evento.CREAR_PRODUCTORA, productora);
                }
            });
        }
        return btnAnadir;
    }

    private JButton getBtnVolver() {
        if (btnVolver == null) {
            btnVolver = new JButton("Volver");
            btnVolver.setForeground(Color.WHITE);
            btnVolver.setBackground(Color.RED);
            btnVolver.setBounds(80, 180, 100, 30);
            btnVolver.addActionListener(e -> dispose());
        }
        return btnVolver;
    }

    private boolean validar() {
        String nombre = txtNombre.getText().trim();
        if (!nombre.matches("^[A-Za-z][A-Za-z\\s]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Nombre no válido (mínimo 3 caracteres, sin símbolos).", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int anio = Integer.parseInt(txtAnio.getText().trim());
            if (anio < 1895 || anio > 2026) {
                JOptionPane.showMessageDialog(this, "El año debe estar entre 1895 y 2026.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int idNacionalidad = Integer.parseInt(txtNacionalidad.getText().trim());
            if (idNacionalidad <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de nacionalidad debe ser un número entero positivo.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void actualizar(int evento, Object datos) {
        switch (evento) {
            case Evento.RES_CREAR_PRODUCTORA_OK:
                JOptionPane.showMessageDialog(this, "Productora creada con éxito.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                break;
            case Evento.RES_CREAR_PRODUCTORA_KO:
                JOptionPane.showMessageDialog(this, "Error: " + datos, "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}