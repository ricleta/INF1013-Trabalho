package gameshop.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JSONReader {
    public static <T> T[] readJSON(Class<T[]> valueType, InputStream inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        T[] results = null;

        try{
            String text = new String(inputStream.readAllBytes(), "UTF-8");
            results = objectMapper.readValue(text, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

}