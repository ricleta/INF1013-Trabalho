package gameshop.ui;

import gameshop.models.Jogo;
import gameshop.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import java.awt.image.BufferedImage;
import javax.swing.border.TitledBorder;

public class TelaJogoCatalogo extends Tela {

    private Jogo jogo; // Store the Jogo object

    // Slider de imagens
    private Timer bannerTimer;
    private int currentBannerIndex = 0;
    private JLabel bannerLabel;
    private ImageIcon[] bannerImages;

    // Slider de avaliacoes
    private Timer reviewTimer;
    private int currentReviewIndex = 0;
    private JLabel reviewLabel;
    private String[] reviewContents; // Placeholder for review content

    public TelaJogoCatalogo(Jogo jogo, Usuario usuario) {
        super(usuario);
        this.jogo = jogo; // Assign the passed Jogo object

        // Basic JFrame settings
        setTitle("GameShop - Jogo: " + jogo.getNome());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE to close only this window
        setLocationRelativeTo(null); // Center the window

        // Main panel using BoxLayout for vertical organization
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(45, 45, 45)); // Dark background

        // 1. Header Panel (from Tela.java)
        JPanel headerPanel = setHeaderPanel(); // Reusing the method from the superclass
        mainPanel.add(headerPanel);

        // 2. Game Image Slider (similar to banner in TelaCatalogo)
        JPanel imageSliderPanel = new JPanel();
        imageSliderPanel.setLayout(new BorderLayout());
        imageSliderPanel.setBackground(new Color(60, 60, 60));
        imageSliderPanel.setPreferredSize(new Dimension(800, 400)); // Set a preferred size

        // Placeholder banner images (replace with actual game images if available)
        bannerImages = new ImageIcon[3];
        try {
            bannerImages[0] = loadAndScaleImage("/Jogo_Zelda_Breath_of_the_Wild/Imagem_1.jpeg", "Imagem_1.jpeg", 800, 400);
            bannerImages[1] = loadAndScaleImage("/Jogo_Zelda_Breath_of_the_Wild/Imagem_2.jpg", "Imagem_2.jpg", 800, 400);
            bannerImages[2] = loadAndScaleImage("/Jogo_Zelda_Breath_of_the_Wild/Imagem_3.jpg", "Imagem_3.jpg", 800, 400);
        } catch (Exception e) {
            for (int i = 0; i < bannerImages.length; i++) {
                bannerImages[i] = new ImageIcon(new BufferedImage(800, 400, BufferedImage.TYPE_INT_ARGB));
            }
        }
        
        bannerLabel = new JLabel(bannerImages[currentBannerIndex], SwingConstants.CENTER);
        imageSliderPanel.add(bannerLabel, BorderLayout.CENTER);

        JButton prevImageButton = new JButton("<");
        styleSliderButton(prevImageButton);
        prevImageButton.addActionListener(e -> {
            currentBannerIndex = (currentBannerIndex - 1 + bannerImages.length) % bannerImages.length;
            bannerLabel.setIcon(bannerImages[currentBannerIndex]);
            bannerTimer.restart();
        });

        JButton nextImageButton = new JButton(">");
        styleSliderButton(nextImageButton);
        nextImageButton.addActionListener(e -> {
            currentBannerIndex = (currentBannerIndex + 1) % bannerImages.length;
            bannerLabel.setIcon(bannerImages[currentBannerIndex]);
            bannerTimer.restart();
        });

        imageSliderPanel.add(prevImageButton, BorderLayout.WEST);
        imageSliderPanel.add(nextImageButton, BorderLayout.EAST);

        // Timer for automatic banner rotation (e.g., every 5 seconds)
        bannerTimer = new Timer(5000, e -> {
            currentBannerIndex = (currentBannerIndex + 1) % bannerImages.length;
            bannerLabel.setIcon(bannerImages[currentBannerIndex]);
        });
        bannerTimer.start();
        mainPanel.add(imageSliderPanel);

