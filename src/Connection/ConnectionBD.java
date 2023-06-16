package Connection;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionBD {

    private static Connection conn = null;
    private static final String url = "jdbc:mysql://localhost";
    private static final String port = "3306";
    private static final String user = "root";
    private static final String passwd = "";
    private static final String db = "universidad";
    private static Statement stmt;
    private static ResultSet rs;


    //Abrir la conexión de la BBDD
    public static void openConn() {

        // Primero se comprueba que carga el controlador (Si está la librería necesaria)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador");
        }

        // Seguidamente se intenta establecer al conexión
        try {
            String sUrl = url + ":" + port + "/" + db + "?zeroDateTimeBehavior=convertToNull";
            conn = DriverManager.getConnection(sUrl, user, passwd);
            System.out.println(sUrl);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
        }

        // Por último se carga el objeto de la clase Statement que se utilizará para realizar las consultas
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
        }

    }

    public static Connection getConn() {
        return conn;
    }

    //Cuando se cierre la aplicación hay que cerrar la conexión a la BBDD
    public static void closeConn() {
        try {
            JOptionPane.showMessageDialog(null, "Se cerró la conexión con la BBDD");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Statement getStmt() {
        return stmt;
    }

    public static void testConnection() {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT 1");
            if (rs.next()) {
                System.out.println("La conexión a la base de datos funciona correctamente.");
            } else {
                System.out.println("La conexión a la base de datos parece estar activa, pero no se pudo ejecutar la consulta.");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }




}
