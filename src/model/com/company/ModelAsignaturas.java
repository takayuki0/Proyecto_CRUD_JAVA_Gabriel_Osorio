package model.com.company;

import Connection.ConnectionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ModelAsignaturas {

    private Statement stmt;
    private Sounds sonido = new Sounds();

    public DefaultTableModel CargaDatos(DefaultTableModel m) {
        String[] titulos = {"ID", "Nombre", "Créditos", "Tipo", "Curso", "Cuatrimestre", "Id Profesor", "Id Grado"};
        m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConnectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("select * from asignatura");
            String[] fila = new String[8];

            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("creditos");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("curso");
                fila[5] = rs.getString("cuatrimestre");
                fila[6] = rs.getString("id_profesor");
                fila[7] = rs.getString("id_grado");
                m.addRow(fila);
            }
        } catch (SQLException e) {

        }
        return m;
    }


    public DefaultTableModel cargaDatosPorCaracteristicas(DefaultTableModel m, QueryPossibilities queryPossibilities, String texto) {
        String[] titulos = {"ID", "Nombre", "Créditos", "Tipo", "Curso", "Cuatrimestre", "Id Profesor", "Id Grado"};
        m = new DefaultTableModel(null, titulos);

        try {
            stmt = ConnectionBD.getStmt();
            ResultSet rs = stmt.executeQuery("SELECT * FROM asignatura WHERE " + queryPossibilities.getNombreColumna() + " LIKE '%" + texto + "%'");
            String[] fila = new String[8];

            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("creditos");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("curso");
                fila[5] = rs.getString("cuatrimestre");
                fila[6] = rs.getString("id_profesor");
                fila[7] = rs.getString("id_grado");
                m.addRow(fila);
            }
        } catch (SQLException e) {

        }
        return m;
    }


    public void eliminarAsignatura(int asignaturaId) {
        System.out.println(asignaturaId);
        try {
            Connection connection = ConnectionBD.getConn();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM asignatura WHERE id = ?");
            statement.setInt(1, asignaturaId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                sonido.reproduceExito();
                JOptionPane.showMessageDialog(null, "La asignatura con ID " + asignaturaId + " ha sido eliminada de la base de datos.");
                System.out.println("La asignatura con ID " + asignaturaId + " ha sido eliminada de la base de datos.");
            } else {
                sonido.reproduceBreak();
                JOptionPane.showMessageDialog(null, "No se encontró ninguna asignatura con ID " + asignaturaId + ".");
                System.out.println("No se encontró ninguna asignatura con ID " + asignaturaId + ".");
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar la asignatura con ID " + asignaturaId + ": " + e.getMessage());
        }
    }

    public void insertarAsignatura(String nombre, String creditos, String tipo, String curso, String cuatrimestre, String id_profesor, String id_grado) {
        System.out.println("Haz entrado en el método");

        try {
            Connection connection = ConnectionBD.getConn();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO asignatura (nombre, creditos, tipo, curso, cuatrimestre, id_profesor, id_grado) VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, nombre);
            statement.setFloat(2, Float.parseFloat(creditos));
            statement.setString(3, tipo);
            statement.setInt(4, Integer.parseInt(curso));
            statement.setInt(5, Integer.parseInt(cuatrimestre));
            statement.setInt(6, Integer.parseInt(id_profesor));
            statement.setInt(7, Integer.parseInt(id_grado));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                sonido.reproduceExito();
                JOptionPane.showMessageDialog(null, "La Asignatura ha sido cargada en la Base de Datos con éxito!!");
                System.out.println("La Asignatura ha sido cargada en la Base de Datos con éxito!!");
            } else {
                sonido.reproduceBreak();
                JOptionPane.showMessageDialog(null, "Algo ha ido mal y tu asignatura no ha sido cargada en la Base de Datos!!");
                System.out.println("Algo ha ido mal y tu asignatura no ha sido cargada en la Base de Datos!!");
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarAsignatura(String nombre, String creditos, String tipo, String curso, String cuatrimestre, String id_profesor, String id_grado, int asignaturaId) {
        System.out.println("Entrando en el Método Modificar Asignatura");
        try {
            Connection connection = ConnectionBD.getConn();
            PreparedStatement statement = connection.prepareStatement("UPDATE asignatura SET nombre = ?, creditos = ?, tipo = ?, curso = ?, cuatrimestre = ?, id_profesor = ?, id_grado = ? WHERE id = ?");
            statement.setString(1, nombre);
            statement.setFloat(2, Float.parseFloat(creditos));
            statement.setString(3, tipo);
            statement.setInt(4, Integer.parseInt(curso));
            statement.setInt(5, Integer.parseInt(cuatrimestre));
            statement.setInt(6, Integer.parseInt(id_profesor));
            statement.setInt(7, Integer.parseInt(id_grado));
            statement.setInt(8, asignaturaId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                sonido.reproduceExito();
                JOptionPane.showMessageDialog(null, "La Asignatura ha sido Modificada con Éxito!!");
                System.out.println("La Asignatura ha sido Modificada con Éxito!!");
            } else {
                sonido.reproduceBreak();
                JOptionPane.showMessageDialog(null, "Algo ha ido mal y tu asignatura no ha sido modificada");
                System.out.println("Algo ha ido mal y tu asignatura no ha sido modificada");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo ha ido mal y tu asignatura no ha sido modificada");
            throw new RuntimeException(e);
        }
    }










}
