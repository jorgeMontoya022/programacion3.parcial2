package programacion3.parcial2.parcial_app.Hilos.Ejercicio1;

public class TareaHilo2  extends Thread{

    @Override
    public void run() {
        int numero=2;

        for( int i=1; i<=10; i++){
            int resultado= numero*numero;
            System.out.println("Hilo 2 - Iteracion : " + i + ": " + numero + " * " + numero + " = " + resultado);
            try {
                Thread.sleep(1500); // 1,5 segundos
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
