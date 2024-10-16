package programacion3.parcial2.parcial_app.Hilos.ejercicio6;

public class MainApp {

    public static void main(String[] args) {

        SumaRecursivaHilo t1 = new SumaRecursivaHilo(10);
        FactorialHilo t2 = new FactorialHilo(6);
        ContarConsonantesHilos t3= new ContarConsonantesHilos("Electroencefalografista");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //S4 multiplicacion de resultado de t1 y t2
        int d= t1.getResultado()* t2.getResultado();

        //S5 imprime el resultado de la multiplicacion y la cantidad de consonantes
        System.out.println("Resultado de s4: " + d);
        System.out.println("Cantidad de consonantes en la palabra ´Electroencefalografista´ : " + t3.getNumConsonantes());
    }
}
