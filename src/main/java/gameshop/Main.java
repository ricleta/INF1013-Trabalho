package gameshop;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;

import java.util.ArrayList;

import gameshop.models.Usuario;
import gameshop.ui.*;
import gameshop.models.*;

public class Main {
    private static Jogo[] jogos;
    private static Usuario[] usuarios;
    private static Catalogo[] catalogos;
    private static Catalogo catalogoAtual;
    private static ArrayList<Compra> compras;

    public static void main(String[] args) {
        // Configura o Look and Feel para FlatLaf Darcula
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put("MenuItem.selectionBackground", UIManager.getColor("Button.background"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        usuarios = JSONHandler.readJSON("usuarios.json", Usuario[].class);
        final Usuario usuarioLogado = usuarios[0]; // Exemplo: usando o primeiro usuário do JSON

        jogos = JSONHandler.readJSON("jogos.json", Jogo[].class);

        catalogos = JSONHandler.readJSON("catalogos.json", Catalogo[].class);
        catalogoAtual = catalogos[0]; // Exemplo: usando o primeiro catálogo do JSON
    
        Compra[] storedCompras = JSONHandler.readJSON("compras.json", Compra[].class);
        compras = new ArrayList<>();
        for (Compra compra : storedCompras) {
            compras.add(compra);
        }

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

    public static Usuario getUsuario(int idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public static int getIdUsuario(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario.getIdUsuario();
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado com o nome: " + username);
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

    public static void showCompra(Usuario usuario, int usuarioPresenteadoID) {
        TelaCompra telaCompra = new TelaCompra(usuario, usuarioPresenteadoID);
        telaCompra.setVisible(true);
    }

    public static void addJogoToBiblioteca(Usuario usuario, JogoCarrinho jogoCarrinho) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo ao adicionar jogo à biblioteca.");
        }

        if (jogoCarrinho == null) {
            throw new IllegalArgumentException("Carrinho não pode ser nulo ou vazio ao adicionar jogo à biblioteca.");
        }

        usuario.addJogoToBiblioteca(Main.getJogo(jogoCarrinho.getIdJogo()));
    }

    public static void showCarrinho(Usuario usuario) {
        TelaCarrinho telaCarrinho = new TelaCarrinho(usuario);
        telaCarrinho.setVisible(true);
    }

    public static void addJogoToCarrinho(Usuario usuario, Jogo jogo) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo ao adicionar ao carrinho.");
        }
        if (jogo == null) {
            throw new IllegalArgumentException("Jogo não pode ser nulo ao adicionar ao carrinho.");
        }

        double preco = catalogoAtual.getPrecoJogo(jogo.getId());
        
        if (preco < 0) {
            throw new IllegalArgumentException("Jogo não encontrado no catálogo.");
        }

        JogoCarrinho novoJogoCarrinho = new JogoCarrinho(jogo.getId(), preco);
        usuario.addJogoToCarrinho(novoJogoCarrinho);
        updateUsuarios();
    }

    public static void removeJogoFromCarrinho(Usuario usuario, JogoCarrinho jogoCarrinho) {
        if (usuario == null || jogoCarrinho == null) {
            throw new IllegalArgumentException("Usuário ou jogo não podem ser nulos ao remover do carrinho.");
        }

        usuario.removeJogoFromCarrinho(jogoCarrinho);
        updateUsuarios();
    }

    public static void updateUsuarios() {
        JSONHandler.writeJSON("usuarios.json", usuarios);
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

    public static void storeCompra(Compra compra) {
        compras.add(compra);
        JSONHandler.writeJSON("compras.json", compras.toArray(new Compra[0]));
    }
}