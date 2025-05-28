package gameshop;

import gameshop.ui.TelaLogin;

public class Main {
    public static void main(String[] args) {
        // Inicia a aplicação
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);

        // ObjectMapper objectMapper = new ObjectMapper();
        // Map<String, Object> map = objectMapper.readValue(new File("jogos.json"), new TypeReference<Map<String,Object>>(){});
    }
}