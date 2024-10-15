package programacion3.parcial2.parcial_app.Hilos.Ejercicio4;

public class MainApp {
    public static void main(String[] args) {

        int N= 10;
        String palabra= "otorrinolaringologia";

        SumaRecursivaHilo t1 = new SumaRecursivaHilo(N);
        ContarVocalesHilo t2 = new ContarVocalesHilo(palabra);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        int resultado = t1.getResultado()* t2.getNumVocales();
        System.out.println("Resultado de S3*S2 : " + resultado);
    }
}
