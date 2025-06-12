package gameshop.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogoBiblioteca {
    @JsonProperty("id_jogo")
    private int idJogo;

    @JsonProperty("horas_jogadas")
    private double horasJogadas;

    @JsonProperty("ultima_data_jogado")
    private LocalDate ultimaDataJogado;

    @JsonProperty("favorito")
    private boolean favorito;

    @JsonProperty("id_conquistas")
    private List<Integer> idConquistas;

    public JogoBiblioteca() {
        // Construtor padrão
    }

    public JogoBiblioteca(int idJogo, double horasJogadas, LocalDate ultimaDataJogado,
                          boolean favorito, List<Integer> idConquistas) {
        this.idJogo = idJogo;
        this.horasJogadas = horasJogadas;
        this.ultimaDataJogado = ultimaDataJogado;
        this.favorito = favorito;
        this.idConquistas = idConquistas;
    }

    // Getters e Setters
    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public double getHorasJogadas() {
        return horasJogadas;
    }

    public void setHorasJogadas(double horasJogadas) {
        this.horasJogadas = horasJogadas;
    }

    public LocalDate getUltimaDataJogado() {
        return ultimaDataJogado;
    }

    public void setUltimaDataJogado(LocalDate ultimaDataJogado) {
        this.ultimaDataJogado = ultimaDataJogado;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public List<Integer> getIdConquistas() {
        return idConquistas;
    }

    public void setIdConquistas(List<Integer> idConquistas) {
        this.idConquistas = idConquistas;
    }
}