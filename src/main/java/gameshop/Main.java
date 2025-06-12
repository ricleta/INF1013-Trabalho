package gameshop;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;

import gameshop.models.Usuario;
import gameshop.ui.*;
import gameshop.models.*;

public class Main {
    private static Jogo[] jogos;
    private static Usuario[] usuarios;

    public static void main(String[] args) {
        // Configura o Look and Feel para FlatLaf Darcula
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put("MenuItem.selectionBackground", UIManager.getColor("Button.background"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        usuarios = JSONReader.readJSON("usuarios.json", Usuario[].class);
        final Usuario usuarioLogado = usuarios[0]; // Exemplo: usando o primeiro usuário do JSON

        jogos = JSONReader.readJSON("jogos.json", Jogo[].class);
        Jogo jogoExemplo = jogos[0]; // Exemplo: usando o primeiro jogo do JSON

        // Usuario usuarioLogado = createMockUsuario(); // Usando o mock de usuário para testes
        
        // Inicia a aplicação
        TelaCatalogo telaCatalogo = new TelaCatalogo(usuarioLogado); // Exemplo: usando o primeiro usuário do JSON
        telaCatalogo.setVisible(true);
    }


    public static Jogo getJogo(int idJogo) {
        for (Jogo jogo : jogos) {
            if (jogo.getId() == idJogo) {
                return jogo;
            }
        }
        return null; // Retorna null se o jogo não for encontrado
    }

    public static void addJogoToCarrinho(Usuario usuario, Jogo jogo) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo ao adicionar ao carrinho.");
        }
        if (jogo == null) {
            throw new IllegalArgumentException("Jogo não pode ser nulo ao adicionar ao carrinho.");
        }
        // TODO: Preco deve ser obtido do Catalogo
        JogoCarrinhoUsuario novoJogoCarrinho = new JogoCarrinhoUsuario(jogo.getId(), 22.99); 
        usuario.addJogoToCarrinho(novoJogoCarrinho);
    }
}