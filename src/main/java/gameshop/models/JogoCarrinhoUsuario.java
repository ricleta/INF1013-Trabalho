package gameshop.models;

public class JogoCarrinhoUsuario {
    private int idJogo;
    private double preco;

    public JogoCarrinhoUsuario(int idJogo, double preco) {
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