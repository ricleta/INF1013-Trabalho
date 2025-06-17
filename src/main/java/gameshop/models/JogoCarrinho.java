package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogoCarrinho {
    @JsonProperty("id_jogo")
    private int idJogo;

    @JsonProperty("preco")
    private double preco;

    public JogoCarrinho() {
        // Construtor padrão
    }

    public JogoCarrinho(int idJogo, double preco) {
        this.idJogo = idJogo;
        this.preco = preco;
    }

    // Getters e Setters
    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}