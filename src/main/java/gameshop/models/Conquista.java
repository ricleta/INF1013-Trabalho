package gameshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Conquista {

    @JsonProperty("id_conquista")
    private int idConquista;

    @JsonProperty("conquista")
    private String conquista;

    @JsonProperty("icone")
    private String icone;

    @JsonProperty("dificuldade")
    private String dificuldade;

    public Conquista() {
        // Construtor padrão necessário para a serialização/deserialização
    }

    public Conquista(int idConquista, String conquista, String icone, String dificuldade) {
        this.idConquista = idConquista;
        this.conquista = conquista;
        this.icone = icone;
        this.dificuldade = dificuldade;
    }

    // Getters
    public int getIdConquista() {
        return idConquista;
    }

    public String getConquista() {
        return conquista;
    }

    public String getIcone() {
        return icone;
    }

    public String getDificuldade() {
        return dificuldade;
    }
}
