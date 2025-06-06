package gameshop.models;

import gameshop.models.enums.EnumOS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DescricaoJogoBiblioteca {

    @JsonProperty("texto")
    private String texto;

    @JsonProperty("icone_path")
    private String iconePath;

    @JsonProperty("sis_ops")
    private EnumOS[] sistemasOperacionais;

    public DescricaoJogoBiblioteca() {
    }

    public DescricaoJogoBiblioteca(String texto, String iconePath, EnumOS[] sistemasOperacionais) {
        this.texto = texto;
        this.iconePath = iconePath;
        this.sistemasOperacionais = sistemasOperacionais;
    }

    // Getters and Setters
    public String getTexto() {
        return texto;
    }

    public String getIconePath() {
        return iconePath;
    }

    public EnumOS[] getSistemasOperacionais() {
        return sistemasOperacionais;
    }
}
