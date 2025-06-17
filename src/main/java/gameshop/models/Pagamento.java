package gameshop.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagamento {
    @JsonProperty("identificacao_pagamento")
    private String idPagamento;

    @JsonProperty("total_a_pagar")
    private double totalAPagar;

    @JsonProperty("numero_controle")
    private String numControle;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("metodo")
    private String metodo;

    public Pagamento() {
        // Construtor padrão
    }

    public Pagamento(String idPagamento, double totalAPagar, LocalDateTime timestamp, String metodo, String numControle) {
        this.idPagamento = idPagamento;
        this.totalAPagar = totalAPagar;
        this.timestamp = timestamp;
        this.metodo = metodo;
        this.numControle = numControle;
    }

    public String getIdPagamento() {
        return idPagamento;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMetodo() {
        return metodo;
    }
}
