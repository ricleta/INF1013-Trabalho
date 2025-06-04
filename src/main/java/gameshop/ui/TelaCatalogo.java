package gameshop.ui;

import gameshop.models.Usuario;
import gameshop.models.JogoBibliotecaUsuario;
import gameshop.models.JogoCarrinhoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCatalogo extends JFrame {

    private Usuario usuario;
    private Timer bannerTimer;
    private int currentBannerIndex = 0;
    private JLabel bannerLabel;
    private ImageIcon[] bannerImages;

    public TelaCatalogo(Usuario usuario) {
        this.usuario = usuario;

        // Configurações da janela
        setTitle("GameShop - Bem-vindo, " + usuario.getUsername());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // TODO Change to BoxLayout (vertical)
        setLayout(new BorderLayout(0, 0));

        // Painel superior (cabeçalho)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 30, 30));
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Barra de busca no cabeçalho
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(30, 30, 30));
        JTextField searchField = new JTextField("Procurar jogo", 20);
        searchField.setForeground(Color.GRAY);
        searchPanel.add(searchField);
        headerPanel.add(searchPanel, BorderLayout.WEST);

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOpcoes = new JMenu("Opções");
        JMenuItem menuItemMeuPerfil = new JMenuItem("Meu Perfil");
        JMenuItem menuItemBiblioteca = new JMenuItem("Biblioteca");
        JMenuItem menuItemContato = new JMenuItem("Contato");
        JMenuItem menuItemSobre = new JMenuItem("Sobre a Plataforma");
        JMenuItem menuItemPolitica = new JMenuItem("Política de Privacidade");
        JMenuItem menuItemSuporte = new JMenuItem("Suporte");
        JMenuItem menuItemOutros = new JMenuItem("Outros");

        menuOpcoes.add(menuItemMeuPerfil);
        menuOpcoes.add(menuItemBiblioteca);
        menuOpcoes.add(menuItemContato);
        menuOpcoes.add(menuItemSobre);
        menuOpcoes.add(menuItemPolitica);
        menuOpcoes.add(menuItemSuporte);
        menuOpcoes.add(menuItemOutros);

        menuBar.add(menuOpcoes);
        setJMenuBar(menuBar);

        // Ações dos itens de menu
        menuItemBiblioteca.addActionListener(e -> exibirBiblioteca());
        menuItemMeuPerfil.addActionListener(e -> System.out.println("Você acessou Meu Perfil"));
        menuItemContato.addActionListener(e -> System.out.println("Você acessou Contato"));
        menuItemSobre.addActionListener(e -> System.out.println("Você acessou Sobre a Plataforma"));
        menuItemPolitica.addActionListener(e -> System.out.println("Você acessou Política de Privacidade"));
        menuItemSuporte.addActionListener(e -> System.out.println("Você acessou Suporte"));
        menuItemOutros.addActionListener(e -> System.out.println("Você acessou Outros"));
        
        headerPanel.add(new JPanel(), BorderLayout.EAST); // Placeholder para manter o layout

        add(headerPanel, BorderLayout.NORTH);

        // Painel do banner com slider
        JPanel bannerPanel = new JPanel();
        bannerPanel.setLayout(new BorderLayout());
        bannerPanel.setBackground(Color.DARK_GRAY);

        // Simulando imagens do banner
        bannerImages = new ImageIcon[3];
        for (int i = 0; i < 3; i++) {
            bannerImages[i] = new ImageIcon(); // Placeholder para imagens
        }

        bannerLabel = new JLabel(bannerImages[currentBannerIndex], SwingConstants.CENTER);
        bannerPanel.add(bannerLabel, BorderLayout.CENTER);

        // Botões de navegação do banner
        JButton prevButton = new JButton("<");
        prevButton.setBackground(new Color(0, 0, 0, 100));
        prevButton.setForeground(Color.WHITE);
        prevButton.setBorderPainted(false);
        prevButton.addActionListener(e -> {
            currentBannerIndex = (currentBannerIndex - 1 + bannerImages.length) % bannerImages.length;
            bannerLabel.setIcon(bannerImages[currentBannerIndex]);
            bannerTimer.restart();
        });

        JButton nextButton = new JButton(">");
        nextButton.setBackground(new Color(0, 0, 0, 100));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBorderPainted(false);
        nextButton.addActionListener(e -> {
            currentBannerIndex = (currentBannerIndex + 1) % bannerImages.length;
            bannerLabel.setIcon(bannerImages[currentBannerIndex]);
            bannerTimer.restart();
        });

        bannerPanel.add(prevButton, BorderLayout.WEST);
        bannerPanel.add(nextButton, BorderLayout.EAST);

        // Timer para troca automática do banner (15 segundos)
        bannerTimer = new Timer(15000, e -> {
            currentBannerIndex = (currentBannerIndex + 1) % bannerImages.length;
            bannerLabel.setIcon(bannerImages[currentBannerIndex]);
        });
        bannerTimer.start();

        add(bannerPanel, BorderLayout.CENTER);

        // Painel de jogos (catálogo)
        JPanel gamesPanel = new JPanel();
        gamesPanel.setLayout(new GridLayout(3, 3, 10, 10));
        gamesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gamesPanel.setBackground(Color.DARK_GRAY);

        // Dados simulados de jogos
        String[] gameNames = {"Jogo 1", "Jogo 2", "Jogo 3", "Jogo 4", "Jogo 5", "Jogo 6", "Jogo 7", "Jogo 8", "Jogo 9"};
        String[] discounts = {"-5%", "-10%", "-7%", "-8%", "-9%", "-18%", "-6%", "-5%", "-3%"};
        double[] prices = {59.99, 49.99, 39.99, 29.99, 19.99, 69.99, 44.99, 54.99, 34.99};

        for (int i = 0; i < 9; i++) {
            JPanel gameCard = new JPanel();
            gameCard.setLayout(new BorderLayout());
            gameCard.setBackground(Color.DARK_GRAY);

            JLabel gameImage = new JLabel(new ImageIcon()); // Placeholder para imagem do jogo
            gameCard.add(gameImage, BorderLayout.CENTER);

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            infoPanel.setBackground(Color.LIGHT_GRAY);

            JLabel discountLabel = new JLabel(discounts[i]);
            discountLabel.setForeground(Color.WHITE);
            discountLabel.setBackground(Color.GREEN);
            discountLabel.setOpaque(true);
            discountLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

            JLabel priceLabel = new JLabel(String.format("R$%.2f", prices[i]));
            priceLabel.setForeground(Color.WHITE);

            infoPanel.add(discountLabel);
            infoPanel.add(priceLabel);

            gameCard.add(infoPanel, BorderLayout.SOUTH);
            gamesPanel.add(gameCard);
        }

        JScrollPane scrollPane = new JScrollPane(gamesPanel);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
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