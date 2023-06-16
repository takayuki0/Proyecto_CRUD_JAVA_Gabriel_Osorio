package view.com.company;

import javax.swing.*;
import java.awt.*;

public class ViewBienvenida extends JFrame {
    private JButton botonPersonas;
    private JButton botonAsignaturas;
    private JLabel imagenBienvenida;
    private JLabel textoBienvenida;
    private JLabel textoPiePagina;
    private JButton botonSalir;
    private JPanel panelBienvenida;
    private JLabel textoTitulo;

    public ViewBienvenida() {
        super("Bienvenido!");
        setContentPane(panelBienvenida);
        setSize(400, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getBotonPersonas() {
        return botonPersonas;
    }

    public void setBotonPersonas(JButton botonPersonas) {
        this.botonPersonas = botonPersonas;
    }

    public JButton getBotonAsignaturas() {
        return botonAsignaturas;
    }

    public void setBotonAsignaturas(JButton botonAsignaturas) {
        this.botonAsignaturas = botonAsignaturas;
    }

    public JLabel getImagenBienvenida() {
        return imagenBienvenida;
    }

    public void setImagenBienvenida(JLabel imagenBienvenida) {
        this.imagenBienvenida = imagenBienvenida;
    }

    public JLabel getTextoBienvenida() {
        return textoBienvenida;
    }

    public void setTextoBienvenida(JLabel textoBienvenida) {
        this.textoBienvenida = textoBienvenida;
    }

    public JLabel getTextoPiePagina() {
        return textoPiePagina;
    }

    public void setTextoPiePagina(JLabel textoPiePagina) {
        this.textoPiePagina = textoPiePagina;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }

    public JPanel getPanelBienvenida() {
        return panelBienvenida;
    }

    public void setPanelBienvenida(JPanel panelBienvenida) {
        this.panelBienvenida = panelBienvenida;
    }
}
