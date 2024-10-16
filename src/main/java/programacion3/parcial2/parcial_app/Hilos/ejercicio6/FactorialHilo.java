package programacion3.parcial2.parcial_app.Hilos.ejercicio6;

public class FactorialHilo extends Thread {

    private int numero;
    private int resultado;

    public FactorialHilo(int numero) {
        this.numero = numero;
    }

    public int getResultado() {
        return resultado;
    }

    @Override
    public void run() {
        resultado = factorial(numero);
        System.out.println("Resultado factorial: " + resultado);
    }

    private int factorial(int numero) {
        if(numero == 0){
            return 1;
        } else{
            return numero*factorial(numero-1);
        }
    }
}
