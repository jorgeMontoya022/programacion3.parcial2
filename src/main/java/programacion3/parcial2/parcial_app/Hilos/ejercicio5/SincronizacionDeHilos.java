package programacion3.parcial2.parcial_app.Hilos.ejercicio5;

public class SincronizacionDeHilos {

    public static void main(String[] args) {

        variableCompartida variable = new variableCompartida();
        long tiempoinicio = System.currentTimeMillis();
        long tiempofin = tiempoinicio + 20000; //Se ejecuta por 20 segundos

        while (System.currentTimeMillis() < tiempofin) {

            //Escribe en la variable hilo 1
            HiloDeEscritura hiloDeEscritura = new HiloDeEscritura(variable);
            hiloDeEscritura.start();
            try {
                hiloDeEscritura.join(); //Esperar a que termine el hilo 1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //hilo 2 que lee y borra
            HiloDeLectura hiloDeLectura = new HiloDeLectura(variable);
            hiloDeLectura.start();
            try {
                hiloDeLectura.join(); //esperar a que termine el hilo 2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Proceso completo despues de 20 segundos");
    }
}
