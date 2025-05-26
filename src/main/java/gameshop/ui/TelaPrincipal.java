package gameshop.ui;

import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        // Configurações da janela
        setTitle("GameShop - Tela Principal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adiciona um rótulo de boas-vindas
        JLabel welcomeLabel = new JLabel("Bem-vindo à GameShop!", SwingConstants.CENTER);
        add(welcomeLabel);

        pack();
    }
}
