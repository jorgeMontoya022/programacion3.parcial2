package programacion3.parcial2.parcial_app.Hilos.ejercicio5;

public class variableCompartida {

    private  String var= ""; //variable Compartida

    //metodo sincronizado para escribir en  la variable
    public  synchronized void escribir(){

        var += "hola"; //concatenar hola
        System.out.println("Hilo 1- variable escrita: " + var);
    }

    //metodo sincronizado para leer y luego borrar la variable
    public synchronized void leerYBorrar(){
        System.out.println("Hilo 2- variable leida: " + var);
        var = ""; //borar la variable
    }

    public synchronized  String getVar(){
        return var;
    }
}
