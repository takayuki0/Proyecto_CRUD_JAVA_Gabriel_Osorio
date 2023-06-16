package model.com.company;

import Connection.ConnectionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class ModelPersonas {

    private Statement stmt;
    private Sounds sonido = new Sounds();


    public ModelPersonas() {
        ConnectionBD.openConn();
    }

    public DefaultTableModel CargaDatos(DefaultTableModel m) {
        String[] titulos = {"NIF", "Nombre", "Apellido1", "Apellido2", "Ciudad", "Dirección", "Teléfono", "Fecha Nacimiento", "Sexo", "Tipo"};
        m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConnectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("select * from persona");
            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("nif");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido1");
                fila[3] = rs.getString("apellido2");
                fila[4] = rs.getString("ciudad");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getString("fecha_nacimiento");
                fila[8] = rs.getString("sexo");
                fila[9] = rs.getString("tipo");
                m.addRow(fila);
            }
        } catch (SQLException e) {

        }
        return m;
    }

    public DefaultTableModel cargaDatosPorCaracteristicas(DefaultTableModel m, QueryPossibilities queryPossibilities, String texto) {
        String[] titulos = {"NIF", "Nombre", "Apellido1", "Apellido2", "Ciudad", "Dirección", "Teléfono", "Fecha Nacimiento", "Sexo", "Tipo"};
        m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConnectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("SELECT * FROM persona WHERE " + queryPossibilities.getNombreColumna() + " LIKE '%" + texto + "%'");
            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("nif");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido1");
                fila[3] = rs.getString("apellido2");
                fila[4] = rs.getString("ciudad");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getString("fecha_nacimiento");
                fila[8] = rs.getString("sexo");
                fila[9] = rs.getString("tipo");
                m.addRow(fila);
            }
        } catch (SQLException e) {

        }
        return m;
    }


    public void eliminarPersona(String personaNif) {
        try {
            Connection connection = ConnectionBD.getConn();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM persona WHERE nif = ?");
            statement.setString(1, personaNif);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                sonido.reproduceExito();
                JOptionPane.showMessageDialog(null, "La persona con NIF: " + personaNif + " ha sido eliminada de la dase de datos.");
                System.out.println("La persona con NIF: " + personaNif + " ha sido eliminada de la dase de datos.");
            } else {
                sonido.reproduceBreak();
                JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna persona con NIF: " + personaNif + ".");
                System.out.println("No se ha encontrado ninguna persona con NIF: " + personaNif + ".");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona con NIF: " + personaNif + ": " + e.getMessage());
        }

    }

    public void insertarPersona(String nif, String  nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaNacimiento, String sexo, String tipo) {

        try {
            Connection connection = ConnectionBD.getConn();
            String consulta = "INSERT INTO persona (nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, sexo, tipo) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nif);
            statement.setString(2, nombre);
            statement.setString(3, apellido1);
            statement.setString(4, apellido2);
            statement.setString(5, ciudad);
            statement.setString(6, direccion);
            statement.setString(7, telefono);
            fechaNacimiento = fechaNacimiento.substring(6, 10) + "-" + fechaNacimiento.substring(3, 5) + "-" + fechaNacimiento.substring(0, 2);
            statement.setDate(8, Date.valueOf(fechaNacimiento));
            statement.setString(9, sexo);
            statement.setString(10, tipo);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                sonido.reproduceExito();
                JOptionPane.showMessageDialog(null, "La Persona ha sido cargada exitosamente en la Base de Datos!!");
                System.out.println("La Persona ha sido cargada exitosamente en la Base de Datos!!");
            } else {
                sonido.reproduceBreak();
                JOptionPane.showMessageDialog(null, "Algo ha ido mal y la Persona no ha sido cargada en la Base de Datos!!");
                System.out.println("Algo ha ido mal y la Persona no ha sido cargada en la Base de Datos!!");
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void modificarPersona(String nif, String  nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaNacimiento, String sexo, String tipo) {

        try {
            Connection connection = ConnectionBD.getConn();
            String consulta = "UPDATE persona SET nif = ?, nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, sexo = ?, tipo = ? WHERE nif = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nif);
            statement.setString(2, nombre);
            statement.setString(3, apellido1);
            statement.setString(4, apellido2);
            statement.setString(5, ciudad);
            statement.setString(6, direccion);
            statement.setString(7, telefono);
            //fechaNacimiento = fechaNacimiento.substring(6, 10) + "-" + fechaNacimiento.substring(3, 5) + "-" + fechaNacimiento.substring(0, 2);
            statement.setDate(8, Date.valueOf(fechaNacimiento));
            statement.setString(9, sexo);
            statement.setString(10, tipo);
            statement.setString(11, nif);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                sonido.reproduceExito();
                JOptionPane.showMessageDialog(null, "Los Datos de la Persona con NIF: " + nif + " han sido Modificados exitosamente en la Base de Datos!!");
                System.out.println("Los Datos de la Persona han sido Modificados exitosamente en la Base de Datos!!");
            } else {
                sonido.reproduceBreak();
                JOptionPane.showMessageDialog(null, "Algo ha ido mal y la Persona no ha sido Modificada!!");
                System.out.println("Algo ha ido mal y la Persona no ha sido Modificada!!");
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
