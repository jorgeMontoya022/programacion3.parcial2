package programacion3.parcial2.parcial_app.Hilos.Ejercicio1;

public class TareaHilo3 extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 15; i++) {
            System.out.println("Hilo 3 - nombre:" + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
