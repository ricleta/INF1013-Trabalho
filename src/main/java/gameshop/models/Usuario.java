package gameshop.models;

// import gameshop.models.JogoBibliotecaUsuario;
// import gameshop.models.JogoCarrinhoUsuario;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    @JsonProperty("id_usuario")
    private int idUsuario;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @JsonProperty("amigos")
    private List<Integer> amigos;

    @JsonProperty("solicitacoes_pendentes")
    private List<Integer> solicitacoesPendentes;

    @JsonProperty("biblioteca")
    private List<JogoBibliotecaUsuario> biblioteca;

    @JsonProperty("carrinho")
    private List<JogoCarrinhoUsuario> carrinho;


    public Usuario() {
        // Construtor padrão
    }

    // Construtor
    public Usuario(int idUsuario, String username, String email, String senha, LocalDate dataNascimento,
                   List<Integer> amigos, List<Integer> solicitacoesPendentes,
                   List<JogoBibliotecaUsuario> biblioteca, List<JogoCarrinhoUsuario> carrinho) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.amigos = amigos;
        this.solicitacoesPendentes = solicitacoesPendentes;
        this.biblioteca = biblioteca;
        this.carrinho = carrinho;
    }

    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Integer> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Integer> amigos) {
        this.amigos = amigos;
    }

    public List<Integer> getSolicitacoesPendentes() {
        return solicitacoesPendentes;
    }

    public void setSolicitacoesPendentes(List<Integer> solicitacoesPendentes) {
        this.solicitacoesPendentes = solicitacoesPendentes;
    }

    public List<JogoBibliotecaUsuario> getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(List<JogoBibliotecaUsuario> biblioteca) {
        this.biblioteca = biblioteca;
    }

    public List<JogoCarrinhoUsuario> getCarrinho() {
        return carrinho;
    }

    public void addJogoToCarrinho(JogoCarrinhoUsuario jogoCarrinho) {
        if (this.carrinho != null && carrinho.stream().noneMatch(j -> j.getIdJogo() == jogoCarrinho.getIdJogo())) {
            this.carrinho.add(jogoCarrinho);
            return;
        }

        throw new IllegalArgumentException("Jogo já está no carrinho ou carrinho não foi inicializado.");
    }
    
    public void removeJogoFromCarrinho(JogoCarrinhoUsuario jogoCarrinho) {
        if (this.carrinho != null) {
            this.carrinho.remove(jogoCarrinho);
        }
    }

    public void setCarrinho(List<JogoCarrinhoUsuario> carrinho) {
        this.carrinho = carrinho;
    }
}