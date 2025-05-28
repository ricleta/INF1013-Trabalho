package gameshop.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;

public class JSONReader {
    private String filePath;

    public JSONReader(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do arquivo não pode ser vazio.");
        }
        this.filePath = filePath;
    }

    public <T> T[] readJSON(Class<T[]> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        T[] results = null;

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            String text = new String(inputStream.readAllBytes(), "UTF-8");
            results = objectMapper.readValue(text, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

}