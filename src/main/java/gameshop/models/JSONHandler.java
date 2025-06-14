package gameshop.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class JSONHandler {
    private static final String RESOURCE_PATH = "src/main/resources/";
    
    public static <T> T[] readJSON(String resourceName, Class<T[]> valueType) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // Register modules for Java 8 date/time support
        T[] results = null;

        try (InputStream inputStream = new FileInputStream(Paths.get(RESOURCE_PATH, resourceName).toFile())) {
            results = objectMapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static void writeJSON(String resourceName, Object [] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // Register modules for Java 8 date/time support
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        try (FileOutputStream outputStream = new FileOutputStream(Paths.get(RESOURCE_PATH, resourceName).toFile())) {
            objectMapper.writeValue(outputStream, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}