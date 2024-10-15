package programacion3.parcial2.parcial_app.Hilos.Ejercicio1;

public class TareaHilo1 extends Thread {

    @Override
    public void run() {
        int[] arreglo = {1, 2,3,4,5};
        for(int valor: arreglo){
            System.out.println("Hilo 1 - valor:  " + valor);
            try {
                Thread.sleep(1000); //Un segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
