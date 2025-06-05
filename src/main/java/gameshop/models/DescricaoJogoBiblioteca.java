package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class DescricaoJogoBiblioteca {
    private String texto;

    @JsonProperty("iconce_path") // Maps JSON field name to Java field
    private String iconePath;

    @JsonProperty("sis_ops") // Maps JSON field name to Java field
    private List<String> sistemasOperacionais;

    public DescricaoJogoBiblioteca() {
        this.sistemasOperacionais = new ArrayList<>();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getIconePath() {
        return iconePath;
    }

    public void setIconePath(String iconePath) {
        this.iconePath = iconePath;
    }

    public List<String> getSistemasOperacionais() {
        return sistemasOperacionais;
    }

    public void setSistemasOperacionais(List<String> sistemasOperacionais) {
        this.sistemasOperacionais = sistemasOperacionais;
    }

    @Override
    public String toString() {
        return "DescricaoJogoBiblioteca{" +
               "texto='" + texto + '\'' +
               ", iconePath='" + iconePath + '\'' +
               ", sistemasOperacionais=" + sistemasOperacionais +
               '}';
    }
}