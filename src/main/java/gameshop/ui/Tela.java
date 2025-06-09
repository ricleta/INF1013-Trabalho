package gameshop.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import gameshop.models.JogoBibliotecaUsuario;
import gameshop.models.JogoCarrinhoUsuario;
import gameshop.models.Usuario;

public class Tela extends JFrame{
    private Usuario usuario;

    public Tela(Usuario usuario) {
        this.usuario = usuario;
    }

    protected JPanel setHeaderPanel() {
                JPanel headerPanel = new JPanel(new BorderLayout(10, 10)); // Use BorderLayout for internal arrangement
        headerPanel.setBackground(new Color(30, 30, 30));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Barra de busca no cabeçalho
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(30, 30, 30));
        JTextField searchField = new JTextField("Procurar jogo", 20);
        searchField.setForeground(Color.GRAY);
        searchPanel.add(searchField);
        headerPanel.add(searchPanel);

        // Painel para os botões de menu
        JPanel menuButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        menuButtonsPanel.setBackground(new Color(30, 30, 30));

        JButton btnMeuPerfil = new JButton("Meu Perfil");
        JButton btnBiblioteca = new JButton("Biblioteca");
        JButton btnContato = new JButton("Contato");
        JButton btnSobre = new JButton("Sobre");
        JButton btnPolitica = new JButton("Política");
        JButton btnSuporte = new JButton("Suporte");
        JButton btnOutros = new JButton("Outros");

        menuButtonsPanel.add(btnMeuPerfil);
        menuButtonsPanel.add(btnBiblioteca);
        menuButtonsPanel.add(btnContato);
        menuButtonsPanel.add(btnSobre);
        menuButtonsPanel.add(btnPolitica);
        menuButtonsPanel.add(btnSuporte);
        menuButtonsPanel.add(btnOutros);

        btnBiblioteca.addActionListener(e -> exibirBiblioteca());
        btnMeuPerfil.addActionListener(e -> System.out.println("Você acessou Meu Perfil"));
        btnContato.addActionListener(e -> System.out.println("Você acessou Contato"));
        btnSobre.addActionListener(e -> System.out.println("Você acessou Sobre a Plataforma"));
        btnPolitica.addActionListener(e -> System.out.println("Você acessou Política de Privacidade"));
        btnSuporte.addActionListener(e -> System.out.println("Você acessou Suporte"));
        btnOutros.addActionListener(e -> System.out.println("Você acessou Outros"));

        headerPanel.add(menuButtonsPanel);

        return headerPanel;
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

    protected ImageIcon loadAndScaleImage(String resourcePath, String imageName, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(resourcePath));
        if (imageIcon.getIconWidth() <= 0) {
            // JOptionPane.showMessageDialog(null, "Imagem '" + imageName + "' não encontrada. Usando placeholder.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
        } else {
            Image img = imageIcon.getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            // JOptionPane.showMessageDialog(null, "Imagem '" + imageName + "' carregada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return scaledIcon;
        }
    }
}
