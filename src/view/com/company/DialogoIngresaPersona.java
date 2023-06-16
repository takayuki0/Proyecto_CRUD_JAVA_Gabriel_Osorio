package view.com.company;

import Connection.ConnectionBD;
import model.com.company.Sounds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DialogoIngresaPersona extends JDialog {
    private JTextField textDireccion;
    private JTextField textTelefono;
    private JTextField textNacimiento;
    private JTextField textNif;
    private JTextField textNombre;
    private JTextField textApellidoUno;
    private JTextField textApellidoDos;
    private JTextField textCiudad;
    private JButton botonGuardar;
    private JPanel DialogoIngresaPersona;
    private JComboBox comboSexo;
    private JComboBox comboTipo;
    private Sounds sonido = new Sounds();

    public DialogoIngresaPersona() {
        setContentPane(DialogoIngresaPersona);
        setModal(true);
        getRootPane().setDefaultButton(botonGuardar);

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    dispose();
                }
            }
        });
    }

    public JTextField getTextDireccion() {
        return textDireccion;
    }

    public JTextField getTextTelefono() {
        return textTelefono;
    }

    public JTextField getTextNacimiento() {
        return textNacimiento;
    }

    public JTextField getTextNif() {
        return textNif;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public JTextField getTextApellidoUno() {
        return textApellidoUno;
    }

    public JTextField getTextApellidoDos() {
        return textApellidoDos;
    }

    public JTextField getTextCiudad() {
        return textCiudad;
    }

    public JComboBox getComboSexo() {
        return comboSexo;
    }

    public JComboBox getComboTipo() {
        return comboTipo;
    }

    private boolean validarCampos() {
        if (textNif.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo NIF no puede estar vacío!");
            return false;
        }
        if (textNombre.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo Nombre no puede estar vacio!");
            return false;
        }
        if (textApellidoUno.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo Apellido 1 no puede estar vacío!");
            return false;
        }
        if (textApellidoDos.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo Apellido 2 no puede estar vacío!");
            return false;
        }
        if (textCiudad.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo Ciudad no puede estar vacío!");
            return false;
        }
        if (textDireccion.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo Dirección no puede estar vacío!");
            return false;
        }
        if (textTelefono.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo Teléfono no puede estar vacío!");
            return false;
        }
        if (textNacimiento.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo Fecha de Nacimiento no puede estar vacío!");
            return false;
        }
        return true;
    }

    public void llenarFormulario(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String nacimiento) {
        textNif.setText(nif);
        textNombre.setText(nombre);
        textApellidoUno.setText(apellido1);
        textApellidoDos.setText(apellido2);
        textCiudad.setText(ciudad);
        textDireccion.setText(direccion);
        textTelefono.setText(telefono);
        textNacimiento.setText(nacimiento);
    }

  /*  public void ingresarPersona() {
        String nif = textNif.getText();
        String nombre = textNombre.getText();
        String apellido1 = textApellidoUno.getText();
        String apellido2 = textApellidoDos.getText();
        String ciudad = textCiudad.getText();
        String direccion = textDireccion.getText();
        String telefono = textTelefono.getText();
        String fecha_nacimiento = textNacimiento.getText();
        String sexo = comboSexo.getName();
        String tipo = comboTipo.getName();

        if (validarCampos()) {
            ConnectionBD.openConn();

            try {
                String consulta = "INSERT INTO asignaturas (nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, sexo, tipo) VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = ConnectionBD.getConn().prepareStatement(consulta);
                ps.setString(1, nif);
                ps.setString(2, nombre);
                ps.setString(3, apellido1);
                ps.setString(4, apellido2);
                ps.setString(5, ciudad);
                ps.setString(6, direccion);
                ps.setString(7, telefono);
                ps.setString(8, fecha_nacimiento);
                ps.setString(9, sexo);
                ps.setString(10, tipo);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0 ) {
                    JOptionPane.showMessageDialog(this, "Persona Insertada Correctamente!");
                    // Actualizar el modelo de la tabla en la ventana principal
                    frPersonas.actualizarTablaPersonas();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al tratar de Insertar la Persona");
                }
                ConnectionBD.closeConn();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al insertar Persona en la base de datos: " + e.getMessage());
            }
        }
    }  */





}