package gameshop;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;

import gameshop.models.Usuario;
import gameshop.ui.*;
import gameshop.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Configura o Look and Feel para FlatLaf Darcula
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put("MenuItem.selectionBackground", UIManager.getColor("Button.background"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Usuario[] usuarios = JSONReader.readJSON("usuarios.json", Usuario[].class);
        final Usuario usuarioLogado = usuarios[0]; // Exemplo: usando o primeiro usuário do JSON

        Jogo[] jogos = JSONReader.readJSON("jogos.json", Jogo[].class);
        Jogo jogoExemplo = jogos[0]; // Exemplo: usando o primeiro jogo do JSON

        // Usuario usuarioLogado = createMockUsuario(); // Usando o mock de usuário para testes
        
        // Inicia a aplicação
        // TelaCatalogo telaCatalogo = new TelaCatalogo(usuarioLogado); // Exemplo: usando o primeiro usuário do JSON
        // telaCatalogo.setVisible(true);

        TelaJogoCatalogo telaJogoCatalogo = new TelaJogoCatalogo(jogoExemplo, usuarioLogado);
        telaJogoCatalogo.setVisible(true);
    }

    public static Usuario createMockUsuario() {
        // Mock data for basic user info
        int idUsuario = 999;
        String username = "MockUser";
        String email = "mock.user@gameshop.com";
        String senha = "mockpassword"; // In a real app, never store plain passwords
        LocalDate dataNascimento = LocalDate.of(1990, 5, 15);

        // Mock data for lists
        List<Integer> amigos = new ArrayList<>();
        amigos.add(101); // Example friend ID
        amigos.add(102);

        List<Integer> solicitacoesPendentes = new ArrayList<>();
        solicitacoesPendentes.add(201); // Example pending request ID

        List<JogoBibliotecaUsuario> biblioteca = new ArrayList<>();
        // Add a mock game to the library
        biblioteca.add(new JogoBibliotecaUsuario(
                1, // idJogo
                50.5, // horasJogadas
                LocalDate.of(2024, 1, 20), // ultimaDataJogado
                true, // favorito
                new ArrayList<>(List.of(1, 2)) // idConquistas
        ));
        biblioteca.add(new JogoBibliotecaUsuario(
                2, // idJogo
                15.0, // horasJogadas
                LocalDate.of(2024, 3, 10), // ultimaDataJogado
                false, // favorito
                new ArrayList<>(List.of(1)) // idConquistas
        ));

        List<JogoCarrinhoUsuario> carrinhos = new ArrayList<>();
        // Add a mock game to the cart
        carrinhos.add(new JogoCarrinhoUsuario(
                3, // idJogo
                29.99 // preco
        ));
        carrinhos.add(new JogoCarrinhoUsuario(
                4, // idJogo
                19.50 // preco
        ));

        // Create and return the mock Usuario object
        return new Usuario(
                idUsuario,
                username,
                email,
                senha,
                dataNascimento,
                amigos,
                solicitacoesPendentes,
                biblioteca,
                carrinhos
        );
    }
}