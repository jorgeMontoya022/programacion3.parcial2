package programacion3.parcial2.parcial_app.ejemploDeSerializacionGson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import programacion3.parcial2.parcial_app.ejercicio2.model.Programa;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    public ArchivoUtil() {
    }

    public static List<Programa> cargarSerializableJSONResource(String programaJsonFilePath) throws IOException {
        List<Programa> programas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(programaJsonFilePath))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Programa>>() {}.getType();
            programas = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo JSON no encontrado. Creando uno nuevo.");
        }
        return programas;
    }

    public static void agregarSerializableJSONResource(String programaJsonFilePath, Programa programa) throws IOException {
        List<Programa> programas = cargarSerializableJSONResource(programaJsonFilePath);
        programas.add(programa);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(programaJsonFilePath))) {
            Gson gson = new Gson();
            String json = gson.toJson(programas);
            writer.write(json);
        }
    }
}


/*Dependencia en pom

<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.6</version>
</dependency>

*/