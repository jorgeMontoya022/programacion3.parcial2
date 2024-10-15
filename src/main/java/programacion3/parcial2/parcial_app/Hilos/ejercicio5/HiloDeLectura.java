package programacion3.parcial2.parcial_app.Hilos.ejercicio5;

public class HiloDeLectura extends Thread {

    private  variableCompartida variable;

    public HiloDeLectura(variableCompartida variable) {
        this.variable = variable;
    }

    public void run() {
        variable.leerYBorrar();
    }
}
