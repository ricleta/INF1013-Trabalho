package gameshop.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogoCatalogo {

    @JsonProperty("id_jogo")
    private int idJogo;

    @JsonProperty("preco")
    private double preco;

    @JsonProperty("data_inicio")
    private LocalDate dataInicio;

    @JsonProperty("data_fim")
    private LocalDate dataFim;

    public JogoCatalogo() {
        // Construtor padrão
    }

    public int getIdJogo() {
        return idJogo;
    }

    public double getPreco() {
        return preco;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public boolean isVigente() {
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(dataInicio) && hoje.isBefore(dataFim);
    }
}
