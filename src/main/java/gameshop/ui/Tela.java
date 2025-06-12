package gameshop.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

import java.util.List;

import gameshop.Main;
import gameshop.models.Jogo;
import gameshop.models.JogoBiblioteca;
import gameshop.models.JogoCarrinho;
import gameshop.models.Usuario;

public class Tela extends JFrame{
    protected Usuario usuario;
    private String tipoTela;

    public Tela(Usuario usuario, String tipoTela) {
        this.usuario = usuario;
        this.tipoTela = tipoTela;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE to close only this window
        setLocationRelativeTo(null); // Center the window        
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


        if (!tipoTela.equals("CATALOGO")) {
            JButton btnLoja = new JButton("Loja");
            menuButtonsPanel.add(btnLoja);
            btnLoja.addActionListener(e -> irParaLoja());
        }

        JButton btnMeuPerfil = new JButton("Meu Perfil");
        menuButtonsPanel.add(btnMeuPerfil);
        btnMeuPerfil.addActionListener(e -> System.out.println("Você acessou Meu Perfil"));

        JButton btnBiblioteca = new JButton("Biblioteca");
        menuButtonsPanel.add(btnBiblioteca);
        btnBiblioteca.addActionListener(e -> exibirBiblioteca());
        
        JButton btnContato = new JButton("Contato");
        menuButtonsPanel.add(btnContato);
        btnContato.addActionListener(e -> System.out.println("Você acessou Contato"));

        JButton btnSobre = new JButton("Sobre");
        menuButtonsPanel.add(btnSobre);
        btnSobre.addActionListener(e -> System.out.println("Você acessou Sobre a Plataforma"));
        
        JButton btnPolitica = new JButton("Política");
        menuButtonsPanel.add(btnPolitica);
        btnPolitica.addActionListener(e -> System.out.println("Você acessou Política de Privacidade"));

        JButton btnSuporte = new JButton("Suporte");
        menuButtonsPanel.add(btnSuporte);        
        btnSuporte.addActionListener(e -> System.out.println("Você acessou Suporte"));

        JButton btnOutros = new JButton("Outros");
        menuButtonsPanel.add(btnOutros);
        btnOutros.addActionListener(e -> System.out.println("Você acessou Outros"));

        if (!tipoTela.equals("CARRINHO"))
        {
            JButton btnCarrinho = new JButton(loadAndScaleImage("/icons/shopping_cart.png", 24, 24));
            menuButtonsPanel.add(btnCarrinho);
            btnCarrinho.addActionListener(e -> exibirCarrinho());
        }
        
        headerPanel.add(menuButtonsPanel);

        return headerPanel;
    }

    private void exibirBiblioteca() {
        StringBuilder mensagem = new StringBuilder("Biblioteca de " + usuario.getUsername() + ":\n");
        for (JogoBiblioteca jogo : usuario.getBiblioteca()) {
            mensagem.append(String.format("Jogo ID: %d, Horas: %.1f, Última jogada: %s, Favorito: %b\n",
                    jogo.getIdJogo(), jogo.getHorasJogadas(), jogo.getUltimaDataJogado(), jogo.isFavorito()));
        }
        JOptionPane.showMessageDialog(this, mensagem.toString(), "Minha Biblioteca", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirCarrinho() {
        Main.showCarrinho(usuario);
        this.dispose();
    }

    protected void irParaLoja() {
        Main.showCatalogo(usuario);
        this.dispose();
    }

    protected ImageIcon loadAndScaleImage(String resourcePath, int width, int height) {
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
