package gameshop.ui;

import javax.swing.*;
import java.awt.*;

import gameshop.Main;
import java.util.List;
import gameshop.models.Jogo;
import gameshop.models.JogoCarrinho;
import gameshop.models.Usuario;

public class TelaCarrinho extends Tela {
    private static final String TIPO_TELA = "CARRINHO";

    public TelaCarrinho(Usuario usuario) {
        super(usuario, TIPO_TELA);
        setTitle("Carrinho de Compras - " + usuario.getUsername());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Configurar o cabeçalho
        JPanel headerPanel = setHeaderPanel();
        mainPanel.add(headerPanel);

        // Configurar o corpo do carrinho
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(new Color(50, 50, 50));
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setPreferredSize(new Dimension(1200, 800));

        List<JogoCarrinho> carrinhoUsuario = usuario.getCarrinho();

        JPanel carrinhoPanel = createItensCarrinhoPanel(carrinhoUsuario);
        bodyPanel.add(carrinhoPanel);

        JPanel compraPanel = createCompraPanel();
        bodyPanel.add(compraPanel);

        mainPanel.add(bodyPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
    }

    private void removeJogoDoCarrinho(Usuario usuario, JogoCarrinho jogoCarrinho) {
        if (usuario == null || jogoCarrinho == null) {
            throw new IllegalArgumentException("Usuário ou jogo não podem ser nulos ao remover do carrinho.");
        }
        
        Main.removeJogoFromCarrinho(usuario, jogoCarrinho);
        JOptionPane.showMessageDialog(this, "Jogo removido do carrinho com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createItensCarrinhoPanel(List<JogoCarrinho> carrinhoUsuario) {

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        if (carrinhoUsuario == null || carrinhoUsuario.isEmpty()) {
            JLabel emptyCartLabel = new JLabel("Seu carrinho está vazio.");
            emptyCartLabel.setForeground(Color.WHITE);
            emptyCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cartPanel.add(emptyCartLabel);
            return cartPanel; // Retorna o painel vazio
        }

        for (JogoCarrinho jogoCarrinho : carrinhoUsuario) {
            Jogo jogo = Main.getJogo(jogoCarrinho.getIdJogo());

            JPanel jogoPanel = new JPanel();
            jogoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            jogoPanel.setBackground(new Color(70, 70, 70));
            jogoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            
            JLabel jogoLabel = new JLabel(jogo.getNome());
            jogoPanel.add(jogoLabel);
            
            JLabel precoLabel = new JLabel(String.format("%.2f", jogoCarrinho.getPreco()));
            jogoPanel.add(precoLabel);

            JButton removerButton = new JButton("Remover");
            removerButton.addActionListener(e -> {
                removeJogoDoCarrinho(usuario, jogoCarrinho);
                
                SwingUtilities.invokeLater(() -> {
                    JPanel parentOfCartPanel = (JPanel) cartPanel.getParent();
                    if (parentOfCartPanel != null) {
                        parentOfCartPanel.removeAll();
                        parentOfCartPanel.add(createItensCarrinhoPanel(usuario.getCarrinho())); // Re-create the cart items
                        parentOfCartPanel.add(createCompraPanel()); // Re-create the purchase panel
                        parentOfCartPanel.revalidate();
                        parentOfCartPanel.repaint();
                    }
                });
            });

            jogoPanel.add(removerButton);
            jogoPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            jogoPanel.setMaximumSize(new Dimension(500, 50)); // Define a largura máxima do painel do jogo
            cartPanel.add(jogoPanel);
        }

        return cartPanel;
    }

    private JPanel createCompraPanel() {
        JPanel compraPanel = new JPanel();
        compraPanel.setLayout(new BoxLayout(compraPanel, BoxLayout.Y_AXIS));
        compraPanel.setBackground(new Color(50, 50, 50));

        JPanel compraPropriaPanel = new JPanel();
        compraPropriaPanel.setLayout(new BoxLayout(compraPropriaPanel, FlowLayout.RIGHT));
        compraPropriaPanel.setBackground(new Color(50, 50, 50));
        compraPropriaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        double totalCompra = usuario.getCarrinho().stream().mapToDouble(JogoCarrinho::getPreco).sum();

        JLabel totalLabel = new JLabel("Total: R$ " + String.format("%.2f", totalCompra));
        totalLabel.setForeground(Color.WHITE);
        compraPropriaPanel.add(totalLabel);

        compraPropriaPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add horizontal space
        
        JButton finalizarCompraButton = new JButton("Comprar");
        finalizarCompraButton.setBackground(new Color(75, 175, 75)); // Green button
        finalizarCompraButton.setForeground(Color.WHITE);
        finalizarCompraButton.setMaximumSize(new Dimension(100, 50));

        // TODO: Implementar a lógica de finalização de compra
        // finalizarCompraButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE));
        finalizarCompraButton.addActionListener(e -> finalizarCompra(-1));

        if (totalCompra <= 0) {
            finalizarCompraButton.setEnabled(false); // Desabilita o botão se o total for zero ou negativo
        }
        
        compraPropriaPanel.add(finalizarCompraButton);
        
        compraPanel.add(compraPropriaPanel);

        JPanel compraPresentePanel = new JPanel();
        compraPresentePanel.setLayout(new BoxLayout(compraPresentePanel, FlowLayout.RIGHT));
        compraPresentePanel.setBackground(new Color(50, 50, 50));
        compraPresentePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton presentearButton = new JButton("Comprar como presente");
        presentearButton.setBackground(new Color(75, 175, 75)); // Green button
        presentearButton.setForeground(Color.WHITE);
        presentearButton.setMaximumSize(new Dimension(100, 50));

        // Adicionar dropdown para selecionar amigo
        List<Integer> amigosIds = usuario.getAmigos();
        String[] amigosUsernames = new String[amigosIds.size()+1];
        amigosUsernames[0] = "Selecione um amigo"; // Placeholder for dropdown
        
        if (amigosIds.isEmpty()) {
            amigosUsernames = new String[]{"Nenhum amigo disponível"}; // Caso não haja amigos
            presentearButton.setEnabled(false); // Desabilita o botão se não houver amigos
        }

        for (int i = 1; i < amigosUsernames.length; i++) {
            amigosUsernames[i] = Main.getUsernameFromID(amigosIds.get(i-1)); // Obtém o nome de usuário do amigo
        }

        JComboBox <String> amigosDropdown = new JComboBox<>(amigosUsernames);
        amigosDropdown.setMaximumSize(new Dimension(200, 30)); // Adjust size as needed
        
        presentearButton.addActionListener(e -> {
            String amigoSelecionado = (String) amigosDropdown.getSelectedItem();
            if (amigoSelecionado != null && !amigoSelecionado.equals("Selecione um amigo") && !amigoSelecionado.equals("Nenhum amigo disponível")) {
                // JOptionPane.showMessageDialog(this, "Compra realizada como presente para: " + amigoSelecionado, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                finalizarCompra(Main.getIdUsuario(amigoSelecionado));
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum amigo selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (totalCompra <= 0) {
            presentearButton.setEnabled(false); // Desabilita o botão se o total for zero ou negativo
            amigosDropdown.setEnabled(false);
        }

        compraPresentePanel.add(amigosDropdown);
        compraPresentePanel.add(presentearButton);

        compraPanel.add(compraPresentePanel);

        return compraPanel;
    }

    private void finalizarCompra(int usuarioPresenteadoID) {
        Main.showCompra(usuario, usuarioPresenteadoID);
        dispose();
    }
}
