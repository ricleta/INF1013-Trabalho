package gameshop.ui;

import gameshop.models.Usuario;
import gameshop.models.JogoBibliotecaUsuario;
import gameshop.models.JogoCarrinhoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private Usuario usuario;

    public TelaPrincipal(Usuario usuario) {
        this.usuario = usuario;

        // Configurações da janela
        setTitle("GameShop - Bem-vindo, " + usuario.getUsername());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel superior (cabeçalho)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 144, 255));
        JLabel welcomeLabel = new JLabel("Bem-vindo, " + usuario.getUsername() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        headerPanel.add(welcomeLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Painel central (área de conteúdo)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botões para funcionalidades
        JButton btnBiblioteca = new JButton("Minha Biblioteca");
        JButton btnLoja = new JButton("Loja");
        JButton btnCarrinho = new JButton("Carrinho");
        JButton btnSair = new JButton("Sair");

        // Estilizando botões
        btnBiblioteca.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLoja.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCarrinho.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSair.setFont(new Font("Arial", Font.PLAIN, 16));

        // Adicionando botões ao painel central
        contentPanel.add(btnBiblioteca);
        contentPanel.add(btnLoja);
        contentPanel.add(btnCarrinho);
        contentPanel.add(btnSair);

        add(contentPanel, BorderLayout.CENTER);

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuArquivo.add(menuItemSair);
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);

        // Ações dos botões
        btnBiblioteca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirBiblioteca();
            }
        });

        btnLoja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TelaPrincipal.this, "Abrindo loja...");
                // Futuro: new TelaLoja();
            }
        });

        btnCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirCarrinho();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaLogin();
            }
        });

        menuItemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaLogin();
            }
        });

        setVisible(true);
    }

    private void exibirBiblioteca() {
        StringBuilder mensagem = new StringBuilder("Biblioteca de " + usuario.getUsername() + ":\n");
        for (JogoBibliotecaUsuario jogo : usuario.getBiblioteca()) {
            mensagem.append(String.format("Jogo ID: %d, Horas: %.1f, Última jogada: %s, Favorito: %b\n",
                    jogo.getIdJogo(), jogo.getHorasJogadas(), jogo.getUltimaDataJogado(), jogo.isFavorito()));
        }
        JOptionPane.showMessageDialog(this, mensagem.toString(), "Minha Biblioteca", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirCarrinho() {
        StringBuilder mensagem = new StringBuilder("Carrinho de " + usuario.getUsername() + ":\n");
        for (JogoCarrinhoUsuario jogo : usuario.getCarrinhos()) {
            mensagem.append(String.format("Jogo ID: %d, Preço: %.2f\n", jogo.getIdJogo(), jogo.getPreco()));
        }
        JOptionPane.showMessageDialog(this, mensagem.toString(), "Carrinho", JOptionPane.INFORMATION_MESSAGE);
    }
}