package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class DescricaoJogoCatalogo {
    private String texto;

    @JsonProperty("iconce_path") // Maps JSON field name to Java field
    private String iconePath;

    private List<String> controles; // From JSON "controles" within descricao_catalogo

    @JsonProperty("fotos_slide") // Maps JSON field name to Java field
    private List<String> fotosSlide;

    @JsonProperty("sis_ops") // Maps JSON field name to Java field
    private List<String> sistemasOperacionais;

    public DescricaoJogoCatalogo() {
        this.controles = new ArrayList<>();
        this.fotosSlide = new ArrayList<>();
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

    public List<String> getControles() {
        return controles;
    }

    public void setControles(List<String> controles) {
        this.controles = controles;
    }

    public List<String> getFotosSlide() {
        return fotosSlide;
    }

    public void setFotosSlide(List<String> fotosSlide) {
        this.fotosSlide = fotosSlide;
    }

    public List<String> getSistemasOperacionais() {
        return sistemasOperacionais;
    }

    public void setSistemasOperacionais(List<String> sistemasOperacionais) {
        this.sistemasOperacionais = sistemasOperacionais;
    }

    @Override
    public String toString() {
        return "DescricaoJogoCatalogo{" +
               "texto='" + texto + '\'' +
               ", iconePath='" + iconePath + '\'' +
               ", controles=" + controles +
               ", fotosSlide=" + fotosSlide +
               ", sistemasOperacionais=" + sistemasOperacionais +
               '}';
    }
}