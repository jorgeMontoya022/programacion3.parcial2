package programacion3.parcial2.parcial_app.Hilos.Ejercicio4;

public class ContarVocalesHilo extends Thread {

    private  String palabra;
    private int numVocales;

    public ContarVocalesHilo(String palabra) {

        this.palabra = palabra;
    }

    public int getNumVocales() {
        return numVocales;
    }

    @Override
    public void run() {
        numVocales= contarVocales(palabra);
        System.out.println("Cantidad de vocales: " + numVocales);
    }

    //Metodo para contar las vocales en la palabra
    private int contarVocales(String palabra) {
        int contador = 0;
        String vocales = "aeiouAEIOU";
        for(char c: vocales.toCharArray()) {
            if(vocales.indexOf(c) != -1) {
                contador++;
            }
        }
        return contador;
    }
}
