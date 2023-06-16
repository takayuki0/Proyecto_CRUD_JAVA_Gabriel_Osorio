package view.com.company;

import model.com.company.ModelAsignaturas;
import model.com.company.QueryPossibilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class ViewPanelAsignaturas extends JFrame {

    private final HashMap<String, QueryPossibilities> posibilidadesDeBusqueda = new HashMap<>();

    private final DefaultTableModel m = null;

    private final ModelAsignaturas modeloAsignaturas = new ModelAsignaturas();

    private JPanel panelAsignaturas;
    private JLabel labelTitulo;
    private JTable tableAsignaturas;
    private JButton botonSalir;
    private JButton botonPersonas;
    private JButton botonInsertar;
    private JButton botonBorrar;
    private JButton botonModificar;
    private JButton botonInicio;
    private JTextField textBusqueda;
    private JComboBox comboBox1;

    private int fila;
    private String id;


    public ViewPanelAsignaturas() {
        super("Panel Asignaturas");
        setContentPane(panelAsignaturas);

        Dimension tamanioPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamanioPantalla.width, tamanioPantalla.height);
        setLocation(0, 0);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        posibilidadesDeBusqueda.put("Nombre", QueryPossibilities.NOMBRE_ASIGNATURA);
        posibilidadesDeBusqueda.put("Creditos", QueryPossibilities.CREDITOS_ASIGNATURAS);
        posibilidadesDeBusqueda.put("Tipo", QueryPossibilities.TIPO_ASIGNATURAS);
        posibilidadesDeBusqueda.put("Curso", QueryPossibilities.CURSO_ASIGNATURAS);
        posibilidadesDeBusqueda.put("Cuatrimestre", QueryPossibilities.CUATRIMESTRE_ASIGNATURAS);

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


    public void actualizarTablaAsignaturas() {
        tableAsignaturas.setModel(modeloAsignaturas.CargaDatos(m));
    }


    //Este método funciona y es uno de los encargados de filtrar
    public void busquedaGeneral() {
        String texto = textBusqueda.getText();
        String itemSeleccionado = comboBox1.getSelectedItem().toString();

        if (texto.isEmpty() || texto == null) {
            tableAsignaturas.setModel(modeloAsignaturas.CargaDatos(m));
        }
        if (itemSeleccionado.equals("Filtrar Aquí...") || itemSeleccionado == null) {
            tableAsignaturas.setModel(modeloAsignaturas.CargaDatos(m));
        }

        QueryPossibilities posibilidades = posibilidadesDeBusqueda.get(itemSeleccionado);
        tableAsignaturas.setModel(modeloAsignaturas.cargaDatosPorCaracteristicas(m, posibilidades, texto));

    }

    //Método para eliminar la una fila de la interfaz gráfica
    public void eliminarFilaTabla(int filaSeleccionada) {
        DefaultTableModel modelo = (DefaultTableModel) tableAsignaturas.getModel();
        modelo.removeRow(filaSeleccionada);
    }



    public JPanel getPanelAsignaturas() {
        return panelAsignaturas;
    }

    public void setPanelAsignaturas(JPanel panelAsignaturas) {
        this.panelAsignaturas = panelAsignaturas;
    }

    public JLabel getLabelTitulo() {
        return labelTitulo;
    }

    public void setLabelTitulo(JLabel labelTitulo) {
        this.labelTitulo = labelTitulo;
    }

    public JTable getTableAsignaturas() {
        return tableAsignaturas;
    }

    public void setTableAsignaturas(JTable tableAsignaturas) {
        this.tableAsignaturas = tableAsignaturas;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }

    public JButton getBotonPersonas() {
        return botonPersonas;
    }

    public void setBotonPersonas(JButton botonPersonas) {
        this.botonPersonas = botonPersonas;
    }

    public JButton getBotonInsertar() {
        return botonInsertar;
    }

    public void setBotonInsertar(JButton botonInsertar) {
        this.botonInsertar = botonInsertar;
    }

    public JButton getBotonBorrar() {
        return botonBorrar;
    }

    public void setBotonBorrar(JButton botonBorrar) {
        this.botonBorrar = botonBorrar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public void setBotonModificar(JButton botonModificar) {
        this.botonModificar = botonModificar;
    }

    public JButton getBotonInicio() {
        return botonInicio;
    }

    public void setBotonInicio(JButton botonInicio) {
        this.botonInicio = botonInicio;
    }
}
