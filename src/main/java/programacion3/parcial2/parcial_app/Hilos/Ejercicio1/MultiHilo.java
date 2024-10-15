package programacion3.parcial2.parcial_app.Hilos.Ejercicio1;

public class MultiHilo {
    public static void main(String[] args) {
        //Intancia de las tareas
        TareaHilo1 tareaHilo1 = new TareaHilo1();
        TareaHilo2 tareaHilo2 = new TareaHilo2();
        TareaHilo3 tareaHilo3 = new TareaHilo3();

        //Hilos con las tareas respectivas
        Thread hilo1 = new Thread(tareaHilo1);
        Thread hilo2 = new Thread(tareaHilo2);
        Thread hilo3 = new Thread(tareaHilo3, "Hilo 3");

        //Inicializar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}


   /* Realizacion de codigo directamente desde el main

     public static void main(String[] args) {

        // Hilo 1: crea un arreglo e imprime sus valores con un tiempo de espera de 1 segundo
        Thread hilo1 = new Thread(() -> {
            int[] arreglo = {1, 2, 3, 4, 5};
            for (int valor : arreglo) {
                System.out.println("Hilo 1 - Valor: " + valor);
                try {
                    Thread.sleep(1000); // 1 segundo de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hilo 2: multiplica un entero por el mismo 10 veces con un tiempo de espera de 1.5 segundos
        Thread hilo2 = new Thread(() -> {
            int numero = 2;
            for (int i = 1; i <= 10; i++) {
                int resultado = numero * numero;
                System.out.println("Hilo 2 - Iteración " + i + ": " + numero + " * " + numero + " = " + resultado);
                try {
                    Thread.sleep(1500); // 1.5 segundos de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hilo 3: imprime el nombre del hilo 15 veces con un tiempo de espera de 500 ms
        Thread hilo3 = new Thread(() -> {
            for (int i = 1; i <= 15; i++) {
                System.out.println("Hilo 3 - Nombre: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500); // 500 ms de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Asignamos nombres a los hilos y los iniciamos
        hilo1.setName("Hilo 1");
        hilo2.setName("Hilo 2");
        hilo3.setName("Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
} */