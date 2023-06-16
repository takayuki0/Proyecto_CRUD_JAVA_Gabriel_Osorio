package Controler.com.company;

import Connection.ConnectionBD;
import model.com.company.Sounds;
import view.com.company.*;

import javax.swing.*;
import java.awt.event.*;

public class ControllerBienvenida implements ActionListener, WindowListener {

    private final ViewBienvenida frBienvenida = new ViewBienvenida();
    private Sounds sonido = new Sounds();


    public ControllerBienvenida() {
        iniciarVentana();
        iniciarEventos();
    }

    public void iniciarVentana() {
        frBienvenida.setVisible(true);
    }

    public void iniciarEventos() {
        frBienvenida.getBotonAsignaturas().addActionListener(this::actionPerformed);
        frBienvenida.getBotonPersonas().addActionListener(this::actionPerformed);
        frBienvenida.getBotonSalir().addActionListener(this::actionPerformed);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {

            case "Personas":
                sonido.reproduceOK();
                frBienvenida.setVisible(false);
                new ControllerPersonas();

                break;

            case "Asignaturas":
                sonido.reproduceOK();
                frBienvenida.setVisible(false);
                new ControllerAsignaturas();

                break;

            case "Salir":
                sonido.reproduceSFX();
                System.exit(0);
                break;
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("ha salido del programa");
        frBienvenida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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