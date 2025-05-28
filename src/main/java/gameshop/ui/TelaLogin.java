package gameshop.ui;

import gameshop.models.JSONReader;
import gameshop.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;

    public TelaLogin() {
        // Configurações da janela
        setTitle("GameShop - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("GameShop - Login", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblTitulo, gbc);

        // Campo de email
        JLabel lblEmail = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(lblEmail, gbc);

        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(txtEmail, gbc);

        // Campo de senha
        JLabel lblSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lblSenha, gbc);

        txtSenha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(txtSenha, gbc);

        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnEntrar = new JButton("Entrar");
        JButton btnRegistrar = new JButton("Registrar");

        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnRegistrar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Ações dos botões
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaRegistro();
            }
        });

        setVisible(true);
    }

    private void realizarLogin() {
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Carrega lista de usuários do JSON
        // models.JSONReader jsonReader = new JSONReader();
        // List<Usuario> usuarios = jsonReader.readJSON(); // Assume que JSONReader tem esse método
        // Usuario usuarioAutenticado = null;
        
        // Autenticação simples (substituir por hash no futuro)
        /*
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                usuarioAutenticado = u;
                break;
            }
        }

        if (usuarioAutenticado != null) {
            dispose();
            new TelaPrincipal(usuarioAutenticado);
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        */
    }

    private void abrirTelaRegistro() {
        JOptionPane.showMessageDialog(this, "Funcionalidade de registro em desenvolvimento!");
        // Futuro: new TelaRegistro();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin();
            }
        });
    }
}