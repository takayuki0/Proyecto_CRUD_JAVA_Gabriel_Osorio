package model.com.company;

public enum QueryPossibilities {

    //Posibilidades de Personas
    NIF_PERSONA(2, "NIF", "nif"),
    NOMBRE_PERSONA(3, "Nombre", "nombre"),
    APELLIDO1_PERSONA(4,"Apellido_Paterno", "apellido1"),
    APELLIDO2_PERSONA(5, "Apellido_Materno", "apellido2"),
    CIUDAD_PERSONA(6, "Ciudad", "ciudad"),
    DIRECCION_PERSONA(7, "Direccion", "direccion"),
    TELEFONO_PERSONA(8, "Telefono", "telefono"),
    GENERO_PERSONA(10, "Genero", "sexo"),
    TIPO_PERSONA(11, "Tipo", "tipo"),
    //Posibilidades de Asignaturas:
    NOMBRE_ASIGNATURA(2, "Nombre", "nombre"),
    CREDITOS_ASIGNATURAS(3, "Creditos", "creditos"),
    TIPO_ASIGNATURAS(4, "Tipo", "tipo"),
    CURSO_ASIGNATURAS(5, "Curso", "curso"),
    CUATRIMESTRE_ASIGNATURAS(6, "Cuatrimestre", "cuatrimestre");

    private final int numeroColumna;
    private final String nombre;
    private final String nombreColumna;

    QueryPossibilities(int numeroColumna, String nombre, String nombreColumna) {
        this.numeroColumna = numeroColumna;
        this.nombre = nombre;
        this.nombreColumna = nombreColumna;
    }

    public int getNumeroColumna() {
        return numeroColumna;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreColumna() {
        return nombreColumna;
    }

}
