package gameshop.ui;

import gameshop.Main;
import gameshop.models.Jogo;
import gameshop.models.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCatalogo extends Tela {
    private final static String TIPO_TELA = "CATALOGO";
    private Timer bannerTimer;
    private int currentBannerIndex = 0;
    private JLabel bannerLabel;
    private ImageIcon[] bannerImages;

    public TelaCatalogo(Usuario usuario) {
        super(usuario, TIPO_TELA);

        // Configurações da janela
        setTitle("GameShop - Bem-vindo, " + usuario.getUsername());

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
            bannerImages[0] = loadAndScaleImage("/Banners_Catalogo/Banner_1.png", 1200, 400);
            bannerImages[1] = loadAndScaleImage("/Banners_Catalogo/Banner_2.jpg", 1200, 400);
            bannerImages[2] = loadAndScaleImage("/Banners_Catalogo/Banner_3.jpg", 1200, 400);
            bannerImages[3] = loadAndScaleImage("/Banners_Catalogo/Banner_4.jpg", 1200, 400);
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

        // Obter jogos do catálogo atual
        gameshop.models.JogoCatalogo[] jogosCatalogo = Main.getJogosCatalogoAtual();

        for (gameshop.models.JogoCatalogo jogoCatalogo : jogosCatalogo) {
            Jogo jogo = Main.getJogo(jogoCatalogo.getIdJogo());

            if (jogo == null) {
                continue; // Pula se o jogo não for encontrado
            }

            JPanel gameCard = new JPanel();
            gameCard.setLayout(new BorderLayout());
            gameCard.setBackground(new Color(50, 50, 50)); // Slightly lighter dark gray for cards
            gameCard.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70))); // Add a subtle border

            ImageIcon gameImageIcon = new ImageIcon();
            // Tenta carregar a primeira imagem do slide do jogo
            String[] fotosSlide = jogo.getDescricaoCatalogo().getFotosSlide();
            if (fotosSlide != null && fotosSlide.length > 0) {
                gameImageIcon = loadAndScaleImage(fotosSlide[0], 320, 200);
            } else {
                // Placeholder se não houver imagem
                gameImageIcon = new ImageIcon(new BufferedImage(320, 200, BufferedImage.TYPE_INT_ARGB));
            }

            JLabel gameImage = new JLabel(gameImageIcon);
            gameCard.add(gameImage, BorderLayout.CENTER);

            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.setBackground(new Color(60, 60, 60)); // Darker gray for info panel
            infoPanel.setBorder(new EmptyBorder(5, 10, 5, 10)); // Padding

            JLabel nameLabel = new JLabel(jogo.getNome());
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            infoPanel.add(nameLabel, BorderLayout.WEST);

            // Placeholder for discount (you might need to calculate this dynamically)
            JLabel discountLabel = new JLabel("-X%"); // Replace with actual discount if available
            discountLabel.setForeground(Color.WHITE);
            discountLabel.setBackground(Color.GREEN);
            discountLabel.setOpaque(true);
            discountLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
            // infoPanel.add(discountLabel, BorderLayout.CENTER); // Add discount if needed

            JLabel priceLabel = new JLabel(String.format("R$%.2f", jogoCatalogo.getPreco()));
            priceLabel.setForeground(Color.WHITE);
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            infoPanel.add(priceLabel, BorderLayout.EAST);

            gameCard.add(infoPanel, BorderLayout.SOUTH); // Add infoPanel to the bottom of the card

            gameCard.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        gameCard.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Muda para cursor de mão ao passar por cima
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        gameCard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Volta para o cursor padrão ao sair
                    }
                });

            // Adicionar ActionListener para abrir a tela de detalhes do jogo
            gameCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TelaJogoCatalogo telaJogo = new TelaJogoCatalogo(jogo, usuario);
                    telaJogo.setVisible(true);
                    dispose(); // Fecha a tela de catálogo ao abrir a tela de jogo
                }
            });

            gamesPanel.add(gameCard);
        }

        mainPanel.add(gamesPanel);
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        add(scrollPane);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the window to full screen
    }
}