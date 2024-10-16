package programacion3.parcial2.parcial_app.Hilos.ejercicio6;

public class ContarConsonantesHilos extends Thread {

    private String palabra;
    private int numConsonantes;

    public ContarConsonantesHilos(String palabra) {
        this.palabra = palabra;
    }

    public int getNumConsonantes() {
        return numConsonantes;
    }

    @Override
    public void run() {
        numConsonantes = contarConsonantes(palabra);
        System.out.println("cantidad de consonantes : " + numConsonantes);
    }

    private int contarConsonantes(String palabra) {
        int contador = 0;
        String consonantes= "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        for(char c: palabra.toCharArray()) {
            if(consonantes.indexOf(c) != -1) {
                contador++;
            }
        }
        return contador;
    }
}
