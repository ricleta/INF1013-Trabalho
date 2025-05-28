package gameshop.models;

import java.time.LocalDate;
import java.util.List;

public class JogoBibliotecaUsuario {
    private int idJogo;
    private double horasJogadas;
    private LocalDate ultimaDataJogado;
    private boolean favorito;
    private List<Integer> idConquistas;

    public JogoBibliotecaUsuario(int idJogo, double horasJogadas, LocalDate ultimaDataJogado,
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