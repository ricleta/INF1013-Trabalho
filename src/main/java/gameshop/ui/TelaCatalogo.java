package gameshop.ui;

import gameshop.Main;
import gameshop.models.Jogo;
import gameshop.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JPanel bannerSliderPanel = new JPanel();
        bannerSliderPanel.setLayout(new BorderLayout());
        bannerSliderPanel.setBackground(Color.DARK_GRAY);
        bannerSliderPanel.setPreferredSize(new Dimension(1200, 400));

        bannerImages = new ImageIcon[4];
        try {
            bannerImages[0] = loadAndScaleImage("/Banners_Catalogo/Banner_1.png", "Banner_1.png", 1200, 400);
            bannerImages[1] = loadAndScaleImage("/Banners_Catalogo/Banner_2.jpg", "Banner_2.jpg", 1200, 400);
            bannerImages[2] = loadAndScaleImage("/Banners_Catalogo/Banner_3.jpg", "Banner_3.jpg", 1200, 400);
            bannerImages[3] = loadAndScaleImage("/Banners_Catalogo/Banner_4.jpg", "Banner_4.jpg", 1200, 400);
        } catch (Exception e) {
            for (int i = 0; i < bannerImages.length; i++) {
                bannerImages[i] = new ImageIcon(new BufferedImage(1200, 400, BufferedImage.TYPE_INT_ARGB));
            }
        }

        bannerLabel = new JLabel(bannerImages[currentBannerIndex], SwingConstants.CENTER);
        bannerSliderPanel.add(bannerLabel, BorderLayout.CENTER);

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

        bannerSliderPanel.add(prevButton, BorderLayout.WEST);
        bannerSliderPanel.add(nextButton, BorderLayout.EAST);

        // Timer para troca automática do banner (15 segundos)
        bannerTimer = new Timer(15000, e -> {
            currentBannerIndex = (currentBannerIndex + 1) % bannerImages.length;
            bannerLabel.setIcon(bannerImages[currentBannerIndex]);
        });
        bannerTimer.start();

        mainPanel.add(bannerSliderPanel);

        // Painel de jogos (catálogo)
        JPanel gamesPanel = new JPanel();
        gamesPanel.setLayout(new GridLayout(3, 3, 10, 10));
        gamesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gamesPanel.setBackground(Color.DARK_GRAY);

        // Dados simulados de jogos
        String[] gameNames = {"The Legend of Zelda: Breath of the Wild", "Jogo 2", "Jogo 3", "Jogo 4", "Jogo 5", "Jogo 6", "Jogo 7", "Jogo 8", "Jogo 9"};
        String[] discounts = {"-5%", "-10%", "-7%", "-8%", "-9%", "-18%", "-6%", "-5%", "-3%"};
        double[] prices = {59.99, 49.99, 39.99, 29.99, 19.99, 69.99, 44.99, 54.99, 34.99};

        for (int i = 0; i < 9; i++) {
            JPanel gameCard = new JPanel();
            gameCard.setLayout(new BorderLayout());
            gameCard.setBackground(Color.DARK_GRAY);

            ImageIcon gameImageIcon = new ImageIcon();
            if (i == 0) { // Primeiro slot
                try {
                    gameImageIcon = loadAndScaleImage("/Jogo_Zelda_Breath_of_the_Wild/Imagem_1.jpeg", "Imagem_1.jpeg", 320, 200);
                } catch (Exception e) {
                    gameImageIcon = new ImageIcon(new BufferedImage(320, 200, BufferedImage.TYPE_INT_ARGB));
                }
            }

            JLabel gameImage = new JLabel(gameImageIcon);
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

            // Tornar o card clicável (apenas para o Zelda no índice 0) e mudar o cursor
            if (i == 0) {
                gameCard.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Redirecionar para TelaJogoCatalogo com o jogo Zelda
                        Jogo jogo = Main.getJogo(1);
                        TelaJogoCatalogo telaJogo = new TelaJogoCatalogo(jogo, usuario);
                        telaJogo.setVisible(true);
                        dispose(); // Fecha a tela atual
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        gameCard.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Muda para cursor de mão ao passar por cima
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        gameCard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Volta para o cursor padrão ao sair
                    }
                });
            }

            gamesPanel.add(gameCard);
        }

        JScrollPane scrollPane = new JScrollPane(gamesPanel);
        mainPanel.add(scrollPane);

        add(mainPanel);
        pack();
    }
}