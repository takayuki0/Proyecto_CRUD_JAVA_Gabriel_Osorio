package Controler.com.company;

import Connection.ConnectionBD;
import model.com.company.ModelAsignaturas;
import model.com.company.ModelPersonas;
import model.com.company.Sounds;
import view.com.company.DialogoIngresaAsignatura;
import view.com.company.ViewPanelAsignaturas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ControllerAsignaturas implements ActionListener, MouseListener, WindowListener {

    private final ViewPanelAsignaturas frAsignaturas = new ViewPanelAsignaturas();
    private final DefaultTableModel m = null;
    private ModelAsignaturas modelAsignaturas;
     private Sounds sonido = new Sounds();

    public ControllerAsignaturas() {
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();
        modelAsignaturas = new ModelAsignaturas();
    }

    public void iniciarVentana() {
        frAsignaturas.setVisible(true);
    }

    public void iniciarEventos() {
        frAsignaturas.getBotonInicio().addActionListener(this::actionPerformed);
        frAsignaturas.getBotonModificar().addActionListener(this::actionPerformed);
        frAsignaturas.getBotonBorrar().addActionListener(this::actionPerformed);
        frAsignaturas.getBotonInsertar().addActionListener(this::actionPerformed);
        frAsignaturas.getBotonPersonas().addActionListener(this::actionPerformed);
        frAsignaturas.getBotonSalir().addActionListener(this::actionPerformed);
        frAsignaturas.getTableAsignaturas().addMouseListener(this);
    }

    public void prepararBaseDatos() {
        ModelPersonas persona = new ModelPersonas();
        ModelAsignaturas asignatura = new ModelAsignaturas();
        frAsignaturas.getTableAsignaturas().setModel(asignatura.CargaDatos(m));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case ("Inicio"):

                sonido.reproduceSFX();
                frAsignaturas.setVisible(false);
                new ControllerBienvenida();

                break;
            case ("Modificar"):

                sonido.reproduceSFX();
                int filaSeleccionadaModif = frAsignaturas.getTableAsignaturas().getSelectedRow();
                if (filaSeleccionadaModif == -1) {
                    JOptionPane.showMessageDialog(null, "Debe Seleccionar la Asignatura que Desea Modificar!");
                }
                if (filaSeleccionadaModif != -1) {
                    String nombre = frAsignaturas.getTableAsignaturas().getValueAt(filaSeleccionadaModif, 1).toString();
                    String creditos = frAsignaturas.getTableAsignaturas().getValueAt(filaSeleccionadaModif, 2).toString();
                    String curso = frAsignaturas.getTableAsignaturas().getValueAt(filaSeleccionadaModif, 4).toString();
                    String cuatrimestre = frAsignaturas.getTableAsignaturas().getValueAt(filaSeleccionadaModif, 5).toString();
                    String id_profesor = frAsignaturas.getTableAsignaturas().getValueAt(filaSeleccionadaModif, 6).toString();
                    if (id_profesor == null || id_profesor.isEmpty()) {
                        id_profesor = "";
                    }
                    String id_grado = frAsignaturas.getTableAsignaturas().getValueAt(filaSeleccionadaModif, 7).toString();

                    DialogoIngresaAsignatura dialogoModificar = new DialogoIngresaAsignatura();
                    dialogoModificar.setTitle("Modificar Asignatura");
                    dialogoModificar.setSize(450, 450);
                    dialogoModificar.llenarFormulario(nombre, creditos, curso, cuatrimestre, id_profesor, id_grado);
                    dialogoModificar.setLocationRelativeTo(null);
                    dialogoModificar.setResizable(false);
                    dialogoModificar.setVisible(true);
                    dialogoModificar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                    String nombreModif = dialogoModificar.getTextNombre().getText();
                    String creditosModif = dialogoModificar.getTextCreditos().getText();
                    String tipoModif = String.valueOf(dialogoModificar.getComboTipo().getSelectedItem());
                    String cursoModif = dialogoModificar.getTextCurso().getText();
                    String cuatrimestreModif = dialogoModificar.getTextCuatrimestre().getText();
                    String idProfesorModif = dialogoModificar.getTextIdProfesor().getText();
                    String idGradoModif = dialogoModificar.getTextIdGrado().getText();

                    DefaultTableModel model = (DefaultTableModel) frAsignaturas.getTableAsignaturas().getModel();
                    int asignaturaId = Integer.valueOf((String) model.getValueAt(filaSeleccionadaModif, 0));
                    modelAsignaturas.modificarAsignatura(nombreModif, creditosModif, tipoModif, cursoModif, cuatrimestreModif, idProfesorModif, idGradoModif, asignaturaId);
                    frAsignaturas.actualizarTablaAsignaturas();
                }

                break;
            case ("Borrar"):

                sonido.reproduceSFX();
                int filaSeleccionadaEliminar = frAsignaturas.getTableAsignaturas().getSelectedRow();
                if (filaSeleccionadaEliminar == -1) {
                    JOptionPane.showMessageDialog(null, "Debe Seleccionar la Asignatura que Desea Eliminar!");
                }
                System.out.println(filaSeleccionadaEliminar);
                if (filaSeleccionadaEliminar != -1) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta asignatura?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        DefaultTableModel model = (DefaultTableModel) frAsignaturas.getTableAsignaturas().getModel();
                        //int asignaturaId = (int) model.getValueAt(filaSeleccionadaEliminar, 0);
                        System.out.println(model.getValueAt(filaSeleccionadaEliminar, 0));
                        int asignaturaId = Integer.valueOf((String) model.getValueAt(filaSeleccionadaEliminar, 0));
                        System.out.println(asignaturaId);
                        modelAsignaturas.eliminarAsignatura(asignaturaId);
                        //modelAsignaturas.eliminarAsignatura(3);
                        frAsignaturas.eliminarFilaTabla(filaSeleccionadaEliminar);
                    }
                }

                break;
            case ("Insertar"):

                sonido.reproduceSFX();

            //    modelAsignaturas.insertarAsignatura("SAXOFÓN", "5", "obligatoria", "4", "4", "15", "5");
            //    prepararBaseDatos();
            //    frAsignaturas.actualizarTablaAsignaturas();

                DialogoIngresaAsignatura dialogoInsertar = new DialogoIngresaAsignatura();
                dialogoInsertar.setTitle("Ingresar Nueva Asignatura");
                dialogoInsertar.setSize(450, 450);
                dialogoInsertar.setLocationRelativeTo(null);
                dialogoInsertar.setResizable(false);
                dialogoInsertar.setVisible(true);
                dialogoInsertar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                String nombre = dialogoInsertar.getTextNombre().getText();
                String creditos = dialogoInsertar.getTextCreditos().getText();
                String tipo = String.valueOf(dialogoInsertar.getComboTipo().getSelectedItem());
                String curso = dialogoInsertar.getTextCurso().getText();
                String cuatrimestre = dialogoInsertar.getTextCuatrimestre().getText();
                String idProfesor = dialogoInsertar.getTextIdProfesor().getText();
                String idGrado = dialogoInsertar.getTextIdGrado().getText();

                if (!nombre.isEmpty() && !creditos.isEmpty() && !tipo.isEmpty() && !curso.isEmpty() && !cuatrimestre.isEmpty() && !idProfesor.isEmpty() && !idGrado.isEmpty()) {
                    modelAsignaturas.insertarAsignatura(nombre, creditos, tipo, curso, cuatrimestre, idProfesor, idGrado);
                    frAsignaturas.actualizarTablaAsignaturas();
                }

                break;
            case ("Personas"):
                sonido.reproduceSFX();
                frAsignaturas.setVisible(false);
                new ControllerPersonas();

                break;
            case ("Salir"):
                sonido.reproduceSFX();
                System.exit(0);
                break;
        }



    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("ha salido del programa");
        frAsignaturas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConnectionBD.closeConn();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}