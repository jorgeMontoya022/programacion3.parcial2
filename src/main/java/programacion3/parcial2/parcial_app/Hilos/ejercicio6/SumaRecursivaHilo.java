package programacion3.parcial2.parcial_app.Hilos.ejercicio6;

public class SumaRecursivaHilo extends Thread {

    private int N;
    private int resultado;

    public SumaRecursivaHilo(int N) {
        this.N = N;
    }

    public int getResultado() {
        return resultado;
    }

    @Override
    public void run() {
        resultado = sumaRecursiva(N);
        System.out.println("Suma recursiva: " + resultado);
    }

    //Metodo recursivo para sumar los numeros de 1 a N
    private int sumaRecursiva(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + sumaRecursiva(n - 1);
        }
    }
}