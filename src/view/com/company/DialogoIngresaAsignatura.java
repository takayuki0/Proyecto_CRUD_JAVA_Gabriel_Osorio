package view.com.company;


import model.com.company.Sounds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoIngresaAsignatura extends JDialog {
    private JTextField textCuatrimestre;
    private JTextField textIdProfesor;
    private JTextField textIdGrado;
    private JTextField textNombre;
    private JTextField textCreditos;
    private JTextField textCurso;
    private JButton botonGuardar;
    private JPanel DialogoIngresaAsignatura;
    private JComboBox comboTipo;

    private Sounds sonido = new Sounds();

    public DialogoIngresaAsignatura() {
        setContentPane(DialogoIngresaAsignatura);
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

    public JTextField getTextCuatrimestre() {
        return textCuatrimestre;
    }

    public JTextField getTextIdProfesor() {
        return textIdProfesor;
    }

    public JTextField getTextIdGrado() {
        return textIdGrado;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public JTextField getTextCreditos() {
        return textCreditos;
    }

    public JTextField getTextCurso() {
        return textCurso;
    }

    public JComboBox getComboTipo() {
        return comboTipo;
    }

    private boolean validarCampos() {
        if (textNombre.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El Campo nombre no puede estar vacio");
            return false;
        }
        if (textCreditos.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El campo Créditos no puede estar vacio");
            return false;
        }
        if (textCurso.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El campo Curso no puede estar vacio");
            return false;
        }
        if (textCuatrimestre.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El campo Cuatrimestre no puede estar vacio");
            return false;
        }
        if (textIdProfesor.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El campo ID Profesor no puede estar vacio");
            return false;
        }
        if (textIdGrado.getText().isEmpty()) {
            sonido.reproduceNO();
            JOptionPane.showMessageDialog(this, "El campo ID Grado no puede estar vacio");
            return false;
        }
        return true;
    }

    public void llenarFormulario(String nombre, String creditos, String curso, String cuatrimestre, String idProfesor, String idGrado) {
        textNombre.setText(nombre);
        textCreditos.setText(creditos);
        textCurso.setText(curso);
        textCuatrimestre.setText(cuatrimestre);
        textIdProfesor.setText(idProfesor);
        textIdGrado.setText(idGrado);
    }







}
