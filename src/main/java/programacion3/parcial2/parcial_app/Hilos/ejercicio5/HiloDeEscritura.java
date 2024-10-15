package programacion3.parcial2.parcial_app.Hilos.ejercicio5;

public class HiloDeEscritura extends Thread {

    private  variableCompartida variable;
    private long tiempoInicio;

    public HiloDeEscritura(variableCompartida variable) {
        this.variable = variable;
    }


    public void run() {
        tiempoInicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - tiempoInicio< 6000) { // 6 Segundos de escritura
            variable.escribir();
            try{
                Thread.sleep(1000); //Esperar un segundo antes de volver a concatenar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
