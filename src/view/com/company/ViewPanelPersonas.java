package view.com.company;

import model.com.company.QueryPossibilities;
import model.com.company.ModelPersonas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.HashMap;

public class ViewPanelPersonas extends JFrame {

    private final HashMap<String, QueryPossibilities> posibilidadesBusqueda = new HashMap<>();
    private final DefaultTableModel m = null;
    private final ModelPersonas modeloPersonas = new ModelPersonas();


    private JLabel labelTitulo;
    private JTable tablePersonas;
    private JButton botonModificar;
    private JButton botonBorrar;
    private JButton botonInsertar;
    private JButton botonSalir;
    private JButton botonPersonas;
    private JPanel panelPersonas;
    private JButton botonInicio;
    private JComboBox comboBox1;
    private JTextField textBusqueda;

    public ViewPanelPersonas() {
        super("Panel Personas");
        setContentPane(panelPersonas);

        Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamanioPantalla.width, tamanioPantalla.height);
        setLocation(0, 0);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar opciones de búsqueda al HashMap
        // Pendiente comentar bien toda esta parte que aquí comienza la cosa
        posibilidadesBusqueda.put("NIF", QueryPossibilities.NIF_PERSONA);
        posibilidadesBusqueda.put("Nombre", QueryPossibilities.NOMBRE_PERSONA);
        posibilidadesBusqueda.put("Apellido1", QueryPossibilities.APELLIDO1_PERSONA);
        posibilidadesBusqueda.put("Apellido2", QueryPossibilities.APELLIDO2_PERSONA);
        posibilidadesBusqueda.put("Ciudad", QueryPossibilities.CIUDAD_PERSONA);
        posibilidadesBusqueda.put("Direccion", QueryPossibilities.DIRECCION_PERSONA);
        posibilidadesBusqueda.put("Telefono", QueryPossibilities.TELEFONO_PERSONA);
        posibilidadesBusqueda.put("Genero", QueryPossibilities.GENERO_PERSONA);
        posibilidadesBusqueda.put("Tipo", QueryPossibilities.TIPO_PERSONA);

        // Agregar KeyListener al JTextField para detectar cambios en el texto
        textBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                busquedaGeneral();
            }
        });

    }

    public void actualizarTablaPersonas() {
        tablePersonas.setModel(modeloPersonas.CargaDatos(m));
    }

    private void busquedaGeneral() {
        String texto = textBusqueda.getText();
        String intemSeleccionado = comboBox1.getSelectedItem().toString();

        if (texto.isEmpty() || texto == null) {
            tablePersonas.setModel(modeloPersonas.CargaDatos(m));
            return;
        }
        if (intemSeleccionado.equals("Filtrar Aquí...") || intemSeleccionado == null) {
            tablePersonas.setModel(modeloPersonas.CargaDatos(m));
            return;
        }

        QueryPossibilities queryPossibilities = posibilidadesBusqueda.get(intemSeleccionado);
        tablePersonas.setModel(modeloPersonas.cargaDatosPorCaracteristicas(m, queryPossibilities, texto));

    }

    public void eliminarFilaTabla(int filaSeleccionada) {
        DefaultTableModel modelo = (DefaultTableModel) tablePersonas.getModel();
        modelo.removeRow(filaSeleccionada);
    }

    public JButton getBotonInicio() {
        return botonInicio;
    }

    public void setBotonInicio(JButton botonInicio) {
        this.botonInicio = botonInicio;
    }

    public JLabel getLabelTitulo() {
        return labelTitulo;
    }

    public void setLabelTitulo(JLabel labelTitulo) {
        this.labelTitulo = labelTitulo;
    }

    public JTable getTablePersonas() {
        return tablePersonas;
    }

    public void setTablePersonas(JTable tablePersonas) {
        this.tablePersonas = tablePersonas;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public void setBotonModificar(JButton botonModificar) {
        this.botonModificar = botonModificar;
    }

    public JButton getBotonBorrar() {
        return botonBorrar;
    }

    public void setBotonBorrar(JButton botonBorrar) {
        this.botonBorrar = botonBorrar;
    }

    public JButton getBotonInsertar() {
        return botonInsertar;
    }

    public void setBotonInsertar(JButton botonInsertar) {
        this.botonInsertar = botonInsertar;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }

    public JButton getBotonAsignaturas() {
        return botonPersonas;
    }

    public void setBotonAsignaturas(JButton botonAsignaturas) {
        this.botonPersonas = botonAsignaturas;
    }

    public JPanel getPanelPersonas() {
        return panelPersonas;
    }

    public void setPanelPersonas(JPanel panelPersonas) {
        this.panelPersonas = panelPersonas;
    }

}