        // 3. Game Details Panel (description on left, price/cart on right)
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, 2, 20, 0)); // 1 row, 2 columns, 20px horizontal gap
        detailsPanel.setBackground(new Color(45, 45, 45));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Left Column: Game Description
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBackground(new Color(55, 55, 55));
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel gameTitleLabel = new JLabel(jogo.getNome());
        gameTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameTitleLabel.setForeground(Color.WHITE);
        gameTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.add(gameTitleLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer

        // Assuming Jogo has a method to get its main description text
        JTextArea gameDescriptionArea = new JTextArea("Descrição detalhada do jogo " + jogo.getNome() + ".\n" +
                "Este é um placeholder para uma descrição mais longa que detalha a jogabilidade, história, etc. " +
                "Você pode carregar isso do modelo 'DescricaoJogoCatalogo' do objeto Jogo.\n\n" +
                "Plataformas: " + String.join(", ", jogo.getPlataformas()) + "\n" +
                "Controles: " + String.join(", ", jogo.getControles())); // Using getPlataformas and getControles directly from Jogo
        gameDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        gameDescriptionArea.setForeground(new Color(200, 200, 200));
        gameDescriptionArea.setBackground(new Color(55, 55, 55));
        gameDescriptionArea.setLineWrap(true);
        gameDescriptionArea.setWrapStyleWord(true);
        gameDescriptionArea.setEditable(false);
        gameDescriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.add(gameDescriptionArea);

        // Right Column: Price and Add to Cart
        JPanel purchasePanel = new JPanel();
        purchasePanel.setLayout(new BoxLayout(purchasePanel, BoxLayout.Y_AXIS));
        purchasePanel.setBackground(new Color(55, 55, 55));
        purchasePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        purchasePanel.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align right column to right

        JLabel priceLabel = new JLabel("R$ XX.XX"); // Placeholder price
        priceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        priceLabel.setForeground(new Color(255, 255, 102)); // Yellowish color for price
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        purchasePanel.add(priceLabel);
        purchasePanel.add(Box.createRigidArea(new Dimension(0, 15))); // Spacer

        JButton addToCartButton = new JButton("Adicionar ao Carrinho");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 18));
        addToCartButton.setBackground(new Color(75, 175, 75)); // Green button
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, addToCartButton.getPreferredSize().height)); // Make button fill width
        addToCartButton.addActionListener(e -> {
            // Logic to add game to cart
            JOptionPane.showMessageDialog(this, "Jogo '" + jogo.getNome() + "' adicionado ao carrinho!", "Carrinho", JOptionPane.INFORMATION_MESSAGE);
            // You'd typically call a method on the Usuario or a CartManager here
        });
        purchasePanel.add(addToCartButton);

        detailsPanel.add(descriptionPanel);
        detailsPanel.add(purchasePanel);
        mainPanel.add(detailsPanel);

        // 4. Game Reviews Slider
        JPanel reviewSliderPanel = new JPanel();
        reviewSliderPanel.setLayout(new BorderLayout());
        reviewSliderPanel.setBackground(new Color(60, 60, 60));
        reviewSliderPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Avaliações", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), Color.WHITE));
        reviewSliderPanel.setPreferredSize(new Dimension(800, 250)); // Set a preferred size

        // Placeholder review content
        reviewContents = new String[]{
                "\"Uma experiência imersiva e gráficos de tirar o fôlego! Recomendo fortemente.\" - JogadorA",
                "\"A jogabilidade é viciante, mas a história principal é um pouco curta.\" - CríticoGames",
                "\"Não vale o preço cheio, espere uma promoção. Mas o multiplayer é divertido.\" - ReviewerXP",
                "\"Simplesmente o melhor jogo do ano! Não consigo parar de jogar.\" - FãDedicado"
        };

        reviewLabel = new JLabel(reviewContents[currentReviewIndex], SwingConstants.CENTER);
        reviewLabel.setForeground(new Color(220, 220, 220));
        reviewLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        reviewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        reviewLabel.setVerticalAlignment(SwingConstants.CENTER);   // Center text vertically
        reviewLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding for text
        reviewSliderPanel.add(reviewLabel, BorderLayout.CENTER);

        JButton prevReviewButton = new JButton("◀");
        styleSliderButton(prevReviewButton);
        prevReviewButton.addActionListener(e -> {
            currentReviewIndex = (currentReviewIndex - 1 + reviewContents.length) % reviewContents.length;
            reviewLabel.setText(reviewContents[currentReviewIndex]);
            reviewTimer.restart();
        });

        JButton nextReviewButton = new JButton("▶");
        styleSliderButton(nextReviewButton);
        nextReviewButton.addActionListener(e -> {
            currentReviewIndex = (currentReviewIndex + 1) % reviewContents.length;
            reviewLabel.setText(reviewContents[currentReviewIndex]);
            reviewTimer.restart();
        });

        reviewSliderPanel.add(prevReviewButton, BorderLayout.WEST);
        reviewSliderPanel.add(nextReviewButton, BorderLayout.EAST);

        // Timer for automatic review rotation (e.g., every 7 seconds)
        reviewTimer = new Timer(7000, e -> {
            currentReviewIndex = (currentReviewIndex + 1) % reviewContents.length;
            reviewLabel.setText(reviewContents[currentReviewIndex]);
        });
        reviewTimer.start();
        mainPanel.add(reviewSliderPanel);

        // Add mainPanel to the JFrame
        add(mainPanel);

        // Set size based on preferred sizes of components
        pack();
    }

    // Helper method to style slider buttons consistently
    private void styleSliderButton(JButton button) {
        button.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent black
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false); // Remove focus border
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
    }
}