package gameshop;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;

import gameshop.models.Usuario;
import gameshop.ui.*;
import gameshop.models.*;

public class Main {
    private static Jogo[] jogos;
    private static Usuario[] usuarios;
    private static Catalogo[] catalogos;
    private static Catalogo catalogoAtual;

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

        catalogos = JSONReader.readJSON("catalogos.json", Catalogo[].class);
        catalogoAtual = catalogos[0]; // Exemplo: usando o primeiro catálogo do JSON
        
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
        double preco = catalogoAtual.getPrecoJogo(jogo.getId());
        
        if (preco < 0) {
            throw new IllegalArgumentException("Jogo não encontrado no catálogo.");
        }

        JogoCarrinho novoJogoCarrinho = new JogoCarrinho(jogo.getId(), preco);
        usuario.addJogoToCarrinho(novoJogoCarrinho);
    }

    public static JogoCatalogo[] getJogosCatalogoAtual() {
        return catalogoAtual.getJogosOferecidos();
    }

    public static double getPrecoJogoCatalogoAtual(int idJogo) {
        return catalogoAtual.getPrecoJogo(idJogo);
    }

    public static void showCatalogo(Usuario usuario) {
        TelaCatalogo telaCatalogo = new TelaCatalogo(usuario);
        telaCatalogo.setVisible(true);
    }

    public static void showCarrinho(Usuario usuario) {
        TelaCarrinho telaCarrinho = new TelaCarrinho(usuario);
        telaCarrinho.setVisible(true);
    }

    public static String getUsernameFromID(int userId)
    {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == userId) {
                return usuario.getUsername();
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado com o ID: " + userId);
    }
}