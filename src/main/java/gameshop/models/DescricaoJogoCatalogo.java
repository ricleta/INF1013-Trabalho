package gameshop.models;

import gameshop.models.enums.EnumOS;
import gameshop.models.enums.EnumControle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DescricaoJogoCatalogo {
    @JsonProperty("texto")
    private String texto;

    @JsonProperty("icone_path")
    private String iconePath;

    @JsonProperty("sis_ops")
    private EnumOS[] sistemasOperacionais;

    @JsonProperty("controles")
    private EnumControle[] controles;

    @JsonProperty("fotos_slide")
    private String[] fotosSlide;

    public DescricaoJogoCatalogo() {
        // Construtor padrão necessário para a serialização/deserialização
    }

    public DescricaoJogoCatalogo(String texto, String iconePath, EnumOS[] sistemasOperacionais, EnumControle[] controles, String[] fotosSlide) {
        this.texto = texto;
        this.iconePath = iconePath;
        this.sistemasOperacionais = sistemasOperacionais;
        this.controles = controles;
        this.fotosSlide = fotosSlide;
    }

    // Getters
    public String getTexto() {
        return texto;
    }

    public String getIconePath() {
        return iconePath;
    }

    public EnumOS[] getSistemasOperacionais() {
        return sistemasOperacionais;
    }

    public EnumControle[] getControles() {
        return controles;
    }

    public String[] getFotosSlide() {
        return fotosSlide;
    }
}
