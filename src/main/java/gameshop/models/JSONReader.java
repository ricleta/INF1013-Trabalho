package gameshop.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class JSONReader {

    public JSONReader() {
    }

     public <T> T[] readJSON(String resourceName, Class<T[]> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        T[] results = null;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourceName);
            }
            results = objectMapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}