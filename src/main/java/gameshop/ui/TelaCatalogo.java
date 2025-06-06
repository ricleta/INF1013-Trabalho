package gameshop.ui;

import gameshop.models.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaCatalogo extends Tela {

    private Timer bannerTimer;
    private int currentBannerIndex = 0;
    private JLabel bannerLabel;
    private ImageIcon[] bannerImages;

    public TelaCatalogo(Usuario usuario) {
        super(usuario);


        // Configurações da janela
        setTitle("GameShop - Bem-vindo, " + usuario.getUsername());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel headerPanel = setHeaderPanel();

        mainPanel.add(headerPanel);

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

        mainPanel.add(bannerPanel);

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

            JLabel nameLabel = new JLabel(gameNames[i]);

            JLabel discountLabel = new JLabel(discounts[i]);
            discountLabel.setForeground(Color.WHITE);
            discountLabel.setBackground(Color.GREEN);
            discountLabel.setOpaque(true);
            discountLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

            JLabel priceLabel = new JLabel(String.format("R$%.2f", prices[i]));
            priceLabel.setForeground(Color.WHITE);

            infoPanel.add(nameLabel);
            infoPanel.add(discountLabel);
            infoPanel.add(priceLabel);

            gameCard.add(infoPanel, BorderLayout.SOUTH);
            gamesPanel.add(gameCard);
        }

        JScrollPane scrollPane = new JScrollPane(gamesPanel);
        mainPanel.add(scrollPane);

        add(mainPanel);

        pack();
    }
}