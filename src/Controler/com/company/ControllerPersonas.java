package Controler.com.company;

import Connection.ConnectionBD;
import model.com.company.*;
import view.com.company.*;

import javax.swing.*;
import javax.swing.plaf.synth.SynthRootPaneUI;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class ControllerPersonas implements ActionListener, MouseListener, WindowListener {

    private final ViewPanelPersonas frPersonas = new ViewPanelPersonas();
    private final DefaultTableModel m = null;
    private ModelPersonas modeloPersonas;
    private Sounds sonido = new Sounds();

    public ControllerPersonas() {
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();
        modeloPersonas = new ModelPersonas();
    }

    public void iniciarVentana() {
        frPersonas.setVisible(true);
    }

    public void iniciarEventos() {
        frPersonas.getBotonInicio().addActionListener(this::actionPerformed);
        frPersonas.getBotonModificar().addActionListener(this::actionPerformed);
        frPersonas.getBotonBorrar().addActionListener(this::actionPerformed);
        frPersonas.getBotonInsertar().addActionListener(this::actionPerformed);
        frPersonas.getBotonAsignaturas().addActionListener(this::actionPerformed);
        frPersonas.getBotonSalir().addActionListener(this::actionPerformed);
        frPersonas.getTablePersonas().addMouseListener(this);
    }

    public void prepararBaseDatos() {
        ModelPersonas entrada = new ModelPersonas();
        frPersonas.getTablePersonas().setModel(entrada.CargaDatos(m));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case ("Inicio"):

                sonido.reproduceSFX();
                frPersonas.setVisible(false);
                new ControllerBienvenida();

                break;
            case ("Modificar"):
                sonido.reproduceSFX();
                int filaSeleccionadaModif = frPersonas.getTablePersonas().getSelectedRow();
                if (filaSeleccionadaModif == -1) {
                    JOptionPane.showMessageDialog(null, "Primero Debe Seleccionar a la Persona que Desea Modificar!");
                }
                if (filaSeleccionadaModif != -1) {
                    String nif = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 0).toString();
                    String nombre = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 1).toString();
                    String apellido1 = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 2).toString();
                    String apellido2 = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 3).toString();
                    String ciudad = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 4).toString();
                    String direccion = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 5).toString();
                    String telefono = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 6).toString();
                    if (telefono == null || telefono.isEmpty()) {
                        telefono = "";
                    }
                    String nacimiento = frPersonas.getTablePersonas().getValueAt(filaSeleccionadaModif, 7).toString();

                    DialogoIngresaPersona dialogoModificar = new DialogoIngresaPersona();
                    dialogoModificar.setTitle("Modificar Persona");
                    dialogoModificar.setSize(900, 300);
                    dialogoModificar.llenarFormulario(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, nacimiento);
                    dialogoModificar.setLocationRelativeTo(null);
                    dialogoModificar.setResizable(false);
                    dialogoModificar.setVisible(true);
                    dialogoModificar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                    String nifModif= dialogoModificar.getTextNif().getText();
                    String nombreModif = dialogoModificar.getTextNombre().getText();
                    String apellido1Modif = dialogoModificar.getTextApellidoUno().getText();
                    String apellido2Modif = dialogoModificar.getTextApellidoDos().getText();
                    String ciudadModif = dialogoModificar.getTextCiudad().getText();
                    String direccionModif = dialogoModificar.getTextDireccion().getText();
                    String telefonoModif = dialogoModificar.getTextTelefono().getText();
                    String fechaNacimientoModif = dialogoModificar.getTextNacimiento().getText();
                    String sexoModif = String.valueOf(dialogoModificar.getComboSexo().getSelectedItem());
                    String tipoModif = String.valueOf(dialogoModificar.getComboTipo().getSelectedItem());

                    modeloPersonas.modificarPersona(nifModif, nombreModif, apellido1Modif, apellido2Modif, ciudadModif, direccionModif, telefonoModif, fechaNacimientoModif, sexoModif, tipoModif);
                    frPersonas.actualizarTablaPersonas();
                }

                break;
            case ("Borrar"):

                sonido.reproduceSFX();
                int filaSeleccionadaEliminar = frPersonas.getTablePersonas().getSelectedRow();
                if (filaSeleccionadaEliminar == -1) {
                    JOptionPane.showMessageDialog(null, "Debe Seleccionar a la Persona que Desea Eliminar!");
                }
                if (filaSeleccionadaEliminar != -1) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar a esta Persona?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        DefaultTableModel modelo = (DefaultTableModel) frPersonas.getTablePersonas().getModel();
                        String personaNif = (String) modelo.getValueAt(filaSeleccionadaEliminar, 0);
                        modeloPersonas.eliminarPersona(personaNif);
                        frPersonas.eliminarFilaTabla(filaSeleccionadaEliminar);
                    }
                }

                break;
            case ("Insertar"):

                sonido.reproduceSFX();
                DialogoIngresaPersona dialogoInsertar = new DialogoIngresaPersona();
                dialogoInsertar.setTitle("Ingreso de Nuevas Personas");
                dialogoInsertar.setSize(900, 300);
                dialogoInsertar.setLocationRelativeTo(null);
                dialogoInsertar.setResizable(false);
                dialogoInsertar.setVisible(true);
                dialogoInsertar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                String nif = dialogoInsertar.getTextNif().getText();
                String nombre = dialogoInsertar.getTextNombre().getText();
                String apellido1 = dialogoInsertar.getTextApellidoUno().getText();
                String apellido2 = dialogoInsertar.getTextApellidoDos().getText();
                String ciudad = dialogoInsertar.getTextCiudad().getText();
                String direccion = dialogoInsertar.getTextDireccion().getText();
                String telefono = dialogoInsertar.getTextTelefono().getText();
                String fechaNacimiento = dialogoInsertar.getTextNacimiento().getText();
                String sexo = String.valueOf(dialogoInsertar.getComboSexo().getSelectedItem());
                String tipo = String.valueOf(dialogoInsertar.getComboTipo().getSelectedItem());

                if (!nif.isEmpty() && !nombre.isEmpty() && !apellido1.isEmpty() && !apellido2.isEmpty() && !ciudad.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty() && !fechaNacimiento.isEmpty() && !sexo.isEmpty() && !tipo.isEmpty()) {
                    modeloPersonas.insertarPersona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fechaNacimiento, sexo, tipo);
                    frPersonas.actualizarTablaPersonas();
                }

                break;
            case ("Asignaturas"):

                sonido.reproduceSFX();
                frPersonas.setVisible(false);
                new ControllerAsignaturas();

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
        frPersonas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
