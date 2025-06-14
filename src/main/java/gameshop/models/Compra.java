package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Compra {
    @JsonProperty("id_usuario")
    private int idUsuario;

    @JsonProperty("id_presenteado")
    private int idPresenteado;

    @JsonProperty("carrinho")
    List<JogoCarrinho> carrinho;

    @JsonProperty("pagamento")
    private Pagamento pagamento;

    public Compra() {
        // Construtor padrão
    }

    public Compra(int idUsuario, int idPresenteado, List<JogoCarrinho> carrinho, Pagamento pagamento) {
        this.idUsuario = idUsuario;
        this.idPresenteado = idPresenteado;
        this.carrinho = carrinho;
        this.pagamento = pagamento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdPresenteado() {
        return idPresenteado;
    }

    public List<JogoCarrinho> getCarrinho() {
        return carrinho;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
}
