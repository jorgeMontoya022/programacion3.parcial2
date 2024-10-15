package programacion3.parcial2.parcial_app.Hilos.Ejercicio4;

public class MainApp {
    public static void main(String[] args) {

        //Se inicializa para s1
        int N= 10;
        String palabra= "otorrinolaringologia";

        //Se crean los hilos t1 y t2 para s2 y s3
        SumaRecursivaHilo t1 = new SumaRecursivaHilo(N);
        ContarVocalesHilo t2 = new ContarVocalesHilo(palabra);

        t1.start();
        t2.start();

        //Espera a que ambos terminen
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //S4: Realiza la multiplicacion y muestra el resultado
        int resultado = t1.getResultado()* t2.getNumVocales();
        System.out.println("Resultado de S3*S2 : " + resultado);
    }
}
