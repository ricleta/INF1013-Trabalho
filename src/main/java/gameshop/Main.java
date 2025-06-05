package gameshop;

import gameshop.models.JSONReader;
import gameshop.models.Jogo;
import gameshop.ui.TelaLogin;

public class Main {
    public static void main(String[] args) {
        // Inicia a aplicação
        // TelaLogin telaLogin = new TelaLogin();
        // telaLogin.setVisible(true);

        JSONReader jsonReader = new JSONReader();
        
        // Exemplo de uso do JSONReader
        Jogo[] jogos = jsonReader.readJSON("jogos.json" ,Jogo[].class);

        if (jogos != null) {
            for (Jogo jogo : jogos) {
                System.out.println(jogo);
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Nenhum jogo encontrado ou erro ao ler o arquivo.");
        }
    }
}