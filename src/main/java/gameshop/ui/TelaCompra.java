// INF1013-Trabalho/src/main/java/gameshop/ui/TelaCompra.java
package gameshop.ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import gameshop.Main; // Import Main to access game data and payment methods
import gameshop.models.JogoCarrinho;
import gameshop.models.Usuario;
import gameshop.models.Jogo;
import java.util.List;

public class TelaCompra extends Tela {
    private static final String TIPO_TELA = "COMPRA"; // Define a type for this screen

    public TelaCompra(Usuario usuario, int usuarioPresenteadoID) {
        super(usuario, TIPO_TELA); // Pass the TIPO_TELA constant
        setTitle("Tela de Compra - " + usuario.getUsername());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(45, 45, 45)); // Dark background for the main panel

        JPanel headerPanel = setHeaderPanel();
        mainPanel.add(headerPanel);

        // Main Content Panel (Payment Method on left, Order Summary on right)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2, 50, 0)); // 1 row, 2 columns, 50px horizontal gap
        contentPanel.setBackground(new Color(45, 45, 45));
        contentPanel.setBorder(new EmptyBorder(30, 50, 30, 50)); // Padding around content

        // Left Column: Payment Method
        JPanel paymentMethodPanel = new JPanel();
        paymentMethodPanel.setLayout(new BoxLayout(paymentMethodPanel, BoxLayout.Y_AXIS));
        paymentMethodPanel.setBackground(new Color(55, 55, 55));
        paymentMethodPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(70, 70, 70), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel paymentMethodLabel = new JLabel("Método de Pagamento");
        paymentMethodLabel.setFont(new Font("Arial", Font.BOLD, 18));
        paymentMethodLabel.setForeground(Color.WHITE);
        paymentMethodLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        paymentMethodPanel.add(paymentMethodLabel);
        paymentMethodPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        JComboBox<String> paymentMethodComboBox = new JComboBox<>(new String[]{
                "Cartão de Crédito",
                "Cartão de Débito",
                "PIX",
        });
        paymentMethodComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        paymentMethodComboBox.setBackground(new Color(70, 70, 70));
        paymentMethodComboBox.setForeground(Color.WHITE);
        paymentMethodComboBox.setMaximumSize(new Dimension(300, 35)); // Set preferred size
        paymentMethodComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        paymentMethodPanel.add(paymentMethodComboBox);
        paymentMethodPanel.add(Box.createVerticalGlue()); // Pushes content to the top

        contentPanel.add(paymentMethodPanel);

        // Right Column: Order Summary
        JPanel orderSummaryPanel = new JPanel();
        orderSummaryPanel.setLayout(new BoxLayout(orderSummaryPanel, BoxLayout.Y_AXIS));
        orderSummaryPanel.setBackground(new Color(55, 55, 55));
        orderSummaryPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(70, 70, 70), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        // List of items
        double totalCompra = 0;
        for (JogoCarrinho item : usuario.getCarrinho()) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(new Color(55, 55, 55));
            JLabel itemNameLabel = new JLabel(Main.getJogo(item.getIdJogo()).getNome()); // Fetch game name
            itemNameLabel.setForeground(Color.WHITE);
            itemNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            itemPanel.add(itemNameLabel, BorderLayout.WEST);

            JLabel itemPriceLabel = new JLabel(String.format("$%.2f", item.getPreco()));
            itemPriceLabel.setForeground(Color.WHITE);
            itemPriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            itemPanel.add(itemPriceLabel, BorderLayout.EAST);
            orderSummaryPanel.add(itemPanel);
            orderSummaryPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between items
            totalCompra += item.getPreco();
        }

        // Add a separator line
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.GRAY);
        separator.setBackground(new Color(70, 70, 70));
        orderSummaryPanel.add(separator);
        orderSummaryPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer

        // Total price
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(new Color(55, 55, 55));
        JLabel totalLabel = new JLabel("Total");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(Color.WHITE);
        totalPanel.add(totalLabel, BorderLayout.WEST);

        JLabel totalPriceLabel = new JLabel(String.format("$%.2f", totalCompra));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalPriceLabel.setForeground(new Color(255, 255, 102)); // Yellowish color for price
        totalPanel.add(totalPriceLabel, BorderLayout.EAST);
        orderSummaryPanel.add(totalPanel);
        orderSummaryPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // Buy button
        JButton buyButton = new JButton("Comprar");
        buyButton.setFont(new Font("Arial", Font.BOLD, 18));
        buyButton.setBackground(new Color(75, 175, 75)); // Green button
        buyButton.setForeground(Color.WHITE);
        buyButton.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align button to the right
        buyButton.setMaximumSize(new Dimension(150, 40)); // Fixed size for the button
        buyButton.addActionListener(e -> {
            if (usuarioPresenteadoID > 0) {
                realizarCompra(Main.getUsuario(usuarioPresenteadoID), usuario.getCarrinho());
            } else {
                // Regular purchase
                realizarCompra(usuario);
            }
        });
        
        // Wrap the button in a panel to align it to the right
        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setBackground(new Color(55, 55, 55));
        buttonWrapper.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonWrapper.add(buyButton);
        orderSummaryPanel.add(buttonWrapper);

        orderSummaryPanel.add(Box.createVerticalGlue()); // Pushes content to the top

        contentPanel.add(orderSummaryPanel);
        mainPanel.add(contentPanel);

        // Add a scroll pane for the main panel if content exceeds screen height
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // No horizontal scroll

        add(scrollPane);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizes the window
    }

    private void realizarCompra(Usuario usuario) {
        for (JogoCarrinho item : usuario.getCarrinho()) {
            Jogo jogo = Main.getJogo(item.getIdJogo());

            if (usuario.hasJogoInBiblioteca(jogo))
            {
                JOptionPane.showMessageDialog(this, "Jogo já está na biblioteca do usuario.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Main.addJogoToBiblioteca(usuario, item);
        }

        usuario.clearCarrinho();
        Main.updateUsuarios();
        Main.showCatalogo(usuario); // Redirect to catalog screen after purchase
        dispose();
    }

    private void realizarCompra(Usuario usuarioPresenteado, List<JogoCarrinho> carrinho) {
        for (JogoCarrinho item : carrinho) {
            Jogo jogo = Main.getJogo(item.getIdJogo());
            if (usuarioPresenteado.hasJogoInBiblioteca(jogo))
            {
                JOptionPane.showMessageDialog(this, "Jogo já está na biblioteca do usuario.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (usuarioPresenteado.hasJogoInCarrinho(jogo))
            {
                JogoCarrinho jogoCarrinho = usuarioPresenteado.getCarrinho().stream().filter(j -> j.getIdJogo() == jogo.getId()).findFirst().orElse(null);

                usuarioPresenteado.removeJogoFromCarrinho(jogoCarrinho);
            }
            Main.addJogoToBiblioteca(usuarioPresenteado, item);
            JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }

        usuario.clearCarrinho();
        Main.updateUsuarios();
        Main.showCatalogo(usuario); // Redirect to catalog screen after purchase
        dispose();
    }
}